package org.usfirst.frc.team2523.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2523.robot.commands.CalibrateLift;
import org.usfirst.frc.team2523.robot.commands.CloseClaw;
import org.usfirst.frc.team2523.robot.commands.FeedersPerDriving;
import org.usfirst.frc.team2523.robot.commands.IncrementLiftLevel;
import org.usfirst.frc.team2523.robot.commands.LowerLift;
import org.usfirst.frc.team2523.robot.commands.MoveLiftByThrottle;
import org.usfirst.frc.team2523.robot.commands.OpenClaw;
import org.usfirst.frc.team2523.robot.commands.OverrideLimits;
import org.usfirst.frc.team2523.robot.commands.PickUpNewCrate;
import org.usfirst.frc.team2523.robot.commands.RaiseLift;
import org.usfirst.frc.team2523.robot.commands.SetDriveSpeed;
import org.usfirst.frc.team2523.robot.commands.SetFeederWheels;
import org.usfirst.frc.team2523.robot.commands.SetLiftTarget;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{  
    // create required buttons
    // Button button = new JoystickButton(stick, buttonNumber);
	
	/*
	 * Main Driver (Primary) Joystick
	 */
    Button slowSpeed = new JoystickButton(RobotMap.primaryStick, 1);
    Button feederPull = new JoystickButton(RobotMap.primaryStick, 5);
    Button feederPush = new JoystickButton(RobotMap.primaryStick, 3);
//    Button manualUp = new JoystickButton(RobotMap.primaryStick, 5);
//    Button manualDown = new JoystickButton(RobotMap.primaryStick, 3);
    
	/*
	 * Second Driver (Secondary) Joystick
	 */
    Button closeClaw = new JoystickButton(RobotMap.secondaryStick, 1);
    Button openClaw = new JoystickButton(RobotMap.secondaryStick, 2);
    Button incrementUp = new JoystickButton(RobotMap.secondaryStick, 6);
    Button incrementDown = new JoystickButton(RobotMap.secondaryStick, 4);
    Button manualUpThrottle = new JoystickButton(RobotMap.secondaryStick, 5);
    Button manualDownThrottle = new JoystickButton(RobotMap.secondaryStick, 3);
    Button calibrateLift = new JoystickButton(RobotMap.secondaryStick, 7);
    Button pickUpNewCrate1 = new JoystickButton(RobotMap.secondaryStick, 9);
    Button pickUpNewCrate2 = new JoystickButton(RobotMap.secondaryStick, 10);
    Button moveToBottom = new JoystickButton(RobotMap.secondaryStick, 11);
    Button moveToTop = new JoystickButton(RobotMap.secondaryStick, 12);
    Button overrideLimits = new JoystickButton(RobotMap.secondaryStick, 8);
    Button feedersPerDriving = new JoystickButton(RobotMap.secondaryStick, 2);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    public OI()
    {
	    /**
	     * WHEN PRESSED COMMANDS
	     * Start the command when the button is pressed and let it run the command
	     * until it is finished as determined by it's isFinished method.
	     */
    	// control the closing and opening of the claw, respectively
		closeClaw.whenPressed(new CloseClaw());
		openClaw.whenPressed(new OpenClaw());
    	
		// set lift target to top and bottom, respectively
		incrementUp.whenPressed(new IncrementLiftLevel(1, 1.0));
		incrementDown.whenPressed(new IncrementLiftLevel(-1, 1.0));
		
		// move the lift to the absolute bottom and top
		moveToTop.whenPressed(new SetLiftTarget(RobotMap.MAX_LIFT_HEIGHT, 1.0));
		moveToBottom.whenPressed(new SetLiftTarget(0.0, 1.0));
		
	    // calibrate lift
	    calibrateLift.whenPressed(new CalibrateLift());
	    
	    // activate the macro to add a crate to the stack, at level 1 and level 2 respectively
	    pickUpNewCrate1.whenPressed(new PickUpNewCrate(1));
	    pickUpNewCrate2.whenPressed(new PickUpNewCrate(2));
		
	    // toggle high speed mode on the chassis
	    slowSpeed.whenPressed(new SetDriveSpeed(RobotMap.DEFAULT_SPEED_REDUCTION));
	    slowSpeed.whenReleased(new SetDriveSpeed(1.0));
	    
	    overrideLimits.whenPressed(new OverrideLimits(true));
    	
//    	// use the trigger and thumb buttons to control the closing and opening of the claw, respectively
//		primaryTriggerButton.whenPressed(new CloseClaw());
//		primaryThumbButton.whenPressed(new OpenClaw());
//		
//		// use top and bottom left buttons to set lift target to top and bottom, respectively
//	    topLeftButton.whenPressed(new SetLiftTarget(100.0, 1.0));
//	    bottomLeftButton.whenPressed(new SetLiftTarget(0.0, 1.0));
//	    
//	    // use button 7 on bottom button array to calibrate lift
//	    bottomButton7.whenPressed(new CalibrateLift());
//	    
//	    // use button 11 to activate the macro to add a crate to the stack
//	    bottomButton11.whenPressed(new PickUpNewCrate());
//	    
//	    // use button 12 to toggle high speed mode on the chassis
//	    bottomButton12.whenPressed(new SetDriveSpeed(1.0));
//	    bottomButton12.whenReleased(new SetDriveSpeed(RobotMap.DEFAULT_SPEED_REDUCTION));

	    /**
	     * WHILE HELD COMMANDS
	     * Run the command while the button is being held down and interrupt it once
	     * the button is released.
	     */
	    // raise and lower the lift at the speed set by the throttle
	    manualUpThrottle.whenPressed(new MoveLiftByThrottle("UP"));
	    manualUpThrottle.whenReleased(new MoveLiftByThrottle("STOP"));
	    manualDownThrottle.whenPressed(new MoveLiftByThrottle("DOWN"));
	    manualDownThrottle.whenReleased(new MoveLiftByThrottle("STOP"));
	    
		// raise and lower the lift manually
//	    manualUp.whileHeld(new RaiseLift(1.0));
//	    manualDown.whileHeld(new LowerLift(1.0));
	    
	    // push and pull the feeders
	    feederPush.whileHeld(new SetFeederWheels(true, 1.0));
	    feederPull.whileHeld(new SetFeederWheels(true, -1.0));
	    
	    // use feeders correlated with driving
//	    feedersPerDriving.whenPressed(new FeedersPerDriving(true));
//	    feedersPerDriving.whenReleased(new FeedersPerDriving(false));
	    
	    // Start the command when the button is released  and let it run the command
	    // until it is finished as determined by it's isFinished method.
	    // button.whenReleased(new ExampleCommand());
    }
}
