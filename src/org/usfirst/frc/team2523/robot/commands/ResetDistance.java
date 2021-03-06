
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Resets integration of the accelerometer
 */
public class ResetDistance extends Command 
{
    public ResetDistance() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.accelerometer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// run function
    	Robot.accelerometer.resetDistance();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// runs once
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
