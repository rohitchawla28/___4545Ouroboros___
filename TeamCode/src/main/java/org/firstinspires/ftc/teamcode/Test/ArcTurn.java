package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;

@Disabled
@Autonomous (name = "ArcTurnTest", group = "Auto")
public class ArcTurn extends LinearOpMode {

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain(this);

        waitForStart();


    }

}
