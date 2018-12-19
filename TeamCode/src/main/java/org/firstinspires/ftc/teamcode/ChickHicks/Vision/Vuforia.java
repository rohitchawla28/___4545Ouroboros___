package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import android.graphics.Bitmap;

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


public class Vuforia {

    private LinearOpMode opMode;

    VuforiaLocalizer vuforia;
    private VuforiaLocalizer.Parameters parameters;

    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

    private VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.BACK;

    private BlockingQueue<VuforiaLocalizer.CloseableFrame> frame;

    public Vuforia (LinearOpMode opMode) throws InterruptedException {

        this.opMode = opMode;

        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    public Bitmap convertToBitmap() throws InterruptedException{
        Image rgb = null;
        com.vuforia.Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        Bitmap imageBitmap;
        vuforia.setFrameQueueCapacity(1);
        vuforia.enableConvertFrameToBitmap();
        VuforiaLocalizer.CloseableFrame picture;

        frame = vuforia.getFrameQueue();

        picture = frame.take();

        long numImages = picture.getNumImages();
        //int screenshot = vuforia.getFrameQueueCapacity() - 1;

        // basically get the number of formats for this frame
        //long numImages = frame.getNumImages();

        // set rgb object if one of the formats is RGB565
        //if(frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
        //    rgb = frame.getImage(i);
        //}

        //if(rgb == null) {

          //  count++;
           // convertToBitmap();
        //}
//        opMode.telemetry.addLine("here");
//        opMode.telemetry.update();
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

    //specific method from vuforia api to convert to bitmap
    //currently not using
    public Bitmap vufConvertToBitmap(Frame frame) {

        return vuforia.convertFrameToBitmap(frame);
    }

    public Mat convertToMat() throws InterruptedException{

        // construct an OpenCV mat from the bitmap using Utils.bitmapToMat()
        Mat imageMat = new Mat(convertToBitmap().getWidth(), convertToBitmap().getHeight(), CvType.CV_8UC4);
        Utils.bitmapToMat(convertToBitmap(), imageMat);
        return imageMat;

    }
}
