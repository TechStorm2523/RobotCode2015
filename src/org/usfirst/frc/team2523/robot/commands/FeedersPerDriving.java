
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Runs the feeders based on how the robot is driving
 */
public class FeedersPerDriving extends Command 
{
	boolean enabled;

    public FeedersPerDriving(boolean enabled) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feederWheels);
        
        this.enabled = enabled;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if (Robot.chassis.forwardValue > 0)
    	{
    		Robot.feederWheels.setArcadeControl(-1.0, 0.0);
    	}
    	else if (Robot.chassis.forwardValue < 0)
    	{
    		Robot.feederWheels.setArcadeControl(1.0, 0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return !enabled;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.feederWheels.setArcadeControl(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
