package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;

@Disabled
@Autonomous
        (name = "StrateLineCrater2", group = "Auto")

public class StraightLineCrater extends LinearOpMode{

    Drivetrain drivetrain;
    Lift lift;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addLine("Init Starting");
        telemetry.update();

        drivetrain = new Drivetrain(this);
        lift = new Lift(this);

        telemetry.addLine("Ready to get this W");
        telemetry.update();

        waitForStart();

        //lift.detachTime();

        //lift.shortUp();

        //drivetrain.turnGyro(0.2, 10,true, 3);

        //lift.shortDown();

        drivetrain.moveEncBadHardwareForward(0.5, 1200, 4);

        sleep(200);

        drivetrain.moveEncBadHardwareForward(0.3, 300, 3);
    }
}