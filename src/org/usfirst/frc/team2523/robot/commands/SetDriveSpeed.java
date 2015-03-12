
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Sets the chassis drive speed
 */
public class SetDriveSpeed extends Command 
{
	double speed;
	
	/**
	 * Sets the chassis drive speed
	 * @param speed The speed to drive the robot at (between 0.0 and 1.0)
	 */
    public SetDriveSpeed(double speed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        
        // then set it
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.chassis.setDriveSpeed(this.speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// only runs once
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
