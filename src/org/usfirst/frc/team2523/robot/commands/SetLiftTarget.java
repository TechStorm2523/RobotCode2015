
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;


/**
 * Used to instruct the lift to travel at MAX_SPEED to target, the relative height of the lift
 */
public class SetLiftTarget extends Command 
{	
	// define globals
	public double speed;
	public double target;
	
	/**
	 * Constructor to instruct the lift to travel at MAX_SPEED to target, the relative height of the lift
	 * @param target The targeted percentage of lift height. Between 0.0 and 100.0
	 */
    public SetLiftTarget(double target) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
        
        // apply target to the lift
        this.target = target; //Robot.lift.setTarget(target);
        
        // set speed
        this.speed = RobotMap.MAX_LIFT_SPEED;
    }
    
	/**
	 * Constructor to instruct the lift to travel at a certain speed to target, the relative height of the lift
	 * @param target The targeted percentage of lift height. Between 0.0 and 100.0
	 * @param speed Max speed to move the lift at. Between 0.0 and 1.0
	 */
    public SetLiftTarget(double target, double speed) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
        
        // apply target to the lift
        this.target = target; //Robot.lift.setTarget(target);
        
        // set speed
        this.speed = speed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() 
    {
        // set lift speed according to input
        RobotMap.liftPIDControl.setMaxMin(-1 * this.speed, this.speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
		// ensure target is within bounds
//		if (Robot.lift.target > 100.0) Robot.lift.target = 100.0;
//		else if (Robot.lift.target < 0.0) Robot.lift.target = 0.0;
    	
		if (this.target > 100.0) this.target = 100.0;
		else if (this.target < 0.0) this.target = 0.0;
    	
		// set the speed of the lift motor according to the value calculated by the PID control class, inputting the encoder distance as current
		Robot.lift.moveLift(RobotMap.liftPIDControl.getPIoutput(this.target, RobotMap.liftEncoder.getDistance()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// this is never done until interrupted		// return true if the robot is at limits OR we are less than STOP_TOLERANCE from the target
        return Math.abs(target - RobotMap.liftEncoder.getDistance()) <= RobotMap.LIFT_STOP_TOLERANCE;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	// ensure motor is stopped
    	Robot.lift.moveLift(0.0);
    	
    	// reset integral value
    	RobotMap.liftPIDControl.resetIntegral();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
