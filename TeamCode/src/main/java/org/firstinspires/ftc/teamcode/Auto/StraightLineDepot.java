package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Sensors;
//import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

@Disabled
@Autonomous
        (name = "StrateLineDepot2", group = "Auto")

public class StraightLineDepot extends LinearOpMode {
    Drivetrain drivetrain;
    Intake intake;
    //TensorFlowDetection vision;


    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
        //vision = new TensorFlowDetection(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        //vision.sample();

//        switch (vision.cubePosition) {
//            case "left" :
//
//            case "center" :
//                drivetrain.moveEncoder(0.5, 1500, 5);
//                intake.markerOut();
////                drivetrain.moveEncoder(-0.5, 1000, 4);
////                drivetrain.turnPI(105, true);
////                drivetrain.moveEncoder(0.5, 1600, 5);
//
//            case "right" :
//            default :
//        }

        drivetrain.moveEncoder(0.5, 1900, 4);

        sleep(300);


//        drivetrain.turnGyro(0.75, 50, true, 5);
//
//        drivetrain.moveEncBadHardware(0.5, 500, 6);
//
//        drivetrain.turnGyro(0.75, 30, true, 5);
//
//        drivetrain.moveEncBadHardware(0.5, 150, 6);
//
//        drivetrain.turnGyro(0.75, 15, true, 5);
//
//        drivetrain.moveEncBadHardware(0.7, 2000, 6);
    }
}
