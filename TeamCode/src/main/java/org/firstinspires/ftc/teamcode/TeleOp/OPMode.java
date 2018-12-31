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

    public DcMotor armPivotL;
    public DcMotor armPivotR;
    public DcMotor liftL;
    public DcMotor liftR;

    public Servo door;
    public Servo intakePivotL;
    public Servo intakePivotR;

    public CRServo collectL;
    public CRServo collectR;

    private double halfSpeedMod = 1;

    // I am probably just being really dumb right now, but do we need to put these variables for the tankLeftPower/tankRightPower
    // inside the method so it keeps running in the loop? Because won't what I currently made just initialize the variable once?

    double tankLeftPower = gamepad1.left_stick_y * halfSpeedMod;
    double tankRightPower = gamepad1.right_stick_y * halfSpeedMod;

    double arcLeftStick = gamepad1.left_stick_y  /* * halfSpeedMod */ ;
    double arcRightStick = gamepad1.right_stick_x /* * halfSpeedMod */ ;

    private int halfSpeedCount = 0;
    private int intakePivotPosCount = 0;
    private int doorPosCount = 0;
    private int collectInCount = 0;
    private int collectOutCount = 0;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

//        armPivotL = hardwareMap.dcMotor.get("armPivotL");
//        armPivotR = hardwareMap.dcMotor.get("armPivotR");
//        liftL = hardwareMap.dcMotor.get("liftL");
//        liftR = hardwareMap.dcMotor.get("liftR");

//        door = hardwareMap.servo.get("door");
//        intakePivotL = hardwareMap.servo.get("intakePivotL");
//        intakePivotR = hardwareMap.servo.get("intakePivotR");
//        collectL = hardwareMap.crservo.get("collectL");
//        collectR = hardwareMap.crservo.get("colllectR");

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

//        armPivotL.setDirection(DcMotor.Direction.FORWARD);
//        armPivotR.setDirection(DcMotor.Direction.REVERSE);
//        liftL.setDirection(DcMotor.Direction.FORWARD);
//        liftR.setDirection(DcMotor.Direction.REVERSE);

//        collectL.setDirection(DcMotor.Direction.FORWARD);
//        collectR.setDirection(DcMotor.Direction.REVERSE);

//        collectPivotL.setPosition();
//        collectPivotR.setPosition();
//        door.setPosition();

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    //====================================  DRIVETRAIN  ============================================

    public void tankDrive() {
        if (Math.abs(tankLeftPower) > .08) {
            fl.setPower(tankLeftPower);
            bl.setPower(tankLeftPower);

        }
        else {
            fl.setPower(0);
            bl.setPower(0);

        }

        if (Math.abs(tankRightPower) > 0.8) {
            fr.setPower(tankRightPower);
            br.setPower(tankRightPower);

        }
        else {
            fr.setPower(0);
            br.setPower(0);

        }

    }

    public void halfSpeed () {
        if (gamepad1.a) {
            while (gamepad1.a) {

            }

            if (halfSpeedCount % 2 == 0) {
                halfSpeedMod = 0.5;

            }
            else {
                halfSpeedMod = 1.0;

            }
            halfSpeedCount++;

        }

    }

    public void arcadeDrive() {
        double leftPower = arcLeftStick + arcRightStick;
        double rightPower = arcLeftStick - arcRightStick;

        if (Math.abs(arcLeftStick) > 0.1 || Math.abs(arcRightStick) > 0.1) {
            fl.setPower(leftPower);
            bl.setPower(leftPower);
            fr.setPower(rightPower);
            br.setPower(rightPower);

        }

    }

    //=================================  MANIPULATOR METHODS  ======================================

    public void lift() {
        double liftPower = gamepad2.right_stick_y;

        if (Math.abs(liftPower) > .1) {
            liftL.setPower(liftPower);
            liftR.setPower(liftPower);

        }
        else {
            liftL.setPower(0);
            liftR.setPower(0);

        }

    }

    public void armPivot() {
        double pivotPower = (gamepad2.left_stick_y / 2);

        if (Math.abs(pivotPower) > 0.1) {
            armPivotL.setPower(pivotPower);
            armPivotR.setPower(pivotPower);

        }
        else {
            armPivotL.setPower(0);
            armPivotR.setPower(0);

        }

    }

    public void intakePivot() {
        if (gamepad2.a) {
            if (intakePivotPosCount % 2 == 0) {
                intakePivotL.setPosition(0);
                intakePivotR.setPosition(0);

            }
            else {
                intakePivotL.setPosition(0.5);
                intakePivotR.setPosition(0.5);

            }
            intakePivotPosCount++;

        }

    }

    public void door() {
        if (gamepad2.b) {
            if (doorPosCount % 2 == 0) {
                door.setPosition(0);

            }
            else {
                door.setPosition(0.5);

            }

        }
        doorPosCount++;

    }

    public void collect() {
        if (gamepad2.left_bumper) {
            if (collectInCount % 2 == 0) {
                collectL.setPower(0.6);
                collectR.setPower(0.6);

            }
            else {
                collectL.setPower(0);
                collectR.setPower(0);

            }
            collectInCount++;

        }

        if (gamepad2.right_bumper) {
            if (collectOutCount % 2 == 0) {
                collectL.setPower(-0.6);
                collectR.setPower(-0.6);

            }
            else {
                collectL.setPower(0);
                collectR.setPower(0);

            }
            collectOutCount++;

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