
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Called from MotorSafetySystem, checks if motors must be stopped because of an overrun timeout
 */
public class CheckMotorSafety extends Command 
{
    public CheckMotorSafety() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveMotorSafety);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
    	// check if time since last feed is greater than timeout (convert timeout to nanoseconds)
    	if (Robot.driveMotorSafety.activated && 
    			System.nanoTime() - Robot.driveMotorSafety.lastTime > Robot.driveMotorSafety.timeout * 1e9)
    	{
    		// if so stop the relevant motors
    		Robot.driveMotorSafety.stopMotors();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// never stops
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
