
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Moves lift down until lower limit switch is triggered, then resets the encoder to be zero at that point.
 */
public class CalibrateLift extends Command 
{
	/**
	 * Moves lift down until lower limit switch is triggered, then resets the encoder to be zero at that point.
	 */
    public CalibrateLift() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	// indicate that the lift is calibrating
    	Robot.lift.liftCalibrating = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
		// iterate while the bottom limit switch is not tripped
		if (!Robot.lift.isAtLowerLimit())
		{
			// set liftMotor to move down
			Robot.lift.moveLift(RobotMap.MAX_LIFT_SPEED);
		}
		else 
		{
			// when the loop breaks and we are at the bottom, stop the motor
			Robot.lift.moveLift(0.0);
			
			// wait for lift to stop
			Timer.delay(0.1);
			
			// then set the encoder to zero 
			RobotMap.liftEncoder.reset();
			RobotMap.liftEncoder.setDistancePerPulse(RobotMap.LIFT_ENCODER_DISTANCE_PER_PULSE);
			
			// we're finished
			Robot.lift.liftCalibrating = false;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// stop if lift is finished or lift is not calibrating
        return !Robot.lift.liftCalibrating;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// ensure motor is stopped
    	Robot.lift.moveLift(0.0);
    	
    	// reset variable
    	Robot.lift.liftCalibrating = false;
    	
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
