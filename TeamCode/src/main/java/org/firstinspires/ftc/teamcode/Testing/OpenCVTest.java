package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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


        vision = new OpenCVDetection(this);

        vuforia = new Vuforia(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        while(opModeIsActive())
        {
            vision.process(vuforia.convertToMat());
            telemetry.addData("Cube Position ", OpenCVDetection.cubePositionAlt);
            telemetry.update();
        }

    }
}


