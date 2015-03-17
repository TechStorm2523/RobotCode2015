package org.usfirst.frc.team2523.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2523.robot.commands.CalibrateLift;
import org.usfirst.frc.team2523.robot.commands.CloseClaw;
import org.usfirst.frc.team2523.robot.commands.IncrementLiftLevel;
import org.usfirst.frc.team2523.robot.commands.MoveLiftByThrottle;
import org.usfirst.frc.team2523.robot.commands.OpenClaw;
import org.usfirst.frc.team2523.robot.commands.PickUpNewCrate;
import org.usfirst.frc.team2523.robot.commands.SetDriveSpeed;
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
    Button primaryTriggerButton = new JoystickButton(RobotMap.primaryStick, 1);
    Button primaryThumbButton = new JoystickButton(RobotMap.primaryStick, 2);
    Button primaryTopLeftButton = new JoystickButton(RobotMap.primaryStick, 5);
    Button primaryBottomLeftButton = new JoystickButton(RobotMap.primaryStick, 3);
    Button primaryTopRightButton = new JoystickButton(RobotMap.primaryStick, 6);
    Button primaryBottomRightButton = new JoystickButton(RobotMap.primaryStick, 4);
    
	/*
	 * Second Driver (Secondary) Joystick
	 */
    
    Button secondaryTriggerButton = new JoystickButton(RobotMap.secondaryStick, 1);
    Button secondaryThumbButton = new JoystickButton(RobotMap.secondaryStick, 2);
    Button secondaryTopLeftButton = new JoystickButton(RobotMap.secondaryStick, 5);
    Button secondaryBottomLeftButton = new JoystickButton(RobotMap.secondaryStick, 3);
    Button secondaryTopRightButton = new JoystickButton(RobotMap.secondaryStick, 6);
    Button secondaryBottomRightButton = new JoystickButton(RobotMap.secondaryStick, 4);
    Button secondaryBottomButton7 = new JoystickButton(RobotMap.secondaryStick, 7);
    Button secondaryBottomButton9 = new JoystickButton(RobotMap.secondaryStick, 9);
    Button secondaryBottomButton10 = new JoystickButton(RobotMap.secondaryStick, 10);
    Button secondaryBottomButton11 = new JoystickButton(RobotMap.secondaryStick, 11);
    Button secondaryBottomButton12 = new JoystickButton(RobotMap.secondaryStick, 12);
    
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
    	// use the trigger and thumb buttons of SECONDARY to control the closing and opening of the claw, respectively
		secondaryTriggerButton.whenPressed(new CloseClaw());
		secondaryThumbButton.whenPressed(new OpenClaw());
    	
		// use top and bottom left buttons to set lift target to top and bottom, respectively
		secondaryTopLeftButton.whenPressed(new IncrementLiftLevel(1, 1.0));
		secondaryBottomLeftButton.whenPressed(new IncrementLiftLevel(-1, 1.0));
		
		// use buttons 11 and 12 to move the lift to the absolute bottom and top
		secondaryBottomButton12.whenPressed(new SetLiftTarget(RobotMap.MAX_LIFT_HEIGHT, 1.0));
		secondaryBottomButton11.whenPressed(new SetLiftTarget(0.0, 1.0));
		
	    // use button 7 on SECONDARY bottom button array to calibrate lift
	    secondaryBottomButton7.whenPressed(new CalibrateLift());
	    
	    // use buttons 9 and 10 activate the macro to add a crate to the stack, at level 1 and level 2 respectively
	    secondaryBottomButton9.whenPressed(new PickUpNewCrate(1));
	    secondaryBottomButton10.whenPressed(new PickUpNewCrate(2));
		
	    // use the PRIMARY trigger to toggle high speed mode on the chassis
	    primaryTriggerButton.whenPressed(new SetDriveSpeed(1.0));
	    primaryTriggerButton.whenReleased(new SetDriveSpeed(RobotMap.DEFAULT_SPEED_REDUCTION));
    	
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
	     * WHILE PRESSED COMMANDS
	     * Run the command while the button is being held down and interrupt it once
	     * the button is released.
	     */
		// use the top and bottom left buttons on top of the SECONDARY joystick to raise and lower (respectively) the lift at the speed set by the throttle
	    secondaryTopRightButton.whileHeld(new MoveLiftByThrottle("UP"));
	    secondaryBottomRightButton.whileHeld(new MoveLiftByThrottle("DOWN"));
	    
	    // TEMP
		// use the top and bottom left buttons on top of the PRIMARY joystick to raise and lower (respectively) the lift at the speed set by the throttle
	    primaryTopRightButton.whileHeld(new MoveLiftByThrottle("UP"));
	    primaryBottomRightButton.whileHeld(new MoveLiftByThrottle("DOWN"));
	    
	    // Start the command when the button is released  and let it run the command
	    // until it is finished as determined by it's isFinished method.
	    // button.whenReleased(new ExampleCommand());
    }
}
