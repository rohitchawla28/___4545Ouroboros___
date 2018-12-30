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
    Lift lift;
    // Vuforia vuforia;
    // OpenCVDetection vision;



    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        // vuforia = new Vuforia(this);
        // vision = new OpenCVDetection(this, vuforia);
         lift = new Lift(this);

        telemetry.addLine(" Init finished");
        telemetry.update();

        waitForStart();

        // vision.process(vuforia.convertToMat());

        // telemetry.addLine("The cube is in position " + vision.cubePositionAlt);
        // telemetry.update();

        // lift.detachEncoder(drivetrain);

        // lift.extendSampling(drivetrain, vuforia);

        drivetrain.turnPI(90, true, .33/90, 0.013, 15);
    }
}
