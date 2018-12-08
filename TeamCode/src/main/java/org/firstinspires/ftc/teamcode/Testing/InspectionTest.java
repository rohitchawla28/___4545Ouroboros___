package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Intake;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;


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

        sleep(450);

        drivetrain.moveEncoder(0.4,200,3);
    }
}
