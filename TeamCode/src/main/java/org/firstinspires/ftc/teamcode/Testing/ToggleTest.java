package Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
        (name = "ToggleTest", group = "Controlled")

public class ToggleTest extends OpMode {

    boolean a_halfSpd = false;

    boolean b_halfSpd = false;

    boolean y_halfSpd = false;
    boolean lastPassed = false;

    public void init() {
        telemetry.addLine("Initialized");
    }

    public void loop() {

        // method #1
        if (gamepad1.a) {
            while (gamepad1.a) {

            }
            a_halfSpd = !a_halfSpd;

            telemetry.update();

            telemetry.addData("The toggle is ", a_halfSpd);
        }


        // method #2
        int count = 0;

        if (gamepad1.b && (count % 2 == 0)) {
            b_halfSpd = true;
            count++;

            telemetry.update();
            telemetry.addData("The toggle is ", b_halfSpd);
        }

        if (gamepad1.b && (count % 2 == 1)) {
            b_halfSpd = false;
            count++;

            telemetry.update();
            telemetry.addData("The toggle is ", b_halfSpd);
        }

        //method #3
        boolean yPressed = gamepad1.y;

        if (yPressed && !lastPassed) {

            y_halfSpd = !y_halfSpd;
            if (y_halfSpd) {
                telemetry.update();
                telemetry.addLine("Switched to half speed");
            }
            else {
                y_halfSpd = false;

                telemetry.update();
                telemetry.addLine("Switched back to normal speed");
            }

        }
        lastPassed = yPressed;


    }

}
