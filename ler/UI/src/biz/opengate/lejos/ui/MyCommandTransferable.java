package biz.opengate.lejos.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import biz.opengate.lejos.commands.MyCommand;

public class MyCommandTransferable implements Transferable {
	 public static final DataFlavor MY_COMMAND_DATA_FLAVOR = new DataFlavor(MyCommand.class, "java/MyCommand");
	 
	 private MyCommandTransferableData myCommandTransferableData;
	 
	 
	 
	 public MyCommandTransferable(MyCommandTransferableData myCommandTransferableData) {
		super();
		this.myCommandTransferableData = myCommandTransferableData;
	}

	@Override
     public DataFlavor[] getTransferDataFlavors() {
         return new DataFlavor[]{MY_COMMAND_DATA_FLAVOR};
     }

     @Override
     public boolean isDataFlavorSupported(DataFlavor flavor) {
         return flavor.equals(MY_COMMAND_DATA_FLAVOR);
     }

     @Override
     public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
         return myCommandTransferableData;

     }
     
}
