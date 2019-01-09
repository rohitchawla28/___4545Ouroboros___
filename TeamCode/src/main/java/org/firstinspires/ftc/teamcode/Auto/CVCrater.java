package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.OpenCVDetection;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.Vuforia;

@Autonomous
        (name = "CVCrater", group = "Auto")

public class CVCrater extends LinearOpMode{

    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    OpenCVDetection vision;
    Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        intake = new Intake(this);
        vision = new OpenCVDetection(this, vuforia);
        vuforia = new Vuforia(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        // lift.extendSampling(drivetrain, vuforia);

        // vision.process(vuforia.convertToMat());

        /** 90 degree turn
         * drivetrain.turnPI(90, true, .33/90, 0.013, 15);
         *
         * Commented out pathing hasn't been edited or tested,
         * currently testing the right position in the default of switch case
         */

        switch(vision.cubePositionAlt) {
            case "left":
//                drivetrain.moveEncoder(0.3, 200, 4);
//                sleep(400);
//                drivetrain.turnGyro(0.4, 20, false, 4);
//                sleep(400);
//                drivetrain.moveEncoder(0.4, 1150, 4);
//                sleep(400);
//                drivetrain.moveEncoder(-0.4, 950, 4);
//                sleep(400);
//                drivetrain.turnGyro(0.4, 20, true, 4);
//                sleep(400);
//                //move to not hit lander
//                drivetrain.moveEncoder(0.5, 400, 4);
//
//                sleep(500);
//
//                // Will turn to go around sampling
//                drivetrain.turnGyro(0.4, 55, false, 4);
//
//                sleep(500);
//
//                //move to wall
//                drivetrain.moveEncoder(0.5, 1975, 3);
//
//                sleep(500);
//
//                //turn to angle against wall
//                drivetrain.turnGyro(0.4, 18, false, 4);
//
//                sleep(500);
//
//                //move along wall
//                drivetrain.moveEncoder(0.6, 850, 4);
//
//                sleep(500);
//
//                drivetrain.turnGyro(0.4, 10, false, 4);
//
//                sleep(500);
//
//                drivetrain.moveEncoder(0.5, 1300, 3);
//
//                sleep(400);
//
//                //drop marker
//                intake.markerOut();
//
//                //backwards to almost crater
//                drivetrain.moveEncoder(-0.6, 1900, 4);
//
//                sleep(1000);
//
//                //touch crater
//                drivetrain.moveEncoder(-0.25, 400, 3);
                break;

            case "center":
//                drivetrain.moveEncoder(0.3, 250, 4);
//                sleep(400);
//                drivetrain.moveEncoder(0.4, 850, 4);
//                sleep(400);
//                drivetrain.moveEncoder(-0.4, 850, 4);
//                sleep(400);
//                //move to not hit lander
//                drivetrain.moveEncoder(0.5, 400, 4);
//
//                sleep(500);
//
//                // Will turn to go around sampling
//                drivetrain.turnGyro(0.4, 55, false, 4);
//
//                sleep(500);
//
//                //move to wall
//                drivetrain.moveEncoder(0.5, 1975, 3);
//
//                sleep(500);
//
//                //turn to angle against wall
//                drivetrain.turnGyro(0.4, 20, false, 4);
//
//                sleep(500);
//
//                //move along wall
//                drivetrain.moveEncoder(0.6, 700, 4);
//
//                sleep(500);
//
//                drivetrain.turnGyro(0.4, 9, false, 4);
//
//                sleep(500);
//
//                drivetrain.moveEncoder(0.5, 1300, 3);
//
//                sleep(400);
//
//                //drop marker
//                intake.markerOut();
//
//                //backwards to almost crater
//                drivetrain.moveEncoder(-0.6, 1900, 4);
//
//                sleep(1000);
//
//                //touch crater
//                drivetrain.moveEncoder(-0.25, 400, 3);
                break;

            case "right":
//                drivetrain.moveEncoder(0.3, 250, 4);
//                sleep(400);
//                drivetrain.turnGyro(0.4, 18, true, 4);
//                sleep(400);
//                drivetrain.moveEncoder(0.4, 1050, 4);
//                sleep(400);
//                drivetrain.moveEncoder(-0.4, 950, 4);
//                sleep(400);
//                drivetrain.turnGyro(0.4, 18, false, 4);
//                sleep(400);
//                //move to not hit lander
//                drivetrain.moveEncoder(0.5, 400, 4);
//
//                sleep(500);
//
//                // Will turn to go around sampling
//                drivetrain.turnGyro(0.4, 55, false, 4);
//
//                sleep(500);
//
//                //move to wall
//                drivetrain.moveEncoder(0.5, 1975, 3);
//
//                sleep(500);
//
//                //turn to angle against wall
//                drivetrain.turnGyro(0.4, 20, false, 4);
//
//                sleep(500);
//
//                //move along wall
//                drivetrain.moveEncoder(0.6, 700, 4);
//
//                sleep(500);
//
//                drivetrain.turnGyro(0.4, 9, false, 4);
//
//                sleep(500);
//
//                drivetrain.moveEncoder(0.5, 1300, 3);
//
//                sleep(400);
//
//                //drop marker
//                intake.markerOut();
//
//                //backwards to almost crater
//                drivetrain.moveEncoder(-0.6, 1900, 4);
//
//                sleep(1000);
//
//                //touch crater
//                drivetrain.moveEncoder(-0.25, 400, 3);
                break;

            default:
                drivetrain.turnPI(20, true, .55/90, 0.013, 7);
                sleep(400);

                drivetrain.moveEncoder(0.4, 800, 4);
                sleep(400);

                drivetrain.moveEncoder(-0.4, 400, 4);
                sleep(400);

                drivetrain.turnPI(90, false, .4/90, 0.013, 5);
                sleep(400);

                drivetrain.moveEncoder(0.4, 1200, 4);
                sleep(400);

                drivetrain.turnPI(30, false, .3/90, 0.013, 5);
                sleep(400);

                drivetrain.moveEncoder(0.5, 1600, 4);
                sleep(400);

                drivetrain.moveEncoder(-0.4, 2000,4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 400, 3);
                break;
        }
    }
}
