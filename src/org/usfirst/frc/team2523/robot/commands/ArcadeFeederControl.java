
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Enables the feeder wheels to be controlled by an arcade-style joystick configuration
 */
public class ArcadeFeederControl extends Command 
{
    public ArcadeFeederControl() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feederWheels);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// run normal arcade control
    	Robot.feederWheels.setJoystickArcadeControl();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	// set drive to be stopped
    	Robot.feederWheels.setArcadeControl(0, 0);
    }
}
