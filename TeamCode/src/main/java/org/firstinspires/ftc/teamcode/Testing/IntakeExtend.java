package org.firstinspires.ftc.teamcode.Testing;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.configuration.ModernRoboticsMotorControllerParams;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.TeleOp.OPMode;

@TeleOp
public class IntakeExtend extends OpMode {

    public DcMotor extend;

    private ElapsedTime time;

    @Override
    public void init() {

        extend = hardwareMap.dcMotor.get("extend");

        extend.setDirection(DcMotor.Direction.FORWARD);

        time = new ElapsedTime();
    }

    public void loop() {

        telemetry.addData("Time", time.seconds());

        if (gamepad1.left_trigger > 0.1) {
            extend.setPower(gamepad1.left_trigger);
        }
        else {
            extend.setPower(0);
        }

        if (gamepad1.right_trigger > 0.1) {
            extend.setPower(-(gamepad1.right_trigger));
        }
        else {
            extend.setPower(0);
        }

        if (gamepad1.a) {

            double initEncoder = extend.getCurrentPosition();

            // Make accuracy a range
            while ((extend.getCurrentPosition() - initEncoder) > 0) {

                extend.setPower(-0.6);

            }
        }
    }

}
