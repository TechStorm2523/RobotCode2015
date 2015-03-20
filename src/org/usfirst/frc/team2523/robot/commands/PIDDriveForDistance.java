
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.subsystems.PIDControl;

/**
 * Drives the robot in the given direction for the given time !!!!!!!!!!!!!!!!!!!!    POSSIBLE PID CONTROL   ??????????????????????
 */
public class PIDDriveForDistance extends Command 
{
	// initialize global inputs
	double maxSpeed;
	double x;
	double y;
	
	// copy PID control system into here
	PIDControl xPID = RobotMap.drivePIDControl;
	PIDControl yPID = RobotMap.drivePIDControl;
	
	/**
	 * Drives the robot in the given direction for a certain distance using PID Control (at full speed)
	 * @param distanceX Distance in feet to drive in x direction (right is positive)
	 * @param distanceY Distance in feet to drive in y direction (forward is positive)
	 */
    public PIDDriveForDistance(double distanceX, double distanceY) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        
        // translate to globals
        this.maxSpeed = 1.0; // full speed if none given
        this.x = distanceX;
        this.y = distanceY;
    }
	
	/**
	 * Drives the robot in the given direction for a certain distance using PID Control
	 * @param distanceX Distance in feet to drive in x direction (right is positive)
	 * @param distanceY Distance in feet to drive in y direction (forward is positive)
	 * @param maxSpeed Speed to drive in both directions (between 0.0 and 1.0)
	 */
    public PIDDriveForDistance(double distanceX, double distanceY, double maxSpeed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        
        // translate to globals
        this.maxSpeed = maxSpeed;
        this.x = distanceX;
        this.y = distanceY;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	// reset wheel distance
    	Robot.accelerometer.resetDistance();
    	
    	// normalize speed
    	if (this.maxSpeed < 0) this.maxSpeed = 0;
    	else if (this.maxSpeed > 1.0) this.maxSpeed = 1.0;
    	
    	// setup PID control system
    	this.xPID.setMaxMin(-this.maxSpeed, this.maxSpeed);
    	this.yPID.setMaxMin(-this.maxSpeed, this.maxSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// set the motors accordingly if we are still under the distance
    	Robot.chassis.setMecanumDrive(this.xPID.getPIoutput(this.x, Robot.accelerometer.distanceX), 
    									this.yPID.getPIoutput(this.y, Robot.accelerometer.distanceY), 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// stop if the distance in either direction is at the max distance 
        return Robot.accelerometer.distanceX >= this.x || Robot.accelerometer.distanceY >= this.y;
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
