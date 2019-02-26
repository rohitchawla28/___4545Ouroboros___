package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVisionWC;

@Autonomous
        (name = "CraterBM", group = "Auto")

public class BMCrater extends LinearOpMode{
    Drivetrain drivetrain;
    Intake intake;
    Lift lift;
    BitmapVisionWC vision;

    private String cubePosition = "unknown";

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
        lift = new Lift(this);
        vision = new BitmapVisionWC(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        while (!isStarted()) {
            cubePosition = vision.sample();

            telemetry.addData("Cube Position", cubePosition);
            telemetry.update();

        }

        waitForStart();

        lift.detachTime(drivetrain, intake);

        switch (cubePosition) {

            case "left" :
                drivetrain.turnPID(30, false, 0.5 / 30, 0, 0, 4);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 400, 3);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 200, 4);
                sleep(500);

                drivetrain.turnPID(30, false, .5 / 30, 0.011, 0.004 / 30, 3);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 600, 3);
                sleep(500);

                drivetrain.turnPID(30, false, 0.5 / 28, 0.005, 0.005 / 28, 3);
                sleep(500);

                drivetrain.moveEncoder(0.6, 600, 5);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 1300, 5);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 5);
                sleep(500);

                break;

            case "center" :
                drivetrain.moveEncoder(0.6, 225, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 100, 4);
                sleep(500);

                drivetrain.turnPID(65, false, .7 / 65, 0, 0.02 / 65, 5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 850, 4);
                sleep(500);

                drivetrain.turnPID(50, false, .7 / 50, 0, 0.02 / 50, 5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 575, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 1300,4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);

                break;

            case "right" :
                drivetrain.turnPID(31, true, 0.6 / 31, 0.009, 0.025 / 31,2);
                sleep(750);

                drivetrain.moveGyroStab(0.6, 250, 4);
                sleep(750);

                drivetrain.moveEncoder(-0.6, 125, 4);
                sleep(750);

                drivetrain.turnPID(95, false, 0.7 / 95, 0.01, 0.02 / 95, 2);
                sleep(750);

                drivetrain.moveGyroStab(0.6, 900, 4);
                sleep(750);

                drivetrain.turnPID(40, false, .7 / 40, 0.009, 0.02 / 40, 2);
                sleep(750);

                drivetrain.moveEncoder(0.6, 575, 4);
                sleep(750);

                intake.deployMarker();
                sleep(750);

                drivetrain.moveEncoder(-0.6, 1300, 4);
                sleep(750);

                drivetrain.moveEncoder(-0.3, 200, 3);

                break;

            default:
                drivetrain.turnPID(31, true, 0.6 / 31, 0.009, 0.025 / 31,2);
                sleep(750);

                drivetrain.moveGyroStab(0.6, 250, 4);
                sleep(750);

                drivetrain.moveEncoder(-0.6, 125, 4);
                sleep(750);

                drivetrain.turnPID(95, false, 0.7 / 95, 0.01, 0.02 / 95, 2);
                sleep(750);

                drivetrain.moveGyroStab(0.6, 900, 4);
                sleep(750);

                drivetrain.turnPID(40, false, .7 / 40, 0.009, 0.02 / 40, 2);
                sleep(750);

                drivetrain.moveEncoder(0.6, 575, 4);
                sleep(750);

                intake.deployMarker();
                sleep(750);

                drivetrain.moveEncoder(-0.6, 1300, 4);
                sleep(750);

                drivetrain.moveEncoder(-0.3, 200, 3);

                break;

        }

    }

}
