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
    public Servo collectPivotL;
    public Servo collectPivotR;

    public CRServo collectL;
    public CRServo collectR;

    double tankLeftPower = gamepad1.left_stick_y;
    double tankRightPower = gamepad1.right_stick_y;

    double arcLeftStick = gamepad1.left_stick_y;
    double arcRightStick = gamepad1.right_stick_x;

    int pos = 0;

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
//        collectPivotL = hardwareMap.servo.get("collectPivotL");
//        collectPivotR = hardwareMap.servo.get("collectPivotR");
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
        } else {
            fl.setPower(0);
            bl.setPower(0);
        }

        if (Math.abs(tankRightPower) > 0.8) {
            fr.setPower(tankRightPower);
            br.setPower(tankRightPower);
        } else {
            fr.setPower(0);
            br.setPower(0);
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

    //=====================================  MANIPULATOR  ==========================================

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
        double pivotPower = gamepad2.left_stick_y/2;

        if (pivotPower > 0.1) {
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
            if (pos % 2 == 0) {
//                collectPivotL.setPosition();
//                collectPivotR.setPosition();
            }
            else {
//                collectPivotL.setPosition();
//                collectPivotR.setPosition();
            }
            pos++;
        }
    }

    public void collect() {
        while (gamepad1.left_bumper) {
            collectL.setPower(0.7);
            collectR.setPower(0.7);
        }

        while (gamepad1.right_bumper) {
            collectL.setPower(-0.6);
            collectR.setPower(-0.6);
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