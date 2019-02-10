package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;

@Autonomous
        (name = "GyroStabilization", group = "Test" )
public class MoveGyro extends LinearOpMode {

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException{

        drivetrain = new Drivetrain(this);

        telemetry.addLine("initialized");
        telemetry.update();

        waitForStart();

        drivetrain.moveGyroStab(0.5, 20000, 30);


    }

}
