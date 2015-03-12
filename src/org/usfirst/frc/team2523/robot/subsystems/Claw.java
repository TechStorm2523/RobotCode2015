
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the solenoid of the claw lifter
 */
public class Claw extends Subsystem
{
    // get objects used in functions
	public Compressor compressor = RobotMap.compressor;
	public Solenoid clawSolenoid = RobotMap.clawSolenoid; 
	
	/**
	 * Opens the claw using the solenoid 
	 */
	public void open()
	{
		// set claw to open
		clawSolenoid.set(false);
	}
	
	/**
	 * Closes the claw using the solenoid
	 */
	public void close()
	{
		// set claw to close
		clawSolenoid.set(true);
	}
	
	/**
	 * Reads solenoid to tell whether the claw is closed
	 * @return Returns true if class is closed, false if open
	 */
	public boolean isClawClosed()
	{
		// check if the solenoid is in "reverse"
		return clawSolenoid.get(); // COMMENTED TO MAKE STUFF WORK
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

