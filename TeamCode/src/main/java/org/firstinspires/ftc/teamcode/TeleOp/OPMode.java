package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public abstract class OPMode extends OpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;

    //private DcMotor extend;
    //private DcMotor dump;
    private DcMotor liftL;
    private DcMotor liftR;

    //private CRServo collectL;
    //private CRServo collectR;

    //private Servo tiltL;
    //private Servo tiltR;
    //private Servo doorL;
    //private Servo doorR;
    private Servo lockLiftL;
    private Servo lockLiftR;
    private Servo marker;

    @Override
    public void init() {

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        //extend = hardwareMap.get(DcMotor.class, "extend");

        //collectL = hardwareMap.get(CRServo.class, "collectL");
        //collectR = hardwareMap.get(CRServo.class, "collectR");

        //tiltL = hardwareMap.get(Servo.class, "tiltL");
        //tiltR = hardwareMap.get(Servo.class, "tiltR");

        //dump = hardwareMap.get(DcMotor.class, "dump");

        //doorL = hardwareMap.get(Servo.class, "doorL");
        //doorR = hardwareMap.get(Servo.class, "doorR");

        liftL = hardwareMap.get(DcMotor.class, "liftL");
        liftR = hardwareMap.get(DcMotor.class, "liftR");

        lockLiftL = hardwareMap.get(Servo.class, "lockLiftL");
        lockLiftR = hardwareMap.get(Servo.class, "lockLiftR");

//        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        //extend.setDirection(DcMotor.Direction.FORWARD);

        liftL.setDirection(DcMotor.Direction.FORWARD);
        liftR.setDirection(DcMotor.Direction.REVERSE);

        marker = hardwareMap.servo.get("marker");


        //collectL.setDirection(CRServo.Direction.REVERSE);
        //collectR.setDirection(CRServo.Direction.FORWARD);

        //servo movement to unfold servo tilt
        //intake.tiltUnfold();

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void setServoTele() {

        //marker up
        if (gamepad2.x){
            marker.setPosition(0.6);
        }

        //marker out
        if (gamepad2.y) {
            marker.setPosition(0);
        }

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

    public void sugatsune() {
//
//        double initEncoder = extend.getCurrentPosition();
//        double accuracy = 50;
//
//        while (gamepad1.left_trigger > 0.1) {
//
//            extend.setPower(gamepad1.left_trigger / 1.25);
//
//        }
//        while (gamepad1.right_trigger > 0.1) {
//
//            extend.setPower(-(gamepad1.right_trigger / 1.25));
//
//        }
//
//        if (gamepad1.a) {
//
//            // Make accuracy a range
//            while (-(extend.getCurrentPosition()) < (initEncoder - accuracy)) {
//
//                extend.setPower(-0.6);
//
//            }
//
//            doorR.setPosition(0.5);
//            doorL.setPosition(0.5);
//
//            tiltL.setPosition(0.25);
//            tiltR.setPosition(0.25);
//
//            doorR.setPosition(0);
//            doorL.setPosition(0);
//
//            tiltL.setPosition(0);
//            tiltR.setPosition(0);
//
//        }
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

        double upEncoder = 0/* TEST VALUE */;
        double accuracy = 0/* TEST VALUE */;
        double hangClearance = 0/* TEST VALUE */;

        resetEncoders();

        double initEncoder = (liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2;

        if (gamepad2.a) {

            while (Math.abs(((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2) - initEncoder) < upEncoder - accuracy) {

                liftL.setPower(0.5);
                liftR.setPower(0.5);

            }
            liftL.setPower(0);
            liftR.setPower(0);

        }
    }


    //================================== UTILITY METHODS ===========================================

    public void resetEncoders() {

        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}