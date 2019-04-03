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

        intake.lock();

        while (!isStarted()) {
            cubePosition = vision.sample();

            telemetry.addData("Cube Position", cubePosition);
            telemetry.update();

        }

        waitForStart();
        
        lift.detachTime(drivetrain);

//        switch (cubePosition) {
//
//            case "left" :
//                drivetrain.turnPID(32, false, 0.65 / 32, 0.012, 0.02 / 32, 1.5);
//                sleep(500);
//
//                drivetrain.moveGyroStab(0.6, 300, 3);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 175, 4);
//                sleep(500);
//
//                drivetrain.turnPID(30, false, 0.7 / 30, 0.016, 0.02 / 30, 1.5);
//                sleep(500);
//
//                drivetrain.moveGyroStab(0.6, 800, 3);
//                sleep(500);
//
//                drivetrain.turnPID(40, false, 0.7 / 40, 0.014, 0.02 / 40, 1.5);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1025, 5);
//                sleep(500);
//
//                intake.deployMarker();
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.75, 1450, 5);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 300, 5);
//                sleep(500);
//
//                intake.unlock();
//                sleep(750);
//
//                break;
//
//            case "center" :
//                drivetrain.moveGyroStab(0.6, 200, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 100, 4);
//                sleep(500);
//
//                drivetrain.turnPID(67, false, .7 / 67, 0.016, 0.02 / 67, 2);
//                sleep(500);
//
//                drivetrain.moveGyroStab(0.6, 825, 4);
//                sleep(500);
//
//                drivetrain.turnPID(40, false, .7 / 40, 0.017, 0.02 / 40, 2);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 975, 4);
//                sleep(500);
//
//                intake.deployMarker();
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.75, 1550,4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 300, 3);
//                sleep(500);
//
//                intake.unlock();
//                sleep(750);
//
//                break;
//
//            case "right" :
//                drivetrain.turnPID(31, true, 0.6 / 31, 0.011, 0.025 / 31,2);
//                sleep(500);
//
//                drivetrain.moveGyroStab(0.6, 250, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 125, 4);
//                sleep(500);
//
//                drivetrain.turnPID(95, false, 0.65 / 95, 0.019, 0.02 / 95, 2);
//                sleep(500);
//
//                drivetrain.moveGyroStab(0.6, 1250, 4);
//                sleep(500);
//
//                drivetrain.turnPID(40, false, .7 / 40, 0.016, 0.02 / 40, 2);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 700, 4);
//                sleep(500);
//
//                intake.deployMarker();
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.75, 1300, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 300, 3);
//                sleep(500);
//
//                intake.unlock();
//                sleep(750);
//
//                break;
//
//            default:
//                drivetrain.moveGyroStab(0.6, 200, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 100, 4);
//                sleep(500);
//
//                drivetrain.turnPID(67, false, .7 / 67, 0.016, 0.02 / 67, 2);
//                sleep(500);
//
//                drivetrain.moveGyroStab(0.6, 825, 4);
//                sleep(500);
//
//                drivetrain.turnPID(40, false, .7 / 40, 0.017, 0.02 / 40, 2);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 975, 4);
//                sleep(500);
//
//                intake.deployMarker();
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.75, 1550,4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 300, 3);
//                sleep(500);
//
//                intake.unlock();
//                sleep(750);
//
//                break;
//
//        }

    }

}
