package Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ChickHicks.Vision.TensorFlowDetection;

//import ChickHicks.Vision;
//import ChickHicks.Vuforia;

@Disabled
@Autonomous
        (name = "OpenCVTest", group = "Auto")

public class TensorFlowTest extends LinearOpMode {

    private TensorFlowDetection tensorFlowDetection;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        tensorFlowDetection = new TensorFlowDetection(this);

        telemetry.addData("Cube Position", TensorFlowDetection.cubePosition);
        telemetry.update();

    }
}

