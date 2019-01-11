package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;

// @Disabled
@Autonomous
        (name = "TFCrater", group = "Auto")

public class TFCrater extends LinearOpMode{
    Drivetrain drivetrain;
    Intake intake;
    TensorFlowDetection vision;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
        vision = new TensorFlowDetection(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        drivetrain.turnPI(15, true, .6/90, 0.05, 4);
        sleep(500);

        vision.sample();

        switch(cubePosition) {
            case "left":
                drivetrain.turnPI(40, false, .3/40, 0.05, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 475, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 125, 4);
                sleep(500);

                drivetrain.turnPI(70, false, .35/70, 0.003, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1150, 4);
                sleep(500);

                drivetrain.turnPI(40, false, .3/90, 0.013, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1375, 4);
                sleep(500);

                // intake.markerOut();

                drivetrain.moveEncoder(-0.6, 2200,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;

            case "center":
                drivetrain.turnPI(15, false, .15/15, 0.04, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 425, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 125, 4);
                sleep(500);

                drivetrain.turnPI(60, false, .3/60, 0.015, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 800, 4);
                sleep(500);

                drivetrain.turnPI(33, false, .45/33, 0.013, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1000, 4);
                sleep(500);

                // intake.markerOut();

                drivetrain.moveEncoder(-0.6, 2200,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;
            case "right":
                drivetrain.turnPI(10, true, 0.4/20, 0.003, 3);
                sleep(500);

                drivetrain.moveEncoder(0.6, 475, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 125, 4);
                sleep(500);

                drivetrain.turnPI(95, false, .35/95, 0.003, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1150, 4);
                sleep(500);

                drivetrain.turnPI(40, false, .3/90, 0.013, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1375, 4);
                sleep(500);

                // intake.markerOut();

                drivetrain.moveEncoder(-0.6, 2200,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;

            default:
                drivetrain.moveEncoder(0.6, 475, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 125, 4);
                sleep(500);

                drivetrain.turnPI(95, false, .35/95, 0.003, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1150, 4);
                sleep(500);

                drivetrain.turnPI(40, false, .3/90, 0.013, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1375, 4);
                sleep(500);

                // intake.markerOut();

                drivetrain.moveEncoder(-0.6, 2200,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;
        }
    }
}
