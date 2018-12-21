package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
        (name = "DriveTestTele", group = "Controlled")

public class DriveTestTele extends OpMode {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

//    private double halfSpeedMod = 1;
//    private int reverseDriveMod = 1;
//
//    boolean halfCount = false;
//    boolean reverseCount = false;

    @Override
    public void init() {

        //Hardware mapping
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void tankDrive() {

        double leftDrive = gamepad1.left_stick_y /* * halfSpeedMod * reverseDriveMod*/;
        double rightDrive = gamepad1.right_stick_y /* * halfSpeedMod * reverseDriveMod*/;

        if (Math.abs(leftDrive) > .1) {
            fl.setPower(leftDrive);
            bl.setPower(leftDrive);
        }
        else {
            fl.setPower(0);
            bl.setPower(0);
        }

        if (Math.abs(rightDrive) > .1) {
            fr.setPower(rightDrive);
            br.setPower(rightDrive);
        }
        else {
            fr.setPower(0);
            br.setPower(0);
        }

//        if (gamepad1.b && !halfCount) {
//            while (gamepad1.b){
//            }
//            halfCount = !halfCount;
//            halfSpeedMod = 0.5;
//
//            telemetry.addLine("Half Speed On");
//            telemetry.update();
//
//        }
//
//        if (gamepad1.b && halfCount) {
//            while(gamepad1.b){
//            }
//            halfCount = !halfCount;
//            halfSpeedMod = 1;
//
//            telemetry.addLine("Half Speed Off");
//            telemetry.update();
//
//        }

//        if (gamepad1.y && !reverseCount) {
//            while (gamepad1.y) {
//            }
//            reverseDriveMod = -1;
//            reverseCount = !reverseCount;
//
//            telemetry.addLine("Reverse Mode On");
//            telemetry.update();
//
//        }
//        if (gamepad1.y && reverseCount) {
//            while (gamepad1.y){
//            }
//            reverseDriveMod = 1;
//            reverseCount = !reverseCount;
//
//            telemetry.addLine("Reverse Mode Off");
//            telemetry.update();
//
//        }
    }

    public void loop() {

        tankDrive();

    }

}