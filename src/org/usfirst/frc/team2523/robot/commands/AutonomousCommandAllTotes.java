
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Lifts all three totes up in autonomous
 */
public class AutonomousCommandAllTotes extends CommandGroup
{
    public AutonomousCommandAllTotes() 
    {
    	//addSequential(new CalibrateLift());
    	
    	// Initiate lift for grabbing crate (move lift fast)
        addParallel(new OpenClaw());
        addParallel(new SetLiftTarget(Robot.lift.PICK_UP_HEIGHT, 1.0));
        
        // drive forward at quarter speed to crate after lift is in place
        addParallel(new DriveForwardUntilCrate(0.25));    
        
        // close claw and raise lift fast
        addSequential(new CloseClaw());
        addSequential(new Wait(0.5));
        addSequential(new SetLiftTarget(Robot.lift.SET_ON_TOP_HEIGHT, 1.0));
               
        // move around the trash can while raising lift (Move sideways for half second, then forwards, then sideways again
        addParallel(new DriveForTime(0.5, -0.25, 0, 0));
        addSequential(new DriveForTime(0.5, 0, 0.5, 0));
        addSequential(new DriveForTime(0.5, 0.25, 0, 0));
        
        // move forward again (at slower speed) until we hit a crate
        addSequential(new DriveForwardUntilCrate(0.15));
        
        // at crate, lower lift to position where the crate is on top, release the claw, lower farther (fast), then close it and lift again
        addSequential(new OpenClaw());
        addSequential(new SetLiftTarget(Robot.lift.PICK_UP_HEIGHT, 1.0));
        addSequential(new CloseClaw());
        addSequential(new Wait(0.5));
        addSequential(new SetLiftTarget(Robot.lift.SET_ON_TOP_HEIGHT));
        
        
        // move around the trash can while raising lift (Move sideways for half second, then forwards, then sideways again
        addParallel(new DriveForTime(0.5, -0.25, 0, 0));
        addSequential(new DriveForTime(0.5, 0, 0.5, 0));
        addSequential(new DriveForTime(0.5, 0.25, 0, 0));
        
        // drive forward to last crate
        addSequential(new DriveForwardUntilCrate(0.25));
        
        // repeat lift up sequence
        addSequential(new OpenClaw());
        addSequential(new SetLiftTarget(Robot.lift.PICK_UP_HEIGHT, 1.0));
        addSequential(new CloseClaw());
        addSequential(new Wait(0.5));
        addSequential(new SetLiftTarget(Robot.lift.DRIVE_HEIGHT));
        
        // move to area where crates must be and drop off the stack
//        addSequential(new DriveForTime(1.0, 0.5, 0, 0));
//        addSequential(new SetLiftTarget(Robot.lift.PICK_UP_HEIGHT));
//        addSequential(new OpenClaw());
//        addSequential(new DriveForTime(0.25, 0.5, 0, 0));
    }
}
