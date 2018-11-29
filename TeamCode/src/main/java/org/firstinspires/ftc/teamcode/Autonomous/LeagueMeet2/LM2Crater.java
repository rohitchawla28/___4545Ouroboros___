package org.firstinspires.ftc.teamcode.Autonomous.LeagueMeet2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Marker;


@Autonomous
        (name = "LM2Crater", group = "Auto")

public class LM2Crater extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Marker marker;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        //marker = new Marker(this);
        lift = new Lift(this);

        telemetry.addLine("Ready to get this W");
        telemetry.update();

        waitForStart();

//        //Detach from lander IN MILLISECONDS
//        lift.detachTime();
//
//        //prepare for movement
//        sleep(300);

        //move to not hit lander
        drivetrain.moveEncoder(0.6, 350, 4);

        sleep(250);

        // Will turn to go around sampling
        drivetrain.turnGyro(0.5, 60, false, 4);

        sleep(250);

        //move to wall
        drivetrain.moveEncoder(0.6, 1825, 3);

        sleep(250);

        //turn parallel to wall
        drivetrain.turnGyro(0.5, 32, false, 4);

        sleep(250);

        // move next to wall
        drivetrain.moveEncoder(0.6, 400, 4);

        sleep(250);

        //turn parallel to wall
        drivetrain.turnGyro(0.5, 5, false, 4);

        sleep(250);

        //move along wall
        drivetrain.moveEncoder(0.6, 1650, 4);

        sleep(250);

//        //drop marker
//        marker.markerOut();

        //backwards to almost crater
        drivetrain.moveEncoder(-0.6, 1900, 4);

        sleep(250);

        //touch crater
        drivetrain.moveEncoder(-0.4, 550, 3);

    }
}
