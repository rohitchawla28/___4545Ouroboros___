package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;

@Autonomous
        (name = "MarkerTest", group = "Auto")
public class Marker extends LinearOpMode {

    private Lift lift;
    private Intake intake;

    @Override
    public void runOpMode() throws InterruptedException{

        lift = new Lift(this);
        intake = new Intake(this);

        waitForStart();

        intake.deployMarker();

    }
}
