
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.commands.MeasureDistance;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the main accelerometer
 */
public class MainAccelerometer extends Subsystem 
{    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// global variables
	public double distanceX;
	public double distanceY;
	private long lastReadTime;
	
	/**
	 * Adds current accelerometer to integrated distance. Called periodically
	 */
	public void addToIntegration()
	{
	    // get delta time from last calculation, and convert to seconds
	    double deltaTime = (this.lastReadTime - System.nanoTime()) * 1e-9;
	    
		// set distance based off acceleration (integration) x = 0.5at^2
	    this.distanceX += 0.5 * -RobotMap.accelerometer.getX() * deltaTime * deltaTime; 
		this.distanceY += 0.5 * RobotMap.accelerometer.getZ() * deltaTime * deltaTime;
		
	    // reset last time read
	    this.lastReadTime = System.nanoTime();
	}
	
	/**
	 * Reset the distance values to zero
	 */
	public void resetDistance()
	{
		this.distanceX = 0;
		this.distanceY = 0;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MeasureDistance());
    }
}

