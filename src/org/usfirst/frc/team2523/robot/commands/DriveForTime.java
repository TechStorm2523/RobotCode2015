
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Drives the robot in the given direction for the given time
 */
public class DriveForTime extends Command 
{
	// initialize global inputs
	double time;
	double x;
	double y;
	double rotation;
	
	// initialize global time handler
	long startTime;
	
	/**
	 * Drives the robot in the given direction for the given time
	 * @param time Time to drive for in seconds
	 * @param x Speed in x direction (between -1.0 and 1.0)
	 * @param y Speed in y direction (between -1.0 and 1.0)
	 * @param rotation Rotation speed (between -1.0 and 1.0) (clockwise is positive)
	 */
    public DriveForTime(double time, double x, double y, double rotation) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        
        // translate to globals
        this.time = time;
        this.x = x;
        this.y = y;
        this.rotation = rotation;        
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	// set the start time
    	this.startTime = System.nanoTime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// set the motors accordingly if we are still in the time limit
    	Robot.chassis.setMecanumDrive(this.x, this.y, this.rotation);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// stop if the elapsed time is greater than the given time (multiply by 1000000000 to convert from seconds to nanoseconds)
        return System.nanoTime() - startTime >= this.time * 1000000000;
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
