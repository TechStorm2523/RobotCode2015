
package org.usfirst.frc.team2523.robot.subsystems;

import org.usfirst.frc.team2523.robot.RobotMap;
import org.usfirst.frc.team2523.robot.commands.DrawBarsOnCamera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class handling all robot camera functions
 */
public class Camera extends Subsystem {
    // define variables
	public int session;
    public Image frame;

    public Camera() 
    {
    	// define frame as RGB with no border
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // get session (the camera name (ex "cam0") can be found through the roborio web interface)
        session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        
        // set up session
        NIVision.IMAQdxConfigureGrab(session);
    }
   
    /**
     * Handles the main camera setup and draws a single vertical bar for crate alignment
     */
    public void drawBar() 
    {
        /**
         * grab an image, draw the bar, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
    	// create left rectangle canvas (top padding, left padding, height, width)
        NIVision.Rect leftRect = new NIVision.Rect(0, RobotMap.CAMERA_WIDTH / 2 - RobotMap.CAMERA_BAR_WIDTH / 2,
        											RobotMap.CAMERA_HEIGHT, RobotMap.CAMERA_BAR_WIDTH);
        
        // grab image and frame
        NIVision.IMAQdxGrab(session, frame, 1);
        
        // draw shape on this frame using the rectangles made
        NIVision.imaqDrawShapeOnImage(frame, frame, leftRect, DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
        
        // set the image
        CameraServer.getInstance().setImage(frame);
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DrawBarsOnCamera());
    }
}

