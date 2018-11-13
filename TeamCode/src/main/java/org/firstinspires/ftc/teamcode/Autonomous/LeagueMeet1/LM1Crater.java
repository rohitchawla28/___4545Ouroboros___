package Autonomous.LeagueMeet1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robocol.RobocolParsable;

import ChickHicks.Drivetrain;
import ChickHicks.Lift;
import ChickHicks.Marker;

@Autonomous
        (name = "LM1Crater", group = "Auto")

public class LM1Crater extends LinearOpMode{
    Drivetrain drivetrain;
    Lift lift;
    Marker marker;

    @Override
    public void runOpMode() throws InterruptedException {

        drivetrain = new Drivetrain(this);
        marker = new Marker(this);
        lift = new Lift(this);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

//        //Detach from lander IN MILLISECONDS
//        lift.detachTime();
//
//        //prepare for movement
//        sleep(300);
//
//        //turn out of hook
//        drivetrain.turnGyro(0.35, 20, true, 3);
//
//        sleep(150);
//
//        //lower lift so can turn back to hook
//        lift.shortDown();
//
//        //turn back to center
//        drivetrain.turnGyro(0.35, 20, false, 3);
//
//        sleep(150);

        //hit mineral
        drivetrain.moveEncBadHardwareForward(0.5, 1042, 4);

        sleep(3000);

        //move backwards
        drivetrain.moveEncBadHardwareBackward(-0.4, 661, 3);

        //turn to face wall
        drivetrain.turnGyro(0.4, 90, false, 4);

        //move closer to wall
        drivetrain.moveEncBadHardwareForward(0.5, 1700, 4);

        //turn more to align with wall
        drivetrain.turnGyro(0.4, 15, false, 3);

        //move along wall
        drivetrain.moveEncBadHardwareForward(0.4, 958, 3);

        //turn to depot
        drivetrain.turnGyro(0.4, 10, false, 4);

        //move to depot
        drivetrain.moveEncBadHardwareForward(0.6, 1168, 4);

        //drop marker
        marker.markerOut();

        //backwards to almost crater
        drivetrain.moveEncBadHardwareBackward(-0.6, 3100, 4);

        //touch crater
        drivetrain.moveEncBadHardwareForward(-0.2, 100, 3);

    }
}
