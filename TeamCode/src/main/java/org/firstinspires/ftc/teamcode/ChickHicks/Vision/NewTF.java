package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class NewTF {
    // import LinearOpMode to be used in this class
    private LinearOpMode opMode;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    // my original vuforia key
    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

    // import vuforia and tensor flow detection classes
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    // cube position variable starts as empty string
    public static String cubePosition = "";

    // constructor used when initializing the class
    public NewTF (LinearOpMode opMode) {
        this.opMode = opMode;
        cubePosition = "";

    }

    // initializing of engines running for this method of sampling
    public void initialize() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();

        }
        else {
            opMode.telemetry.addLine("TFOD Init failed");

        }
    }

    // main sample/process method
    public void sample() {
        if (opMode.opModeIsActive()) {
            if (tfod != null) {
                // activating tensor flow detection engine
                tfod.activate();

            }

            // while cube position is empty string, keep running
            // this means our loop will exit once we have a position established for the yellow cube
            while (cubePosition.equals("") && opMode.opModeIsActive()) {
                if (tfod != null) {
                    // create list for each of minerals tensor flow recognizes
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

                    // enters if we have found minerals
                    if (updatedRecognitions != null) {
                        // sends message to phone for how many minerals found
                        opMode.telemetry.addData("# Object Detected", updatedRecognitions.size());
                        opMode.telemetry.update();

                        // enters only if we have found 2 minerals (since our phone can only see 2 at a time
                        if (updatedRecognitions.size() == 2) {
                            // initializing mineral location variables
                            // these will store the coordinates for the minerals' positions
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;

                            // for loop runs through each mineral that was found
                            for (Recognition recognition : updatedRecognitions) {
                                // seeing if found mineral is actually the gold cube
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    // setting variables from above to getLeft()
                                    // getLeft() find the left most coordinate of the found mineral
                                    goldMineralX = (int) recognition.getLeft();

                                }
                                // enters if silver mineral hasn't been defined yet, also sets left most coordinate
                                else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();

                                }
                                else {
                                    silverMineral2X = (int) recognition.getLeft();

                                }

                            }

                            // enters if we haven't seen a gold mineral, meaning the 2 we saw were both silver so it is left
                            if (goldMineralX == -1) {
                                cubePosition = "left";

                            }
                            // enters if gold cube position is less than the other silver mineral we are seeing
                            else if (goldMineralX < silverMineral1X || goldMineralX < silverMineral2X) {
                                cubePosition = "center";

                            }
                            // enters if gold cube position is greater than other silver mineral we are seeing
                            else if (goldMineralX > silverMineral1X || goldMineralX > silverMineral2X) {
                                cubePosition = "right";

                            }
                            // enters if all else fails in position comparings
                            else {
                                opMode.telemetry.addLine("This is default");
                                opMode.telemetry.update();
                                cubePosition = "center";

                            }
                            opMode.telemetry.addData("Cube Position", cubePosition);
                            opMode.telemetry.update();

                        }

                    }

                }

            }

        }
        tfod.shutdown();

    }



    private void initVuforia() {
        // initialize vuforia classes and variables
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        // set direction of camera to use
        parameters.cameraDirection = CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    private void initTfod() {
        // initialize tensor flow detection engine
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);

    }

}
