
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to control lift as well as limit switches and encoders associated with the lift
 */
public class Lift extends Subsystem 
{
	// define position constants TODO: ADD MORE AND CHECK INCH CONVERSIONS!!!!!!!!!!!!!!!!!!!!!
	public static final double TOTE_INCREMENT_HEIGHT = 12; // actual height of a tote
	public static final double TOTE_TOP_CLEARANCE = 1;// height to move tote above "theoretical" (perfectly on top) height
	public static final double PICK_UP_HEIGHT = 0.0;  // height to pick up crate from
	public static final double DRIVE_HEIGHT = 5; // height to drive with stack of crates
	public static final double MAX_HEIGHT = RobotMap.MAX_LIFT_HEIGHT; // redirect value here as well
	public static final int MAX_LEVEL = (int) (Lift.MAX_HEIGHT / Lift.TOTE_INCREMENT_HEIGHT); // max number of totes to stack (from 1 to 6) (truncated)
	
	// define constant to control lift calibrations
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
				// calculate DISTANCE_PER_PULSE based off encoder reading at top (set so that the max inch reading is at the top)
				RobotMap.LIFT_ENCODER_DISTANCE_PER_PULSE = MAX_HEIGHT / RobotMap.liftEncoder.get();
				
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
		// return the encoder distance (which should be between 0 and RobotMap.MAX_LIFT_HEIGHT)
		return RobotMap.liftEncoder.getDistance();
	}
	
	/**
	 * Used to get real world lift height based off percentage height
	 * @param percentage Actual height in inches
	 * @return Returns the height in a percentage value between 0 and 100.
	 */
	public double getPercentageHeight(double height)
	{
		return 100 * (height / MAX_HEIGHT);
	}
	
	/**
	 * Sets the internal target variable
	 * @param target Value to set
	 */
	public void setTarget(double target)
	{
		this.target = target;
	}
	
	/**
	 * Calculates current tote level height based off inch reading and proximity to the corresponding level
	 * @return Returns the level the lift is at from 0 to 5
	 */
	public int getCurrentToteLevel()
	{
		// round the current level divided by the inches per level
		return (int) Math.round(RobotMap.liftEncoder.getDistance() / TOTE_INCREMENT_HEIGHT);
	}
	
    /**
     * Applies some logic to an inputed tote level request to convert it to inches 
     * while compensating for situations on the top and bottom
     * @param level The targeted tote level (between 0 and 5)
     * @return Returns a lift height in inches
     */
    public double getCalibratedLevel(int level)
    {
    	// normalize level value if at extremes, otherwise convert tote height to inches and add some clearance
    	if (target == 0) return 0;
    	else if (target == MAX_LEVEL) return MAX_HEIGHT; 
    	else return target * TOTE_INCREMENT_HEIGHT + TOTE_TOP_CLEARANCE;
    }
   
	/**
	 * @param level Raw tote level int
	 * @return Returns the level normalized to the max level of the lift
	 */
	public int normalizeToteLevel(int level) 
	{
		// ensure level does not exceed max level (the number of tote heights that fit in our lift height)
		if (level < 0) return 0;
		else if (level > MAX_LEVEL) return (MAX_LEVEL);
		else return level;
	}
	
	public void initDefaultCommand() 
	{
		// continually set the lift to target it's current position
		//setDefaultCommand(new SetLiftTarget(this.target));
	}
}

