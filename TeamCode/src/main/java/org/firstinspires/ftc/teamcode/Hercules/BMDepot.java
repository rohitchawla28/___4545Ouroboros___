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

        intake.lock();

        lift.detachTime(drivetrain);

        switch(cubePosition) {
            case "left" :
                drivetrain.turnPID(32, false, 0.65 / 32, 0.012, 0.02 / 32, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 300, 3);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 175, 4);
                sleep(500);

                drivetrain.turnPID(30, false, 0.7 / 30, 0.016, 0.02 / 30, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 875, 3);
                sleep(500);

                drivetrain.turnPID(100, true, 0.7 / 100, 0.01, 0.02 / 100, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 800, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1450, true, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 300, true, 2);
                sleep(500);

                break;

            case "center" :
                drivetrain.moveGyroStab(0.6, 200, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 100, 4);
                sleep(500);

                drivetrain.turnPID(67, false, .7 / 67, 0.016, 0.02 / 67, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 975, 4);
                sleep(500);

                drivetrain.turnPID(100, true, .7 / 100, 0.01, 0.02 / 100, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1300, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1550,true, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 300, true, 3);
                sleep(500);

                break;

            case "right" :
                drivetrain.turnPID(31, true, 0.6 / 31, 0.011, 0.025 / 31,1.25);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 275, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 250, 4);
                sleep(500);

                drivetrain.turnPID(95, false, 0.6 / 95, 0.015, 0.03 / 95, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1325, 4);
                sleep(500);

                drivetrain.turnPID(100, true, .7 / 100, 0, 0, 6);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1300, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1300, true, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 300, true, 3);
                sleep(500);

                break;

            default:
                drivetrain.turnPID(31, true, 0.6 / 31, 0.011, 0.025 / 31,1.25);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 275, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 250, 4);
                sleep(500);

                drivetrain.turnPID(95, false, 0.6 / 95, 0.015, 0.03 / 95, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1325, 4);
                sleep(500);

                drivetrain.turnPID(100, true, .7 / 100, 0, 0, 6);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1300, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1300, true, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 300, true, 3);
                sleep(500);

                break;

        }
    }
}
