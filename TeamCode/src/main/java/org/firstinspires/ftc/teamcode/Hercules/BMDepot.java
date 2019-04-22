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

        intake.lock();

        while (!isStarted()) {
            cubePosition = vision.sample();

            telemetry.addData("Cube position", cubePosition);
            telemetry.update();

        }

        waitForStart();

        lift.detachTime(drivetrain);

        switch(cubePosition) {
            case "left" :
                drivetrain.turnPID(32, false, 0.65 / 32, 0.012, 0.02 / 32, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 770, 3);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 620, 4);
                sleep(500);

                drivetrain.turnPID(30, false, 0.7 / 30, 0.016, 0.02 / 30, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1200, 3);
                sleep(500);

                drivetrain.turnPID(100, true, 0.7 / 100, 0.01, 0.02 / 100, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 960, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                intake.collectL.setPower(0.6);
                intake.collectR.setPower(0.6);

                drivetrain.moveWall(-0.6, 1570, true, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, true, 2);
                sleep(500);

                break;

            case "center" :
                drivetrain.moveGyroStab(0.6, 500, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 400, 4);
                sleep(500);

                drivetrain.turnPID(67, false, .7 / 67, 0.016, 0.02 / 67, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1250, 4);
                sleep(500);

                drivetrain.turnPID(100, true, .7 / 100, 0.01, 0.02 / 100, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 995, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                intake.collectL.setPower(0.6);
                intake.collectR.setPower(0.6);

                drivetrain.moveWall(-0.6, 1350,true, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, true, 3);
                sleep(500);

                break;

            case "right" :
                drivetrain.turnPID(31, true, 0.6 / 31, 0.011, 0.025 / 31,1.25);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 600, 10);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 450, 10);
                sleep(500);

                drivetrain.turnPID(95, false, 0.6 / 95, 0.015, 0.03 / 95, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1450, 10);
                sleep(500);

                drivetrain.turnPID(100, true, .6 / 100, 0.016, 0.03 / 100, 2.5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 905, 10);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                intake.collectL.setPower(0.6);
                intake.collectR.setPower(0.6);

                drivetrain.moveWall(-0.6, 1380, true, 10);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, true, 10);
                sleep(500);

                break;

            default:
                drivetrain.turnPID(32, false, 0.65 / 32, 0.012, 0.02 / 32, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 725, 3);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 575, 4);
                sleep(500);

                drivetrain.turnPID(30, false, 0.7 / 30, 0.016, 0.02 / 30, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1200, 3);
                sleep(500);

                drivetrain.turnPID(100, true, 0.7 / 100, 0.01, 0.02 / 100, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 985, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                intake.collectL.setPower(0.6);
                intake.collectR.setPower(0.6);

                drivetrain.moveWall(-0.6, 1570, true, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, true, 2);
                sleep(500);

                break;

        }
    }
}
