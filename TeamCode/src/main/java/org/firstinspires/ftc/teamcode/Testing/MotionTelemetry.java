package Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ChickHicks.Drivetrain;

@Disabled
@Autonomous
        (name = "MotionTelemetry", group = "Teleop")

public class MotionTelemetry extends LinearOpMode {

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);

        waitForStart();

        drivetrain.composeTelemetryEncoders();
        drivetrain.composeTelemetryGyro();
        telemetry.update();

        if (gamepad1.a) {

            drivetrain.resetEncoders();

        }

    }
}

