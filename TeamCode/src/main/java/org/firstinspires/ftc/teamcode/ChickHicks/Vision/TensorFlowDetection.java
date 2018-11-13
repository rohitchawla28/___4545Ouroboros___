package ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

    private static final String VUFORIA_KEY = "AVVhjKz/////AAABmSUTzsySy0l7nvSEO4/tklwYBNqq5gvpXVF2MnqOW+ENbnMruZbOaQHck" +
            "DCi0mPF9M2LoOcqBnpHHowbYKy1ODgbLMmmwSLH4UZyYTGCcwEN6s+xfz6Sd35BQxcEJQ77zhY9ZklFWKnxZdIDYl11XkIQK+Yi2iU+zpK" +
            "p5YTWCzOW570bcIdyPc0G/KTLWrxBrAOBIZu68HntklnQKvkciI6FQCN9n3T6MlqBDbx3SxXqaFALXWhZvbIs1cryGvmWSekjB9MeH642rZw" +
            "SdLyOzQGa7xwcZGbek2Fj94vxmKaq8wTCz3hR0eBSqCVD22RPGMcJW8VoWz2+xVim2XS7A5XmbROKKZPNz95Xbfvt0eMb";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    int goldMineralX = -1;
    int silverMineral1X = -1;
    int silverMineral2X = -1;

    public static int cubePosition = 0;

    public TensorFlowDetection(LinearOpMode opMode) {

        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            this.opMode.telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        if (this.opMode.opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (this.opMode.opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        this.opMode.telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 3) {

                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                                }
                            }

                            detect();
                        }
                    }
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

    public void detect() {
        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
            if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                this.opMode.telemetry.addData("Gold Mineral Position", "Left");
                this.opMode.telemetry.update();
                cubePosition = 1;
            }
            else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                this.opMode.telemetry.addData("Gold Mineral Position", "Right");
                this.opMode.telemetry.update();
                cubePosition = 2;
            }
            else {
                this.opMode.telemetry.addData("Gold Mineral Position", "Center");
                this.opMode.telemetry.update();
                cubePosition = 3;
            }
        }
    }
    
}