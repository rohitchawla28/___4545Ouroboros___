package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
        (name = "TooOverPowered", group = "Controlled")

public class TooOverPowered extends OPMode {

    private double halfSpeedMod = 1;
    private boolean halfSpeed = false;
    private boolean reverseDrive = false;

    private double leftDrive;
    private double rightDrive;

    public void loop() {

        telemetry.addData("Extend encoder", extend.getCurrentPosition());
        telemetry.update();

        leftDrive = gamepad1.left_stick_y * halfSpeedMod;
        rightDrive = gamepad1.right_stick_y * halfSpeedMod;

        //puts reverse drive on
        if (gamepad1.y && !reverseDrive) {
            while (gamepad1.y) {
            }
            reverseDrive = !reverseDrive;

            leftDrive = gamepad1.right_stick_y * halfSpeedMod * -1;
            rightDrive = gamepad1.left_stick_y * halfSpeedMod * -1;

            telemetry.addLine("Reverse Drive On");
            telemetry.update();
        }
        //turns reverse drive off
        if (gamepad1.y && reverseDrive) {
            while (gamepad1.y) {
            }
            reverseDrive = !reverseDrive;

            leftDrive = gamepad1.left_stick_y * halfSpeedMod;
            rightDrive = gamepad1.right_stick_y * halfSpeedMod;

            telemetry.addLine("Reverse Drive Off");
            telemetry.update();
        }

        if (leftDrive > .1) {
            fl.setPower(leftDrive);
            bl.setPower(leftDrive);
        }
        else if (leftDrive < -.1) {
            fl.setPower(leftDrive);
            bl.setPower(leftDrive);
        }
        else {
            fl.setPower(0);
            bl.setPower(0);
        }

        if (rightDrive > .1) {
            fr.setPower(rightDrive);
            br.setPower(rightDrive);
        }
        else if (rightDrive < -.1) {
            fr.setPower(rightDrive);
            br.setPower(rightDrive);
        }
        else {
            fr.setPower(0);
            br.setPower(0);
        }

        if (gamepad1.b && !halfSpeed) {
            while (gamepad1.b){
            }
            halfSpeed = !halfSpeed;
            halfSpeedMod = 0.5;

            telemetry.addLine("Half Speed On");
            telemetry.update();
        }
        if (gamepad1.b && halfSpeed) {
            while(gamepad1.b){
            }
            halfSpeed = !halfSpeed;
            halfSpeedMod = 1;

            telemetry.addLine("Half Speed Off");
            telemetry.update();
        }


        if (gamepad1.y && reverseDrive) {
            while(gamepad1.y){
            }
            halfSpeed = !halfSpeed;
            halfSpeedMod = 1;

            telemetry.addLine("Half Speed Off");
            telemetry.update();
        }


        lift();
        extend();
    }
}
