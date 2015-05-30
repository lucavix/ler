package biz.opengate.lejos.ui;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import biz.opengate.lejos.commands.Backward;
import biz.opengate.lejos.commands.Forward;
import biz.opengate.lejos.commands.FunctionA;
import biz.opengate.lejos.commands.Left;
import biz.opengate.lejos.commands.MyCommand;
import lejos.hardware.BrickFinder;
import lejos.hardware.BrickInfo;
import lejos.hardware.ev3.EV3;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;

public class LejosController {
	
	private static String NAME = "EV3";
	
	private static LejosController instance;
	
	private LejosController() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		    	System.out.println("This sounds like a destructor :-D ");
		    	try {
			        if (ma!=null) ma.close();
			        if (mb!=null) mb.close();
			        if (mc!=null) mc.close();
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	
		    }
		});
		getMA();
		getMB();
		getMC();
		
	}
	
	public static synchronized LejosController getInstance() {
		if (instance==null) {
			instance = new LejosController();
		}
		return instance;
	}
	
	BrickInfo[] devices;
	RemoteEV3 ev3;
	
	RMIRegulatedMotor ma;
	RMIRegulatedMotor mb;
	RMIRegulatedMotor mc;
	
	public RemoteEV3 getEV3() {
		if (ev3==null) {
			try {
				for (BrickInfo bi:getDevices()) {
					if(bi.getName().equals(NAME)) {
						ev3 = new RemoteEV3(bi.getIPAddress());					
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ev3;
	}
	
	public RMIRegulatedMotor getMA() {
		if (ma==null) {
			ma = getEV3().createRegulatedMotor("A", 'L');
			try {
				ma.setSpeed((int)ma.getMaxSpeed());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return ma;
	}
	
	public RMIRegulatedMotor getMB() {
		if (mb==null) {
			mb = getEV3().createRegulatedMotor("B", 'L');
			try {
				mb.setSpeed((int)mb.getMaxSpeed());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return mb;
	}

	public RMIRegulatedMotor getMC() {
		if (mc==null) {
			mc = getEV3().createRegulatedMotor("C", 'M');
			try {
				mc.setSpeed((int)mb.getMaxSpeed());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return mc;
	}

	public BrickInfo[] getDevices() {
		if (devices==null) {
			try {
				devices = BrickFinder.discover();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return devices;
	}
	
	private boolean stopRunning;
	private boolean running;
	
	
	public void execute(Home home) {
		if (running) return;
		running = true;
		stopRunning = false;
		executeList(home.getProgramList(),home);
		home.getProgramList().setSelectedIndices(new int[]{});
		home.getFunctionList().setSelectedIndices(new int[]{});
		running = false;
	}
	
	public void executeList(JList<MyCommand> list,Home home ) {
		for(int i=0;i<list.getModel().getSize();i++){
			if (stopRunning) return;
			MyCommand mc = list.getModel().getElementAt(i);
			if (mc instanceof FunctionA) {
				list.setSelectedIndices(new int[]{i});
				executeList(home.getFunctionList(),home);
			} else {
				System.out.println("Executing Step " + i);
				list.setSelectedIndices(new int[]{i});
//				home.update(home.getGraphics());
				mc.execute(this);
			}
		}
		list.setSelectedIndices(new int[]{});
	}

	
	public void stopRunning() {
		if (!running) return;
		stopRunning = true;
		running = false;
		
	}
}
