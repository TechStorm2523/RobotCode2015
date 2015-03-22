
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;

/**
 * Just drives straight for 10 feet at half speed (for setting up behind tote)
 */
public class AutonomousCommandAlliance extends CommandGroup
{
    public AutonomousCommandAlliance() 
    {
    	// calibrate and wait
    	addSequential(new Wait(8));
    	//addParallel(new CalibrateLift());
    	
    	// drive forward for 1 second
    	addSequential(new DriveForTime(2.25, 0, 0.5, 0));
    }
}
