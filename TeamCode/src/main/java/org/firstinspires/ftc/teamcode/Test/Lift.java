package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hercules.TeleLib;

@TeleOp
        (name = "ElevatorTest", group = "Controlled")

public class Lift extends TeleLib {

    public void loop() {
        double rightStick = gamepad2.right_stick_y;
        double leftStick = gamepad2.left_stick_y;

        if (Math.abs(rightStick) > 0.08) {
            liftR.setPower(rightStick);

        }
        else if (Math.abs(leftStick) > 0.08) {
            liftL.setPower(leftStick);

        }
        else {
            liftL.setPower(0);
            liftR.setPower(0);

        }

    }

}
