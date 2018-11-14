package org.firstinspires.ftc.teamcode.Autonomous.LeagueMeet1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Marker;


@Autonomous
        (name = "LM1Depot", group = "Auto")

public class LM1Depot extends LinearOpMode {

    Drivetrain drivetrain;
    Lift lift;
    Marker marker;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        marker = new Marker(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

//        //Detach from lander IN MILLISECONDS
//        lift.detachTime();
//
//        //prepare for movement
//        sleep(300);
//
//        //turn out of hook
//        drivetrain.turnGyro(0.35, 20, true, 3);
//
//        sleep(150);
//
//        //lower lift so can turn back
//        lift.shortDown();
//
//        sleep(150);
//
//        //turns back to center
//        drivetrain.turnGyro(0.35, 20, false, 3);
//
//        sleep(150);

        /**
         *
         * default Cube is middle
         *
         */

        //hit mineral and move into depot
        drivetrain.moveEncBadHardwareForward(0.6, 2200, 4);

        sleep(250);

        // Will turn towards the crater
        drivetrain.turnGyro(0.5, 45, true, 4);

        sleep(250);

        //drop marker
        marker.markerOut();

        sleep(150);

        //move to almost the crater
        drivetrain.moveEncBadHardwareBackward(-0.6, 3050, 4);

        sleep(150);

        //move to touch the crater
        drivetrain.moveEncBadHardwareBackward(-0.5, 300, 4);
    }

}
