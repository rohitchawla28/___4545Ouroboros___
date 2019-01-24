package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import android.graphics.Bitmap;

import static android.graphics.Color.red;
import static android.graphics.Color.green;
import static android.graphics.Color.blue;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Frame;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.Parameters;

import java.util.concurrent.BlockingQueue;


public class BitmapVision {

    private LinearOpMode opMode;
    private VuforiaLocalizer vuforia;
    private Parameters parameters;
    private CameraDirection CAMERA_CHOICE = CameraDirection.BACK;

    private final int RED_THRESHOLD = 244;
    private final int GREEN_THRESHOLD = 218;
    private final int BLUE_THRESHOLD = 59;

    private static final String VUFORIA_KEY = "AU2n8aH/////AAABmYrAj+Z2rkB2q3LKkJDH3r0CozVgynwwk40JfnKP/wpamF0Km5t4Nza3w/SPNBs6ghM5D+mOgyGRJp9q8gPAeYI8p/c4iSzQ9yjX23yyDv3aqHfC6yFAy41Uz1C98mOcDeEHkyl1Bgc7k/YO3Ci6FDFzL6irifQ/Hpud9d4D7F1+y9KVuB3vd+xp7AG2r2OpJRvgrYi9PJ4MPNuddhhZovf37Dq58FkYlsIk67i/KK8WISyPE4jKwbZtBUmrQZxnxoBKvTqkRMV2T/MhezcOjJXfAdkH2/MLuIAJx4KR5cvbr/97g90njxKHR+pWHMQyqk46g9UYO4fPbCb8vJDzrG9Dsfj8mNDGceuYfhZyYwu3";

    private BlockingQueue<VuforiaLocalizer.CloseableFrame> frame;

    public static String bitmapCubePosition;

    public BitmapVision (LinearOpMode opMode) {
//        this.opMode = opMode;
//
//        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
//        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//        parameters.vuforiaLicenseKey = VUFORIA_KEY;
//        parameters.cameraDirection = CAMERA_CHOICE;
//        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        this.opMode = opMode;

        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        params.vuforiaLicenseKey = VUFORIA_KEY;
        params.cameraDirection = CAMERA_CHOICE;
        vuforia = ClassFactory.getInstance().createVuforia(params);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true); //enables RGB565 format for the image
        vuforia.setFrameQueueCapacity(4); //tells VuforiaLocalizer to only store one frame at a time
        vuforia.enableConvertFrameToBitmap();

    }

    private Bitmap getBitmap2() throws InterruptedException{
        VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
        Image rgb = null;

        long numImages = frame.getNumImages();

        for (int i = 0; i < numImages; i++) {
            Image img = frame.getImage(i);

            int fmt = img.getFormat();

            if (fmt == PIXEL_FORMAT.RGB565) {
                rgb = frame.getImage(i);
                break;

            }

        }
        Bitmap bm = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        bm.copyPixelsFromBuffer(rgb.getPixels());

        frame.close();

        opMode.telemetry.addLine("Got Bitmap");
        opMode.telemetry.update();

        return bm;

    }

    public Bitmap getBitmap() throws InterruptedException{

        VuforiaLocalizer.CloseableFrame picture;
        picture = vuforia.getFrameQueue().take();
        Image rgb = picture.getImage(1);

        long numImages = picture.getNumImages();

        opMode.telemetry.addData("Num Images", numImages);
        opMode.telemetry.update();

        for (int i = 0; i < numImages; i++) {
            int format = picture.getImage(i).getFormat();
            if (format == PIXEL_FORMAT.RGB565) {
                rgb = picture.getImage(i);
                break;

            }

            else {
                opMode.telemetry.addLine("Didn't find correct RGB format");
                opMode.telemetry.update();


            }

        }

        Bitmap imageBitmap = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        imageBitmap.copyPixelsFromBuffer(rgb.getPixels());

        opMode.telemetry.addData("Image width", imageBitmap.getWidth());
        opMode.telemetry.addData("Image height", imageBitmap.getHeight());
        opMode.telemetry.update();


        picture.close();

        opMode.telemetry.addLine("Got bitmap");
        opMode.telemetry.update();

        return imageBitmap;
    }

    public String sample() throws InterruptedException{
        Bitmap bitmap = getBitmap();

        for (int colNum = 0; colNum < bitmap.getWidth(); colNum+=2) {

            for (int rowNum = (int)(bitmap.getHeight() *(1.0/6)); rowNum < bitmap.getHeight(); rowNum+= 3) {
                int pixel = bitmap.getPixel(colNum, rowNum);

                int redPixel = red(pixel);
                int greenPixel = green(pixel);
                int bluePixel = blue(pixel);

                opMode.telemetry.addData("red val", redPixel);
                opMode.telemetry.addData("blue val", bluePixel);
                opMode.telemetry.addData("green val", greenPixel);
                opMode.telemetry.update();

                if ((redPixel <= RED_THRESHOLD + 10) && (redPixel >= RED_THRESHOLD - 10) && (greenPixel <= GREEN_THRESHOLD + 10) && (greenPixel >= GREEN_THRESHOLD - 10) && (bluePixel >= BLUE_THRESHOLD - 10)) {
                    bitmapCubePosition = (colNum >= 1450 && colNum <= 1600) ? "center"
                                        : (colNum >= 225 && colNum <= 450)  ? "left"
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
