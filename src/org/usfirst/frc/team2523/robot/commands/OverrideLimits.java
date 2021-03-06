
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Overrides the limit switches
 */
public class OverrideLimits extends Command 
{
	boolean enabled;

    public OverrideLimits(boolean enabled) 
    {
        // Use requires() here to declare subsystem dependencies
        //requires(Robot.exampleSubsystem);
    	
    	this.enabled = enabled;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.lift.limitSwitchOverride = this.enabled;
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
