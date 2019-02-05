package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class TeleLib extends OpMode {

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

//    // continuous rotation collection Vex 393 motors
//    public CRServo collectL;
//    public CRServo collectR;

    // servos lock the arm in for auto
    public Servo lockLift;

    // variables for toggles
    private double halfSpeedDrive = 1;
    private boolean driveSpeedToggle = false;

    private double halfSpeedPivot = 1;

    private boolean locked = true;

    // variables for gamepad joysticks (tank drive)
    private double tankLeftPower;
    private double tankRightPower;

    // variables for gamepad joysticks (arcade drive)
    private double arcLeftStick;
    private double arcRightStick;

    private boolean intake = false;
    private boolean buttonStateL = false;
    private boolean buttonStateR = false;

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

//        collect L = hardwareMap.crservo.get("collectL");
//        collectR = hardwareMap.crservo.get("collectR");

        lockLift = hardwareMap.servo.get("liftLock");

        // setting reverse directions of right motors because they are mounted opposite
        fl.setDirection(DcMotor.Direction.FORWARD);
        fr.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);

        armPivotL.setDirection(DcMotor.Direction.FORWARD);
        armPivotR.setDirection(DcMotor.Direction.REVERSE);

        liftL.setDirection(DcMotor.Direction.FORWARD);
        liftR.setDirection(DcMotor.Direction.REVERSE);

//        collectL.setDirection(DcMotor.Direction.FORWARD);
//        collectR.setDirection(DcMotor.Direction.REVERSE);

        // setting arm pivot motors to BRAKE mode instead of FLOAT makes it easier to control because it won't fall from gravity
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

        double leftPower = arcLeftStick - arcRightStick;
        double rightPower = arcLeftStick + arcRightStick;

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

    public void halfSpeed() {
            if (gamepad1.a) {
                while (gamepad1.a) { }

                // boolean variable allows you to use one button to toggle half speed mode
                if (!driveSpeedToggle) {
                    halfSpeedDrive = 0.5;
                    telemetry.addLine("Half speed on");
                    telemetry.update();

                } else {
                    halfSpeedDrive = 1.0;
                    telemetry.addLine("Half speed off");
                    telemetry.update();

                }
                driveSpeedToggle = !driveSpeedToggle;

            }

    }

    //==============================  LARGE MANIPULATOR METHODS  ===================================

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

    public void depositLiftMacro() {
        double liftTimeout = 2.2;

        if (gamepad1.dpad_up) {
            ElapsedTime time = new ElapsedTime();

            time.reset();

            while (time.seconds() < liftTimeout) {
                liftL.setPower(0.8);
                liftR.setPower(0.8);

            }

        }

    }

    public void pivotMacro() {
        double timeout = 1.0;

        if (gamepad1.dpad_down) {
            ElapsedTime time = new ElapsedTime();

            time.reset();

            while (time.seconds() < timeout) {
                armPivotL.setPower(-0.65);
                armPivotR.setPower(-0.65);

            }

        }

    }

    //=====================================  INTAKE METHODS  =======================================

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

        if(gamepad2.x) {
            //scoop position
            intakePivotL.setPosition(0.2);
            intakePivotR.setPosition(0.4);

        }

    }

//    public void collect() {
//        if (gamepad2.left_bumper ^ buttonStateL) {
//            // spin in
//            intake = !intake;
//            buttonStateL = gamepad2.left_bumper;
//        }
//
//        collectL.setPower(intake ? .6 : 0);
//        collectR.setPower(intake ? .6 : 0);
//
//        if (gamepad2.right_bumper ^ buttonStateR) {
//            // spin in
//            intake = !intake;
//            buttonStateR = gamepad2.right_bumper;
//        }
//
//        collectL.setPower(intake ? -.6 : 0);
//        collectR.setPower(intake ? -.6 : 0);
//
//    }
//
//    public void testCollect() {
//        if (gamepad2.left_trigger > 0.1) {
//            collectL.setPower(0.6);
//        }
//        else collectL.setPower(0);
//
//        if (gamepad2.right_trigger > .1) collectR.setPower(0.6);
//        else collectR.setPower(0);
//
//    }

    public void door() {
        if (gamepad1.left_bumper) {
            // collection position
            door.setPosition(0.6);

        }

        if (gamepad1.right_bumper) {
            // deposit position
            door.setPosition(0);

        }

    }

    public void lockLift() {
        if (gamepad1.b) {
            while (gamepad1.b) { }

            if (locked) {
                lockLift.setPosition(0);

                telemetry.addLine("Unlocked");
                telemetry.update();

            }
            else {
                lockLift.setPosition(0.5);

                telemetry.addLine("Locked");
                telemetry.update();

            }
            locked = !locked;

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

}