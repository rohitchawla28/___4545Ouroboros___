package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;

@Disabled
@Autonomous
        (name = "PID", group = "Auto")

public class PID extends LinearOpMode{

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);

        waitForStart();

        if (gamepad1.a) {
            //drivetrain.movePID();
        }

        if (gamepad1.b) {
            //drivetrain.turnPID();
        }

        if (gamepad1.x) {
            //drivetrain.moveGyroStabPID();
        }



    }

}
