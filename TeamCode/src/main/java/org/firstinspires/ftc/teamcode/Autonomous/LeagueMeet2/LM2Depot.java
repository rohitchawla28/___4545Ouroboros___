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
        drivetrain.moveEncoder(0.6, 400, 4);

        sleep(250);

        // Will turn to go around sampling
        drivetrain.turnGyro(0.5, 90, true, 4);

        //move around sampling
        drivetrain.moveEncoder(0.6, 1741, 4);

        sleep(250);

        // Will turn towards the depot
        drivetrain.turnGyro(0.5, 140, true, 4);

        sleep(250);

        //move to depot
        drivetrain.moveEncoder(0.6, 2013, 4);

        sleep(250);

        //drop marker
//        marker.markerOut();
//
//        sleep(150);

        //move to almost the crater
        drivetrain.moveEncoder(-0.6, 1505, 4);

        sleep(150);

        //move to touch the crater
        drivetrain.moveEncoder(-0.5, 250, 4);
    }

}
