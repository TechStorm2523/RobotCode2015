
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Toggles the feeder wheels at a certain speed
 */
public class SetPOVFeederControl extends Command 
{
	// globals
	double speed;
	
	/**
	 * Sets the feeder wheels to run based off POV
	 */
    public SetPOVFeederControl() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feederWheels);
        
        this.speed = 1.0;
    }
	
	/**
	 * Sets the feeder wheels to run based off POV
	 * @param speed Speed to run at, between -1.0 and 1.0
	 */
    public SetPOVFeederControl( double speed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feederWheels);
        
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// run at speed according to POV axis (4 and 5)
    	Robot.feederWheels.setArcadeControl(RobotMap.primaryStick.getRawAxis(4), 
    										RobotMap.primaryStick.getRawAxis(5));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// runs once, called repeatedly
        return true;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// set drive to be stopped
    	Robot.feederWheels.setArcadeControl(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
    	end();
    }
}
