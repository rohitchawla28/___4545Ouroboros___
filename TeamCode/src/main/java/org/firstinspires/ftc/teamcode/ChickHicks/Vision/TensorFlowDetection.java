package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

public class TensorFlowDetection {

    LinearOpMode opMode;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    private int goldMineralX = -1;
    private int silverMineral1X = -1;
    private int silverMineral2X = -1;

    public static String cubePosition = "";

    public TensorFlowDetection(LinearOpMode opMode) {

        this.opMode = opMode;
        initVuforia();

        opMode.telemetry.addLine("Vuforia init finished");
        opMode.telemetry.update();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            this.opMode.telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        this.opMode.telemetry.addLine("Tensor Flow Initialized");
        this.opMode.telemetry.update();
    }

    public void sample() {
        cubePosition = "";

        tfod.activate();
        ElapsedTime time = new ElapsedTime();

        while (cubePosition.equals("") && opMode.opModeIsActive()) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                if (updatedRecognitions.size() >= 1) {

                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (recognition.getLabel().equals(LABEL_SILVER_MINERAL) && silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    detect();
                }
            }
        }

        tfod.shutdown();
    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.FRONT;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public void detect() {
        if (goldMineralX == -1) {
            cubePosition = "left";
            opMode.telemetry.addData("Cube Position", cubePosition);
            opMode.telemetry.update();

        } else if ((goldMineralX < silverMineral1X)) {
            cubePosition = "center";
            opMode.telemetry.addData("Cube Position", cubePosition);
            opMode.telemetry.update();
        }
        else {
            cubePosition = "left";
            opMode.telemetry.addData("Cube Position", cubePosition);
            opMode.telemetry.update();
        }
    }
}
