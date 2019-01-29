package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.HSL_OpenCVDetection;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.Vuforia;


@Autonomous
        (name = "DepotCV", group = "Auto")

public class CVDepot extends LinearOpMode{

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

        switch(vision.cubePositionAlt) {
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
                drivetrain.turnPI(20, true, .25/90, 0.013, 5);
                sleep(400);

                drivetrain.moveEncoder(0.4, 1000, 4);
                sleep(400);

                drivetrain.moveEncoder(-0.4, 700, 4);
                sleep(400);

                drivetrain.turnPI(90, false, .33/90, 0.013, 5);
                sleep(400);

                drivetrain.moveEncoder(0.4, 1200, 4);
                sleep(400);

                drivetrain.turnPI(60, false, .3/90, 0.013, 5);
                sleep(400);

                drivetrain.moveEncoder(0.5, 1600, 4);
                sleep(400);

                sleep(2000);

                drivetrain.moveEncoder(-0.4, -2000,4);
                break;
        }
    }
}
