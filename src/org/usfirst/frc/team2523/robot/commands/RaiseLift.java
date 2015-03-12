
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Raises motor at given speed TODO: IS THIS NECCESARY???????
 */
public class RaiseLift extends Command 
{
	// define variable
	double speed;
	
	/**
	 * Raises motor at given speed
	 * @param speed Speed to raise motor at. Between 0.0 and 1.0
	 */
    public RaiseLift(double speed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
        
        // set variable
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// move motor at speed
    	Robot.lift.moveLift(this.speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// this command only runs while button is pressed, and ends when it is released
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// stop motor
    	Robot.lift.moveLift(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
