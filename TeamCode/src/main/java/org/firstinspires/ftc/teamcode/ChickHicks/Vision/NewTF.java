package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class NewTF {
    private LinearOpMode opMode;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public static String cubePosition = "";

    public NewTF (LinearOpMode opMode) {
        this.opMode = opMode;
        cubePosition = "";

    }

    public void initialize() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();

        }
        else {
            opMode.telemetry.addLine("TFOD Init failed");

        }
    }

    public void sample() {
        if (opMode.opModeIsActive()) {
            if (tfod != null) {
                tfod.activate();
            }

            while (cubePosition.equals("") && opMode.opModeIsActive()) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

                    if (updatedRecognitions != null) {
                        opMode.telemetry.addData("# Object Detected", updatedRecognitions.size());
                        opMode.telemetry.update();

                        if (updatedRecognitions.size() == 2) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;

                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();

                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();

                                } else {
                                    silverMineral2X = (int) recognition.getLeft();

                                }

                            }

                            if (goldMineralX == -1) {
                                cubePosition = "left";

                            } else if (goldMineralX < silverMineral1X || goldMineralX < silverMineral2X) {
                                cubePosition = "right";

                            } else if (goldMineralX > silverMineral1X || goldMineralX > silverMineral2X) {
                                // theoretically should be right but phone is flipped
                                cubePosition = "center";

                            } else {
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
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    private void initTfod() {
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);

    }

}
