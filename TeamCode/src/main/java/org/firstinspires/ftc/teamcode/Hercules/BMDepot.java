package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HerculesLibraries.Drivetrain;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Intake;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Lift;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVision;
import org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVisionWC;

import static org.firstinspires.ftc.teamcode.HerculesLibraries.Vision.BitmapVision.bitmapCubePosition;

@Autonomous
        (name = "DepotBM", group = "Auto")

public class BMDepot extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Intake intake;
    BitmapVisionWC vision;

    private String cubePosition = "unknown";

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(this);
        lift = new Lift(this);
        intake = new Intake(this);
        vision = new BitmapVisionWC(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        while (!isStarted()) {
            cubePosition = vision.sample();

            telemetry.addData("Cube position", cubePosition);
            telemetry.update();

        }

        waitForStart();

        lift.detachTime(drivetrain, intake);

        switch(cubePosition) {
            case "left" :
                drivetrain.turnPID(32, false, 0.65 / 32, 0.012, 0.02 / 32, 1.5);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 350, 3);
                sleep(500);

                break;

            case "center" :

                drivetrain.moveGyroStab(0.6, 700, 4);
                sleep(500);

//                intake.deployMarker();
//                sleep(500);
//
//                drivetrain.moveGyroStab(-0.7, 575, 4);
//                sleep(500);
//
//                drivetrain.turnPID(67, false, .7 / 67, 0.016, 0.02 / 67, 2);
//                sleep(500);
//
//                drivetrain.moveGyroStab(0.6, 850, 4);
//                sleep(500);
//
//                drivetrain.turnPID(40, false, .7 / 40, 0.017, 0.02 / 40, 2);
//                sleep(500);
//
//                drivetrain.moveEncoder(0.6, 850, 4);
//                sleep(500);

                break;

            case "right" :
                drivetrain.turnPID(31, true, 0.6 / 31, 0.011, 0.025 / 31,2);
                sleep(500);

                drivetrain.moveGyroStab(0.6, 250, 4);
                sleep(500);

                break;

            default:


                break;

        }
    }
}
