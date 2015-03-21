
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.subsystems.Lift;

/**
 * Grabs one tote and drives to the auto zone with it
 */
public class AutonomousCommandDriveWithTote extends CommandGroup
{
    public AutonomousCommandDriveWithTote() 
    {
    	// close claw
    	addSequential(new CloseClaw());
    	addSequential(new Wait(1));
    	
    	// raise lift up a couple inches (?)
    	addSequential(new SetLiftTarget(Lift.DRIVE_HEIGHT));
    	
    	// turn about 90 degrees
    	addSequential(new DriveForTime(2, 0, 0, 0.25));
    	
    	// drive for 10 feet
    	addSequential(new ResetDistance());  
    	addSequential(new DriveForTime(2, 0, 0.5, 0));
    }
}
