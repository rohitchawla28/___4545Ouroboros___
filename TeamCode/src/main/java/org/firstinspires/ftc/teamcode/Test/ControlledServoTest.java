package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
        (name = "ControllerServoTest", group = "Controlled")

public class ControlledServoTest extends OpMode {

    private Servo door;
    private Servo lock;

    @Override
    public void init() {
        door = hardwareMap.servo.get("door");
        lock = hardwareMap.servo.get("lock");

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void loop() {
        if (gamepad1.dpad_up) {
            while (gamepad1.dpad_up) { }
            door.setPosition(door.getPosition() + 0.05);

            telemetry.addData("Door Position", door.getPosition());
            telemetry.update();

        }

        if (gamepad1.dpad_down) {
            while (gamepad1.dpad_down) { }
            door.setPosition(door.getPosition() - 0.05);

            telemetry.addData("Door Position", door.getPosition());
            telemetry.update();

        }

        if (gamepad1.dpad_left) {
            while (gamepad1.dpad_left) { }
            lock.setPosition(lock.getPosition() + 0.05);

            telemetry.addData("Lock position", lock.getPosition());
            telemetry.update();

        }

        if (gamepad1.dpad_right) {
            while (gamepad1.dpad_right) { }
                lock.setPosition(lock.getPosition() - 0.05);

                telemetry.addData("Lock position", lock.getPosition());
                telemetry.update();

        }

    }

}
