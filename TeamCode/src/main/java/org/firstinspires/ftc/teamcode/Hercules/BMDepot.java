package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVision;
import static org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVision.bitmapCubePosition;

@Autonomous
        (name = "BMDepot", group = "Auto")

public class BMDepot extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    BitmapVision vision;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        intake = new Intake(this);
        vision = new BitmapVision(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        vision.sample();

        switch(bitmapCubePosition) {
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
