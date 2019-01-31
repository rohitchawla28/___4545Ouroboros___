package org.firstinspires.ftc.teamcode.HerculesLibraries.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class TensorFlowWC {

    private LinearOpMode opMode;
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AXtO2M//////AAABmcMr24IU3EwXtwKtaHQhutN5NptfG5aAhCKARxBcDdmWWEPI1ZjR2eW6hR202bTzCPYAd1vb1tCnWwFarQF6Nag5iRaGxyxHeF9W8MeQRGZS5i5tK8DX8IKUsdn+vcrqRNtkuL6cY+2JoRc3YsdiPj8Wf7G/O+mUxoBU7+asjHuK62eBwWOw6KGxwGWWkhf4Yukhs1PNfIqPrK58SdU+OjPBb6Bgy02wVvywQKlsUKpyuhE78g/L2UEwa1P/YGhgQdxlpFEFExkvgRPaKfiSmRmRUJxn81eTmjX9WJut3+Lll+RRGOUWqW4YA06lCD2+4Vtdglm9iHIRy20DsGIXE2T99Xmc3aqaHEh5gGjs97o7";


    public TensorFlowWC(LinearOpMode opMode) throws InterruptedException {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            this.opMode.telemetry.addLine("Tensorflow init failed");
            this.opMode.telemetry.update();

        }
    }

    public void sample() {
        if (opMode.opModeIsActive()) {
            if (tfod != null) {
                tfod.activate();

            }

            if (tfod != null) {
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    opMode.telemetry.addData("Num Objects Detected", updatedRecognitions.size());
                    if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;

                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                goldMineralX = (int) recognition.getLeft();

                            }
                            else if (silverMineral1X == -1) {
                                silverMineral1X = (int) recognition.getLeft();

                            }
                            else {
                                    silverMineral2X = (int) recognition.getLeft();
                            }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                            if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                opMode.telemetry.addData("Gold Mineral Position", "Left");
                            }
                            else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                opMode.telemetry.addData("Gold Mineral Position", "Right");

                            }
                            else {
                                opMode.telemetry.addData("Gold Mineral Position", "Center");

                            }
                        }
                    }
                    opMode.telemetry.update();

                }
            }

        }

        if (tfod != null) {
            tfod.shutdown();

        }

    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");

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
