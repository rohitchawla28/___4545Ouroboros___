package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;

@Autonomous
        (name = "LiftAuto", group = "Auto")
public class LiftAuto extends LinearOpMode {

    private Lift lift;

    @Override
    public void runOpMode() throws InterruptedException{

        lift = new Lift(this);

        waitForStart();

        lift.moveLift(1, 1.5, true);

        lift.moveLift(1, 1, false);

        lift.moveLift(1, 2, true);

        lift.moveLift(1, 1.5, false);

    }
}
