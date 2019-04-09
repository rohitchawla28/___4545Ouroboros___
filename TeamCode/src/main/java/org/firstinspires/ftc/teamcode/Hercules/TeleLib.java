package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class TeleLib extends OpMode {

    /**  ==========================  GAME PAD CONTROLS  ======================================
     *
     * Gamepad 1 - a_button (half speed drive), left/right bumper (door), left/right trigger (lift)
     *
     * Gampepad 2 - a_button (half speed pivot), b_button (unlock intake), y_button (lock intake),
     *              left/right bumper (collection), left trigger (lift macro), right trigger (pivot macro)
     *
     */

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

    // intake servos
    public Servo door;
    public Servo lock;

    // continuous rotation collection Vex 393 motors
    public CRServo collectL;
    public CRServo collectR;

    // variables for toggles
    public boolean arcade = true;
    private double halfSpeedDrive = 1;
    private boolean driveSpeedToggle = false;
    private double halfSpeedPivot = 1;
    private boolean pivotSpeedToggle = false;

    // variables for gamepad joysticks (arcade drive)
    private double arcLeftStick;
    private double arcRightStick;

    // final positions
    private final double LIFT_MACRO_TIME = 1.7;
    private final double PIVOT_MACRO_TIME = 1.1;
    private final double OPEN_DOOR = 1.0;
    private final double CLOSE_DOOR = 0.7;
    private final double OPEN_INTAKE = 1.0;
    private final double CLOSE_INTAKE = 0.92;

    // boolean states for threads
    private boolean leftTrigPressed = false;
    private boolean rightTrigPressed = true;

    // Threads
    private Thread pivotThread;
    private Thread liftThread;

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

        collectL = hardwareMap.crservo.get("collectL");
        collectR = hardwareMap.crservo.get("collectR");

        door = hardwareMap.servo.get("door");
        lock = hardwareMap.servo.get("lock");

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

        // setting arm pivot motors to BRAKE mode instead of FLOAT makes it easier to control because it won't fall from gravity
        armPivotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armPivotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // initializing threads
//      pivotThread = new Thread(pivotMacro);
//      liftThread = new Thread(liftMacro);

        // send message to phone to tell drive team when necessary actions have been completed
        telemetry.addLine("Initialized");
        telemetry.update();

    }

    //====================================  RUNNABLES  =============================================

//    public Runnable pivotMacro = new Runnable() {
//        @Override
//        public void run() {
//            if (leftTrigPressed) {
//                ElapsedTime time = new ElapsedTime();
//
//                time.reset();
//
//                while (time.seconds() < PIVOT_MACRO_TIME) {
//                    armPivotL.setPower(-0.7);
//                    armPivotR.setPower(-0.7);
//
//                }
//                armPivotL.setPower(0);
//                armPivotR.setPower(0);
//
//            }
//            leftTrigPressed = false;
//            Thread.currentThread().interrupt();
//        }
//    };
//
//    public Runnable liftMacro = new Runnable() {
//        @Override
//        public void run() {
//            if (rightTrigPressed) {
//                ElapsedTime time = new ElapsedTime();
//
//                time.reset();
//
//                while (time.seconds() < LIFT_MACRO_TIME) {
//                    liftL.setPower(-1);
//                    liftR.setPower(-1);
//
//                }
//                liftL.setPower(0);
//                liftR.setPower(0);
//
//            }
//            rightTrigPressed = false;
//
//            Thread.currentThread().interrupt();
//        }
//    };
//
//    @Override
//    public void stop() {
//        pivotThread.interrupt();
//        liftThread.interrupt();
//
//    }
//
//    @Override
//    public void start() {
//        pivotThread.start();
//        liftThread.start();
//
//    }

    //====================================  RUNNABLE MOTORS  =======================================

    public void threadStates() {
        if (gamepad2.left_trigger > 0.08) {
            while (gamepad2.left_trigger > 0.08) { }

            leftTrigPressed = true;

        }

        if (gamepad2.right_trigger > 0.08) {
            while (gamepad2.left_trigger > 0.08) { }

            rightTrigPressed = true;

        }

    }

    //====================================  DRIVETRAIN  ============================================

    // in arcade drive, the left stick controls all motors either moving forwards or backwards
    // the right stick controls turning left or right
    public void arcadeDrive() {
        /** Y Axis Reversed */
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

    public void tankDrive() {
        if (Math.abs(gamepad1.left_stick_y) > 0.08) {
            fl.setPower(gamepad1.left_stick_y);
            bl.setPower(gamepad1.left_stick_y);

        }
        else {
            fl.setPower(0);
            bl.setPower(0);

        }

        if (Math.abs(gamepad1.right_stick_y) > 0.08) {
            fr.setPower(gamepad1.right_stick_y);
            br.setPower(gamepad1.right_stick_y);

        }
        else {
            fr.setPower(0);
            br.setPower(0);

        }

    }

    public void halfSpeed() {
        if (gamepad1.a) {
            while (gamepad1.a) { }

            // boolean variable allows you to use one button to toggle half speed mode
            if (!driveSpeedToggle) {
                halfSpeedDrive = 0.4;
                telemetry.addLine("Half speed drive on");
                telemetry.update();

            }
            else {
                halfSpeedDrive = 1.0;
                telemetry.addLine("Half speed drive off");
                telemetry.update();

            }
            driveSpeedToggle = !driveSpeedToggle;

        }

        if (gamepad2.a) {
            while (gamepad2.a) { }

            if (!pivotSpeedToggle) {
                halfSpeedPivot = 0.5;
                telemetry.addLine("Half speed pivot on");
                telemetry.update();

            }
            else {
                halfSpeedPivot = 1.0;
                telemetry.addLine("Half speed pivot off");
                telemetry.update();

            }
            pivotSpeedToggle = !pivotSpeedToggle;

        }

    }

    public void tankToggle() {
        if (gamepad1.x) {
            while (gamepad1.x) { }

            arcade = !arcade;

        }

    }



    //==============================  LARGE MANIPULATOR METHODS  ===================================

    public void lift() {
        double rightStick = gamepad2.right_stick_y;
        double leftTrig = gamepad1.left_trigger;
        double rightTrig = gamepad1.right_trigger;

        if (Math.abs(rightStick) > 0.08) {
            liftL.setPower(rightStick);
            liftR.setPower(rightStick);

        }
        else if (leftTrig > 0.08) {
            liftL.setPower(gamepad1.left_trigger);
            liftR.setPower(gamepad1.left_trigger);

        }
        else if (rightTrig > 0.08) {
            liftL.setPower(-gamepad1.right_trigger);
            liftR.setPower(-gamepad1.right_trigger);

        }
        else {
            liftL.setPower(0);
            liftR.setPower(0);

        }

    }

    public void armPivot() {
        // added option for half speed on pivot arm so easier for driver to control when lining up to hang
        double pivotPower = gamepad2.left_stick_y * halfSpeedPivot;

        if (Math.abs(pivotPower) > 0.08) {
            armPivotL.setPower(pivotPower);
            armPivotR.setPower(pivotPower);

        }
        else {
            armPivotL.setPower(0);
            armPivotR.setPower(0);

        }

    }

    public void testArmPivot() {
        double left = gamepad2.left_stick_y;
        double right = gamepad2.right_stick_y;

        if (Math.abs(left) > 0.08) {
            armPivotL.setPower(left);

        }
        else {
            armPivotL.setPower(0);

        }

        if (Math.abs(right) > 0.08) {
            armPivotR.setPower(right);

        }
        else {
            armPivotR.setPower(0);

        }

    }


    //=====================================  INTAKE METHODS  =======================================

    public void collect() {
        if (gamepad2.left_bumper) {
            collectL.setPower(0.8);
            collectR.setPower(0.8);

        }
        else if (gamepad2.right_bumper) {
            collectL.setPower(-0.8);
            collectR.setPower(-0.8);

        }
//        else if (gamepad2.x) {
//            collectL.setPower(-0.3);
//            collectR.setPower(-0.3);
//
//        }
        else {
            collectL.setPower(0);
            collectR.setPower(0);

        }

    }

//    public void testCollect() {
//        if (gamepad2.left_bumper) {
//            collectL.setPower(0.6);
//            collectR.setPower(0.6);
//        }
//        else if (gamepad2.right_bumper) {
//            collectL.setPower(-0.6);
//            collectR.setPower(-0.6);
//        }
//        else {
//            collectL.setPower(0);
//            collectR.setPower(0);
//        }
//    }

    public void openDoor() {
        if (gamepad1.left_bumper) {
            door.setPosition(OPEN_DOOR);
        }
    }

    public void closeDoor() {
        if (gamepad1.right_bumper) {
            door.setPosition(CLOSE_DOOR);
        }
    }

    public void unlock() {
        if (gamepad2.b) {
            lock.setPosition(OPEN_INTAKE);
        }
    }

    public void lock() {
        if (gamepad2.y) {
            lock.setPosition(CLOSE_INTAKE);
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