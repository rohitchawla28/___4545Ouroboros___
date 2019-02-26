package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVisionWC;

@Disabled
@Autonomous
        (name = "StraightLineCrater", group = "Auto")

public class StraightLineCrater extends LinearOpMode{

    Drivetrain drivetrain;
    BitmapVisionWC vision;
    Lift lift;
    Intake intake;

    String cubePosition = "";

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(this);
        vision = new BitmapVisionWC(this);
        lift = new Lift(this);
        intake = new Intake(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        while (!isStarted()) {
            cubePosition = vision.sample();

        }

        waitForStart();

        lift.detachTime(drivetrain, intake);

        switch (cubePosition) {
            case "left" :
                drivetrain.turnPID(30, false, 0.7 / 32, 0.012, 0.02 / 32, 2);
                sleep(750);

                drivetrain.moveGyroStab(0.6, 250, 3);
                sleep(750);

                drivetrain.turnPID(15, true, 0.5 / 32, 0.01, 0.005 / 15, 2);
                sleep(750);

                drivetrain.moveEncoder(0.3, 200, 3);

            case "center" :
                drivetrain.moveGyroStab(0.6, 225, 3);
                sleep(750);

                drivetrain.moveEncoder(0.3, 200, 3);

            case "right" :
                drivetrain.turnPID(31, true, 0.6 / 31, 0.009, 0.025 / 31,2);
                sleep(750);

                drivetrain.moveGyroStab(0.6, 250, 3);
                sleep(750);

                drivetrain.turnPID(15, false, 0.5 / 32, 0.01, 0.005 / 15, 2);
                sleep(750);

                drivetrain.moveEncoder(0.3, 200, 3);

        }


    }

}
