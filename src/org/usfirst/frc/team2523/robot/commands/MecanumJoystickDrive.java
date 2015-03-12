
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Enables the robot to be controlled by the joystick in mecanum drive configuration
 */
public class MecanumJoystickDrive extends Command 
{

    public MecanumJoystickDrive() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// run normal mecanum drive
    	Robot.chassis.setJoystickMecanumDrive();
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
    	Robot.chassis.setMecanumDrive(0, 0, 0);
    }
}
