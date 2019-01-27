package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.HSV_OpenCVDetection;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.Vuforia;

import static org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.HSV_OpenCVDetection.cubePosition;

@Disabled
@Autonomous
        (name = "HSVOpenCVTest", group = "Auto")

public class HSVOpenCVTest extends LinearOpMode {

    private HSV_OpenCVDetection vision;
    private Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        vuforia = new Vuforia(this);
        vision = new HSV_OpenCVDetection(this, vuforia);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        // objective is to test the Hue Saturation Value GRIP filter
        vision.process(vuforia.convertToMat());
        telemetry.addData("Cube Position", cubePosition);
        telemetry.update();

    }

}


