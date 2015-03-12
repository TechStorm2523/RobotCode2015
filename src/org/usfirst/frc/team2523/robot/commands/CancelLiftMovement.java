
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Cancels lift calibration sequence
 */
public class CancelLiftMovement extends Command
{

    public CancelLiftMovement() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// set lift to stop calibrating if it is
    	Robot.lift.liftCalibrating = false;
    	
    	// stop motor
    	Robot.lift.moveLift(0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// only run once
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}