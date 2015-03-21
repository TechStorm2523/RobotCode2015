
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.subsystems.Lift;

/**
 * Lifts just the can in auto
 */
public class AutonomousCommandDriveWithCan extends CommandGroup
{
    public AutonomousCommandDriveWithCan() 
    {
    	// close claw
    	addSequential(new CloseClaw());
    	
    	// raise lift up a couple inches (?)
    	addSequential(new SetLiftTarget(Lift.DRIVE_HEIGHT));
    	
    	// turn about 90 degrees THE OPPOSITE DIRECTION
    	addSequential(new DriveForTime(2, 0, 0, -0.25));
    	
    	// drive for 10 feet
    	addSequential(new ResetDistance());  
    	addSequential(new DriveForTime(2, 0, 0.5, 0));
    	//addSequential(new DriveForDistance(10, 0, 0.5));
    }
}
