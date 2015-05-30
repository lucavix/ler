package biz.opengate.lejos.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

import biz.opengate.lejos.commands.Backward;
import biz.opengate.lejos.commands.Fire;
import biz.opengate.lejos.commands.Forward;
import biz.opengate.lejos.commands.FunctionA;
import biz.opengate.lejos.commands.Left;
import biz.opengate.lejos.commands.MyCommand;
import biz.opengate.lejos.commands.Right;

public class Home extends JFrame {
	

	private static final long serialVersionUID = 1L;
	
	GridLayout mainLayout = new GridLayout(0,4);
	JPanel mainPanel;
	JPanel toolboxPanel;
	JPanel programPanel;
	JPanel functionPanel;
	JPanel controlPanel;
	
	MyCommandListModel toolboxListModel;
	JList <MyCommand> toolboxList;
	
	MyCommandListModel programListModel;
	JList <MyCommand> programList;
	
	MyCommandListModel functionListModel;
	JList <MyCommand> functionList;

	JButton startButton;
	JButton stopButton;
	JButton trashButton;
	MyCommandTransferHandler myCommandTransferHandler;

	LejosController lejosController;
	
	
	ListCellRenderer<MyCommand> simpleCellRenderer = new ListCellRenderer<MyCommand>() {
	     private final JPanel p = new JPanel(new BorderLayout());
	     private final JLabel icon = new JLabel("", JLabel.CENTER);
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends MyCommand> list, MyCommand value, int index,
				boolean isSelected, boolean cellHasFocus) {
			icon.setIcon(new ImageIcon("img/" + value.getImage()));
			if (isSelected) {
				p.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
			} else  {
				p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
			}
		    p.add(icon,BorderLayout.CENTER);
		    return p;
		}
	};
	
	ListCellRenderer<MyCommand> simpleCellRendererWithLabel = new ListCellRenderer<MyCommand>() {
	      private final JPanel p = new JPanel(new BorderLayout());
	      private final JLabel icon = new JLabel("", JLabel.CENTER);
	      private final JLabel label = new JLabel("", JLabel.CENTER);
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends MyCommand> list, MyCommand value, int index,
				boolean isSelected, boolean cellHasFocus) {
			icon.setIcon(new ImageIcon("img/" + value.getImage()));
			label.setText(value.getDescription());
		    label.setForeground(isSelected? list.getSelectionForeground():list.getForeground());
		    p.add(icon,BorderLayout.CENTER);
		    p.add(label, BorderLayout.SOUTH);
		    p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			return p;
		}
	};

	public MyCommandTransferHandler getMyCommandTransferHandler() {
		if (myCommandTransferHandler==null) {
			myCommandTransferHandler = new MyCommandTransferHandler(this); 
		} 
		return myCommandTransferHandler;
	}
	
	public MyCommandListModel getToolboxListModel() {
		if (toolboxListModel == null) {
			toolboxListModel  = new MyCommandListModel();
			toolboxListModel.add(new Forward());
			toolboxListModel.add(new Backward());
			toolboxListModel.add(new Left());
			toolboxListModel.add(new Right());
			toolboxListModel.add(new FunctionA());
			toolboxListModel.add(new Fire());
		}
		return toolboxListModel;
	}

	
	public MyCommandListModel getProgramListModel() {
		if (programListModel == null) {
			programListModel  = new MyCommandListModel();
		}
		return programListModel;
	}

	public MyCommandListModel getFunctionListModel() {
		if (functionListModel == null) {
			functionListModel  = new MyCommandListModel();
		}
		return functionListModel;
	}

	public JList<MyCommand> getToolboxList() {
		if (toolboxList==null) {
			toolboxList = new JList<MyCommand>(getToolboxListModel());
			toolboxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			toolboxList.setDragEnabled(true);	
			toolboxList.setTransferHandler(getMyCommandTransferHandler());
			toolboxList.setDropMode(DropMode.INSERT);
			toolboxList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			toolboxList.setLayoutOrientation(JList.VERTICAL_WRAP);
			toolboxList.setCellRenderer(simpleCellRenderer);
			
		}
		return toolboxList;
	}
	
	public JList<MyCommand> getProgramList() {
		if (programList==null) {
			programList = new JList<MyCommand>(getProgramListModel());
			programList.setVisibleRowCount(6);
			
			programList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			programList.setDragEnabled(true);
			programList.setTransferHandler(getMyCommandTransferHandler());
			programList.setDropMode(DropMode.INSERT);
			programList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			programList.setLayoutOrientation(JList.VERTICAL_WRAP);
			programList.setCellRenderer(simpleCellRenderer);
			
		}
		return programList;
	}

	public JList<MyCommand> getFunctionList() {
		if (functionList==null) {
			functionList = new JList<MyCommand>(getFunctionListModel());
			functionList.setVisibleRowCount(6);
			functionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			functionList.setDragEnabled(true);
			functionList.setTransferHandler(getMyCommandTransferHandler());
			functionList.setDropMode(DropMode.INSERT);
			functionList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			functionList.setLayoutOrientation(JList.VERTICAL_WRAP);
			functionList.setCellRenderer(simpleCellRenderer);
			
		}
		return functionList;
	}

	public JPanel getToolboxPanel() {
		if (toolboxPanel==null) {
			toolboxPanel = new JPanel();
			toolboxPanel.setLayout(new BorderLayout());
			toolboxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			toolboxPanel.add(new JLabel(new ImageIcon("img/toolkit.png"),JLabel.CENTER),BorderLayout.NORTH);
			toolboxPanel.add(getToolboxList(),BorderLayout.CENTER);
		}
		return toolboxPanel;
	}
	
	
	public JPanel getProgramPanel() {
		if (programPanel==null) {
			programPanel = new JPanel();
			programPanel.setLayout(new BorderLayout());
			programPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			programPanel.add(new JLabel(new ImageIcon("img/program.png"),JLabel.CENTER),BorderLayout.NORTH);
			programPanel.add(getProgramList(),BorderLayout.CENTER);
		}		
		return programPanel;
	}
	public JPanel getFunctionPanel() {
		if (functionPanel==null) {
			functionPanel = new JPanel();
			functionPanel.setLayout(new BorderLayout());
			functionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			functionPanel.add(new JLabel(new ImageIcon("img/function.png"),JLabel.CENTER),BorderLayout.NORTH);
			functionPanel.add(getFunctionList(),BorderLayout.CENTER);
		}		
		return functionPanel;
	}
	

	
	public JButton getStartButton() {
		if (startButton==null) {
			startButton=new JButton(new ImageIcon("img/start.png"));
			startButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					startExecution();
					super.mouseClicked(e);
				}
			});
		}
		return startButton;
	}
	

	public void startExecution() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				LejosController.getInstance().execute(Home.this);
				return null;
			}
		};
		worker.execute();
	}
	
	public JButton getStopButton() {
		if (stopButton==null) {
			stopButton=new JButton(new ImageIcon("img/stop.png"));
			stopButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("Stop!");
					LejosController.getInstance().stopRunning();
					super.mouseClicked(e);
				}
			});
		}
		return stopButton;
	}
	
	public JButton getTrashButton() {
		if (trashButton==null) {
			trashButton=new JButton(new ImageIcon("img/trash.png"));
			trashButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					LejosController.getInstance().stopRunning();
					clearAll();
					super.mouseClicked(e);
				}
			});
		}
		return trashButton;
	}

	public void clearAll() {
		System.out.println("Cleaning all");
		((MyCommandListModel)getProgramList().getModel()).removeAll();
		((MyCommandListModel)getFunctionList().getModel()).removeAll();
//		this.update(this.getGraphics());
	}
	
	public JPanel getControlPanel() {
		if (controlPanel==null) {
			System.out.println("Creating Control Panel");
			controlPanel = new JPanel();
			controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			controlPanel.add(getStartButton());
			controlPanel.add(getStopButton());
			controlPanel.add(getTrashButton());
		}		
		return controlPanel;
	}
	public JPanel getMainPanel() {
		if (mainPanel==null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(mainLayout);
			
			mainPanel.add(getToolboxPanel());
			mainPanel.add(getProgramPanel());
			mainPanel.add(getFunctionPanel());
			mainPanel.add(getControlPanel());
			System.out.println("Finish Initialization");
		}
		return mainPanel;
	}
	
	
	
	public void prepareUI() {
		getContentPane().add(getMainPanel(), BorderLayout.CENTER);
		 
	}
}
