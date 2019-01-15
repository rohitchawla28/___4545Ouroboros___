package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    private double halfSpeedPivot = 1;

    private double tankLeftPower;
    private double tankRightPower;

    private double arcLeftStick;
    private double arcRightStick;

    private int halfSpeedCount = 0;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        armPivotL = hardwareMap.dcMotor.get("armPivotL");
        armPivotR = hardwareMap.dcMotor.get("armPivotR");
        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");

        door = hardwareMap.servo.get("door");
        intakePivotL = hardwareMap.servo.get("intakePivotL");
        intakePivotR = hardwareMap.servo.get("intakePivotR");
        collectL = hardwareMap.crservo.get("collectL");
        collectR = hardwareMap.crservo.get("collectR");

        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        armPivotL.setDirection(DcMotor.Direction.FORWARD);
        armPivotR.setDirection(DcMotor.Direction.REVERSE);
        liftL.setDirection(DcMotor.Direction.FORWARD);
        liftR.setDirection(DcMotor.Direction.REVERSE);

        armPivotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armPivotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        collectL.setDirection(DcMotor.Direction.FORWARD);
        collectR.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    //====================================  DRIVETRAIN  ============================================

    public void tankDrive() {
        tankLeftPower = gamepad1.left_stick_y * halfSpeedMod;
        tankRightPower = gamepad1.right_stick_y * halfSpeedMod;

        if (Math.abs(tankLeftPower) > 0.08) {
            fl.setPower(tankLeftPower);
            bl.setPower(tankLeftPower);

        }
        else {
            fl.setPower(0);
            bl.setPower(0);

        }

        if (Math.abs(tankRightPower) > 0.08) {
            fr.setPower(tankRightPower);
            br.setPower(tankRightPower);

        }
        else {
            fr.setPower(0);
            br.setPower(0);

        }

    }

    public void arcadeDrive() {
        arcLeftStick = gamepad1.left_stick_y /* * halfSpeedMod */;
        arcRightStick = gamepad1.right_stick_x /* * halfSpeedMod */;

        double leftPower = arcLeftStick + arcRightStick;
        double rightPower = arcLeftStick - arcRightStick;

        if (Math.abs(arcLeftStick) > 0.08 || Math.abs(arcRightStick) > 0.08) {
            fl.setPower(leftPower);
            fr.setPower(rightPower);
            bl.setPower(leftPower);
            br.setPower(rightPower);

        }
        else {
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
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

    //=================================  MANIPULATOR METHODS  ======================================

    public void lift() {
        double liftPower = gamepad2.right_stick_y;

        if (Math.abs(liftPower) > .08) {
            liftL.setPower(liftPower);
            liftR.setPower(liftPower);

        }
        else {
            liftL.setPower(0);
            liftR.setPower(0);

        }

    }

    public void armPivot() {
        double pivotPower = gamepad2.left_stick_y * halfSpeedPivot;

        if (Math.abs(pivotPower) > .08) {
            armPivotL.setPower(pivotPower);
            armPivotR.setPower(pivotPower);

        }
        else {
            armPivotL.setPower(0);
            armPivotR.setPower(0);

        }

    }

    public void intakePivot() {
        if (gamepad2.b) {
            while (gamepad2.b) {
            }
            //collection
            intakePivotL.setPosition(0.4);
            intakePivotR.setPosition(0.6);

        }

        if (gamepad2.y) {
            while (gamepad2.y) {

            }
            //deposit
            intakePivotL.setPosition(0.9);
            intakePivotR.setPosition(0.05);
        }


    }
    public void halfSpeedPivot(){
        if (gamepad2.a){
            while(gamepad2.a){

            }
            halfSpeedPivot = 0.5;

        }

        if (gamepad2.x) {
            while (gamepad2.x) {

            }
            halfSpeedPivot = 1;
        }
    }


    public void door() {
        if (gamepad1.left_bumper) {
            while (gamepad1.left_bumper) {
            }
            door.setPosition(0.6);
        }

        if (gamepad1.right_bumper) {
            while (gamepad1.right_bumper) {
            }
            door.setPosition(0);
        }

    }

    public void collect() {
        if (gamepad2.right_bumper) {
            collectL.setPower(-0.6);
            collectR.setPower(-0.6);

        }
        else {
            collectL.setPower(0);
            collectR.setPower(0);

        }

        if (gamepad2.left_bumper) {
            collectL.setPower(0.6);
            collectR.setPower(0.6);

        }
        else {
            collectL.setPower(0);
            collectR.setPower(0);

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