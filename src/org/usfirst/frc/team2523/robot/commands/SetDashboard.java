
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 *
 */
public class SetDashboard extends Command 
{
    public SetDashboard() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.dashboard);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
		// Configure SmartDashboard displays
        SmartDashboard.putNumber(" Lift Speed ", Math.abs(Robot.lift.currentLiftSpeed));
		SmartDashboard.putNumber(" Lift Height ", Robot.lift.getLiftPosition());
		SmartDashboard.putBoolean(" Claw Closed ", Robot.claw.isClawClosed());
		SmartDashboard.putBoolean(" Crate Present ", Robot.lift.isCratePresent());
		SmartDashboard.putBoolean(" Lift Calibrating ", Robot.lift.liftCalibrating);
		SmartDashboard.putNumber(" Encoder Reading ", RobotMap.liftEncoder.get());
		SmartDashboard.putNumber(" Encoder Distance ", Robot.lift.getLiftPosition());
		SmartDashboard.putNumber(" Actual Lift Height ", Robot.lift.getHeight(Robot.lift.getLiftPosition()));
		//SmartDashboard.putNumber(" Direction ", RobotMap.chassisGyro.getAngle());
		SmartDashboard.putNumber(" Drive Distance ", Robot.chassis.rearRightDistance);
		SmartDashboard.putBoolean(" High Speed Mode On", Robot.chassis.globalSpeedChange == 1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	// only needs to run once, constantly called
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
