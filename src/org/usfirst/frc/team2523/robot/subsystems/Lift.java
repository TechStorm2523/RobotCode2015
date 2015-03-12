
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to control lift as well as limit switches and encoders associated with the lift
 */
public class Lift extends Subsystem 
{
	// define position constants
	public final double PICK_UP_HEIGHT = 0.0;  // height to lift crate
	public final double SET_ON_TOP_HEIGHT = 85.0; // height to release claw on top of other crate
	public final double DRIVE_HEIGHT = 20.0; // height to drive with stack of crates and be able to go over another crate
	
	// define constant to control lift calibration
	public boolean liftCalibrating = false;
	
	// define throttle speed control variable
	public double currentLiftSpeed = 0.0;
	
	// define target variable for lift to pursue
	public double target = 0.0;
		
	/**
	 * Method to determine if the lift is touching either the top or bottom limit switches, a.k.a. is at it's limits.
	 * @return Returns true if the lift is at a limit switch, false otherwise.
	 */
	public boolean isAtLimits()
	{
		return !RobotMap.liftTopLimitSwitch.get() || !RobotMap.liftBottomLimitSwitch.get();
	}
	
	/**
	 * Method to determine if the lift is at the top, using limit switches and encoders
	 * @return Returns true if at top, false if not
	 */
	public boolean isAtUpperLimit()
	{
		return !RobotMap.liftTopLimitSwitch.get(); //|| 100.0 - this.encoder.getDistance() <= RobotMap.LIFT_STOP_TOLERANCE;
	}
	
	/**
	 * Method to determine if the lift is at the bottom, using limit switches and encoders
	 * @return Returns true if at bottom, false if not
	 */
	public boolean isAtLowerLimit()
	{
		return !RobotMap.liftBottomLimitSwitch.get(); // || RobotMap.liftEncoder.getDistance() <= RobotMap.LIFT_STOP_TOLERANCE;
	}
	
	/**
	 * Method to determine if there is a crate (or trash can) in the robot's lift
	 * @return Returns true if there is crate, false otherwise
	 */
	public boolean isCratePresent()
	{
		return !RobotMap.crateSensingLimitSwitch.get();
	}
	
	/**
	 * Moves lift at a certain speed, which isn't limited by MAX_SPEED.
	 * @param speed Speed at which to move lift. Between -1.0 and 1.0
	 */
	public void moveLift(double speed)
	{
		// (speed is opposite of motor)
		// only allow upward movement if not at the top
		if (-1 * speed > 0 && !this.isAtUpperLimit())
		{
			// set liftMotor to given speed (invert motor)
			RobotMap.liftMotor.set(-1 * speed);
		}
		// allow only downward movement if not at the bottom
		else if (-1 * speed < 0 && !this.isAtLowerLimit())
		{
			// set liftMotor to given speed (invert motor)
			RobotMap.liftMotor.set(-1 * speed);
		}
		else
		{
			// stop motor
			RobotMap.liftMotor.set(0.0);
			
			// reset encoder as maintenance if at the bottom
			if (isAtLowerLimit())
			{
		    	RobotMap.liftEncoder.reset();
		    	RobotMap.liftEncoder.setDistancePerPulse(RobotMap.LIFT_ENCODER_DISTANCE_PER_PULSE);
			}
			// set the distance per pulse if at the top
			else if (isAtUpperLimit())
			{			
				// calculate DISTANCE_PER_PULSE based off encoder reading at top (set so that 100 is max)
				RobotMap.LIFT_ENCODER_DISTANCE_PER_PULSE = 100.0 / RobotMap.liftEncoder.get();
				
				// set encoder distance based off this
				RobotMap.liftEncoder.setDistancePerPulse(RobotMap.LIFT_ENCODER_DISTANCE_PER_PULSE);
			}
		}
		
		// set display value
		this.currentLiftSpeed = speed;
	}
	
	/**
	 * Returns the lift height on SmartDashboard
	 */
	public double getLiftPosition()
	{
		// return the encoder distance (which should be between 0 and 100)
		return RobotMap.liftEncoder.getDistance();
	}
	
	/**
	 * Used to get real world lift height based off percentage height
	 * @param percentage Percentage of full height
	 * @return Returns the height in inches
	 */
	public double getHeight(double percentage)
	{
		return percentage * RobotMap.LIFT_HEIGHT_PER_PERCENTAGE;
	}
	
	/**
	 * Sets the internal target variable
	 * @param target Value to set
	 */
	public void setTarget(double target)
	{
		this.target = target;
	}
	
	public void initDefaultCommand() 
	{
		// continually set the lift to target it's current position
		//setDefaultCommand(new SetLiftTarget(this.target));
	}
}

