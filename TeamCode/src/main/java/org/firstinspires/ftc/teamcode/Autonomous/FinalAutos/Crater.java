package org.firstinspires.ftc.teamcode.Autonomous.FinalAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Marker;


@Disabled
@Autonomous
        (name = "Crater", group = "Auto")

public class Crater extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
//    Vision vision;
//    Vuforia vuforia;
    Marker marker;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
//        vision = new Vision(this);
//        vuforia = new Vuforia(this);
        marker = new Marker(this);

        telemetry.addLine("Initialized");
        telemetry.update();

//        vision.process(vuforia.convertToMat());
//
//        telemetry.addData("The cube is in position ", vision.cubePositionAlt);
        telemetry.update();

        waitForStart();

        //Detach from lander
        lift.detachRange();
//
        //prepare for movement
        sleep(500);



    }
}
