package biz.opengate.lejos.ui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import biz.opengate.lejos.commands.MyCommand;

public class MyCommandListModel implements ListModel<MyCommand>{
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private List<MyCommand> data = new LinkedList<MyCommand>();
	private List<ListDataListener> dataListener = new LinkedList<ListDataListener>(); 
	

	@Override
	public void addListDataListener(ListDataListener l) {
		dataListener.add(l);		
	}

	@Override
	public MyCommand getElementAt(int index) {
		return data.get(index);
	}

	@Override
	public int getSize() {
		return data.size();
	}
	
	public List<MyCommand> getData() {
		return data;
	}
	
	public void add(MyCommand myCommand) {		
		data.add(myCommand);		
		ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, data.size(), data.size()); 
		for(ListDataListener l:dataListener) {
			l.intervalAdded(e);
		}
	}
	
	public void insert(MyCommand myCommand,int index) {
		System.out.println("Inserting at index " + index + " size is " + data.size());
		data.add(index, myCommand);
		
		ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, index); 
		for(ListDataListener l:dataListener) {
			l.intervalAdded(e);
		}
		dump();
	}


	public void remove(int index) {
		System.out.println("Removing index " + index + " size is " + data.size());
		data.remove(index);
		ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index, index); 
		for(ListDataListener l:dataListener) {
			l.intervalAdded(e);
		}
		dump();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		dataListener.remove(l);		
	}
	
	private void dump(){
		for(MyCommand c:data) {
			System.out.println(c.getDescription());
		}
	}
	
	
	public void removeAll(){
		data.clear();
		ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, 0, data.size()); 
		for(ListDataListener l:dataListener) {
			l.intervalAdded(e);
		}
		dump();		
	}
}
