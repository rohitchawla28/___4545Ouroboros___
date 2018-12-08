package org.firstinspires.ftc.teamcode.Autonomous.LeagueMeet2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

@Disabled
@Autonomous
        (name = "LM2Crater", group = "Auto")

public class LM2Crater extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
//        lift = new Lift(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        intake.extendSampling(drivetrain);

        //move to not hit lander
        drivetrain.moveEncoder(0.5, 500, 4);

        sleep(1000);

        // Will turn to go around sampling
        drivetrain.turnGyro(0.4, 60, false, 4);

        sleep(1000);

        //move to wall
        drivetrain.moveEncoder(0.5, 1900, 3);

        sleep(1000);

        //turn parallel to wall
        drivetrain.turnGyro(0.4, 29, false, 4);

        sleep(1000);

        //move along wall
        drivetrain.moveEncoder(0.6, 1700, 4);

        sleep(1000);

        //drop marker
        intake.markerOut();

        //backwards to almost crater
        drivetrain.moveEncoder(-0.6, 1900, 4);

        sleep(1000);

        //touch crater
        drivetrain.moveEncoder(-0.25, 400, 3);

    }
}
