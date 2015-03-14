
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.subsystems.Lift;

/**
 * Command group to lower stack, release claw, lower further, and then close the claw and lift it slightly
 */
public class PickUpNewCrate extends CommandGroup
{	
	/**
	 * Lowers stack, release claw, lower further, and then close the claw and lift it slightly
	 * @param level The tote level to do this at, between 1 and 5 (1 drops a tote on the first tote, 2 drops it on the second, etc.)
	 */
    public PickUpNewCrate(int level) 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.claw);
        requires(Robot.lift);
        
        // ensure level is in bounds (shift upwards because we have shifted the input)
        level = Robot.lift.normalizeToteLevel(level);
        
    	// reset integral value
    	RobotMap.liftPIDControl.resetIntegral();
        
        // start commands
        // lower crate on top and release claw, then wait a sec
        addSequential(new SetLiftTarget(level * Lift.TOTE_INCREMENT_HEIGHT, 0.75));
        addSequential(new OpenClaw());
        addSequential(new Wait(0.25));
        
        // lower down one tote and grab crate
        addSequential(new SetLiftTarget((level - 1) * Lift.TOTE_INCREMENT_HEIGHT, 1.0));
        addSequential(new Wait(0.5));
        addSequential(new CloseClaw());
        
        // wait a bit
        addSequential(new Wait(0.5));
        
        // lift to a height to move with
        addSequential(new SetLiftTarget(level * Lift.TOTE_INCREMENT_HEIGHT + Lift.TOTE_TOP_CLEARANCE, 1.0));
    }
}
