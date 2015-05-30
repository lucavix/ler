package biz.opengate.lejos.commands;

import java.rmi.RemoteException;

import biz.opengate.lejos.ui.LejosController;

public class Fire extends MyCommand {

	public Fire(){
		super("fire.png","Fire");
	}
	@Override
	public void execute(LejosController lejosController) {
		try {
			lejosController.getMC().rotate(1000);
			lejosController.getMC().waitComplete();
		} catch (RemoteException e) {
			e.printStackTrace();
		};
		
		
	}

}
