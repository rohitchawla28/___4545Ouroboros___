package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class OPMode extends OpMode {

    // drive motors, example: fl = front left
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    // arm pivot motors
    public DcMotor armPivotL;
    public DcMotor armPivotR;

    // lift extension motors
    public DcMotor liftL;
    public DcMotor liftR;

    // intake module servos
    public Servo door;
    public Servo intakePivotL;
    public Servo intakePivotR;

    // continuous rotation collection Vex 393 motors
    public CRServo collectL;
    public CRServo collectR;

    // servos lock the arm in for auto
    public Servo lockLift;
    public Servo unhookL;
    public Servo unhookR;

    // half speed variables for drivetrain and pivoting arm
    private double halfSpeedDrive = 1;
    private int halfSpeedDriveCount = 0;
    private double halfSpeedPivot = 1;

    // variables for gamepad joysticks (tank drive)
    private double tankLeftPower;
    private double tankRightPower;

    // variables for gamepad joysticks (arcade drive)
    private double arcLeftStick;
    private double arcRightStick;

    @Override
    // actions that occur when drive team presses init before match
    public void init() {
        // hardware mapping of all devices
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

        lockLift = hardwareMap.servo.get("lockLift");
        unhookL = hardwareMap.servo.get("unhookL");
        unhookR = hardwareMap.servo.get("unhookR");

        // setting reverse directions of right motors because they are mounted opposite
        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        armPivotL.setDirection(DcMotor.Direction.FORWARD);
        armPivotR.setDirection(DcMotor.Direction.REVERSE);

        liftL.setDirection(DcMotor.Direction.FORWARD);
        liftR.setDirection(DcMotor.Direction.REVERSE);

        collectL.setDirection(DcMotor.Direction.FORWARD);
        collectR.setDirection(DcMotor.Direction.REVERSE);

        // setting arm pivot motors to BRAKE mode instead of FLOAT
        // makes it easier for driver to control, won't just fall down because
        // of added resistance to motors
        armPivotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armPivotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // send message to phone to tell drive team when necessary actions have been completed
        telemetry.addLine("Initialized");
        telemetry.update();

    }

    //====================================  DRIVETRAIN  ============================================

    // in tank drive, the left side (left drive motors) of the chassis is controlled by the left joystick
    // and the right side is controlled by the right stick
    public void tankDrive() {
        tankLeftPower = gamepad1.left_stick_y /* * halfSpeedDrive */;
        tankRightPower = gamepad1.right_stick_y /* * halfSpeedDrive */;

        // 0.08 serves as a buffer zone so motors won't run if joystick resets to 0.00001 or small number other than 0
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

    // in arcade drive, the left stick controls all motors either moving forwards or backwards
    // the right stick controls turning left or right
    public void arcadeDrive() {
        arcLeftStick = gamepad1.left_stick_y * halfSpeedDrive;
        arcRightStick = gamepad1.right_stick_x * halfSpeedDrive;

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
        // added option for half speed on pivot arm so easier for driver to control when lining up to hang
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
        if (gamepad2.y) {
            //collection position
            intakePivotL.setPosition(0.6);
            intakePivotR.setPosition(0.3);

        }

        if (gamepad2.b) {
            //deposit position
            intakePivotL.setPosition(0.9);
            intakePivotR.setPosition(0);

        }

    }

    public void door() {
        if (gamepad1.left_bumper) {
            while (gamepad1.left_bumper) { }
            // collection position
            door.setPosition(0.6);

        }

        if (gamepad1.right_bumper) {
            while (gamepad1.right_bumper) { }
            // deposit position
            door.setPosition(0);

        }

    }

    public void collect() {
        if (gamepad2.left_bumper) {
            // spin in
            collectL.setPower(0.7);
            collectR.setPower(0.7);

        }
        else {
            collectL.setPower(0);
            collectR.setPower(0);

        }

        if (gamepad2.right_bumper) {
            // spin out
            collectL.setPower(-0.7);
            collectR.setPower(-0.7);

        }
        else {
            collectL.setPower(0);
            collectR.setPower(0);

        }

    }

    public void lockLift() {
        if (gamepad1.x) {
            lockLift.setPosition(0);

        }

        if (gamepad1.y) {
            lockLift.setPosition(0.5);
        }
    }

    public void unhook() {
        if (gamepad2.dpad_up) {
            unhookL.setPosition(0);
            // unhookR.setPosition(0);

        }

        if (gamepad2.dpad_down) {
            unhookL.setPosition(0.5);
            // unhookR.setPosition(0.5);

        }
    }

    //================================== UTILITY METHODS ===========================================

    public void resetEncoders() {
        // method sets lift encoder counts to 0
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void halfSpeed () {
        if (gamepad1.a) {
            while (gamepad1.a) { }
            // counter variable allows us to use same button to change between half speed and normal
            if (halfSpeedDriveCount % 2 == 0) {
                halfSpeedDrive = 0.5;

            }
            else {
                halfSpeedDrive = 1.0;

            }
            halfSpeedDriveCount++;

        }

        // TODO: CHANGE TO SAME BUTTON WITH MODULUS AND TEST
        if (gamepad2.a) {
            while(gamepad2.a){ }
            halfSpeedPivot = 0.5;

        }
        if (gamepad2.x) {
            while (gamepad2.x) { }
            halfSpeedPivot = 1;

        }

    }

}