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

import java.util.concurrent.BlockingQueue;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;


public class BitmapVisionWC {

    private LinearOpMode opMode;
    private VuforiaLocalizer vuforia;
    private Parameters parameters;

    private final int RED_THRESHOLD = 244;
    private final int GREEN_THRESHOLD = 218;
    private final int BLUE_THRESHOLD = 59;

    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

    private BlockingQueue<VuforiaLocalizer.CloseableFrame> frame;

    private WebcamName webcam;

    public static String bitmapCubePosition;

    public BitmapVisionWC(LinearOpMode opMode) {
        this.opMode = opMode;

        webcam = this.opMode.hardwareMap.get(WebcamName.class, "Webcam 1");

        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());

        bitmapCubePosition = "";

        parameters = new Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true); //enables RGB565 format for the image
        vuforia.setFrameQueueCapacity(4); //tells VuforiaLocalizer to only store one frame at a time
        vuforia.enableConvertFrameToBitmap();

    }

    public Bitmap getBitmap() throws InterruptedException{
        VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
        Image rgb = frame.getImage(1);

        long numImages = frame.getNumImages();

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

        // create a new bitmap and copy the byte buffer returned by rgb.getPixels() to it
        Bitmap bm = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        bm.copyPixelsFromBuffer(rgb.getPixels());

        frame.close();

        opMode.telemetry.addLine("Got Bitmap");
        opMode.telemetry.update();

        return bm;
    }

    public String sample() throws InterruptedException{
        Bitmap bitmap = getBitmap();

        for (int colNum = 0; colNum < bitmap.getWidth(); colNum+= 2) {

            for (int rowNum = ((bitmap.getHeight() * 2) / 3); rowNum >= 0; rowNum-= 3) {
                int pixel = bitmap.getPixel(colNum, rowNum);

                int redPixel = red(pixel);
                int greenPixel = green(pixel);
                int bluePixel = blue(pixel);

                opMode.telemetry.addData("red val", redPixel);
                opMode.telemetry.addData("blue val", bluePixel);
                opMode.telemetry.addData("green val", greenPixel);
                opMode.telemetry.update();

                if ((redPixel <= RED_THRESHOLD + 10) && (redPixel >= RED_THRESHOLD - 10) && (greenPixel <= GREEN_THRESHOLD + 10) && (greenPixel >= GREEN_THRESHOLD - 10) && (bluePixel >= BLUE_THRESHOLD - 10)) {
                    bitmapCubePosition = (colNum <= (bitmap.getWidth() / 3)) ? "left"
                            : (colNum > ((bitmap.getWidth() / 3) + 150) && colNum < ((bitmap.getWidth() * 2) / 3))  ? "center"
                            : "right";

                    opMode.telemetry.addData("Cube Pos", bitmapCubePosition);
                    opMode.telemetry.update();

                    break;

                }

            }

        }
        opMode.telemetry.addData("Cube Position", bitmapCubePosition);
        opMode.telemetry.update();

        return bitmapCubePosition;

    }


    public Bitmap vufConvertToBitmap(Frame frame) { return vuforia.convertFrameToBitmap(frame); }


}
