package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;

@Autonomous
        (name = "MoveTelemetry", group = "Auto")

public class MotionTelemetry extends LinearOpMode {

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);

        drivetrain.resetEncoders();

        waitForStart();

        // objective is to move the robot to desired position manually
        // robot will return values for encoder ticks (distance) and gyro angle changes
        // this makes it much much faster to put in values into autonomous movements when testing pathing
        drivetrain.composeTelemetryEncoders();
        drivetrain.composeTelemetryGyro();

    }
}

