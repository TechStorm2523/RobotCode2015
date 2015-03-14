package org.usfirst.frc.team2523.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2523.robot.commands.CalibrateLift;
import org.usfirst.frc.team2523.robot.commands.CancelLiftMovement;
import org.usfirst.frc.team2523.robot.commands.CloseClaw;
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
    Button triggerButton = new JoystickButton(RobotMap.primaryStick, 1);
    Button thumbButton = new JoystickButton(RobotMap.primaryStick, 2);
    Button topLeftButton = new JoystickButton(RobotMap.primaryStick, 5);
    Button bottomLeftButton = new JoystickButton(RobotMap.primaryStick, 3);
    Button topRightButton = new JoystickButton(RobotMap.primaryStick, 6);
    Button bottomRightButton = new JoystickButton(RobotMap.primaryStick, 4);
    Button bottomButton7 = new JoystickButton(RobotMap.primaryStick, 7);
    Button bottomButton11 = new JoystickButton(RobotMap.primaryStick, 11);
    Button bottomButton12 = new JoystickButton(RobotMap.primaryStick, 12);
    
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
    	// use the trigger and thumb buttons to control the closing and opening of the claw, respectively
		//triggerButton.whenPressed(new CloseClaw());
		//thumbButton.whenPressed(new OpenClaw());
		
		// use top and bottom left buttons to set lift target to top and bottom, respectively
	    topLeftButton.whenPressed(new SetLiftTarget(100.0, 1.0));
	    bottomLeftButton.whenPressed(new SetLiftTarget(0.0, 1.0));
	    
	    // use button 7 on bottom button array to calibrate lift
	    bottomButton7.whenPressed(new CalibrateLift());
	    
	    // use button 11 to activate the macro to add a crate to the stack
	    bottomButton11.whenPressed(new PickUpNewCrate());
	    
	    // use button 12 to toggle high speed mode on the chassis
	    bottomButton12.whenPressed(new SetDriveSpeed(1.0));
	    bottomButton12.whenReleased(new SetDriveSpeed(RobotMap.DEFAULT_SPEED_REDUCTION));

	    /**
	     * WHILE PRESSED COMMANDS
	     * Run the command while the button is being held down and interrupt it once
	     * the button is released.
	     */
		// use the top and bottom left buttons on top of the joystick to raise and lower (respectively) the lift at the speed set by the throttle
	    topRightButton.whileHeld(new MoveLiftByThrottle("UP"));
	    bottomRightButton.whileHeld(new MoveLiftByThrottle("DOWN"));
	    
	    // Start the command when the button is released  and let it run the command
	    // until it is finished as determined by it's isFinished method.
	    // button.whenReleased(new ExampleCommand());
    }
}
