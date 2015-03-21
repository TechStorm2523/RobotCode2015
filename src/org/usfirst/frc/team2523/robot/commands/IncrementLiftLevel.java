
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Used to instruct the lift to move up or down a certain tote height
 */
public class IncrementLiftLevel extends CommandGroup
{
	/**
	 * Constructor to instruct the lift to move at MAX_LIFT_SPEED up or down a certain tote height
	 * @param rate The number of increments (1 is up one tote, -2 is down two totes)
	 */
    public IncrementLiftLevel(int rate) 
    {   
    	// set the lift target at whatever level we are approximately at plus whatever increment was given
    	addSequential(new SetLiftTarget(Robot.lift.getCurrentToteLevel() + rate));
    }
    
	/**
	 * Constructor to instruct the lift to move at the given speed up or down a certain tote height
	 * @param rate The number of increments (1 is up one tote, -2 is down two totes)
	 * @param speed The actual speed of the lift to move at, between 0.0 and 1.0
	 */
    public IncrementLiftLevel(int rate, double speed)
    {
    	// set the lift target at whatever level we are approximately at plus whatever increment was given, and go at the given speed
    	addSequential(new SetLiftTarget(Robot.lift.getCurrentToteLevel() + rate, speed));
    }
}
