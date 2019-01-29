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

    String cubePosition = "center";

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
        lift = new Lift(this);
        vision = new BitmapVisionWC(this);

        telemetry.addLine("Initialized");
        telemetry.update();

//        while (!isStarted()) {
//            cubePosition = vision.sample();
//
//        }

        waitForStart();

        // lift.detachTime1(drivetrain);

        switch (cubePosition) {

            case "left" :
                drivetrain.turnPI(30, false, 0.65 / 60, 0.006, 2);
                sleep(500);

                drivetrain.moveEncoder(0.6, 350, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.6, 150, 4);
                sleep(500);

                // TODO: Remember to change timeout time to less and change PID method error to stop when < 1
                drivetrain.turnPID(35, false, .7 / 35, 0, 0, 10);
                sleep(500);

                drivetrain.moveEncoder(0.6, 600, 3);
                sleep(500);

                drivetrain.turnPID(30, false, 0.6 / 30, 0, 0, 10);
                sleep(500);

                drivetrain.moveEncoder(0.6, 1500, 5);
                sleep(500);

                lift.moveArmUp();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 2200, 5);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 5);
                sleep(500);

                // TODO: Once done with all pathing, try to make faster (make speeds faster, decrease timeouts on turns, lift faster, etc)

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

                lift.moveArmUp();
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

                lift.moveArmUp();
                sleep(500);

                drivetrain.moveEncoder(-0.6, 2200, 4);
                sleep(500);

                drivetrain.moveEncoder(-0.3, 200, 3);

        }



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
