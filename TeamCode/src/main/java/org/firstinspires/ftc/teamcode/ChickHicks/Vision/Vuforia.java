//package org.firstinspires.ftc.teamcode.ChickHicks.Vision;
//
//import android.graphics.Bitmap;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.vuforia.Frame;
//import com.vuforia.Image;
//import com.vuforia.PIXEL_FORMAT;
//
//import org.firstinspires.ftc.robotcore.external.ClassFactory;
//import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
//import org.opencv.android.Utils;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//
//import java.util.NoSuchElementException;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorCompletionService;
//
//
//public class Vuforia {
//
//    private LinearOpMode opMode;
//
//    VuforiaLocalizer vuforia;
//    private VuforiaLocalizer.Parameters parameters;
//
//    private static final String VUFORIA_KEY = "AVVhjKz/////AAABmSUTzsySy0l7nvSEO4/tklwYBNqq5gvpXVF2MnqOW+ENbnMruZbOaQHckDCi0mP" +
//            "F9M2LoOcqBnpHHowbYKy1ODgbLMmmwSLH4UZyYTGCcwEN6s+xfz6Sd35BQxcEJQ77zhY9ZklFWKnxZdIDYl11XkIQK+Yi2iU+zpKp" +
//            "5YTWCzOW570bcIdyPc0G/KTLWrxBrAOBIZu68HntklnQKvkciI6FQCN9n3T6MlqBDbx3SxXqaFALXWhZvbIs1cryGvmWSekjB9MeH" +
//            "642rZwSdLyOzQGa7xwcZGbek2Fj94vxmKaq8wTCz3hR0eBSqCVD22RPGMcJW8VoWz2+xVim2XS7A5XmbROKKZPNz95Xbfvt0eMb";
//
//    private VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.BACK;
//
//    private BlockingQueue<VuforiaLocalizer.CloseableFrame> frame;
//
//    public Vuforia (LinearOpMode opMode) throws InterruptedException {
//
//        this.opMode = opMode;
//
//        int cameraMonitorViewId = this.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", this.opMode.hardwareMap.appContext.getPackageName());
//        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
//        parameters.vuforiaLicenseKey = VUFORIA_KEY;
//        parameters.cameraDirection = CAMERA_CHOICE;
//
//        vuforia = ClassFactory.getInstance().createVuforia(parameters);
//    }
//
//    public Bitmap convertToBitmap()throws InterruptedException{
//        Image rgb = null;
//        com.vuforia.Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
//        Bitmap imageBitmap;
//        vuforia.setFrameQueueCapacity(1);
//        vuforia.enableConvertFrameToBitmap();
//        VuforiaLocalizer.CloseableFrame picture;
//
//        opMode.telemetry.addLine("Starting Bitmap Conversion");
//        opMode.telemetry.update();
//
//
//        frame = vuforia.getFrameQueue();
//
//        picture = frame.take();
//
//        long numImages = picture.getNumImages();
//        //int screenshot = vuforia.getFrameQueueCapacity() - 1;
//
//        // basically get the number of formats for this frame
//        //long numImages = frame.getNumImages();
//
//        // set rgb object if one of the formats is RGB565
//        //if(frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
//        //    rgb = frame.getImage(i);
//        //}
//
//
//        //if(rgb == null) {
//
//          //  count++;
//           // convertToBitmap();
//        //}
////        opMode.telemetry.addLine("here");
////        opMode.telemetry.update();
//            for (int i = 0; i < numImages; i++) {
//                if (picture.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
//                    rgb = picture.getImage(i);
//                    break;
//                }
//            }
//
//
//        // create a new bitmap and copy the byte buffer returned by rgb.getPixels() to it
//        imageBitmap = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
//        imageBitmap.copyPixelsFromBuffer(rgb.getPixels());
//
//        picture.close();
//        return imageBitmap;
//    }
//
//    //specific method from vuforia api to convert to bitmap
//    //currently not using
//    public Bitmap vufConvertToBitmap(Frame frame) {
//
//        return vuforia.convertFrameToBitmap(frame);
//    }
//
//    public Mat convertToMat() throws InterruptedException{
//
//        // construct an OpenCV mat from the bitmap using Utils.bitmapToMat()
//        Mat imageMat = new Mat(convertToBitmap().getWidth(), convertToBitmap().getHeight(), CvType.CV_8UC4);
//
//        Utils.bitmapToMat(convertToBitmap(), imageMat);
//
//        opMode.telemetry.addLine("Finished Mat Conversion");
//        opMode.telemetry.update();
//
//        return imageMat;
//
//    }
//}
