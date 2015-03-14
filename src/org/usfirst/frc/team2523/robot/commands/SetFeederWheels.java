
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Toggles the feeder wheels at a certain speed
 */
public class SetFeederWheels extends Command 
{
	// globals
	boolean running;
	double speed;
	
	/**
	 * Sets the feeder wheels to either run at full speed or stop 
	 * @param toggle Set to true to run, set to false to stop
	 */
    public SetFeederWheels(boolean toggle) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feederWheels);
        
        this.running = toggle;
        this.speed = 1.0;
    }
	
	/**
	 * Sets the feeder wheels to either run at the given speed or stop
	 * @param toggle Set to true to run, set to false to stop
	 * @param speed Speed to run at, between -1.0 and 1.0
	 */
    public SetFeederWheels(boolean toggle, double speed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feederWheels);
        
        this.running = toggle;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// run at speed with no rotation
    	Robot.feederWheels.setArcadeControl(this.speed, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// stop if we are told to stop
        return !running;
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
