package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVisionWC;

@Autonomous
        (name = "BitmapTestingWC", group = "Auto")


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
