package biz.opengate.lejos.ui;

import javax.swing.JComponent;

import biz.opengate.lejos.commands.MyCommand;

public class MyCommandTransferableData {
	private JComponent source;
	private JComponent dest;
	private MyCommand data;
	private int sourceIndex;
	private int destIndex;
	
	public JComponent getSource() {
		return source;
	}
	public void setSource(JComponent source) {
		this.source = source;
	}
	public JComponent getDest() {
		return dest;
	}
	public void setDest(JComponent dest) {
		this.dest = dest;
	}
	public MyCommand getData() {
		return data;
	}
	public void setData(MyCommand data) {
		this.data = data;
	}
	public int getSourceIndex() {
		return sourceIndex;
	}
	public void setSourceIndex(int sourceIndex) {
		this.sourceIndex = sourceIndex;
	}
	public int getDestIndex() {
		return destIndex;
	}
	public void setDestIndex(int destIndex) {
		this.destIndex = destIndex;
	}
	
	
}
