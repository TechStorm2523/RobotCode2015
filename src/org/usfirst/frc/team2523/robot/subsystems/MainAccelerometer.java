
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.commands.UpdateDistance;

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
	    this.distanceX += convertToFeet(0.5 * -RobotMap.accelerometer.getX() * deltaTime * deltaTime); 
		this.distanceY += convertToFeet(0.5 * RobotMap.accelerometer.getZ() * deltaTime * deltaTime);
		
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
	
	/**
	 * Converts the given value in meters to feet
	 * @param meters Value in meters
	 * @return Returns value in feet
	 */
	public double convertToFeet(double meters)
	{
		return 3.28084 * meters;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateDistance());
    }
}

