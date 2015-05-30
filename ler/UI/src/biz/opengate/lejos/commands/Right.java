package biz.opengate.lejos.commands;

import biz.opengate.lejos.ui.LejosController;

public class Right extends MyCommand {
	public Right() {
		super("right.png","Turn Right");
	}

	@Override
	public void execute(LejosController lejosController) {
		try {			
			lejosController.getMA().rotate(+STEER_ANGLE, true);
			lejosController.getMB().rotate(-STEER_ANGLE, true);
			lejosController.getMB().waitComplete();
			lejosController.getMA().waitComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
