package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVision;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVisionWC;

import static org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVision.bitmapCubePosition;

@Autonomous
        (name = "DepotBM", group = "Auto")

public class BMDepot extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    BitmapVisionWC vision;

    private String cubePosition = "unknown";

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        intake = new Intake(this);
        vision = new BitmapVisionWC(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        while (!isStarted()) {
            cubePosition = vision.sample();

            telemetry.addData("Cube position", cubePosition);
            telemetry.update();

        }

        waitForStart();

        lift.detachTime(drivetrain, intake);

        switch(cubePosition) {
            case "left" :
                drivetrain.turnPI(32.5, false, 0.65 / 60, 0.006, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 350, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 100, 4);
                sleep(500);

                drivetrain.turnPID(30, false, .5 / 30, 0.011, 0.004 / 30, 3);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1000, 3);
                sleep(500);

                drivetrain.turnGyro(0.3, 30, true, 4);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1300, 5);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 2000, 5);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 5);
                sleep(500);

                break;

            case "center" :
                drivetrain.moveEncoder(0.6, 300, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 110, 4);
                sleep(500);

                drivetrain.turnPID(65, false, .6 / 65, 0.008, 0.004 / 65, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 900, 4);
                sleep(500);

                drivetrain.turnPI(33, false, .4 / 33, 0.009, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1150, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 2200,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);
                break;

            case "right" :
                drivetrain.turnPI(30, true, 0.65 / 60, 0.006, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 350, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 150, 4);
                sleep(500);

                drivetrain.turnPI(95, false, .35 / 95, 0.003, 3);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1000, 4);
                sleep(500);

                drivetrain.turnPI(50, false, .3 / 90, 0.013, 4);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1150, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 2200, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);

                break;

            default:
                drivetrain.turnPI(30, true, 0.65 / 60, 0.006, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 350, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 150, 4);
                sleep(500);

                drivetrain.turnPI(95, false, .35 / 95, 0.003, 3);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1000, 4);
                sleep(500);

                drivetrain.turnPI(50, false, .3 / 90, 0.013, 4);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1150, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 2200, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);

                break;

        }
    }
}
