package ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import TeleOp.OPMode;

public class Marker {

    private LinearOpMode opMode;
    private OPMode OPmode;

    private Servo marker;

    public Marker (LinearOpMode opMode) {
        this.opMode = opMode;

        marker = this.opMode.hardwareMap.servo.get("marker");

        marker.setPosition(0.6);
    }
    public void markerOut() {

        marker.setPosition(0);

        opMode.sleep(2000);

        marker.setPosition(0.6);

    }


}
