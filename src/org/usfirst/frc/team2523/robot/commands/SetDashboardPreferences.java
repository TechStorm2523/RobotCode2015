
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 *
 */
public class SetDashboardPreferences extends Command 
{
    public SetDashboardPreferences() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.dashboard);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
		// Configure SmartDashboard displays
    	// ESSENTIALS
        SmartDashboard.putNumber(" Lift Speed ", Math.abs(Robot.lift.currentLiftSpeed));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// only needs to run once
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
