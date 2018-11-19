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
        marker = new Marker(this);
        lift = new Lift(this);

        telemetry.addLine("Ready to get this W");
        telemetry.update();

        waitForStart();

//        //Detach from lander IN MILLISECONDS
//        lift.detachTime();
//
//        //prepare for movement
//        sleep(300);

        //hit mineral
        drivetrain.moveEncoder(0.5, 1042, 4);

        sleep(3000);

        //move backwards
        drivetrain.moveEncoder(-0.4, 661, 3);

        //turn to face wall
        drivetrain.turnGyro(0.4, 90, false, 4);

        //move closer to wall
        drivetrain.moveEncoder(0.5, 1700, 4);

        //turn more to align with wall
        drivetrain.turnGyro(0.4, 15, false, 3);

        //move along wall
        drivetrain.moveEncoder(0.4, 958, 3);

        //turn to depot
        drivetrain.turnGyro(0.4, 10, false, 4);

        //move to depot
        drivetrain.moveEncoder(0.6, 1168, 4);

        //drop marker
        marker.markerOut();

        //backwards to almost crater
        drivetrain.moveEncoder(-0.6, 3100, 4);

        //touch crater
        drivetrain.moveEncoder(-0.2, 100, 3);

    }
}
