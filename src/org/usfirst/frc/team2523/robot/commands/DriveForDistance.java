
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Drives the robot in the given direction for the given time
 */
public class DriveForDistance extends Command 
{
	// initialize global inputs
	double speed;
	double x;
	double y;
	
	/**
	 * Drives the robot in the given direction for a certain distance.
	 * @param distanceX Distance in feet to drive in x direction (right is positive)
	 * @param distanceY Distance in feet to drive in y direction (forward is positive)
	 * @param speed Speed to drive in both directions (between 0.0 and 1.0)
	 */
    public DriveForDistance(double distanceX, double distanceY, double speed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        
        // translate to globals
        this.speed = speed;
        this.x = distanceX;
        this.y = distanceY;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	// reset wheel distance
    	Robot.accelerometer.resetDistance();
    	
    	// normalize speed
    	if (this.speed < 0) this.speed = 0;
    	else if (this.speed > 1.0) this.speed = 1.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// set the motors accordingly if we are still under the distance
    	Robot.chassis.setMecanumDrive(this.speed, this.speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// stop if the distance in either direction exceeds the max distance 
        return Robot.accelerometer.distanceX > this.x || Robot.accelerometer.distanceY > this.y;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// stop motors
    	Robot.chassis.setMecanumDrive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
