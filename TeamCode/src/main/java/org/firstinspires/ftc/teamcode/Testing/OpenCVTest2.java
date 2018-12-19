package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.AlternativeVision;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.OpenCVDetection;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.Vuforia;

//import ChickHicks.Vision;
//import ChickHicks.Vuforia;

//@Disabled
@Autonomous
        (name = "OpenCVTest2", group = "Auto")

public class OpenCVTest2 extends LinearOpMode {

    private AlternativeVision vision;
    private Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {


        vision = new AlternativeVision(this);

        vuforia = new Vuforia(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        while(opModeIsActive())
        {
            vision.process(vuforia.convertToMat());
            telemetry.addData("Cube Position", AlternativeVision.cubePosition3);
        }
    }
}


