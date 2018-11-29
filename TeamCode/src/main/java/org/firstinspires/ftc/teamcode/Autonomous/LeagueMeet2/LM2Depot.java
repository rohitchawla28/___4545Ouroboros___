package org.firstinspires.ftc.teamcode.Autonomous.LeagueMeet2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Marker;


@Autonomous
        (name = "LM2Depot", group = "Auto")

public class LM2Depot extends LinearOpMode {

    Drivetrain drivetrain;
    Lift lift;
    Marker marker;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        //  marker = new Marker(this);

        telemetry.addLine("Ready to get this W");
        telemetry.update();

        waitForStart();

//        //Detach from lander IN MILLISECONDS
//        lift.detachTime();
//
//        //prepare for movement
//        sleep(300);

        /**
         *
         * default Cube is middle
         *
         */

        //move to not hit lander
        drivetrain.moveEncoder(0.5, 350, 4);

        sleep(250);

        // Will turn to go around sampling
        drivetrain.turnGyro(0.4, 60, false, 4);

        sleep(250);

        //move around sampling
        drivetrain.moveEncoder(0.5, 1600, 4);

        sleep(250);

        // Will turn towards the depot
        drivetrain.turnGyro(0.4, 95, true, 4);

        sleep(250);

        // Get closer to wall
        drivetrain.moveEncoder(0.5, 400, 4);

        sleep(150);

        // Align with wall
        drivetrain.turnGyro(0.4, 5, true, 4);

        sleep(250);

        //move to depot
        drivetrain.moveEncoder(0.5, 1650, 4);

        sleep(250);

        //drop marker
//        marker.markerOut();
//
//        sleep(150);

        //move to almost the crater
        drivetrain.moveEncoder(-0.5, 2150, 4);

        sleep(150);

        //move to touch the crater
        drivetrain.moveEncoder(-0.3, 300, 4);
    }

}
