package org.firstinspires.ftc.teamcode.Autonomous.LeagueMeet2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;


@Autonomous
        (name = "CraterNoIntake", group = "Auto")

public class CraterNoIntake extends LinearOpMode{
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

        telemetry.addData("Cube Position", TensorFlowDetection.cubePosition);
        telemetry.update();

        switch(cubePosition) {
            case "left":
                drivetrain.turnGyro(0.4, 20, false, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1050, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 1050, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 20, true, 4);
                sleep(400);
                break;

            case "center":
                drivetrain.moveEncoder(0.4, 950, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 950, 4);
                sleep(400);
                break;

            case "right":
                drivetrain.turnGyro(0.4, 18, true, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1050, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 1050, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 18, false, 4);
                sleep(400);
                break;

            default:
                drivetrain.moveEncoder(0.4, 650, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 650, 4);
                sleep(400);
                break;
        }

        //move to not hit lander
        drivetrain.moveEncoder(0.5, 500, 4);

        sleep(1000);

        // Will turn to go around sampling
        drivetrain.turnGyro(0.4, 60, false, 4);

        sleep(1000);

        //move to wall
        drivetrain.moveEncoder(0.5, 2200, 3);

        sleep(1000);

        //turn parallel to wall
        drivetrain.turnGyro(0.4, 25, false, 4);

        sleep(1000);

        //move along wall
        drivetrain.moveEncoder(0.6, 1700, 4);

        sleep(1000);

        //drop marker
        intake.markerOut();

        //backwards to almost crater
        drivetrain.moveEncoder(-0.6, 1900, 4);

        sleep(1000);

        //touch crater
        drivetrain.moveEncoder(-0.25, 400, 3);

    }
}
