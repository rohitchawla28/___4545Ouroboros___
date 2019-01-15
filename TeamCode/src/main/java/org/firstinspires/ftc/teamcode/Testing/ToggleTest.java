package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
        (name = "ToggleTest", group = "Controlled")

public class ToggleTest extends OpMode {

    private boolean a_halfSpd = false;

    private boolean b_halfSpd = false;

    private boolean y_halfSpd = false;
    private boolean lastPassed = false;

    private int count = 0;

    public void init() {
        telemetry.addLine("Initialized");

    }

    // objective is to test different methods of toggles to see which is the most accurate
    public void loop() {
        // method #1
        if (gamepad1.a) {
            while (gamepad1.a) { }
            a_halfSpd = !a_halfSpd;

            telemetry.addData("The toggle is ", a_halfSpd);
            telemetry.update();

        }

        // method #2
        if (gamepad1.b && (count % 2 == 0)) {
            b_halfSpd = true;
            count++;

            telemetry.addData("The toggle is ", b_halfSpd);
            telemetry.update();

        }

        if (gamepad1.b && (count % 2 == 1)) {
            b_halfSpd = false;
            count++;

            telemetry.addData("The toggle is ", b_halfSpd);
            telemetry.update();

        }

        //method #3
        boolean yPressed = gamepad1.y;

        if (yPressed && !lastPassed) {

            y_halfSpd = !y_halfSpd;
            if (y_halfSpd) {
                telemetry.addLine("Switched to half speed");
                telemetry.update();

            }
            else {
                y_halfSpd = false;

                telemetry.addLine("Switched back to normal speed");
                telemetry.update();

            }

        }
        lastPassed = yPressed;

    }

}
