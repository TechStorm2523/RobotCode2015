
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Lifts all three totes up in autonomous
 */
public class AutonomousCommandDriveWithCan extends CommandGroup
{
    public AutonomousCommandDriveWithCan() 
    {
    	// close on the bin and raise it
    	addSequential(new CloseClaw());
    	addSequential(new SetLiftTarget(Robot.lift.SET_ON_TOP_HEIGHT, 1.0));
    	
    	// drive to the crate
    	addSequential(new DriveForwardUntilCrate(0.25));
    	
    	// wait
        addSequential(new Wait(0.25));
    	
        // lower crate on top and release claw, then wait a sec
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
    	
        // drive off
    	addSequential(new DriveForTime(2, 0, 0, -0.25));
    	addSequential(new DriveForTime(2, 0, 0.5, 0));
    }
}
