
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Enables the robot to be controlled by the joystick in mecanum drive configuration
 */
public class SetMecanumDrive extends Command 
{
	// define variables input in constructor
	public double xSpeed;
	public double ySpeed;
	public double rotationSpeed;
	
    public SetMecanumDrive(double xSpeed, double ySpeed, double rotationSpeed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        
        // set variables
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.rotationSpeed = rotationSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// run mecanum drive according to input
    	Robot.chassis.setMecanumDrive(this.xSpeed, this.ySpeed, this.rotationSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// runs until interrupted
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// stop everything
    	Robot.chassis.setMecanumDrive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
