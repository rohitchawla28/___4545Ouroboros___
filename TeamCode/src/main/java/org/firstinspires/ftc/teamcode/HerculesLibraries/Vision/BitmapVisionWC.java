package org.firstinspires.ftc.teamcode.HerculesLibraries.Vision;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;


public class BitmapVisionWC {

    private LinearOpMode opMode;

    // importing vuforia class for taking an image
    private VuforiaLocalizer vuforia;

    // variable for the gold cube's position
    private String cubePos;

    // RGB values for finding yellow pixels
    private final int RED_THRESHOLD = 140;
    private final int GREEN_THRESHOLD = 100;
    private final int BLUE_THRESHOLD = 60;

    // constructor turns on webcam imaging and sets capacity and format for frame
    public BitmapVisionWC(LinearOpMode opMode) {
        this.opMode = opMode;
        cubePos = "";

        // variable allows image to show up on robot controller phone
        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // original vuforia license key
        parameters.vuforiaLicenseKey = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

        // hardware mapping of webcam device
        parameters.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");

        // start vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // set RGB format to 565
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);

        // allowing the frame to only be 4 images at a time
        vuforia.setFrameQueueCapacity(4);

    }

    public Bitmap getBitmap() throws InterruptedException{
        // method to actually capture frame
        VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
        Image rgb = frame.getImage(1);

        long numImages = frame.getNumImages();

        // go through all images taken in frame and find ones that match correct format
        for (int i = 0; i < numImages; i++) {
            int fmt = frame.getImage(i).getFormat();

            if (fmt == PIXEL_FORMAT.RGB565) {
                rgb = frame.getImage(i);
                break;

            }
            else {
                opMode.telemetry.addLine("Didn't find correct rgb format");
                opMode.telemetry.update();

            }

        }

        // create bitmap
        Bitmap bm = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        bm.copyPixelsFromBuffer(rgb.getPixels());

        frame.close();

        opMode.telemetry.addLine("Got Bitmap");
        opMode.telemetry.update();

        opMode.sleep(500);

        return bm;
    }

    public String sample() throws InterruptedException {
        Bitmap bitmap = getBitmap();
        ArrayList<Integer> xValues = new ArrayList<>();

        int avgX = 0;

        // top left = (0,0)

        // scan all columns
        for (int colNum = 0; colNum < bitmap.getWidth(); colNum ++) {

            // scan rows 120-240 to block out cubes from crater
            for (int rowNum = 120 ; rowNum < 240; rowNum ++) {
                int pixel = bitmap.getPixel(colNum, rowNum);

                // receive R, G, and B values for each pixel
                int redPixel = red(pixel);
                int greenPixel = green(pixel);
                int bluePixel = blue(pixel);

                // only add x-coordinates of yellow pixels to list
                if (redPixel >= RED_THRESHOLD && greenPixel >= GREEN_THRESHOLD && bluePixel <= BLUE_THRESHOLD) {
                    xValues.add(colNum);

                }

            }

        }

        // get sum of all yellow pixels' x coordinates
        for (int x : xValues) {
            avgX+= x;

        }

        opMode.telemetry.addData("Num Pixels found", xValues.size());

        // get average x-coordinate value of all yellow pixels
        try {
            avgX /= xValues.size();
        } catch (ArithmeticException E) {
            cubePos = "right";

        }

        opMode.telemetry.addData("AVG X = ", avgX);

        // use constants (235, 500) to determine which third of the image cube is in
        if (avgX < 235) {
            cubePos = "left";

        }
        else if (avgX < 500) {
            cubePos = "center";

        }
        else {
            cubePos = "right";

        }
        opMode.telemetry.addData("Cube Position", cubePos);
        opMode.telemetry.update();

        return cubePos;

    }


    public Bitmap vufConvertToBitmap(Frame frame) { return vuforia.convertFrameToBitmap(frame); }
}
