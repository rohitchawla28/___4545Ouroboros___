package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.core.Core;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * GripPipeline class.
 *
 * An OpenCV pipeline generated by GRIP.
 *
 */

public class OpenCVDetection {
    public static int cubePositionAlt;
    private LinearOpMode opMode;

    //Outputs
    private Mat hslThresholdOutput;
    private Mat blurOutput;
    private MatOfKeyPoint findBlobsOutput;

    public OpenCVDetection(LinearOpMode opMode) throws InterruptedException {

        this.opMode = opMode;

        System.loadLibrary("opencv_java3");

        this.opMode.waitForStart();


        cubePositionAlt = 0;
        hslThresholdOutput = new Mat();
        blurOutput = new Mat();
        findBlobsOutput = new MatOfKeyPoint();

    }


    /**
     * This is the primary method that runs the entire pipeline and updates the outputs.
     */
    //@Override
    public void process(Mat matImage) {

        opMode.telemetry.addLine("Starting process");
        opMode.telemetry.update();

        // GRIP input for HSL threshold
        double[] hslThresholdHue = {0.0, 142.0};
        double[] hslThresholdSaturation = {117.0, 255.0};
        double[] hslThresholdLuminance = {0, 255.0};
        hslThreshold(matImage, hslThresholdHue, hslThresholdSaturation, hslThresholdLuminance, hslThresholdOutput);

        // GRIP input for median blur
        Mat blurInput = hslThresholdOutput;
        BlurType blurType = BlurType.get("Median Filter");
        double blurRadius = 30.0;
        blur(blurInput, blurType, blurRadius, blurOutput);

        // GRIP input for find blobs
        Mat findBlobsInput = blurOutput;
        double findBlobsMinArea = 1.0;
        double[] findBlobsCircularity = {0.65, 1.0};
        boolean findBlobsDarkBlobs = false;
        findBlobs(findBlobsInput, findBlobsMinArea, findBlobsCircularity, findBlobsDarkBlobs, findBlobsOutput);

        KeyPoint[] findBlobsArray = findBlobsOutput.toArray();


        findSampling(matImage, findBlobsArray);
    }

    //================================ OUR SCANNING METHOD ======================================================

    public void findSampling(Mat image, KeyPoint[] array){
        // Variables for number of pixels found from FindBlobs in each third (divided by columns) of image
        int leftBlobs = 0;
        int middleBlobs = 0;
        int rightBlobs = 0;
        // Variables for number of columns and rows
        // Columns must be multiplied by channels since channels accounts for a 3D possibility
        // In our case, channels() is always 1 since we aren't using 3-D
        int nCol = image.channels();
        int nRow = image.rows();



        for (int index = 0; index < array.length; index++){

            //(5/9) value needs to be tested, but by starting our row scanning process from there,
            // we won't waste time with the useless top half of our portrait picture
            // pic[index].pt.x gets x position of pixel
            if (array[index].pt.x < (nCol * (1.0/3)) )
            {
                leftBlobs++;
                opMode.telemetry.addData("LB", leftBlobs);
                opMode.telemetry.update();

            }
            else if ((array[index].pt.x < nCol * (2.0/3) && (array[index].pt.x > nCol * (1.0/3))))
            {
                middleBlobs++;
                opMode.telemetry.addData("MB", middleBlobs);
                opMode.telemetry.update();

            }
            else if ((array[index].pt.x < nCol && (array[index].pt.x > nCol * (2.0/3))))
            {
                rightBlobs++;
                opMode.telemetry.addData("RB", rightBlobs);
                opMode.telemetry.update();
            }
        }

        if (leftBlobs > rightBlobs && leftBlobs > middleBlobs)
        {
            cubePositionAlt = 1;
        }

        else if (middleBlobs > leftBlobs && middleBlobs > rightBlobs) {
            cubePositionAlt = 2;
        }
        else if (rightBlobs > leftBlobs && rightBlobs > middleBlobs)
        {
            cubePositionAlt = 3;
        }
        else
        {
            cubePositionAlt = 0;
        }

        // We need to add some kind of return function if it doesn't work or if there are equal number of pixels
        // We can add a retry or something like that with a different filter maybe
    }

    //================================ ACTUAL GRIP FILTERS BELOW ================================================

    /**
     * This method is a generated getter for the output of a HSL_Threshold.
     * @return Mat output from HSL_Threshold.
     */
    public Mat hslThresholdOutput() {
        return hslThresholdOutput;
    }

    /**
     * This method is a generated getter for the output of a Blur.
     * @return Mat output from Blur.
     */
    public Mat blurOutput() {
        return blurOutput;
    }

    /**
     * This method is a generated getter for the output of a Find_Blobs.
     * @return MatOfKeyPoint output from Find_Blobs.
     */
    public MatOfKeyPoint findBlobsOutput() {
        return findBlobsOutput;
    }


    /**
     * Segment an image based on hue, saturation, and luminance ranges.
     *
     * @param input The image on which to perform the HSL threshold.
     * @param hue The min and max hue
     * @param sat The min and max saturation
     * @param lum The min and max luminance
     * @param out The image in which to store the output.
     */
    private void hslThreshold(Mat input, double[] hue, double[] sat, double[] lum,
                              Mat out) {
        Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HLS);
        Core.inRange(out, new Scalar(hue[0], lum[0], sat[0]),
                new Scalar(hue[1], lum[1], sat[1]), out);
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


