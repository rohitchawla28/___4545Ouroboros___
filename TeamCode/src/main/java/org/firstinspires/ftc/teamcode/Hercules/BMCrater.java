package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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

        intake.lock();

        lift.detachTime(drivetrain);

        intake.unlock();

        switch (cubePosition) {

            case "left" :
                drivetrain.turnPID(32, false, 0.65 / 32, 0.012, 0.02 / 32, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 600, 3);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 325, 4);
                sleep(500);

                drivetrain.turnPID(30, false, 0.7 / 30, 0.016, 0.02 / 30, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 950, 3);
                sleep(500);

                drivetrain.turnPID(40, false, 0.7 / 40, 0.014, 0.02 / 40, 1.5);
                sleep(500);

                drivetrain.moveEncoder(0.6, 900, 5);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1350, false, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, false, 3);
                sleep(500);

                intake.unlock();
                sleep(750);

                break;

            case "center" :
                drivetrain.moveGyroStab(0.6, 500, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 400, 4);
                sleep(500);

                drivetrain.turnPID(67, false, .7 / 67, 0.016, 0.02 / 67, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1200, 4);
                sleep(500);

                drivetrain.turnPID(40, false, .7 / 40, 0.017, 0.02 / 40, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 875, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1300,false, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, false, 3);
                sleep(500);

                intake.unlock();
                sleep(750);

                break;

            case "right" :
                drivetrain.turnPID(31, true, 0.6 / 31, 0.011, 0.025 / 31,1.25);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 600, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 475, 4);
                sleep(500);

                drivetrain.turnPID(95, false, 0.6 / 95, 0.015, 0.03 / 95, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1400, 4);
                sleep(500);

                drivetrain.turnPID(40, false, .7 / 40, 0.016, 0.02 / 40, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 825, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1275, false, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, false, 3);
                sleep(500);

                intake.unlock();
                sleep(750);

                break;

            default:
                drivetrain.moveGyroStab(0.6, 500, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 400, 4);
                sleep(500);

                drivetrain.turnPID(67, false, .7 / 67, 0.016, 0.02 / 67, 2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 1200, 4);
                sleep(500);

                drivetrain.turnPID(40, false, .7 / 40, 0.017, 0.02 / 40, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 875, 4);
                sleep(500);

                intake.deployMarker();
                sleep(500);

                drivetrain.moveWall(-0.6, 1300,false, 4);
                sleep(500);

                drivetrain.moveWall(-0.3, 325, false, 3);
                sleep(500);

                intake.unlock();
                sleep(750);

                break;

        }

    }

}
