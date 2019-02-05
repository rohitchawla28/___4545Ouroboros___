package org.firstinspires.ftc.teamcode.HerculesLibraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift {

    private LinearOpMode opMode;

    private DcMotor liftL;
    private DcMotor liftR;

    private DcMotor armPivotL;
    private DcMotor armPivotR;

    private Servo liftLock;

    public Lift(LinearOpMode opMode) {
        this.opMode = opMode;

        liftL = this.opMode.hardwareMap.dcMotor.get("liftL");
        liftR = this.opMode.hardwareMap.dcMotor.get("liftR");

        armPivotL = this.opMode.hardwareMap.dcMotor.get("armPivotL");
        armPivotR = this.opMode.hardwareMap.dcMotor.get("armPivotR");

        liftLock = this.opMode.hardwareMap.servo.get("liftLock");

        liftL.setDirection(DcMotorSimple.Direction.FORWARD);
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);

        armPivotL.setDirection(DcMotorSimple.Direction.FORWARD);
        armPivotR.setDirection(DcMotorSimple.Direction.REVERSE);

        armPivotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armPivotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    //===================================  DETATCH METHODS  ====================================

    public void detachTime(Drivetrain drivetrain, Intake intake) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        //release pressure from the servo
        while (time.seconds() < 2) {
            liftL.setPower(0.5);
            liftR.setPower(0.5);

            // unlock servo
            if (time.seconds() > 0.2) {
                liftLock.setPosition(0.63);

            }


        }

        //allow lift to drop
        liftL.setPower(0);
        liftR.setPower(0);

        opMode.sleep(1500);

        time.reset();

        // raise lift to get out of hook
        while (time.seconds() < 0.7) {
            liftL.setPower(-0.6);
            liftR.setPower(-0.6);

        }
        liftL.setPower(0);
        liftR.setPower(0);

        opMode.sleep(750);

        // slow move away from lander
        drivetrain.moveEncoder(0.3, 150, 3);
        opMode.sleep(500);

        time.reset();

        //bring lift down
        while (time.seconds() < 2) {
            liftL.setPower(0.5);
            liftR.setPower(0.5);

        }

        // make sure intake doesn't get crushed
        intake.setIntakePivotDepositPosition();

        opMode.sleep(750);

        time.reset();

        // bring arm down
        while (time.seconds() < 1.2) {
            armPivotL.setPower(0.6);
            armPivotR.setPower(0.6);

        }
        armPivotL.setPower(0);
        armPivotR.setPower(0);

    }

    public void detachEncoder(Drivetrain drivetrain) {
//        ElapsedTime time = new ElapsedTime();
//
//        double initEncoder = (liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2;
//        double distance = 750;
//        double timeout = 4000;
//
//        resetEncoders();
//
//        time.reset();
//
//        while (((Math.abs((liftL.getCurrentPosition()) + Math.abs(liftR.getCurrentPosition())) / 2) - initEncoder) < distance
//                && time.milliseconds() < timeout && opMode.opModeIsActive()) {
//            liftL.setPower(-0.4);
//            liftR.setPower(-0.4);
//
//            opMode.telemetry.addData("Encoder distance left - ", (distance - ((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2)));
//            opMode.telemetry.update();
//        }
//
//        while ((int)Math.abs(sensors.getGyroYawR()) > 0) {
//            if (sensors.getGyroYawR() > 0) {
//                //test PI values
//                drivetrain.turnPI(Math.abs(sensors.getGyroYaw()), false, 0.15 / 90, 0.013, 3);
//
//            } else if (sensors.getGyroYawR() < 0) {
//                drivetrain.turnPI(Math.abs(sensors.getGyroYaw()), true, 0.15 / 90, 0.013, 3);
//
//            }
//
//        }

    }

    //======================================  LIFT/ARM MOVEMENTS  ==================================

    public void depositLiftMacro(boolean extending) {
        ElapsedTime time = new ElapsedTime();

        double timeout = 2.2;

        time.reset();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            if (extending) {
                liftL.setPower(0.8);
                liftR.setPower(0.8);

            }
            else {
                liftL.setPower(-0.8);
                liftR.setPower(-0.8);
            }

        }

    }

    public void pivotMacro(boolean up) {
        ElapsedTime time = new ElapsedTime();

        double timeout = 1.0;

        time.reset();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            if (up) {
                armPivotL.setPower(-0.65);
                armPivotR.setPower(-0.65);

            }
            else {
                armPivotL.setPower(0.65);
                armPivotR.setPower(0.65);

            }

        }

    }

    public void markerOut(Intake intake) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        // raise arm
        while (time.seconds() < 1.1) {
            armPivotL.setPower(-0.4);
            armPivotR.setPower(-0.4);

        }
        armPivotL.setPower(0);
        armPivotR.setPower(0);

        // deploy marker
        intake.setIntakePivotMarkerDeploymentPosition();

//        intake.collect(false);

        opMode.sleep(750);

        intake.setIntakePivotDepositPosition();

    }

    //===================================== UTILITY METHODS ========================================

    public void resetEncoders() {
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();

    }

}
