package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;

@Autonomous
        (name = "TurnTest", group = "Auto")
public class Turn extends LinearOpMode {

    private Drivetrain drivetrain;

    @Override
    public void runOpMode() throws InterruptedException{

        drivetrain = new Drivetrain(this);

        waitForStart();

        drivetrain.turnPID(100, true, .6 / 100, 0.01, 0.03 / 100, 2);



    }
}
