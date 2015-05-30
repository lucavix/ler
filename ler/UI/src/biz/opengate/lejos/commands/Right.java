package biz.opengate.lejos.commands;

import biz.opengate.lejos.ui.LejosController;

public class Right extends MyCommand {
	public Right() {
		super("right.png","Turn Right");
	}

	@Override
	public void execute(LejosController lejosController) {
		try {			
			lejosController.getMA().rotate(+500, true);
			lejosController.getMB().rotate(-500, true);
			lejosController.getMB().waitComplete();
			lejosController.getMA().waitComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
