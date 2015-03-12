
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.commands.ArcadeFeederControl;

import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the feeder wheels on the front of the robot
 */
public class FeederWheels extends Subsystem 
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void setArcadeControl(double forward, double rotation)
	{
		// create motor speed values
	    double leftMotorSpeed;
	    double rightMotorSpeed;
	
	    // normalize speed values
	    forward = Robot.chassis.normalize(forward);
	    rotation = Robot.chassis.normalize(rotation);
	
//	    if (squaredInputs) {
//	        // square the inputs (while preserving the sign) to increase fine control while permitting full power
//	        if (forward >= 0.0) {
//	            forward = (forward * forward);
//	        } else {
//	            forward = -(forward * forward);
//	        }
//	        if (rotation >= 0.0) {
//	            rotation = (rotation * rotation);
//	        } else {
//	            rotation = -(rotation * rotation);
//	        }
//	    }
	    
	    // set one wheel based off rotation and the other based off the most signifigant input of either speed or rotation
	    if (forward > 0.0) 
	    {
	        if (rotation > 0.0) 
	        {
	            leftMotorSpeed = forward - rotation;
	            rightMotorSpeed = Math.max(forward, rotation);
	        } 
	        else 
	        {
	            leftMotorSpeed = Math.max(forward, -rotation);
	            rightMotorSpeed = forward + rotation;
	        }
	    } 
	    else 
	    {
	        if (rotation > 0.0) 
	        {
	            leftMotorSpeed = -Math.max(-forward, rotation);
	            rightMotorSpeed = forward + rotation;
	        } 
	        else 
	        {
	            leftMotorSpeed = forward - rotation;
	            rightMotorSpeed = -Math.max(-forward, -rotation);
	        }
	    }
	    
	    // set motor outputs
	    RobotMap.rightFeedMotor.set(rightMotorSpeed);
	    RobotMap.leftFeedMotor.set(leftMotorSpeed);
	}
	
	/**
	 * Sets drive to normal Mecanum Drive, controlled by the joystick
	 */
	public void setJoystickArcadeControl()
	{		
		// get joystick values (use z for rotation)
		double yValue = RobotMap.secondaryStick.getY(); //Robot.chassis.getExpodentialValue(yValue)
		double zValue = RobotMap.secondaryStick.getZ();
		
		// cutoff near zero
		if (Math.abs(yValue) < RobotMap.JOYSTICK_DEADZONE) yValue = 0;
		if (Math.abs(zValue) < RobotMap.JOYSTICK_DEADZONE) zValue = 0;
		
    	// Use the joystick Y axis for forward/backward movement, and Z axis for rotation. (reverse for joystick)
		this.setArcadeControl(-1.0 * yValue, zValue);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeFeederControl());
    }
}

