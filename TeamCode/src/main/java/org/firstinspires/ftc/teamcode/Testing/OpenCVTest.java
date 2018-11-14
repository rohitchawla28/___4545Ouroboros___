package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import ChickHicks.Vision;
//import ChickHicks.Vuforia;

@Disabled
@Autonomous
        (name = "OpenCVTest", group = "Auto")

public class OpenCVTest extends LinearOpMode {

//    private Vision vision;
//    private Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {

//        vuforia = new Vuforia(this);
//        vision = new Vision(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();


        while(opModeIsActive())
        {
//            vision.process(vuforia.convertToMat());
//            telemetry.addData("Cube Position ", Vision.cubePositionAlt);
            telemetry.update();
        }

    }
}


