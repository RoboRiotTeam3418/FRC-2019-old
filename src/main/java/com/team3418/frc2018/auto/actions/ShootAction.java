package com.team3418.frc2018.auto.actions;

import com.team3418.frc2018.subsystems.MrHush;
//import com.team3418.frc2018.subsystems.Shooter;

public class ShootAction implements Action{

	private MrHush mMrHush;
	
	private boolean finished = false;
	
	public ShootAction() {
		mMrHush = MrHush.getInstance();
	}
	
	@Override
	public void start() {
		mMrHush.Retract();
		finished = true;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean isFinished() {
		if (finished) {
			System.out.println("MrHushy Shoot Action Completed");
			return true;
		}
		return false;
	}

	@Override
	public void done() {
		
	}

}
