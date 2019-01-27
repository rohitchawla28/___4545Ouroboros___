package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;

@Autonomous
        (name = "InspectionTest", group = "Auto")

public class InspectionTest extends LinearOpMode{
    private Drivetrain drivetrain;

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        // objective is to show inspectors that our robot can endlessly do a movement and then stop when needed
        while (opModeIsActive()) {
            drivetrain.turn(0.3, true);

        }

    }

}
