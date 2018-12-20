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
        (name = "OpenCVTest", group = "Auto")

public class OpenCVTest extends LinearOpMode {

  private OpenCVDetection vision;
  private Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {

        vuforia = new Vuforia(this);
        vision = new OpenCVDetection(this, vuforia);



        telemetry.addLine("Initialized");
        telemetry.update();

        while(opModeIsActive())
        {
            vision.process(vuforia.convertToMat());
            telemetry.addData("Cube Postion", OpenCVDetection.cubePositionAlt);
            telemetry.update();
        }
    }
}


