package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


public abstract class OPMode extends OpMode {

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    public DcMotor extend;

    public DcMotor liftL;
    public DcMotor liftR;

    public Servo marker;

    public Servo lockLiftL;
    public Servo lockLiftR;

    @Override
    public void init() {

        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        extend = hardwareMap.dcMotor.get("extend");

        liftL = hardwareMap.get(DcMotor.class, "liftL");
        liftR = hardwareMap.get(DcMotor.class, "liftR");

        lockLiftL = hardwareMap.get(Servo.class, "lockLiftL");
        lockLiftR = hardwareMap.get(Servo.class, "lockLiftR");

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        extend.setDirection(DcMotor.Direction.FORWARD);

        liftL.setDirection(DcMotor.Direction.FORWARD);
        liftR.setDirection(DcMotor.Direction.REVERSE);

        marker = hardwareMap.servo.get("marker");

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void lockLift() {

        //open
        if (gamepad2.a){
             lockLiftL.setPosition(0.2);
             lockLiftR.setPosition(0.55);
        }

        //lock
        if(gamepad2.b) {
            lockLiftL.setPosition(0.4);
            lockLiftR.setPosition(0.25);
        }
    }

    public void lift() {

        double liftPower = gamepad2.right_stick_y;

        if (liftPower > .1) {
            liftL.setPower(liftPower);
            liftR.setPower(liftPower);
        }

        else if (liftPower < -.1) {
            liftL.setPower(liftPower);
            liftR.setPower(liftPower);
        }
        else {
            liftL.setPower(0);
            liftR.setPower(0);
        }
    }

    public void extend() {

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

        if (gamepad1.y) {

            double initEncoder = extend.getCurrentPosition();

            // Make accuracy a range
            while ((extend.getCurrentPosition() - initEncoder) > 0) {

                extend.setPower(-0.6);

            }
        }
    }

//    public void collect() {
//
//        while (gamepad1.left_bumper) {
//
//            collectL.setPower(-0.6);
//            collectR.setPower(0.6);
//
//        }
//        while (gamepad1.right_bumper) {
//
//            collectL.setPower(0.6);
//            collectR.setPower(-0.6);
//
//        }
//
//        collectR.setPower(0);
//        collectL.setPower(0);
//
//    }
//
//    public void output() {
//
//        double initEncoder = 0;
//        double upEncoder = 0 /* TEST VALUE */;
//        double accuracy = 0 /* TEST VALUE */;
//
//        if (gamepad2.dpad_up) {
//
//            initEncoder = dump.getCurrentPosition();
//
//            while (initEncoder < (upEncoder - accuracy)) {
//
//                dump.setPower(0.7);
//
//            }
//
//            doorR.setPosition(0.5);
//            doorL.setPosition(0.5);
//
//        }
//
//        // down will reset the dump
//        if (gamepad2.dpad_down) {
//
//            while (-(dump.getCurrentPosition()) < (initEncoder + accuracy)) {
//
//                dump.setPower(-0.7);
//
//            }
//
//            doorR.setPosition(0);
//            doorL.setPosition(0);
//
//        }
//    }

     //Macro hang
    public void macroHang() {

//        double upEncoder = 0/* TEST VALUE */;
//        double accuracy = 0/* TEST VALUE */;
//        double hangClearance = 0/* TEST VALUE */;
//
//        resetEncoders();
//
//        double initEncoder = (liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2;
//
//        if (gamepad2.a) {
//
//            while (Math.abs(((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2) - initEncoder) < upEncoder - accuracy) {
//
//                liftL.setPower(0.5);
//                liftR.setPower(0.5);
//
//            }
//            liftL.setPower(0);
//            liftR.setPower(0);
//
//        }
    }


    //================================== UTILITY METHODS ===========================================

    public void resetEncoders() {

        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}