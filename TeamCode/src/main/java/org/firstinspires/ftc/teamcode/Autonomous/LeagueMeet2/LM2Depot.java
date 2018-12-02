package org.firstinspires.ftc.teamcode.Autonomous.LeagueMeet2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;


@Autonomous
        (name = "LM2Depot", group = "Auto")

public class LM2Depot extends LinearOpMode {

    Drivetrain drivetrain;
    Lift lift;
    TensorFlowDetection vision;
    //Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
//        vision = new TensorFlowDetection(this);

//        telemetry.addData("Mineral Position", vision.cubePosition);
        telemetry.addLine("Initialized");
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
        drivetrain.moveEncoder(0.5, 500, 4);

        sleep(400);

        // Will turn to go around sampling
        drivetrain.turnGyro(0.4, 60, false, 4);

        sleep(400);

        //move to wall
        drivetrain.moveEncoder(0.5, 1900, 4);

        sleep(400);

        // Will turn towards the depot
        drivetrain.turnGyro(0.4, 85, true, 4);

        sleep(400);

        //move to depot
        drivetrain.moveEncoder(0.5, 2050, 4);

        sleep(400);

        //drop marker
//        intake.markerOut();
//
//        sleep(150);

        //move to almost the crater
        drivetrain.moveEncoder(-0.5, 2150, 4);

        sleep(400);

        //move to touch the crater
        drivetrain.moveEncoder(-0.3, 300, 4);
    }

}
