package com.team3418.frc2018.subsystems;
import java.awt.image.VolatileImage;
import com.team3418.frc2018.Setup;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {

	AnalogInput ElevatorLaser = new AnalogInput(0);
	int raw = ElevatorLaser.getValue();
	double volts = ElevatorLaser.getVoltage();
	int averageRaw = ElevatorLaser.getAverageValue();
	double averageVolts = ElevatorLaser.getAverageVoltage();
	static Elevator mInstance = new Elevator();
	double elevatorHighPosition = 0;
	double elevatorMiddlePosition = 0;
	double elevatorLowPosition = 0;


    public static Elevator getInstance() {
        return mInstance;
    }
    
    VictorSPX mRightElevator;
    VictorSPX mLeftElevator;
	
    public Elevator(){
	}

	public void setElevatorPosition(String position)
	{
		if (position == "HIGH")
		{
			while (volts < elevatorHighPosition)
			{
				setElevatorSpeed(.25, .25);
			}
		} 

		if (position == "MIDDLE")
		{
			while (volts < elevatorMiddlePosition)
			{
				setElevatorSpeed(.25, .25);
			}

			while (volts > elevatorMiddlePosition)
			{
				setElevatorSpeed(-.25, -.25);
			}
		} 

		if (position == "LOW")
		{
			while (volts > elevatorLowPosition)
			{
				setElevatorSpeed(-.25, -.25);
			}
		} 


	}
    	
    private double mElevatorLeftSpeed;
    private double mElevatorRightSpeed;
    private double mMoveSpeed;
    private double mRotateSpeed;
    
	public void setElevatorSpeed(double left, double right)

	{
    	//System.out.println("Left speed = " + left + " right speed = " + right);
    	mElevatorLeftSpeed = left;
    	mElevatorLeftSpeed = right;
    	mLeftElevator.set(ControlMode.PercentOutput,left);
    	mRightElevator.set(ControlMode.PercentOutput,right);
    }
    
//    public void setArcadeDriveSpeed(double move, double rotate){
//    	mRotateSpeed = rotate;
//    	mMoveSpeed = move;
//    	mDrive.arcadeDrive(move, rotate);
//    }
    
    
    @Override
    public void stop(){
    	mLeftElevator.set(ControlMode.PercentOutput,0);
		mRightElevator.set(ControlMode.PercentOutput,0);
		
    }
    

	@Override
	public void updateSubsystem() {

		//Update Laser
		raw = ElevatorLaser.getValue();
		volts = ElevatorLaser.getVoltage();
		averageRaw = ElevatorLaser.getAverageValue();
		averageVolts = ElevatorLaser.getAverageVoltage();

		outputToSmartDashboard();
	}
	
	@Override
	public void outputToSmartDashboard() {
		//SmartDashboard.putNumber("Elevator_LeftMotorSpeeds", mLeftSpeed);
		//SmartDashboard.putNumber("Elevator_RightMotorSpeeds", mRightSpeed);
		SmartDashboard.putNumber("Elevator_MoveValue", mMoveSpeed);
		SmartDashboard.putNumber("Elevator_RotateValue", mRotateSpeed);
		SmartDashboard.putNumber("Laser Distance", raw);
	}
}
