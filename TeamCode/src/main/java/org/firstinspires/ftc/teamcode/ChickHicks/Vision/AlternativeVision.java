package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.opencv.core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.*;


/**
 * AlternativeVision class.
 *
 * An OpenCV pipeline generated by GRIP.
 * Updated and Edited by 4545Ouroborous
 *
 * @author GRIP
 */
public class AlternativeVision  {

    private LinearOpMode opMode;
    private Vuforia vuforia;
    private int recCount;
    // Cube Position
    public static String cubePosition3;
    //Outputs
    private Mat hsvThresholdOutput;
    private Mat blur0Output;
    private Mat cvErodeOutput;
    private Mat blur1Output;
    private Mat maskOutput;
    private MatOfKeyPoint findBlobsOutput;

    private final double LEFT_CONST = 1300.0;
    private final double RIGHT_CONST = 200.0;
    private final double MIDDLE_CONST = 600.0 ;

    public AlternativeVision(LinearOpMode opMode, Vuforia vuforia) {
        this.opMode = opMode;
        this.vuforia = vuforia;

        System.loadLibrary("opencv_java3");

        cubePosition3 = null;
        hsvThresholdOutput = new Mat();
        blur0Output = new Mat();
        blur1Output = new Mat();
        cvErodeOutput = new Mat();
        maskOutput = new Mat();
        findBlobsOutput = new MatOfKeyPoint();
    }

    /**
     * This is the primary method that runs the entire pipeline and updates the outputs.
     */
    public void process (Mat matImage) throws InterruptedException {
        while (recCount < 2) {
            // Step HSV_Threshold0:
            Mat hsvThresholdInput = matImage;
            double[] hsvThresholdHue = {0.0, 100.0};
            double[] hsvThresholdSaturation = {100.0, 255.0};
            double[] hsvThresholdValue = {150.0, 255.0};
            hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);

            // Step Blur0:
            Mat blur0Input = hsvThresholdOutput;
            BlurType blur0Type = BlurType.get("Median Filter");
            double blur0Radius = 100.0;
            blur(blur0Input, blur0Type, blur0Radius, blur0Output);

            // Step CV_erode0:
            Mat cvErodeSrc = blur0Output;
            Mat cvErodeKernel = new Mat();
            Point cvErodeAnchor = new Point(-1, -1);
            double cvErodeIterations = 12.0;
            int cvErodeBordertype = Core.BORDER_CONSTANT;
            Scalar cvErodeBordervalue = new Scalar(-1);
            cvErode(cvErodeSrc, cvErodeKernel, cvErodeAnchor, cvErodeIterations, cvErodeBordertype, cvErodeBordervalue, cvErodeOutput);

            opMode.telemetry.addLine("CV Erode finished");
            opMode.telemetry.update();

            // Step Blur1:
            Mat blur1Input = cvErodeOutput;
            BlurType blur1Type = BlurType.get("Median Filter");
            double blur1Radius = 22.5;
            blur(blur1Input, blur1Type, blur1Radius, blur1Output);

            // Step Mask0:
            Mat maskInput = matImage;
            Mat maskMask = blur1Output;
            mask(maskInput, maskMask, maskOutput);

            opMode.telemetry.addLine("Mask finished");
            opMode.telemetry.update();

            // Step Find_Blobs0:
            Mat findBlobsInput = maskOutput;
            double findBlobsMinArea = 1;
            double[] findBlobsCircularity = {0.0, 1.0};
            boolean findBlobsDarkBlobs = false;
            findBlobs(findBlobsInput, findBlobsMinArea, findBlobsCircularity, findBlobsDarkBlobs, findBlobsOutput);

            KeyPoint[] findBlobsArray = findBlobsOutput.toArray();

            try {
                findSampling(findBlobsArray);
                opMode.telemetry.addData("Blobs", findBlobsArray[0]);
                opMode.telemetry.update();
            }catch (ArrayIndexOutOfBoundsException E){

                opMode.telemetry.addLine("OutOfBoundsE - Trying to Scan Again");
                opMode.telemetry.update();
                process(vuforia.convertToMat());
                recCount++;
            }
        }
    }
    private void findSampling(KeyPoint[] array) throws ArrayIndexOutOfBoundsException{
        for (KeyPoint point: array){
                if (point.pt.x <= (LEFT_CONST * 0.80) || point.pt.x >= (LEFT_CONST * 1.20 ))
                    cubePosition3 = "left";
                else if (point.pt.x <= (MIDDLE_CONST* 0.80) || point.pt.x >= (MIDDLE_CONST* 1.20) )
                    cubePosition3 = "middle";
                else if (point.pt.x <= (RIGHT_CONST* 0.80) || point.pt.x >= (RIGHT_CONST* 1.20 ))
                    cubePosition3 = "right";
                else
                    cubePosition3 = "failed";
            }
    }

    /**
     * This method is a generated getter for the output of a HSV_Threshold.
     * @return Mat output from HSV_Threshold.
     */
    public Mat hsvThresholdOutput() {
        return hsvThresholdOutput;
    }

    /**
     * This method is a generated getter for the output of a Blur.
     * @return Mat output from Blur.
     */
    public Mat blur0Output() {
        return blur0Output;
    }

    /**
     * This method is a generated getter for the output of a CV_erode.
     * @return Mat output from CV_erode.
     */
    public Mat cvErodeOutput() { return cvErodeOutput; }

    /**
     * This method is a generated getter for the output of a Blur.
     * @return Mat output from Blur.
     */
    public Mat blur1Output() { return blur1Output; }

    /**
     * This method is a generated getter for the output of a Mask.
     * @return Mat output from Mask.
     */
    public Mat maskOutput() { return maskOutput; }

    /**
     * This method is a generated getter for the output of a Find_Blobs.
     * @return MatOfKeyPoint output from Find_Blobs.
     */
    public MatOfKeyPoint findBlobsOutput() { return findBlobsOutput; }


    /**
     * Segment an image based on hue, saturation, and value ranges.
     *
     * @param input The image on which to perform the HSL threshold.
     * @param hue The min and max hue
     * @param sat The min and max saturation
     * @param val The min and max value
     */
    private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val,
                              Mat out) {
        Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
        Core.inRange(out, new Scalar(hue[0], sat[0], val[0]),
                new Scalar(hue[1], sat[1], val[1]), out);
    }

    /**
     * Expands area of lower value in an image.
     * @param src the Image to erode.
     * @param kernel the kernel for erosion.
     * @param anchor the center of the kernel.
     * @param iterations the number of times to perform the erosion.
     * @param borderType pixel extrapolation method.
     * @param borderValue value to be used for a constant border.
     * @param dst Output Image.
     */
    private void cvErode(Mat src, Mat kernel, Point anchor, double iterations,
                         int borderType, Scalar borderValue, Mat dst) {
        if (kernel == null) {
            kernel = new Mat();
        }
        if (anchor == null) {
            anchor = new Point(-1,-1);
        }
        if (borderValue == null) {
            borderValue = new Scalar(-1);
        }
        Imgproc.erode(src, dst, kernel, anchor, (int)iterations, borderType, borderValue);
    }

    /**
     * An indication of which type of filter to use for a blur.
     * Choices are BOX, GAUSSIAN, MEDIAN, and BILATERAL
     */
    enum BlurType{
        BOX("Box Blur"), GAUSSIAN("Gaussian Blur"), MEDIAN("Median Filter"),
        BILATERAL("Bilateral Filter");

        private final String label;

        BlurType(String label) {
            this.label = label;
        }

        public static BlurType get(String type) {
            if (BILATERAL.label.equals(type)) {
                return BILATERAL;
            }
            else if (GAUSSIAN.label.equals(type)) {
                return GAUSSIAN;
            }
            else if (MEDIAN.label.equals(type)) {
                return MEDIAN;
            }
            else {
                return BOX;
            }
        }

        @Override
        public String toString() {
            return this.label;
        }
    }

    /**
     * Softens an image using one of several filters.
     * @param input The image on which to perform the blur.
     * @param type The blurType to perform.
     * @param doubleRadius The radius for the blur.
     * @param output The image in which to store the output.
     */
    private void blur(Mat input, BlurType type, double doubleRadius,
                      Mat output) {
        int radius = (int)(doubleRadius + 0.5);
        int kernelSize;
        switch(type){
            case BOX:
                kernelSize = 2 * radius + 1;
                Imgproc.blur(input, output, new Size(kernelSize, kernelSize));
                break;
            case GAUSSIAN:
                kernelSize = 6 * radius + 1;
                Imgproc.GaussianBlur(input,output, new Size(kernelSize, kernelSize), radius);
                break;
            case MEDIAN:
                kernelSize = 2 * radius + 1;
                Imgproc.medianBlur(input, output, kernelSize);
                break;
            case BILATERAL:
                Imgproc.bilateralFilter(input, output, -1, radius, radius);
                break;
        }
    }

    /**
     * Filter out an area of an image using a binary mask.
     * @param input The image on which the mask filters.
     * @param mask The binary image that is used to filter.
     * @param output The image in which to store the output.
     */
    private void mask(Mat input, Mat mask, Mat output) {
        mask.convertTo(mask, CvType.CV_8UC1);
        Core.bitwise_xor(output, output, output);
        input.copyTo(output, mask);
    }

    /**
     * Detects groups of pixels in an image.
     * @param input The image on which to perform the find blobs.
     * @param minArea The minimum size of a blob that will be found
     * @param circularity The minimum and maximum circularity of blobs that will be found
     * @param darkBlobs The boolean that determines if light or dark blobs are found.
     * @param blobList The output where the MatOfKeyPoint is stored.
     */
    private void findBlobs(Mat input, double minArea, double[] circularity,
                           Boolean darkBlobs, MatOfKeyPoint blobList) {
        FeatureDetector blobDet = FeatureDetector.create(FeatureDetector.SIMPLEBLOB);
        try {
            File tempFile = File.createTempFile("config", ".xml");

            StringBuilder config = new StringBuilder();

            config.append("<?xml version=\"1.0\"?>\n");
            config.append("<opencv_storage>\n");
            config.append("<thresholdStep>10.</thresholdStep>\n");
            config.append("<minThreshold>50.</minThreshold>\n");
            config.append("<maxThreshold>220.</maxThreshold>\n");
            config.append("<minRepeatability>2</minRepeatability>\n");
            config.append("<minDistBetweenBlobs>10.</minDistBetweenBlobs>\n");
            config.append("<filterByColor>1</filterByColor>\n");
            config.append("<blobColor>");
            config.append((darkBlobs ? 0 : 255));
            config.append("</blobColor>\n");
            config.append("<filterByArea>1</filterByArea>\n");
            config.append("<minArea>");
            config.append(minArea);
            config.append("</minArea>\n");
            config.append("<maxArea>");
            config.append(Integer.MAX_VALUE);
            config.append("</maxArea>\n");
            config.append("<filterByCircularity>1</filterByCircularity>\n");
            config.append("<minCircularity>");
            config.append(circularity[0]);
            config.append("</minCircularity>\n");
            config.append("<maxCircularity>");
            config.append(circularity[1]);
            config.append("</maxCircularity>\n");
            config.append("<filterByInertia>1</filterByInertia>\n");
            config.append("<minInertiaRatio>0.1</minInertiaRatio>\n");
            config.append("<maxInertiaRatio>" + Integer.MAX_VALUE + "</maxInertiaRatio>\n");
            config.append("<filterByConvexity>1</filterByConvexity>\n");
            config.append("<minConvexity>0.95</minConvexity>\n");
            config.append("<maxConvexity>" + Integer.MAX_VALUE + "</maxConvexity>\n");
            config.append("</opencv_storage>\n");
            FileWriter writer;
            writer = new FileWriter(tempFile, false);
            writer.write(config.toString());
            writer.close();
            blobDet.read(tempFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        blobDet.detect(input, blobList);
    }




}


