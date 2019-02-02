package com.team3418.frc2018;
import com.team3418.frc2018.subsystems.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class Setup 
{
private static Setup mInstance = new Setup();

    public static Setup getInstance() {
        return mInstance;
    }
    
    //Create Joystick Object
    Joystick mDriverStick;
    Joystick mSecondaryDriverStick;
    Joystick mSwitchboard;
    
    //Initialize Joystick Object
    void ControlBoard() {
    	mDriverStick = new Joystick(0);
    	mSecondaryDriverStick = new Joystick(1);
    	mSwitchboard = new Joystick(2);
    }
    
    //DRIVER CONTROLLER
    
    //drive controls
    public double getDriverLeftX(){
    	return mDriverStick.getRawAxis(0);
    }
    
    public double getDriverLeftY(){
    	return -mDriverStick.getRawAxis(1);
    }
    
    public double getDriverRightX(){
    	return mDriverStick.getRawAxis(4);
    }
    
    public double getDriverRightY(){
    	return -mDriverStick.getRawAxis(5);
    }
    
    public int getDriverPov(){
    	return mDriverStick.getPOV(0);
    }
    
    //driver functional controls
    public boolean getDriverHighGearButton(){
    	return mDriverStick.getRawButton(6);
    }
    
    public boolean getDriverLowGearButton(){
    	return mDriverStick.getRawButton(5);
    }
    
    public boolean getDriverRightTrigger() {
    	return mDriverStick.getRawAxis(3) > .1;
    }
    
    public boolean getDriverLeftTrigger() {
    	return mDriverStick.getRawAxis(2) > .1;
    }
    
    public boolean getPovUp(){
    	return mSecondaryDriverStick.getPOV(0)==0 || mSecondaryDriverStick.getPOV(0)==45||mSecondaryDriverStick.getPOV(0)==315;
    	
    }
    public boolean getPovRight(){
    	return mSecondaryDriverStick.getPOV(0)==90 || mSecondaryDriverStick.getPOV(0)==45||mSecondaryDriverStick.getPOV(0)==135;
    	
    }
    public boolean getSecondaryReverseSpoolButton(){
    	return mSecondaryDriverStick.getPOV(0)==180 || mSecondaryDriverStick.getPOV(0)==135||mSecondaryDriverStick.getPOV(0)==225;
    	
    }
    public boolean getPovLeft(){
    	return mSecondaryDriverStick.getPOV(0)==270 || mSecondaryDriverStick.getPOV(0)==315||mSecondaryDriverStick.getPOV(0)==225;
    	
    }
    //
    
    //SECONDARY CONTROLLER
    //secondary functional controls

    //Intake Motors
    public boolean getSecondaryLeftIntakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) > .2;
    }
    
    public boolean getSecondaryRightIntakeButton(){
    	return mSecondaryDriverStick.getRawAxis(5) > .2;
    }
    
    public boolean getSecondaryLeftOutakeButton(){
    	return mSecondaryDriverStick.getRawAxis(1) < -.2;
    }
    
    public boolean getSecondaryRightOutakeButton(){
    	return mSecondaryDriverStick.getRawAxis(5) < -.2;
	}
	
    //Mr Hush
    public boolean getSecondaryHushButton(){
    	return mSecondaryDriverStick.getRawAxis(3) > .1;
    }
    
    //Elevator 
    public double getSecondaryElevatorAnalog(){
    	return mSecondaryDriverStick.getRawAxis(1);
	}

	public boolean getSecondaryElevatorHighButton(){
    	return mSecondaryDriverStick.getRawButton(4);
	}
	
	public boolean getSecondaryElevatorMiddleButton(){
    	return mSecondaryDriverStick.getRawButton(2);
	}
	
	public boolean getSecondaryElevatorLowButton(){
    	return mSecondaryDriverStick.getRawButton(1);
    }
	
	

    
    //Switchboard
    public boolean getSwitchboardLeftAuto(){
    	return mSwitchboard.getRawButton(9);
    }
    
    public boolean getSwitchboardMiddleAuto(){
    	return mSwitchboard.getRawButton(10);
    }
    
    public boolean getSwitchboardRightAuto(){
    	return mSwitchboard.getRawButton(11);
    }
    
    public boolean getSwitchboardForwardAuto(){
    	return mSwitchboard.getRawButton(12);
    }
    
    public boolean getSwitchboardStandStillAuto(){
    	return mSwitchboard.getRawButton(13);
    }

//----------------------------------------------------------------------------Hardware Map------------------------------------------------------------------------------------//
	
	/*public static HardwareMap getInstance(){
		return mInstance;
	}*/
	
	public Compressor mCompressorHardware;
	public VictorSPX mIntakeLeftHardware;
	public VictorSPX mIntakeRightHardware;
//	public VictorSP mFeederHardware;
	public VictorSP mClimberHardware;
	public Solenoid mLeftShifterHardware;
	public Solenoid mRightShifterHardware;
	public Solenoid mClimberReleaseHardware;
	public VictorSP mServoMotorHardware;
	public VictorSPX IntakeRotaryHardware;
	
	public ADXRS450_Gyro mGyro;	
	
	void HardwareMap() {
		//test
		try
		{
			mClimberHardware = new VictorSP(Setup.kClimberId);
			mLeftShifterHardware = new Solenoid(Setup.kLeftShifterSolenoidId);
	    	mRightShifterHardware = new Solenoid(Setup.kRightShifterSolenoidId);
	    	mIntakeLeftHardware = new VictorSPX(Setup.kIntakeLeftId);
	    	mIntakeRightHardware = new VictorSPX(Setup.kIntakeRightId);
			mCompressorHardware = new Compressor(0);
			mGyro = new ADXRS450_Gyro();
			mGyro.calibrate();
			
			mClimberReleaseHardware = new Solenoid(Setup.kClimberReleaseSolenoidId);
			mServoMotorHardware = new VictorSP(Setup.kServoMotorId);
			

			
		}
		catch(Exception e)
		{
			
		}
    }
    

//-----------------------------------------------------------------------------------Constants----------------------------------------------------------------------------------//

    //-------------------------------//
	//-subsystem speed motor speeds-//
	//-----------------------------//
	
    //PID
    public static double kFlywheelKp = 0.1;
    public static double kFlywheelKi = 0.00035;
    public static double kFlywheelKd = 2.3;
    public static double kFlywheelKf = 0.037;
    public static int kFlywheelIZone = (int) (1023.0 / kFlywheelKp);
    public static double kFlywheelRampRate = 0;
    
	//misc. (not used)
    public static int kFlywheelAllowableError = 0;
    public static double kFlywheelOnTargetTolerance = 100.0;
    
    public static double kLowRumbleValue = 0.3;
    public static double kHighRumbleValue = 1;
    
	//-------------------------------//
	//-subsystem motor speeds-//
	//-----------------------------//
    
    //Intake Roller
    public static double kRollerIntakeSpeed = 1;
	public static double kRollerReverseSpeed = -1;
    
	//Climber
	public static double kClimberReverseSpeed = -.5;
	public static double kClimberHoldSpeed = .40;
	
	//shooter
	public static double kTargetShooterRpm = 1785;
	public static double kTargetLowShooterRpm = 785;
	
	//-------------//
	//-Autonomous-//
	//-----------//
	

	//--------------------------//
	//-static port assignments-//
	//------------------------//
	
	//Do not change anything below this line
	
	//PWM (0-9)
//	public static int kLeftFrontMotorId = 0;
//	public static int kLeftRearMotorId = 1;
//	public static int kRightFrontMotorId = 2;
//	public static int kRightRearMotorId = 3;
	
	//CAN (0-3)
	public static int kLeftFrontShooterMotorId = 5;
	public static int kLeftRearShooterMotorId = 6;
	public static int kRightFrontShooterMotorId = 3;
	public static int kRightRearShooterMotorId = 4;
	public static int kLeftFrontMotorId = 9;
	public static int kLeftRearMotorId = 10;
	public static int kRightFrontMotorId = 7;
	public static int kRightRearMotorId = 8;
	public static int kClimberId = 13;
	public static int kServoMotorId = 5;
	public static int kIntakeLeftId = 12;
	public static int kIntakeRightId = 11;
	public static int kiIntakeRotary = 1;
	//DIO (0-9)
	public static int kLeftEncoderChannelA = 0;
	public static int kLeftEncoderChannelB = 1;
	public static int kRightEncoderChannelA = 2;
	public static int kRightEncoderChannelB = 3;
	
	//SOLENOIDS (0-7)
	
	public static int kLeftShifterSolenoidId = 3; //DONT CONNECT ON ACTUAL ROBOT!!!!
	public static int kRightShifterSolenoidId = 1;
	public static int kIntakeLeftSolenoidId = 5;
	public static int kIntakeRightSolenoidId = 4;
	public static int kRampLeftSolenoidId = 2;
	public static int kClimberReleaseSolenoidId = 6;
	public static int kMrHushySolenoid = 0;

	//LASER$!!!!!!!!
	public static int klaseranalog = 0;
}