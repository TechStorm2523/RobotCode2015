
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Moves lift according to throttle setting, interupts the holding of the lift until finished
 */
public class MoveLiftByThrottle extends Command 
{
	// create constant to determine direction
	public double multiplier = 0.0;
	
	/**
	 * Moves the lift, using the throttle as speed control
	 * @param direction Direction to move lift with throttle. Either "up" or "down"
	 */
    public MoveLiftByThrottle(String direction) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
        
        // set multiplier based on string
        if (direction.toLowerCase().equals("up")) this.multiplier = 1.0;
        else if (direction.toLowerCase().equals("down")) this.multiplier = -1.0;
        else multiplier = 0.0;
    }

	// Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	// get lift speed according to throttle control, and multiplier determing direction
    	// getThrottle is between -1.0 and 1.0, so shift up by 1 and multiply by -0.5 so value is 1.0 at max throttle and 0 at min
    	Robot.lift.currentLiftSpeed = (RobotMap.stick.getThrottle() - 1) * 0.5 * this.multiplier;
    	
		// move lift at this speed
    	Robot.lift.moveLift(Robot.lift.currentLiftSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// set lift speed to zero
    	Robot.lift.currentLiftSpeed = 0.0;
    	
		// stop motor
    	Robot.lift.moveLift(0.0);
    	
    	// set PID target to the current position
    	Robot.lift.target = RobotMap.liftEncoder.getDistance();
    	
    	// have lift maintain target (current) position
    	new SetLiftTarget(Robot.lift.target);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
