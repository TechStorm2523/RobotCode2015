
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Drives the robot forward until the limit switch indicating a crate is hit is tripped
 */
public class DriveForwardUntilCrate extends Command 
{
	public double speed;
	
	/**
	 * Drives the robot forward until the limit switch indicating a crate is hit is tripped
	 * @param speed Speed at which to drive forward
	 */
    public DriveForwardUntilCrate(double speed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	// ensure crate isn't already present
    	if (Robot.lift.isCratePresent()) end();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.chassis.setMecanumDrive(0.0, this.speed, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return Robot.lift.isCratePresent();
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// stop the motors
    	Robot.chassis.setMecanumDrive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
