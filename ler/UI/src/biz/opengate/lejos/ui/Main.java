package biz.opengate.lejos.ui;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		Home home = new Home();
		
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		home.prepareUI();
		home.pack();
		
		
//		home.setAlwaysOnTop(true);
//		home.setUndecorated(true);
//		home.setResizable(false);
		home.setVisible(true);

		home.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		home.setSize(tk.getScreenSize());
	}
}
