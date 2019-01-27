package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVision;

// @Disabled
@Autonomous
        (name = "BMCrater", group = "Auto")

public class BMCrater extends LinearOpMode{
    Drivetrain drivetrain;
    Intake intake;
    Lift lift;
    BitmapVision vision;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
//        intake = new Intake(this);
        lift = new Lift(this);
//        vision = new BitmapVision(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        lift.detachTime1(drivetrain);

//        drivetrain.turnPID(95, false, 0.8/95,.003, .05/95, 15);

//        vision.sample();
//
//        lift.detachTime();

//        switch(bitMapCubePosition) {
//            case "left":
//                drivetrain.turnPI(40, false, .3/40, 0.05, 3);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 475, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 125, 4);
//                sleep(500);
//
//                drivetrain.turnPI(70, false, .35/70, 0.003, 5);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1150, 4);
//                sleep(500);
//
//                drivetrain.turnPI(40, false, .3/90, 0.013, 5);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1375, 4);
//                sleep(500);
//
//                // intake.markerOut();
//
//                drivetrain.moveEncoder(-0.6, 2200,4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 200, 3);
//                break;
//
//            case "center" :
//                drivetrain.moveEncoder(0.6, 425, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 125, 4);
//                sleep(500);
//
//                drivetrain.turnPI(60, false, .3/60, 0.015, 5);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 800, 4);
//                sleep(500);
//
//                drivetrain.turnPI(33, false, .45/33, 0.013, 5);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1000, 4);
//                sleep(500);
//
//                // intake.markerOut();
//
//                drivetrain.moveEncoder(-0.6, 2200,4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 200, 3);
//                break;
//
//            case "right":
//                drivetrain.turnPI(25, true, 0.65/60, 0.004, 3);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 475, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 125, 4);
//                sleep(500);
//
//                drivetrain.turnPI(95, false, .35/95, 0.003, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1150, 4);
//                sleep(500);
//
//                drivetrain.turnPI(40, false, .3/90, 0.013, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1150, 4);
//                sleep(500);
//
//                lift.moveArmUp();
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 2200,4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 200, 3);
//                break;
//
//            default:
//                drivetrain.moveEncoder(0.6, 475, 4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.6, 125, 4);
//                sleep(500);
//
//                drivetrain.turnPI(95, false, .35/95, 0.003, 5);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1150, 4);
//                sleep(500);
//
//                drivetrain.turnPI(40, false, .3/90, 0.013, 5);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 1375, 4);
//                sleep(500);
//
//                // intake.markerOut();
//
//                drivetrain.moveEncoder(-0.6, 2200,4);
//                sleep(500);
//
//                drivetrain.moveEncoder(-0.3, 200, 3);
//                break;
//        }
    }
}
