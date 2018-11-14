package org.firstinspires.ftc.teamcode.Autonomous.FinalAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Marker;


@Disabled
@Autonomous
        (name = "Depot", group = "Auto")

public class Depot extends LinearOpMode {

    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
//    Vision vision;
//    Vuforia vuforia;
    Marker marker;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        intake = new Intake(this);
//        vision = new Vision(this);
//        vuforia = new Vuforia(this);
        marker = new Marker(this);

        telemetry.addLine("Initialized");
        telemetry.update();

//        vision.process(vuforia.convertToMat());
//
//        telemetry.addData("Cube is in position", vision.cubePositionAlt);
        telemetry.update();

        waitForStart();

        //Detach from lander IN MILLISECONDS
        lift.detachRange();

        //prepare for movement
        sleep(300);

        drivetrain.turnGyro(0.2,20, true, 4);

        sleep(150);

        //gets out of hook
        drivetrain.moveEncoder(0.3, 100, 2);

        sleep(150);

        //turns to face minerals
        drivetrain.turnGyro(0.2, 20, false, 4);

        sleep(200);

//        switch (vision.cubePositionAlt) {
//
//            case 1: // Cube is far left
//                drivetrain.turnGyro(0.3, 45, false, 4);
//
//                sleep(150);
//
//                //hit mineral
//                drivetrain.moveEncBadHardwareForward(0.6, 800, 3);
//
//                sleep(150);
//
//                // Turns to face the depot
//                drivetrain.turnGyro(0.4, 45, true, 4);
//
//                sleep(150);
//
//                // Will drive to depot
//                drivetrain.moveEncBadHardwareForward(0.5, 800, 3);
//
//                sleep(150);
//
//                //drop marker
//                marker.markerOut();
//
//                sleep(150);
//
//                // Will turn towards the wall
//                drivetrain.turnGyro(0.5, 110, false, 5);
//
//                sleep(150);
//
//                //Moves closer to wall
//                drivetrain.moveEncBadHardwareForward(0.3, 300, 4);
//
//                sleep(150);
//
//                // Will turn towards the crater
//                drivetrain.turnGyro(0.5, 30, false, 4);
//
//                sleep(150);
//
//                //drive to almost crater
//                drivetrain.moveEncBadHardwareForward(0.7, 2000, 4);
//
//                sleep(150);
//
//                //touch crater
//                drivetrain.moveEncBadHardwareForward(0.2, 100, 4);
//
//                break;
//
//            case 2: // Cube is middle
//                //hit mineral and move into depot
//                drivetrain.moveEncBadHardwareForward(0.6, 1500, 4);
//
//                //drop marker
//                marker.markerOut();
//
//                // Will turn towards the wall
//                drivetrain.turnGyro(0.3, 120, false, 5);
//
//                //Moves closer to wall
//                drivetrain.moveEncBadHardwareForward(0.3, 400, 3);
//
//                // Will turn towards the crater
//                drivetrain.turnGyro(0.3, 35, false, 3);
//
//                //move to almost crater
//                drivetrain.moveEncBadHardwareForward(0.7, 2000, 6);
//
//                //touch crater
//                drivetrain.moveEncBadHardwareForward(0.2, 100, 3);
//
//                break;
//
//            case 3: // Cube is far right
//                //turn to mineral
//                drivetrain.turnGyro(0.3, 45, true, 3);
//
//                //hit mineral
//                drivetrain.moveEncBadHardwareForward(0.5, 800, 3);
//
//                //turn to depot
//                drivetrain.turnGyro(0.4, 45, false, 3);
//
//                // Will drive to depot
//                drivetrain.moveEncBadHardwareForward(0.5, 600, 3);
//
//                //drops marker
//                marker.markerOut();
//
//                // Will turn towards the crater
//                drivetrain.turnGyro(0.5, 90, false, 4);
//
//                //move to almost crater
//                drivetrain.moveEncBadHardwareForward(0.7, 2200, 7);
//
//                //touch crater
//                drivetrain.moveEncBadHardwareForward(0.2, 100, 3);
//
//                break;
//
//            default: // Defaults to move as if the cube is in the middle
//
//                //hit mineral and move into depot
//                drivetrain.moveEncBadHardwareForward(0.6, 1500, 4);
//
//                //drop marker
//                marker.markerOut();
//
//                // Will turn towards the wall
//                drivetrain.turnGyro(0.3, 120, false, 5);
//
//                //Moves closer to wall
//                drivetrain.moveEncBadHardwareForward(0.3, 400, 3);
//
//                // Will turn towards the crater
//                drivetrain.turnGyro(0.3, 35, false, 3);
//
//                //move to almost crater
//                drivetrain.moveEncBadHardwareForward(0.7, 2000, 6);
//
//                //touch crater
//                drivetrain.moveEncBadHardwareForward(0.2, 100, 3);
//
//                break;

//        }
    }
}
