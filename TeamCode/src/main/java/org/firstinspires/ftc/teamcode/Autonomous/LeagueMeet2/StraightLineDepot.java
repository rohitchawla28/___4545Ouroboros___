package org.firstinspires.ftc.teamcode.Autonomous.LeagueMeet2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ChickHicks.Drivetrain;
import org.firstinspires.ftc.teamcode.ChickHicks.Lift;
import org.firstinspires.ftc.teamcode.ChickHicks.Sensors;

@Disabled
@Autonomous
        (name = "StrateLineDepot2", group = "Auto")

public class StraightLineDepot extends LinearOpMode {
    Drivetrain drivetrain;
    Sensors sensors;
    Lift lift;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("Starting Init");
        telemetry.update();

        drivetrain = new Drivetrain(this);
        sensors = new Sensors(this, true);
        lift = new Lift(this);

        telemetry.addLine("Ready to get this W");
        telemetry.update();

        waitForStart();

//        lift.detachTime();
//
        drivetrain.moveEncBadHardwareForward(0.5, 1900, 4);

        sleep(300);


//        drivetrain.turnGyro(0.75, 50, true, 5);
//
//        drivetrain.moveEncBadHardware(0.5, 500, 6);
//
//        drivetrain.turnGyro(0.75, 30, true, 5);
//
//        drivetrain.moveEncBadHardware(0.5, 150, 6);
//
//        drivetrain.turnGyro(0.75, 15, true, 5);
//
//        drivetrain.moveEncBadHardware(0.7, 2000, 6);
    }
}
