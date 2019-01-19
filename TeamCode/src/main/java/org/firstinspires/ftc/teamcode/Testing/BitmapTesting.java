package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.BitmapVision;

@Autonomous
        (name = "BitmapTesting", group = "Auto")


public class BitmapTesting extends LinearOpMode {

    BitmapVision bitmapVision;

    public void runOpMode() throws InterruptedException{
        bitmapVision = new BitmapVision(this);

        while (!isStarted())
        {
            bitmapVision.sample();
        }
        //bitmapVision.sample();

        waitForStart();

        telemetry.addLine("Initialization Complete");
        telemetry.update();

    }
}
