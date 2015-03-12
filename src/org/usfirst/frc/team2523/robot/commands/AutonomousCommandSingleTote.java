
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Lifts all three totes up in autonomous
 */
public class AutonomousCommandSingleTote extends CommandGroup
{
    public AutonomousCommandSingleTote() 
    {
    	//addSequential(new CalibrateLift());
    	
    	addSequential(new DriveForTime(2.0, 0.25, 0.25, 0));
    	
    	// Initiate lift for grabbing crate (move lift fast)
//        addParallel(new OpenClaw());
//        addParallel(new SetLiftTarget(Robot.lift.PICK_UP_HEIGHT, 1.0));
//        
//        // drive forward at quarter speed to crate after lift is in place
//        addSequential(new DriveForwardUntilCrate(0.5));
//        
//        // close claw and raise lift fast
//        addSequential(new CloseClaw());
//        addSequential(new Wait(0.5));
//        addSequential(new SetLiftTarget(Robot.lift.DRIVE_HEIGHT, 1.0));

        
        // move to area where crates must be and drop off the stack
//        addSequential(new DriveForTime(1.0, 0.5, 0, 0));
//        addSequential(new SetLiftTarget(Robot.lift.PICK_UP_HEIGHT));
//        addSequential(new OpenClaw());
//        addSequential(new DriveForTime(0.25, 0.5, 0, 0));
    }
}
