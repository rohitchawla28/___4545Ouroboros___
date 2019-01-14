package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;

public class LM3Depot extends LinearOpMode {
    Drivetrain drivetrain;
    Intake intake;
    Lift lift;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        intake = new Intake(this);
        lift = new Lift(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();


        drivetrain.moveEncoder(0.6, 475, 4);
        sleep(500);

        lift.moveArmUp();
        sleep(500);

        drivetrain.moveEncoder(-0.6, 300, 4);
        sleep(500);

    }
}
