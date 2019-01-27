package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;

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
