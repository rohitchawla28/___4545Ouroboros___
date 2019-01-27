package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.HSL_OpenCVDetection;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.Vuforia;

@Autonomous
        (name = "CVCrater", group = "Auto")

public class CVCrater extends LinearOpMode{

    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    HSL_OpenCVDetection vision;
    Vuforia vuforia;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        intake = new Intake(this);
        vision = new HSL_OpenCVDetection(this, vuforia);
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
                drivetrain.moveEncoder(0.4, 690, 4);
                sleep(750);

                drivetrain.moveEncoder(-0.4, 340, 4);
                sleep(750);

                drivetrain.turnPI(95, false, .35/90, 0, 5);
                sleep(750);

                drivetrain.moveEncoder(0.4, 1400 /* 1800 */, 4);
                sleep(750);

                drivetrain.turnPI(40, false, .3/90, 0.013, 5);
                sleep(750);

                drivetrain.moveEncoder(0.5, 1600, 4);
                sleep(750);

                drivetrain.moveEncoder(-0.4, 2200,4);
                sleep(750);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;

            case "right":
                drivetrain.turnPI(25, true, .54/90, 0.03, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 725, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 375, 4);
                sleep(500);

                drivetrain.turnPI(95, false, .35/90, 0, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1400, 4);
                sleep(500);

                drivetrain.turnPI(40, false, .3/90, 0.013, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1600, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 2200,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;

            default:
                drivetrain.turnPI(25, true, .65/90, 0.03, 5);
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

                drivetrain.moveEncoder(-0.6, 2200,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;
        }
    }
}
