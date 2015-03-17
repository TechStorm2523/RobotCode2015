package org.usfirst.frc.team2523.robot;

import org.usfirst.frc.team2523.robot.subsystems.PIDControl;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
	/**
	 * CONSTANTS TODO: CHANGE NUMBERS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	// LIFT (NOTE: HEIGHTS START AT BOTTOM LIFT HEIGHT)
	public static final double TICKS_AT_TOP = 3625; // ticks encoder reads at top (determined theoretically)
	public static final double MAX_LIFT_SPEED = 0.6;  // max speed for auto rising lift
	public static final double MAX_LIFT_HEIGHT = 60; // max height in inches
	public static double LIFT_ENCODER_DISTANCE_PER_PULSE = MAX_LIFT_HEIGHT / TICKS_AT_TOP; // theoretical limit - "distance" per pulse of encoder - set so that encoder.getDistance() is MAX_LIFT_HEIGHT when the lift is at the top
	public static final double LIFT_STOP_TOLERANCE = 0.5; // sets distance off target that lift will stop moving near it's max or min height
	
	// CAMERA
	public static final int CAMERA_WIDTH = 640;
	public static final int CAMERA_HEIGHT = 480;
	public static final int CAMERA_BAR_WIDTH = 5;
	
	// CHASSIS
	public static final double JOYSTICK_DEADZONE = 0.05;
	public static final double EXPONENETIAL_FACTOR = 2;
	public static final double DEFAULT_SPEED_REDUCTION = 0.5;
	public static final double DRIVE_GYRO_SENSITIVITY = 9e-3; // Volts/degree/second

    /**
     * PID INPUTS
     */
	// Main Drive Wheels
    public static final int frontLeftPort	= 3;
    public static final int rearLeftPort	= 2;
    public static final int frontRightPort  = 1;
    public static final int rearRightPort	= 0;
	
    // Lift Motor
	public static Jaguar liftMotor = new Jaguar(4);
	
	// Feeder Wheel controllers
	public static Victor rightFeedMotor = new Victor(5);
	public static Victor leftFeedMotor = new Victor(6);	
	
	/**
	 * PCM INPUTS (PNEUMATICS)
	 */
	public static Compressor compressor = new Compressor(0);
	public static Solenoid clawSolenoid = new Solenoid(4);
	
	/**
	 * DIGITAL INPUTS
	 */
	// Initiate encoder with A and B on 0 and 1 WITHOUT inverting direction on 4x mode
	public static Encoder liftEncoder = new Encoder(0, 1, true, EncodingType.k2X);
	
	// Initiate limit switches as counters
	public static DigitalInput liftTopLimitSwitch = new DigitalInput( 3 );
	public static DigitalInput liftBottomLimitSwitch = new DigitalInput( 2 );
	public static DigitalInput crateSensingLimitSwitch = new DigitalInput( 4 );
	
	// Initiate drive encoders WITHOUT inverting direction on 4x mode
//	public static Encoder frontLeftEncoder = new Encoder(5, 6, true, EncodingType.k4X);
//	public static Encoder rearLeftEncoder = new Encoder(7, 8, true, EncodingType.k4X);
//	public static Encoder frontRightEncoder = new Encoder(9, 10, true, EncodingType.k4X);
//	public static Encoder rearRightEncoder = new Encoder(11, 12, true, EncodingType.k4X);
	
	/**
	 * ANALOG INPUTS
	 */
	// Initiate gyro on first analog channel (TODO: SET GYRO SENSITIVITY: http://wpilib.screenstepslive.com/s/4485/m/13809/l/241871-gyros-measuring-rotation-and-controlling-robot-driving-direction)
    //public static Gyro chassisGyro = new Gyro(0);
	
    //public static AnalogInput Input1 = new AnalogInput(0);
	
	// Initate the accelerometer to measure robot displacement
	public static Accelerometer accelerometer = new BuiltInAccelerometer(Accelerometer.Range.k8G); 
    
	/**
	 * RobotDrive
	 */
	public static RobotDrive robotDrive = new RobotDrive(frontLeftPort, rearLeftPort, frontRightPort, rearRightPort);
    
    // individual motors for drive
//	public static Talon frontLeftMotor = new Talon(frontLeftPort);
//	public static Talon rearLeftMotor = new Talon(rearLeftPort);
//	public static Talon frontRightMotor = new Talon(frontRightPort);
//	public static Talon rearRightMotor = new Talon(rearRightPort);
	
	/**
	 * Joystick(s)
	 */
    public static Joystick primaryStick = new Joystick(0);
    public static Joystick secondaryStick = new Joystick(1); 
    
	/**
	 * PID CONTROL SYSTEMS
	 */
	public static PIDControl liftPIDControl = new PIDControl(0.01, 0.005, 0.1, -1.0, 1.0, 0.5);
    // TODO: Values require adjusting!
    //public static PIDControl gyroPIDControl = new PIDControl(0.012, 0.005, 0.01, -1.0, 1.0, 0.5);
}
