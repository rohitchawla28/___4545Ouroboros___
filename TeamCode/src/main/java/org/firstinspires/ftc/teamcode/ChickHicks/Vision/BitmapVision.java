package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import android.graphics.Bitmap;
import android.graphics.Color;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static android.graphics.Color.red;
import static android.graphics.Color.green;
import static android.graphics.Color.blue;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.concurrent.BlockingQueue;


public class BitmapVision {

    private final int RED_VAL = 244;
    private final int GREEN_VAL = 218;
    private final int BLUE_VAL = 59;

    private LinearOpMode opMode;

    VuforiaLocalizer vuforia;
    private VuforiaLocalizer.Parameters parameters;

    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

    private VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.BACK;

    private BlockingQueue<VuforiaLocalizer.CloseableFrame> frame;

    String bitMapCubePosition;

    public BitmapVision (LinearOpMode opMode) {
        this.opMode = opMode;

        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;
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
        return imageBitmap;
    }

    public String sample() throws InterruptedException{
        Bitmap bitmap = getBitmap();

        for (int x = 0; x < bitmap.getHeight(); x++) {
            for (int y = 0; y < bitmap.getWidth(); y++) {
                int pixel = bitmap.getPixel(x, y);

                int R = red(pixel);
                int G = green(pixel);
                int B = blue(pixel);

                if ((R <= RED_VAL + 10) && (R >= RED_VAL - 10) && (G <= GREEN_VAL + 10) && (G >= GREEN_VAL - 10)
                        && (B >= BLUE_VAL - 10)) {
                    bitMapCubePosition = (x >= 1450 && x <= 1600)? "center"
                            : (x >= 225 && x <= 450)? "left"
                           : "right";
                }
            }
        }
        return bitMapCubePosition;
    }

    public Bitmap vufConvertToBitmap(Frame frame) { return vuforia.convertFrameToBitmap(frame); }


}
