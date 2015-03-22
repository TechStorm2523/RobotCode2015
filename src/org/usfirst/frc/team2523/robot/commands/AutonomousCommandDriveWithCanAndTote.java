
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.subsystems.Lift;

/**
 * Lifts the can, then moves and grabs the crate, then drives to the auto zone
 */
public class AutonomousCommandDriveWithCanAndTote extends CommandGroup
{
    public AutonomousCommandDriveWithCanAndTote() 
    {
        addSequential(new CalibrateLift());
    	
    	// close on the bin and raise it up one
    	addSequential(new CloseClaw());
    	addSequential(new Wait(0.5));
    	addSequential(new SetLiftTarget(Lift.TOTE_INCREMENT_HEIGHT + Lift.TOTE_TOP_CLEARANCE, 1.0)); // five inches
    	
    	// drive to the crate
        //addParallel(new SetFeederWheels(true));
    	addSequential(new DriveForwardUntilCrate(0.5));
    	
    	// wait
        addSequential(new Wait(0.25));
    	
        // lower bin on top and release claw, then wait a sec
        addSequential(new OpenClaw());
        addSequential(new Wait(0.25));
        
        // lower to bottom and grab crate
        addSequential(new SetLiftTarget(Lift.PICK_UP_HEIGHT, 1.0));
        addSequential(new Wait(0.5));
        addSequential(new CloseClaw());
        
        // wait a bit
        addSequential(new Wait(0.5));
        
        // lift to a height to move with
        addSequential(new SetLiftTarget(Lift.DRIVE_HEIGHT, 1.0));
    	
        // drive off
    	addSequential(new DriveForTime(2, 0, 0, -0.25));
    	addSequential(new ResetDistance());  
    	addSequential(new DriveForTime(2, 0, 0.5, 0));
//    	addSequential(new DriveForDistance(10, 0, 0.5));
    }
}
