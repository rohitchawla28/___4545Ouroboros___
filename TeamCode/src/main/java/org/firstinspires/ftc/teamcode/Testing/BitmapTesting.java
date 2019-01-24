package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.BitmapVision;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.BitmapVisionWC;

@Autonomous
        (name = "BitmapTesting", group = "Auto")


public class BitmapTesting extends LinearOpMode {

    BitmapVisionWC vision;

    public void runOpMode() throws InterruptedException{
        vision = new BitmapVisionWC(this);

        while (!isStarted()) {
            vision.sample();
        }

        waitForStart();

        telemetry.addLine("Initialization Complete");
        telemetry.update();

    }
}
