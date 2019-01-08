package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;


@Autonomous
        (name = "TFDepot", group = "Auto")

public class TFDepot extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    TensorFlowDetection vision;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
        vision = new TensorFlowDetection(this);
//        lift = new Lift(this);


        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();
        vision.sample();

        switch(cubePosition) {
            case "left":
                drivetrain.moveEncoder(0.3, 200, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 16, false, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1200, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 1150, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 16, true, 4);
                sleep(400);
                //move to not hit lander
                drivetrain.moveEncoder(0.5, 500, 4);

                sleep(400);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 60, false, 4);

                sleep(400);

                //move to wall
                drivetrain.moveEncoder(0.5, 2050, 4);

                sleep(400);

                // Will turn towards the depot
                drivetrain.turnGyro(0.4, 85, true, 4);

                sleep(400);

                //move to depot
                drivetrain.moveEncoder(0.5, 2050, 4);

                sleep(400);

                //drop marker
                intake.markerOut();

                sleep(150);

                //move to almost the crater
                drivetrain.moveEncoder(-0.5, 2100, 4);

                sleep(400);

                //move to touch the crater
                drivetrain.moveEncoder(-0.3, 320, 4);
                break;

            case "center":
                drivetrain.moveEncoder(0.3, 200, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1000, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 1000, 4);
                sleep(400);
                //move to not hit lander
                drivetrain.moveEncoder(0.5, 500, 4);

                sleep(400);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 60, false, 4);

                sleep(400);

                //move to wall
                drivetrain.moveEncoder(0.5, 2050, 4);

                sleep(400);

                // Will turn towards the depot
                drivetrain.turnGyro(0.4, 85, true, 4);

                sleep(400);

                //move to depot
                drivetrain.moveEncoder(0.5, 2050, 4);

                sleep(400);

                //drop marker
                intake.markerOut();

                sleep(150);

                //move to almost the crater
                drivetrain.moveEncoder(-0.5, 2050, 4);

                sleep(400);

                //move to touch the crater
                drivetrain.moveEncoder(-0.3, 350, 4);
                break;

            case "right":
                drivetrain.moveEncoder(0.3, 200, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 16, true, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1150, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 1000, 4);
                sleep(400);
                drivetrain.turnGyro(0.4, 16, false, 4);
                sleep(400);
                //move to not hit lander
                drivetrain.moveEncoder(0.5, 500, 4);

                sleep(400);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 60, false, 4);

                sleep(400);

                //move to wall
                drivetrain.moveEncoder(0.5, 1950, 4);

                sleep(400);

                // Will turn towards the depot
                drivetrain.turnGyro(0.4, 85, true, 4);

                sleep(400);

                //move to depot
                drivetrain.moveEncoder(0.5, 2050, 4);

                sleep(400);

                //drop marker
                intake.markerOut();

                sleep(150);

                //move to almost the crater
                drivetrain.moveEncoder(-0.5, 2250, 4);

                sleep(400);

                //move to touch the crater
                drivetrain.moveEncoder(-0.3, 400, 4);
                break;

            default:
                drivetrain.moveEncoder(0.3, 200, 4);
                sleep(400);
                drivetrain.moveEncoder(0.4, 1000, 4);
                sleep(400);
                drivetrain.moveEncoder(-0.4, 1000, 4);
                sleep(400);

                //move to not hit lander
                drivetrain.moveEncoder(0.5, 500, 4);

                sleep(400);

                // Will turn to go around sampling
                drivetrain.turnGyro(0.4, 60, false, 4);

                sleep(400);

                //move to wall
                drivetrain.moveEncoder(0.5, 2050, 4);

                sleep(400);

                // Will turn towards the depot
                drivetrain.turnGyro(0.4, 85, true, 4);

                sleep(400);

                //move to depot
                drivetrain.moveEncoder(0.5, 2050, 4);

                sleep(400);

                //drop marker
                intake.markerOut();

                sleep(150);

                //move to almost the crater
                drivetrain.moveEncoder(-0.5, 2100, 4);

                sleep(400);

                //move to touch the crater
                drivetrain.moveEncoder(-0.3, 320, 4);
                break;
        }
    }
}
