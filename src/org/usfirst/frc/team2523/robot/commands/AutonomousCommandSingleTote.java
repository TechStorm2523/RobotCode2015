
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Just drives straight for 10 feet at half speed (for setting up behind tote)
 */
public class AutonomousCommandSingleTote extends CommandGroup
{
    public AutonomousCommandSingleTote() 
    {
    	addSequential(new CalibrateLift());
    	
    	addSequential(new ResetDistance());      	
    	addSequential(new DriveForTime(2, 0, 0.5, 0));
    }
}
