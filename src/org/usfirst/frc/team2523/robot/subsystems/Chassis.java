
package org.usfirst.frc.team2523.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2523.robot.Robot;
import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.commands.MecanumJoystickDrive;

/**
 * Controls actions related to the main drive chassis of the robot.
 */
public class Chassis extends Subsystem 
{	
    // define globals
    public double targetGyroAngle = 0.0;
    public double lastReadTime = 0;
    public double globalSpeedChange = RobotMap.DEFAULT_SPEED_REDUCTION;
    
    // define motor inversions
    double frontLeftInversion = 1;
    double frontRightInversion = -1; // INVERTED
    double rearLeftInversion = 1;
    double rearRightInversion = -1; // INVERTED
    
    // define motor distances
    public double frontLeftDistance = 0;
    public double frontRightDistance= 0;
    public double rearLeftDistance = 0;
    public double rearRightDistance = 0; 
    
//	// set constant(s)
//	public final double MOTOR_TO_RPM_FACTOR = 0.0; // set so that the motor power input from -1.0 to 1.0 corresponds to shaft RPMS
//	private final double MOTOR_COMPENSATION_THRESHOLD = 0.05; //percentage above (or below) one after which we should compensate the motor's power
//	
//	// set global lists for mecanum drive use
//    double oldEncoderValues[] = new double[4]; // could use getDistance for these values and set each encoder's distance per tick such that getDistance is RPMs, or just use ticks as units
//    double oldMotorOutputs[] = new double[4];
//    double motorCompensations[] = new double[4];

	/**
	 * Sets drive to normal Mecanum Drive, controlled by the joystick
	 */
	public void setJoystickMecanumDrive()
	{
		// reverse right side motors
		RobotMap.robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		RobotMap.robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		
		// get joystick values
		double xValue = this.getExpodentialValue(RobotMap.primaryStick.getX() * this.globalSpeedChange);
		double yValue = this.getExpodentialValue(RobotMap.primaryStick.getY() * this.globalSpeedChange);
		double zValue = this.getExpodentialValue(RobotMap.primaryStick.getZ() / 2 * this.globalSpeedChange);
		
		// cutoff near zero
		if (Math.abs(xValue) < RobotMap.JOYSTICK_DEADZONE) xValue = 0;
		if (Math.abs(yValue) < RobotMap.JOYSTICK_DEADZONE) yValue = 0;
		if (Math.abs(zValue) < RobotMap.JOYSTICK_DEADZONE) zValue = 0;
		
		
    	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation. (reverse for joystick)
		this.setMecanumDrive(xValue, -1.0 * yValue, zValue);
	}

	
	/**
	 * Sets speeds for the Mecanum drive to operate at, while compensating with a gyro.
	 * @param xSpeed Speed in y direction. From -1.0 to 1.0
	 * @param ySpeed Speed in x direction. From -1.0 to 1.0
	 * @param rotationSpeed Speed of rotation. From -1.0 to 1.0 (positive is clockwise)
	 */
	public void setMecanumDrive(double xSpeed, double ySpeed, double rotationSpeed)
	{
//		// start seeing if gyro must be compensated only if there are no commands to rotate (deadzoning for joysticks is external)
//      if (rotationSpeed == 0)
//      {
//          // set rotation speed according to PID to compensate proportionally to error (invert) (NO INTEGRAL RIGHT????)
//          rotationSpeed = -1 * RobotMap.gyroPIDControl.getPDoutput(targetGyroAngle, RobotMap.chassisGyro.getAngle());
//      }
//      else
//      {
//          // until we stop commanding rotation, determine which angle we're at in preparation for compensation
//          targetGyroAngle = RobotMap.chassisGyro.getAngle();
//      }
		
        // set Mecanum Drive according to parameters, ignoring gyro option (invert ySpeed because function is oriented for joysticks)
		RobotMap.robotDrive.mecanumDrive_Cartesian(xSpeed, -1.0 * ySpeed, rotationSpeed, 0); 
		//this.MecanumDrive(xSpeed, -1.0 * ySpeed, rotationSpeed);
	}

   
//	/**
//	 * Function to compute mecanum outputs, compensate via gyro, and threshold outputs to ensure control at high speeds.
//	 * @param x Speed in x direction to drive (Between -1.0 and 1.0)
//	 * @param x Speed in y direction to drive (Between -1.0 and 1.0)
//	 * @param x Speed to rotate at (Between -1.0 and 1.0, positive is clockwise)
//	 */
//	public void MecanumDrive(double x, double y, double rotation)
//	{
//		// invert y for joystick 
//		y = -y;
//		
//	    // start seeing if gyro must be compensated only if there are no commands to rotate (deadzoning for joysticks is external)
//	    if (rotation == 0)
//	    {
//	        // set rotation speed according to PID to compensate proportionally to error (NO INTEGRAL RIGHT????)
//	        rotation = RobotMap.gyroPIDControl.getPDoutput(targetGyroAngle, RobotMap.chassisGyro.getAngle());
//	    }
//	    else
//	    {
//	        // until we stop commanding rotation, determine which angle we're at in preparation for compensation
//	        this.targetGyroAngle = RobotMap.chassisGyro.getAngle();
//	    }
//	    
//	    // set outputs based on mecanum principle
//	    double frontLeftOutput = y + x + rotation;
//	    double frontRightOutput = y - x - rotation;
//	    double rearLeftOutput = y - x + rotation;
//	    double rearRightOutput = y + x - rotation;
//	
//	    // limit outputs
//	    double max = frontLeftOutput; // because this is the only motor that SUMS all values
//	        
//	    if (Math.abs(rearLeftOutput) > max) max = rearLeftOutput;
//	    else if (Math.abs(frontRightOutput) > max) max = frontRightOutput;
//	    else if (Math.abs(rearRightOutput) > max) max = rearRightOutput;
//	      
//	    // and normalize so speed ratios are maintained if we're maxed out
//	    if (max > 1)
//	    {
//	        frontRightOutput /= max;
//	        frontLeftOutput /= max;
//	        rearRightOutput /= max;
//	        rearLeftOutput /= max;
//	    }
//	    
//	    // set motor outputs
//	//    RobotMap.frontRightMotor.set(frontRightOutput * frontRightInversion);
//	//    RobotMap.frontLeftMotor.set(frontLeftOutput * frontLeftInversion);
//	//    RobotMap.rearRightMotor.set(rearRightOutput * rearRightInversion);
//	//    RobotMap.rearLeftMotor.set(rearLeftOutput * rearRightInversion);
//	    
//	    // get delta time from last calculation, and convert to seconds
//	    double deltaTime = (this.lastReadTime - System.nanoTime()) * 1e-9;
//	    
//	    // sum integrals (add speed in feet per second divided by the time interval
//	    this.frontLeftDistance += frontLeftOutput * RobotMap.FEET_PER_SECOND_PER_SPEED_UNIT * deltaTime;
//	    this.frontRightDistance += frontRightOutput * RobotMap.FEET_PER_SECOND_PER_SPEED_UNIT * deltaTime;
//	    this.rearLeftDistance += rearLeftOutput * RobotMap.FEET_PER_SECOND_PER_SPEED_UNIT * deltaTime;
//	    this.rearRightDistance += rearRightOutput * RobotMap.FEET_PER_SECOND_PER_SPEED_UNIT * deltaTime; 
//	    
//	    // reset last time read
//	    this.lastReadTime = System.nanoTime();
//	    
//	    // Feed for motor safety
//	    Robot.driveMotorSafety.feed();
//	}
   
	
	/**
	 * Changes the given input to an exponential value
	 * @param input Input to be translated to exponential between -1.0 and 1.0
	 * @return Returns the exponential value between -1.0 and 1.0
	 */
	public double getExpodentialValue(double input)
	{
		if (input > 0)
		{
			return Math.pow(input, RobotMap.EXPONENETIAL_FACTOR);
		}
		else 
		{
			return -1.0 * Math.pow(input, RobotMap.EXPONENETIAL_FACTOR);
		}
	}
	
	/**
	 * Resets the integral distance readings on the wheels
	 */
	public void resetDistance()
	{
		this.frontLeftDistance = 0;
		this.frontRightDistance = 0;
		this.rearLeftDistance = 0;
		this.rearRightDistance = 0;
	}
	
	/**
	 * Sets the chassis drive speed
	 * @param speed The speed to drive the robot at (between 0.0 and 1.0)
	 */
    public void setDriveSpeed(double speed) 
    {      
        // ensure speed is in limits
        if (speed > 1.0) speed = 1.0;
        else if (speed < 0.0) speed = 0.0;
        
        // then set it
        this.globalSpeedChange = speed;
    }
	
	/** 
	 * Sets any values above 1.0 or below -1.0 to those maximums/minimums
	 * @param values List of values to be normalized
	 */
	public double[] normalize(double[] values) 
	{
		for (int i = 0; i < values.length; i++)
		{
			if (values[i] > 1.0) values[i] = 1.0;
			else if (values[i] < -1.0) values[i] = -1.0;
		}
		return values;
	}
	
	/**
	 * Sets a value above 1.0 or below -1.0 to those maximums/minimums
	 * @param value A single value to be normalized
	 */
	public double normalize(double value) 
	{
		if (value > 1.0) return 1.0;
		else if (value < -1.0) return -1.0;
		else return value;
	}
    
//	/**
//	 * Resets gyro and apply configuration
//	 */
//	public void resetGyro()
//	{
//    	RobotMap.chassisGyro.reset();
//    	RobotMap.chassisGyro.setSensitivity(RobotMap.DRIVE_GYRO_SENSITIVITY);
//	}
	
    public void initDefaultCommand() {
        // IMPORTANT! Enables the robot to drive in Mecanum drive consistently
        setDefaultCommand(new MecanumJoystickDrive());
    }
}

///**
//* Sets the wheel speeds for mecanum drive based of x, y, and rotation inputs. Also compensates each motor to achive expected RPM performance
//* @param x Speed in y direction. From -1.0 to 1.0
//* @param y Speed in x direction. From -1.0 to 1.0
//* @param rotation Speed of rotation. From -1.0 to 1.0 (positive is clockwise)
//*/
//public void setMecanumDrive(double x, double y, double rotation) 
//{
//	// get encoder speeds (in ticks per iteration interval)
//	double encoderSpeeds[] = new double[4];
//  encoderSpeeds[RobotMap.frontLeftMotor] = oldEncoderValues[RobotMap.frontLeftMotor] - RobotMap.frontLeftEncoder.get();
//  encoderSpeeds[RobotMap.frontRightMotor] = oldEncoderValues[RobotMap.frontRightMotor] - RobotMap.frontRightEncoder.get();
//  encoderSpeeds[RobotMap.rearLeftMotor] = oldEncoderValues[RobotMap.rearLeftMotor] - RobotMap.rearLeftEncoder.get();
//  encoderSpeeds[RobotMap.rearRightMotor] = oldEncoderValues[RobotMap.rearRightMotor] - RobotMap.rearRightEncoder.get();
//	
//  // set each motor's compensation (local) based off MOST RECENT measurements
//  double motorCompensations[] = new double[4];        
//  motorCompensations[RobotMap.frontLeftMotor] = (oldMotorOutputs[RobotMap.frontLeftMotor] * this.MOTOR_TO_RPM_FACTOR) / encoderSpeeds[RobotMap.frontLeftMotor];
//  motorCompensations[RobotMap.frontRightMotor] = (oldMotorOutputs[RobotMap.frontRightMotor] * this.MOTOR_TO_RPM_FACTOR) / encoderSpeeds[RobotMap.frontRightMotor];
//  motorCompensations[RobotMap.rearLeftMotor] = (oldMotorOutputs[RobotMap.rearLeftMotor] * this.MOTOR_TO_RPM_FACTOR) / encoderSpeeds[RobotMap.rearLeftMotor];
//  motorCompensations[RobotMap.rearRightMotor] = (oldMotorOutputs[RobotMap.rearRightMotor] * this.MOTOR_TO_RPM_FACTOR) / encoderSpeeds[RobotMap.rearRightMotor];
//  
//  // then set the actual (global) compensation value only if compensation is needed (if the compensation factor is ~ greater than 1)
//  if (Math.abs(motorCompensations[RobotMap.frontLeftMotor] - 1) > this.MOTOR_COMPENSATION_THRESHOLD) 
//  	this.motorCompensations[RobotMap.frontLeftMotor] = motorCompensations[RobotMap.frontLeftMotor];
//  if (Math.abs(motorCompensations[RobotMap.frontRightMotor] - 1) > this.MOTOR_COMPENSATION_THRESHOLD)
//  	this.motorCompensations[RobotMap.frontRightMotor] = motorCompensations[RobotMap.frontRightMotor];
//  if (Math.abs(motorCompensations[RobotMap.rearLeftMotor] - 1) > this.MOTOR_COMPENSATION_THRESHOLD)
//  	this.motorCompensations[RobotMap.rearLeftMotor] = motorCompensations[RobotMap.rearLeftMotor];
//  if (Math.abs(motorCompensations[RobotMap.rearRightMotor] - 1) > this.MOTOR_COMPENSATION_THRESHOLD)
//  	this.motorCompensations[RobotMap.rearRightMotor] = motorCompensations[RobotMap.rearRightMotor];
//  
//  // determine wheel speeds for mecanums 
//  double wheelSpeeds[] = new double[4];
//  wheelSpeeds[RobotMap.frontLeftMotor] = x + y + rotation;
//  wheelSpeeds[RobotMap.frontRightMotor] = -x + y - rotation;
//  wheelSpeeds[RobotMap.rearLeftMotor] = -x + y + rotation;
//  wheelSpeeds[RobotMap.rearRightMotor] = x + y - rotation;
//  
//  // set previous motor settings to these motor settings (DONT apply compensation to these, so we can see what the actual compensation from the theoretical output is)
//  oldMotorOutputs[RobotMap.frontLeftMotor] = wheelSpeeds[RobotMap.frontLeftMotor];
//  oldMotorOutputs[RobotMap.frontRightMotor] = wheelSpeeds[RobotMap.frontRightMotor];
//  oldMotorOutputs[RobotMap.rearLeftMotor] = wheelSpeeds[RobotMap.rearLeftMotor];
//  oldMotorOutputs[RobotMap.rearRightMotor] = wheelSpeeds[RobotMap.rearRightMotor];
//  
//  // compensate motor output based on GLOBAL compensations
//  wheelSpeeds[RobotMap.frontLeftMotor] *= this.motorCompensations[RobotMap.frontLeftMotor];
//  wheelSpeeds[RobotMap.frontRightMotor] *= this.motorCompensations[RobotMap.frontRightMotor];
//  wheelSpeeds[RobotMap.rearLeftMotor] *= this.motorCompensations[RobotMap.rearLeftMotor];
//  wheelSpeeds[RobotMap.rearRightMotor] *= this.motorCompensations[RobotMap.rearRightMotor];
//  
//  // normalize outputs
//  normalize(wheelSpeeds);
//  
//  // set motor outputs
//  //RobotMap.frontLeftController.set();
// 
//  
//  // set previous encoder distance to current distance
//  oldEncoderValues[RobotMap.frontLeftMotor] = RobotMap.frontLeftEncoder.get();
//  oldEncoderValues[RobotMap.frontRightMotor] = RobotMap.frontRightEncoder.get();
//  oldEncoderValues[RobotMap.rearLeftMotor] = RobotMap.rearLeftEncoder.get();
//  oldEncoderValues[RobotMap.rearRightMotor] = RobotMap.rearRightEncoder.get();
//}
//

