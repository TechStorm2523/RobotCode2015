
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Does nothing!
 */
public class AutonomousCommandNULL extends CommandGroup
{
    public AutonomousCommandNULL() 
    {
    	addSequential(new CalibrateLift());
    }
}
