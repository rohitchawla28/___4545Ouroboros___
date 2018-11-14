package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp
        (name = "HangTest", group = "Controlled")

public class HangTest extends OpMode {

    private DcMotor liftL;
    private DcMotor liftR;

    @Override
    public void init() {

        //Hardware mapping
        liftL = hardwareMap.get(DcMotor.class, "liftL");
        liftR = hardwareMap.get(DcMotor.class, "liftR");

        liftL.setDirection(DcMotor.Direction.FORWARD);
        liftR.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void loop() {
        liftL.setPower(gamepad1.right_stick_y);
        liftR.setPower(gamepad1.right_stick_y);
    }

}
