package com.team3418.frc2018;
import com.team3418.frc2018.auto.AutoExecuter;
import com.team3418.frc2018.subsystems.Drivetrain;
import com.team3418.frc2018.subsystems.Elevator;
import com.team3418.frc2018.subsystems.Intake;
import com.team3418.frc2018.subsystems.MrHush;
import com.team3418.frc2018.Setup;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
	//Initialize main parts of the robot
	//Setup mHardwareMap;
	Setup mSetup;
	SmartDashboardInteractions mSmartDashboardInteractions;
	
	
	//initialize subsystems
	Drivetrain mDrivetrain;
	Intake mIntake;
	MrHush mMrHush;
	Elevator mElevator;

	AutoExecuter mAutoExecuter = null;
	
	public void updateAllSubsystems(){
		
		mDrivetrain.updateSubsystem();
		mIntake.updateSubsystem();
		mMrHush.updateSubsystem();
	    mElevator.updateSubsystem();

	}
	
	public void stopAllSubsystems(){
		
		
		mDrivetrain.stop();
		mDrivetrain.lowGear();
		mIntake.stop();
		mMrHush.stop();
		mElevator.stop();
	}
	
	@Override
	public void robotInit() {
		
		mSetup = Setup.getInstance();
		mSetup = Setup.getInstance();
		mSmartDashboardInteractions = SmartDashboardInteractions.getInstance();
//		mMinionVision = MinionVision.getInstance();
//		mMinionVision.startVision();
		mDrivetrain = Drivetrain.getInstance();
		mIntake = Intake.getInstance();
		mMrHush = MrHush.getInstance();
		
		mSmartDashboardInteractions.initWithDefaults();
		
		stopAllSubsystems();
	}
	
	@Override
	public void autonomousInit() {
		if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
//        
        mAutoExecuter = new AutoExecuter();
        mAutoExecuter.setAutoRoutine(mSmartDashboardInteractions.getSelectedAutonMode(0));
        mAutoExecuter.start();
                
		stopAllSubsystems();
		updateAllSubsystems();
		
	}
	
	@Override
	public void autonomousPeriodic() {
		updateAllSubsystems();
	}

	@Override
	public void disabledInit(){
		
		if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
        
		//mMinionVision.startVision();
        
        //mMinionVision.stopVision();
        System.out.println("resetting encoders");
        mDrivetrain.resetEncoders();
        mDrivetrain.highGear();
        
		
		stopAllSubsystems();
		updateAllSubsystems();
	}
	
	@Override
	public void disabledPeriodic() {
		
	}
	
	@Override
	public void teleopInit(){
		
		if (mAutoExecuter != null) {
            mAutoExecuter.stop();
        }
        mAutoExecuter = null;
       
		stopAllSubsystems();
		mDrivetrain.lowGear();
		updateAllSubsystems();
	}
	
	@Override
	public void teleopPeriodic() {
		
		//Intake Left
		if(mSetup.getSecondaryLeftIntakeButton()) {
			mIntake.intake();
		} else if(mSetup.getSecondaryLeftOutakeButton()){ 
			mIntake.reverse();
		}
		else {
			mIntake.stopIntakeMotor();
		}
		
		//Intake Right
		if(mSetup.getSecondaryRightIntakeButton()) {
			mIntake.intake();
		} else if(mSetup.getSecondaryRightOutakeButton()){ 
			mIntake.reverse();
		}
		else {
			mIntake.stopIntakeMotor();
		}
		
		//Drive train 
		if(mSetup.getDriverHighGearButton()) {
			mDrivetrain.highGear();
		}
		if(mSetup.getDriverLowGearButton()) {
			mDrivetrain.lowGear();
		}
		mDrivetrain.setTankDriveSpeed(mSetup.getDriverLeftY(), mSetup.getDriverRightY());

		//Elevator Analog
		mElevator.setElevatorSpeed(mSetup.getSecondaryElevatorAnalog(), mSetup.getSecondaryElevatorAnalog());

		updateAllSubsystems();
	}
	
	@Override
	public void testInit( ){
		stopAllSubsystems();
		updateAllSubsystems();
	}
	
	@Override
	public void testPeriodic() {
		
	}
}

