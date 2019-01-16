package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;


public class BitmapVisionWC {

    private LinearOpMode opMode;
    private VuforiaLocalizer vuforia;
    private Parameters parameters;
    // private CameraDirection CAMERA_CHOICE = CameraDirection.BACK;

    private final int RED_VAL = 244;
    private final int GREEN_VAL = 218;
    private final int BLUE_VAL = 59;

    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

    private BlockingQueue<VuforiaLocalizer.CloseableFrame> frame;

    private WebcamName webcam;

    public static String bitmapCubePosition;

    public BitmapVisionWC(LinearOpMode opMode) {
        this.opMode = opMode;

        webcam = this.opMode.hardwareMap.get(WebcamName.class, "Webcam");

        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
        parameters = new Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        // parameters.cameraDirection = CAMERA_CHOICE;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    public Bitmap getBitmap() throws InterruptedException{
        Image rgb = null;
        com.vuforia.Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        Bitmap imageBitmap;
        vuforia.setFrameQueueCapacity(1);
        vuforia.enableConvertFrameToBitmap();
        VuforiaLocalizer.CloseableFrame picture;

        frame = vuforia.getFrameQueue();

        picture = frame.take();

        long numImages = picture.getNumImages();
        for (int i = 0; i < numImages; i++) {
            if (picture.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                rgb = picture.getImage(i);
                break;
            }

        }

        // create a new bitmap and copy the byte buffer returned by rgb.getPixels() to it
        imageBitmap = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        imageBitmap.copyPixelsFromBuffer(rgb.getPixels());

        picture.close();

        opMode.telemetry.addLine("Got bitmap");
        opMode.telemetry.update();

        return imageBitmap;
    }

    public String sample() throws InterruptedException{
        Bitmap bitmap = getBitmap();
        for (int x = 0; x < bitmap.getHeight(); x++) {


            for (int y = 0; y < bitmap.getWidth(); y++) {

                int pixel = bitmap.getPixel(y, x);

                int R = red(pixel);
                int G = green(pixel);
                int B = blue(pixel);

                if ((R <= RED_VAL + 10) && (R >= RED_VAL - 10) && (G <= GREEN_VAL + 10) && (G >= GREEN_VAL - 10) && (B >= BLUE_VAL - 10)) {
                    bitmapCubePosition = (x >= 1450 && x <= 1600) ? "center"
                                        : (x >= 225 && x <= 450)  ? "left"
                                        : "right";
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
