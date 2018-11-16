package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

@Autonomous
        (name = "ACTUALTensorFlowTest", group = "Auto")

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

