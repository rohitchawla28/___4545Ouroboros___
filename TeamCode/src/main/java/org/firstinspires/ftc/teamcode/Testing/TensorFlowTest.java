package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

@Autonomous
        (name = "TensorFlowTest", group = "Auto")

public class TensorFlowTest extends LinearOpMode {

    private TensorFlowDetection tensorFlowDetection;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        tensorFlowDetection = new TensorFlowDetection(this);

        tensorFlowDetection.sample();

        telemetry.addData("Cube Position", TensorFlowDetection.cubePosition);
        telemetry.update();

    }
}

