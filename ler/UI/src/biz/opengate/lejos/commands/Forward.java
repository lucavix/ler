package biz.opengate.lejos.commands;

import lejos.utility.Delay;
import biz.opengate.lejos.ui.LejosController;

public class Forward extends MyCommand{
	public Forward() {
		super("up.png","Forward");
	}

	@Override
	public void execute(LejosController lejosController) {
		try {			
			lejosController.getMA().rotate(MOVE_ANGLE, true);
			lejosController.getMB().rotate(MOVE_ANGLE, true);
			lejosController.getMB().waitComplete();
			lejosController.getMA().waitComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
