package org.firstinspires.ftc.teamcode.Autonomous.FinalAutos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.OpenCVDetection;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.Vuforia;

@Autonomous
        (name = "Crater", group = "Auto")

public class Crater extends LinearOpMode{
    Drivetrain drivetrain;
//    Lift lift;
    //OpenCVDetection vision;
   // Vuforia vuforia;


    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
       // vuforia = new Vuforia(this);
       // vision = new OpenCVDetection(this, vuforia);

        telemetry.addLine(" Init finished");
        telemetry.update();

        //lift = new Lift(this);

        waitForStart();

//        while (opModeIsActive())
//        {
//            vision.process(vuforia.convertToMat());
//
//            telemetry.addLine("The cube is in position " + vision.cubePositionAlt);
//            telemetry.update();
//
//        }

        drivetrain.turnPI(90, true, .33/90, 0.013, 2);
    }
}
