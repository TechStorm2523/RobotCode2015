
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Lifts all three totes up in autonomous
 */
public class AutonomousCommandDriveWithTote extends CommandGroup
{
    public AutonomousCommandDriveWithTote() 
    {
    	addSequential(new CloseClaw());
    	
    	addSequential(new DriveForTime(2, 0, 0, 0.25));
    	addSequential(new DriveForTime(2, 0, 0.5, 0));
    }
}
