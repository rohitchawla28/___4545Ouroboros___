package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.HSL_OpenCVDetection;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.Vuforia;

import static org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.HSL_OpenCVDetection.cubePositionAlt;

@Disabled
@Autonomous
        (name = "HSLOpenCVTest", group = "Auto")

public class HSLOpenCVTest extends LinearOpMode {

  private HSL_OpenCVDetection vision;
  private Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {

        vuforia = new Vuforia(this);
        vision = new HSL_OpenCVDetection(this, vuforia);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            vision.process(vuforia.convertToMat());

            telemetry.addData("Cube Position", cubePositionAlt);
            telemetry.update();

        }

    }
}


