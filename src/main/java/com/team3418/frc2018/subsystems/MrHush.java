package com.team3418.frc2018.subsystems;
import com.team3418.frc2018.Setup;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MrHush extends Subsystem {
	
	static MrHush mInstance = new MrHush();

    public static MrHush getInstance() {
        return mInstance;
    }
    
	public MrHush() {
		MrHushySolenoid = new Solenoid(Setup.kMrHushySolenoid);
		System.out.println("Mr Hushy has been initialized :D");
	}

	private Solenoid MrHushySolenoid;
	
	public enum MrHushState {
      	EXTENDED, RETRACTED
    }
  	
  	private MrHushState mMrHushState;
  	
  	public MrHushState getClimberState() {
  		return mMrHushState;
  	}
	
  	
	@Override
	public void updateSubsystem() {
		
		switch(mMrHushState){
		case EXTENDED:
			setExtend();
			break;
		case RETRACTED:
			setRetract();
			break;
		default:
			mMrHushState = MrHushState.EXTENDED;
			break;
		}
		
		outputToSmartDashboard();
	}
	
	public void Extend() {
		mMrHushState = MrHushState.EXTENDED;
	}
	
	public void Retract() {
		mMrHushState = MrHushState.RETRACTED;
	}
	
	private void setExtend() {
		MrHushySolenoid.set(false);
	}
	
	private void setRetract() {
		MrHushySolenoid.set(true);
	}

	@Override
	public void outputToSmartDashboard() {
		SmartDashboard.putString("MrHushyState", mMrHushState.toString());
	}

	@Override
	public void stop() {
		Extend();
	}
}
