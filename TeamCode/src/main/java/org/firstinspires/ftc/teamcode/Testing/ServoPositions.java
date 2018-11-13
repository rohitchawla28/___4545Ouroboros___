package Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous
        (name = "ServoTesting", group = "Auto")

public class ServoPositions extends LinearOpMode {

    private Servo marker;
    private Servo lockLiftL;
    private Servo lockLiftR;

    public void initialize() throws InterruptedException{
        marker = hardwareMap.servo.get("marker");
        lockLiftL = hardwareMap.servo.get("lockLiftL");
        lockLiftR = hardwareMap.servo.get("lockLiftR");

        Thread.sleep(2000);

        lockLiftL.setPosition(0.2);
        lockLiftR.setPosition(0.55);
        marker.setPosition(0.6);
    }

    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();

        lockLiftL.setPosition(0.4);
        lockLiftR.setPosition(0.25);
        marker.setPosition(0);


        Thread.sleep(5000);
    }

}
