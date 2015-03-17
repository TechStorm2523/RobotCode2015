
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.subsystems.Lift;

/**
 * Lifts all three totes up in autonomous, with the assumption that there is no cans in the way
 */
public class AutonomousCommandAllTotes extends CommandGroup
{
    public AutonomousCommandAllTotes() 
    {
    	//addSequential(new CalibrateLift()); 
        
        // close claw and raise lift fast
        addSequential(new CloseClaw());
        addSequential(new Wait(0.5));
        addSequential(new SetLiftTarget(1, 1.0));
        addParallel(new SetFeederWheels(true));
               
        // move around the trash can while raising lift (Move sideways, then forwards, then sideways again)
//        addParallel(new ResetDistance());
//        addParallel(new DriveForDistance(2, 0, 0.5)); // 2 feet 
//        addSequential(new DriveForDistance(4, 0, 0.5)); // 4 feet
//        addSequential(new DriveForDistance(-2, 0, 0.5)); // 2 feet back
        
        // move forward again (at slower speed) until we hit a crate, then stop feeders
        addSequential(new DriveForwardUntilCrate(0.5));
        addSequential(new SetFeederWheels(false));
        
        // at crate, lower lift to position where the crate is on top, release the claw, lower farther (fast), then close it and lift again
        addSequential(new OpenClaw());
        addSequential(new SetLiftTarget(Lift.PICK_UP_HEIGHT, 1.0));
        addSequential(new CloseClaw());
        addSequential(new Wait(0.5));
        addSequential(new SetLiftTarget(1, 0.75));
        addParallel(new SetFeederWheels(true));
        
        // move around the trash can while raising lift (Move sideways, then forwards, then sideways again
//      addParallel(new ResetDistance());
//      addParallel(new DriveForDistance(2, 0, 0.75)); // 2 feet 
//      addSequential(new DriveForDistance(4, 0, 0.75)); // 4 feet
//      addSequential(new DriveForDistance(-2, 0, 0.75)); // 2 feet back
        
        // drive forward to last crate and stop feeders again
        addSequential(new DriveForwardUntilCrate(0.25));
        addSequential(new SetFeederWheels(false));
        
        // repeat lift up sequence
        addSequential(new OpenClaw());
        addSequential(new SetLiftTarget(Lift.PICK_UP_HEIGHT, 1.0));
        addSequential(new CloseClaw());
        addSequential(new Wait(0.5));
        addSequential(new SetLiftTarget(Lift.DRIVE_HEIGHT, 0.75));
        
    	// turn about 90 degrees
    	addSequential(new DriveForTime(2, 0, 0, 0.25));
        
        // move to auto zone and drop off the stack
    	addSequential(new ResetDistance());  
    	addSequential(new DriveForDistance(10, 0, 0.5));
      
    }
}
