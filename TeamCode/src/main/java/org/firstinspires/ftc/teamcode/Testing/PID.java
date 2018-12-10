package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;

@Autonomous
        (name = "PID", group = "Auto")

public class PID extends LinearOpMode{

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);

        waitForStart();

        drivetrain.turnP(90 ,true, .6/90);

    }

}
