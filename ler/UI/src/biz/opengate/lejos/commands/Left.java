package biz.opengate.lejos.commands;

import biz.opengate.lejos.ui.LejosController;

public class Left extends MyCommand {
	public Left() {
		super("left.png","Turn Left");
	}

	@Override
	public void execute(LejosController lejosController) {
		try {			
			lejosController.getMA().rotate(-STEER_ANGLE, true);
			lejosController.getMB().rotate(+STEER_ANGLE, true);
			lejosController.getMB().waitComplete();
			lejosController.getMA().waitComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
