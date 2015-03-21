
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.commands.ArcadeFeederControl;
import org.usfirst.frc.team2523.robot.commands.SetPOVFeederControl;

import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the feeder wheels on the front of the robot
 */
public class FeederWheels extends Subsystem 
{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// motor inversion constants
	double leftMotorInversion = 1;
	double rightMotorInversion = 1;
	
	/**
	 * Controls the feed wheels in an arcade fashion, blending feed and rotation
	 * @param feedIn Speed to feed totes in at, between -1.0 and 1.0
	 * @param rotation Speed to rotate totes at, between -1.0 and 1.0
	 */
	public void setArcadeControl(double feedIn, double rotation)
	{
		// create motor speed values
	    double leftMotorSpeed;
	    double rightMotorSpeed;
	
	    // normalize speed values
	    feedIn = Robot.chassis.normalize(feedIn);
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
	    if (feedIn > 0.0) 
	    {
	        if (rotation > 0.0) 
	        {
	            leftMotorSpeed = feedIn - rotation;
	            rightMotorSpeed = Math.max(feedIn, rotation);
	        } 
	        else 
	        {
	            leftMotorSpeed = Math.max(feedIn, -rotation);
	            rightMotorSpeed = feedIn + rotation;
	        }
	    } 
	    else 
	    {
	        if (rotation > 0.0) 
	        {
	            leftMotorSpeed = -Math.max(-feedIn, rotation);
	            rightMotorSpeed = feedIn + rotation;
	        } 
	        else 
	        {
	            leftMotorSpeed = feedIn - rotation;
	            rightMotorSpeed = -Math.max(-feedIn, -rotation);
	        }
	    }
	    
	    // set motor outputs
	    RobotMap.rightFeedMotor.set(rightMotorSpeed * rightMotorInversion * RobotMap.FEED_WHEEL_COMPENSATION);
	    RobotMap.leftFeedMotor.set(leftMotorSpeed * leftMotorInversion * RobotMap.FEED_WHEEL_COMPENSATION);
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
		
    	// Use the joystick Y axis for forward/backward movement, and Z axis for rotation. (DONT reverse for joystick, because we want pushing to feed out (forward of joystick is negative))
		this.setArcadeControl(yValue, zValue);
	}

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeFeederControl()); // new SetPOVFeederControl());
    }
}

