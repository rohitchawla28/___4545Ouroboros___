package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;

@Disabled
@Autonomous
        (name = "TFCrater", group = "Auto")

public class TFCrater extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    TensorFlowDetection vision;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
//        lift = new Lift(this);
        vision = new TensorFlowDetection(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        vision.sample();

        switch(cubePosition) {
            case "left":
                drivetrain.moveEncoder(0.3, 200, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 20, false, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1150, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 950, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 20, true, 4);
                sleep(400);
                //move to not hit lander
                drivetrain.moveEncoder(0.5, 400, 4);

                sleep(500);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 55, false, 4);

                sleep(500);

                //move to wall
                drivetrain.moveEncoder(0.5, 1975, 3);

                sleep(500);

                //turn to angle against wall
                drivetrain.turnGyro(0.4, 18, false, 4);

                sleep(500);

                //move along wall
                drivetrain.moveEncoder(0.6, 850, 4);

                sleep(500);

                drivetrain.turnGyro(0.4, 10, false, 4);

                sleep(500);

                drivetrain.moveEncoder(0.5, 1300, 3);

                sleep(400);

                //drop marker
                intake.markerOut();

                //backwards to almost crater
                drivetrain.moveEncoder(-0.6, 1900, 4);

                sleep(1000);

                //touch crater
                drivetrain.moveEncoder(-0.25, 400, 3);
                break;

            case "center":
                drivetrain.moveEncoder(0.3, 250, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 850, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 850, 4);
                sleep(400);
                //move to not hit lander
                drivetrain.moveEncoder(0.5, 400, 4);

                sleep(500);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 55, false, 4);

                sleep(500);

                //move to wall
                drivetrain.moveEncoder(0.5, 1975, 3);

                sleep(500);

                //turn to angle against wall
                drivetrain.turnGyro(0.4, 20, false, 4);

                sleep(500);

                //move along wall
                drivetrain.moveEncoder(0.6, 700, 4);

                sleep(500);

                drivetrain.turnGyro(0.4, 9, false, 4);

                sleep(500);

                drivetrain.moveEncoder(0.5, 1300, 3);

                sleep(400);

                //drop marker
                intake.markerOut();

                //backwards to almost crater
                drivetrain.moveEncoder(-0.6, 1900, 4);

                sleep(1000);

                //touch crater
                drivetrain.moveEncoder(-0.25, 400, 3);
                break;

            case "right":
                drivetrain.moveEncoder(0.3, 250, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 18, true, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1050, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 950, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 18, false, 4);
                sleep(400);
                //move to not hit lander
                drivetrain.moveEncoder(0.5, 400, 4);

                sleep(500);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 55, false, 4);

                sleep(500);

                //move to wall
                drivetrain.moveEncoder(0.5, 1975, 3);

                sleep(500);

                //turn to angle against wall
                drivetrain.turnGyro(0.4, 20, false, 4);

                sleep(500);

                //move along wall
                drivetrain.moveEncoder(0.6, 700, 4);

                sleep(500);

                drivetrain.turnGyro(0.4, 9, false, 4);

                sleep(500);

                drivetrain.moveEncoder(0.5, 1300, 3);

                sleep(400);

                //drop marker
                intake.markerOut();

                //backwards to almost crater
                drivetrain.moveEncoder(-0.6, 1900, 4);

                sleep(1000);

                //touch crater
                drivetrain.moveEncoder(-0.25, 400, 3);
                break;

            default:
                drivetrain.moveEncoder(0.3, 250, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 950, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 950, 4);
                sleep(400);
                //move to not hit lander
                drivetrain.moveEncoder(0.5, 400, 4);

                sleep(500);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 55, false, 4);

                sleep(500);

                //move to wall
                drivetrain.moveEncoder(0.5, 1975, 3);

                sleep(500);

                //turn to angle against wall
                drivetrain.turnGyro(0.4, 20, false, 4);

                sleep(500);

                //move along wall
                drivetrain.moveEncoder(0.6, 700, 4);

                sleep(500);

                drivetrain.turnGyro(0.4, 9, false, 4);

                sleep(500);

                drivetrain.moveEncoder(0.5, 1300, 3);

                sleep(400);

                //drop marker
                intake.markerOut();

                //backwards to almost crater
                drivetrain.moveEncoder(-0.6, 1900, 4);

                sleep(1000);

                //touch crater
                drivetrain.moveEncoder(-0.25, 400, 3);
                break;
        }
    }
}
