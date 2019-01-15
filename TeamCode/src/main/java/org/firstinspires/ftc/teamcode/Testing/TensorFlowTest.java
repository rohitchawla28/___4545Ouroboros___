package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.NewTF;

//@Disabled
@Autonomous
        (name = "TensorFlowTest", group = "Auto")

public class TensorFlowTest extends LinearOpMode {

    private NewTF vision;

    @Override
    public void runOpMode() {
        vision = new NewTF(this);

        waitForStart();

        // objective is to test the Tensor flow vision from the FTC sdk
        vision.initialize();

        vision.sample();

    }
}

