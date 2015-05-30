package biz.opengate.lejos.ui;


import java.awt.Component;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.TransferHandler;

import biz.opengate.lejos.commands.MyCommand;

public class MyCommandTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;
	private Home home;
	
	
	
	public MyCommandTransferHandler(Home home) {
		super();
		this.home = home;
	}

	public int getSourceActions(JComponent c) {
		if (c.equals(home.getToolboxList())) {
			return COPY;
		}
		return MOVE;
	}

	public Transferable createTransferable(JComponent c) {
		if (c instanceof JList) {
			JList<?> l = ((JList<?>)c);
			Object o = l.getSelectedValue();
			int index = l.getSelectedIndex();
			System.out.println("index: " + index);
			if (o instanceof MyCommand) {				
				System.out.println("-----------------");
				MyCommandTransferableData td = new MyCommandTransferableData();
				td.setSource(c);
				td.setSourceIndex(index);
				td.setData((MyCommand)o);
				return new MyCommandTransferable(td);
			}
		}
		return null;	    
	}
	
	
	
	public void exportDone(JComponent c, Transferable t, int action) {
		try {
    		Object o = t.getTransferData(MyCommandTransferable.MY_COMMAND_DATA_FLAVOR);
    	    if (action == MOVE) {	    	
		    	if (c instanceof JList) {
		    		ListModel<?> m = ((JList<?>)c).getModel();
		    		if (m instanceof MyCommandListModel) {
		    			if (o instanceof MyCommandTransferableData) {
		    				MyCommandTransferableData td = (MyCommandTransferableData)o;		    				
		    				((MyCommandListModel)m).remove( td.getSourceIndex() );
		    			}
		    		}
		    	}
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    		return;
    	}
	}
	

	@Override
	public boolean canImport(TransferSupport support) {
		boolean canImport = (support.getComponent() instanceof javax.swing.JList<?>);
		canImport = canImport && support.isDataFlavorSupported(MyCommandTransferable.MY_COMMAND_DATA_FLAVOR);
		return canImport;
	}
	
	
	@Override
	public boolean importData(TransferSupport support) {
		Component comp = support.getComponent();
		Transferable t = support.getTransferable();
		
    	if (comp instanceof JList) {
    		JList<?> l = ((JList<?>)comp);
    		ListModel<?> m = l.getModel();
    		if (l.equals(home.getProgramList()) || l.equals(home.getFunctionList())) {    			
        		if (m instanceof MyCommandListModel) {
        			try {
	        			Object o = t.getTransferData(MyCommandTransferable.MY_COMMAND_DATA_FLAVOR);
	        			
	        			if (o instanceof MyCommandTransferableData) {
	        				MyCommandTransferableData td = (MyCommandTransferableData)o;
	        				JList.DropLocation d = (JList.DropLocation)support.getDropLocation();	
	        				if ((d.getIndex()<=td.getSourceIndex()) && td.getSource().equals(comp))  {	        				
	        					td.setSourceIndex(td.getSourceIndex()+1);
	        				}	        				
	        				((MyCommandListModel)m).insert(td.getData(),d.getIndex());
	        				return true;
	        			}
        			} catch (Exception e) {
        				return false;
        			}
        			
        		}    			
    		}
    		if (l.equals(home.getToolboxList())) {
    			return true;
    		}
    	}
    	return false;
	}
}
