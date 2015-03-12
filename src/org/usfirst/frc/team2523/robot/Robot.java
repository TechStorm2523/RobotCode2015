
package org.usfirst.frc.team2523.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2523.robot.commands.AutonomousCommandAllTotes;
import org.usfirst.frc.team2523.robot.commands.AutonomousCommandBasic;
import org.usfirst.frc.team2523.robot.commands.AutonomousCommandDriveWithCanAndTote;
import org.usfirst.frc.team2523.robot.commands.AutonomousCommandDriveWithTote;
import org.usfirst.frc.team2523.robot.commands.AutonomousCommandSingleTote;
import org.usfirst.frc.team2523.robot.commands.SetLiftTarget;
import org.usfirst.frc.team2523.robot.subsystems.Camera;
import org.usfirst.frc.team2523.robot.subsystems.Chassis;
import org.usfirst.frc.team2523.robot.subsystems.Claw;
import org.usfirst.frc.team2523.robot.subsystems.Dashboard;
import org.usfirst.frc.team2523.robot.subsystems.Lift;
import org.usfirst.frc.team2523.robot.subsystems.MainAccelerometer;
import org.usfirst.frc.team2523.robot.subsystems.MotorSafetySystem;
import org.usfirst.frc.team2523.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
	// instantiate subsystems
	public static Lift lift;
	public static Chassis chassis;
	public static Claw claw;
	public static Camera camera;
	public static Dashboard dashboard;
	public static MotorSafetySystem driveMotorSafety;
	public static MainAccelerometer accelerometer;
	
	// instantiate operator interface
	public static OI oi;
	
	// define command for autonomous 
    Command autonomousCommand;
    SendableChooser autoChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
    	// set up subsystems
    	lift = new Lift();
    	chassis = new Chassis();
    	claw = new Claw();
    	camera = new Camera();
    	dashboard = new Dashboard();
		oi = new OI();
		accelerometer = new MainAccelerometer();
		//driveMotorSafety = new MotorSafetySystem(0.1);
				//RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontLeftMotor, RobotMap.rearRightMotor); // set timeout to be 0.1 seconds
		
		// create the autonomous command chooser
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Turn with tote and go", new AutonomousCommandDriveWithTote());
		autoChooser.addObject("Turn with can, get crate and go", new AutonomousCommandDriveWithCanAndTote());
		autoChooser.addObject("Just go straight", new AutonomousCommandBasic());
		
		// put option on dashboard
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
		       
        // ensure robot will stop motors if they do not receive commands for 0.1 seconds
        RobotMap.robotDrive.setSafetyEnabled(true);
    	RobotMap.robotDrive.setExpiration(0.1);
    	
    	// reset gyro (because it takes a while)
    	//chassis.resetGyro();
    }
	
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}	

    public void autonomousInit() 
    {
    	// make sure chassis is full speed
    	chassis.setDriveSpeed(1.0);
    	
    	// set up autonomous
    	autonomousCommand = (Command) autoChooser.getSelected();
    	autonomousCommand.start();

    	// reset lift encoder and distance per pulse
    	RobotMap.liftEncoder.reset();
    	RobotMap.liftEncoder.setDistancePerPulse(RobotMap.LIFT_ENCODER_DISTANCE_PER_PULSE);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        // make sure drive speed is at default
    	chassis.setDriveSpeed(RobotMap.DEFAULT_SPEED_REDUCTION);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() 
    {
    	// reset lift encoder and distance per pulse
    	RobotMap.liftEncoder.reset();
    	RobotMap.liftEncoder.setDistancePerPulse(RobotMap.LIFT_ENCODER_DISTANCE_PER_PULSE);
    	
    	// reset gyro (because it takes a while)
    	//chassis.resetGyro();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
        LiveWindow.run();
    }
}
