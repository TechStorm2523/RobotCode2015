
package org.usfirst.frc.team2523.robot.subsystems;


import java.util.ArrayList;

import org.usfirst.frc.team2523.robot.commands.CheckMotorSafety;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Allows control of motor systems to stop motor output if commands are not given for a certain time
 */
public class MotorSafetySystem extends Subsystem 
{
	// define globals
	public double timeout;
	public boolean activated;
	public double lastTime;
	public ArrayList<Talon> motors;
	
	/**
	 * Class to control motor system, by deactivating motors after certain time.
	 * The system is activated after this constructor is called.
	 * @param timeout Time after which to deactivate motors
	 * @param motors Motors to control with this safety system (as Talon object)
	 */
	public MotorSafetySystem(double timeout, Talon... motors)
	{
		// set values
		this.timeout = timeout;
		this.activated = true;
		
		// add motors to central list
		for (Talon motor : motors)
		{
			this.motors.add(motor);
		}
	}
	
	/**
	 * Resets the timeout to continue motor operation
	 */
	public void feed()
	{
		// check for activation
		if (activated)
		{
			// "reset" timer (set last time to now so delay is zero)
			this.lastTime = System.nanoTime();
		}
	}
	
	/**
	 * Stop all motors, called if timer times out
	 */
	public void stopMotors()
	{
		// stop all motors
		for (Talon motor : motors)
		{
			motor.set(0.0);
		}
	}
	
	/**
	 * Turns on or off motor safety
	 * @param activated Boolean to activate (true) or deactivate (false) system
	 */
	public void setActivation(boolean activated)
	{
		this.activated = activated;
	}
	
    public void initDefaultCommand() 
    {
        // Run command to check if timeout has exceeded the limit
        setDefaultCommand(new CheckMotorSafety());
    }
}

