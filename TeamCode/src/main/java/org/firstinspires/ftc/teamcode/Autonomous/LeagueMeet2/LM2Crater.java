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
        drivetrain.moveEncoder(0.6, 400, 4);

        sleep(250);

        // Will turn to go around sampling
        drivetrain.turnGyro(0.5, 90, true, 4);

        //move to wall
        drivetrain.moveEncoder(0.4, 1965, 3);

        //turn parallel to wall
        drivetrain.turnGyro(0.4, 70, false, 4);

        //move along
        drivetrain.moveEncoder(0.5, 1926, 4);

//        //drop marker
//        marker.markerOut();

        //backwards to almost crater
        drivetrain.moveEncoder(-0.6, 1465, 4);

        //touch crater
        drivetrain.moveEncoder(-0.2, 250, 3);

    }
}
