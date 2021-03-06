
package org.usfirst.frc.team2523.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * Just drives straight for 10 feet at half speed (for setting up behind tote)
 */
public class AutonomousCommandExperimental extends CommandGroup
{
    public AutonomousCommandExperimental() 
    {
    	addSequential(new CalibrateLift());
    	addSequential(new SetLiftTarget(3, 1.0));
    	//addSequential(new CloseCLaw());
    	addSequential(new OpenClaw());
    	
    }
}
