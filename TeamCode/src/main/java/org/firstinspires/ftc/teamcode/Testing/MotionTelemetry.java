package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;

@Autonomous
        (name = "MotionTelemetry", group = "Teleop")

public class MotionTelemetry extends LinearOpMode {

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);

        waitForStart();

        drivetrain.composeTelemetryEncoders();
        drivetrain.composeTelemetryGyro();
        telemetry.update();

        if (gamepad1.a) {

            drivetrain.resetEncoders();

        }

    }
}

