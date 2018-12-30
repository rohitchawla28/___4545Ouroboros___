package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous
        (name = "ServoTesting", group = "Auto")

public class ServoPositions extends LinearOpMode {

    public void initialize() throws InterruptedException{
        sleep(2000);

        // insert servo movements

        sleep(1000);

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();

        // insert servo movements

        sleep(1000);

    }

}
