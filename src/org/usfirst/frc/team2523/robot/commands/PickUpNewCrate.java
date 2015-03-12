
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Command group to lower stack, release claw, lower further, and then close the claw and lift it slightly
 */
public class PickUpNewCrate extends CommandGroup
{	
    public PickUpNewCrate() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.claw);
        requires(Robot.lift);
        
    	// reset integral value
    	RobotMap.liftPIDControl.resetIntegral();
        
        // start commands
        // lower crate on top and release claw, then wait a sec
        addSequential(new SetLiftTarget(Robot.lift.SET_ON_TOP_HEIGHT, 0.75));
        addSequential(new OpenClaw());
        addSequential(new Wait(0.25));
        
        // lower to bottom and grab crate
        addSequential(new SetLiftTarget(Robot.lift.PICK_UP_HEIGHT, 1.0));
        addSequential(new Wait(0.5));
        addSequential(new CloseClaw());
        
        // wait a bit
        addSequential(new Wait(0.5));
        
        // lift to a height to move with
        addSequential(new SetLiftTarget(Robot.lift.SET_ON_TOP_HEIGHT, 1.0));
    }
}
