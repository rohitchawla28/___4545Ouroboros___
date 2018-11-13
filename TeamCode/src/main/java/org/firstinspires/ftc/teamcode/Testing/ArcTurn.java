package Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import ChickHicks.Drivetrain;

@Disabled
@Autonomous (name = "ArcTurn", group = "Auto")
public class ArcTurn extends LinearOpMode {

    Drivetrain drivetrain;

    public void runOpMode() throws InterruptedException {

        Drivetrain drivetrain = new Drivetrain(this);


    }

}
