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

    // constructor to initialize lift and arm motors
    public Lift(LinearOpMode opMode) {
        this.opMode = opMode;

        liftL = this.opMode.hardwareMap.dcMotor.get("liftL");
        liftR = this.opMode.hardwareMap.dcMotor.get("liftR");

        armPivotL = this.opMode.hardwareMap.dcMotor.get("armPivotL");
        armPivotR = this.opMode.hardwareMap.dcMotor.get("armPivotR");

        liftL.setDirection(DcMotorSimple.Direction.FORWARD);
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);

        armPivotL.setDirection(DcMotorSimple.Direction.FORWARD);
        armPivotR.setDirection(DcMotorSimple.Direction.REVERSE);

        armPivotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armPivotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    //===================================  DETATCH METHODS  ====================================

    public void detachTime(Drivetrain drivetrain) {
        // retract lift to get out of clip
        moveLift(1, 0.75, false);

        //allow lift and arm to drop to ground
        opMode.sleep(1500);

        // small arm movement to align 90 deg with lander
        moveArm(0.5, 0.15, true);

        // raise lift to get out of hook
        moveLift(1, 0.5, true);

        // slow move away from lander
        drivetrain.moveEncoder(0.3, 150, 3);

        opMode.sleep(500);

        // bring lift down to retracted position
        moveLift(0.8, 0.75, false);

        // bring arm down to folded position
        moveArm(0.6, 1, false);

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

    public void moveLift(double power, double timeout, boolean extending) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            if (extending) {
                liftL.setPower(-power);
                liftR.setPower(-power);

            }
            else {
                liftL.setPower(power);
                liftR.setPower(power);

            }

        }
        liftL.setPower(0);
        liftR.setPower(0);

    }

    public void moveArm(double power, double timeout, boolean raising) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            if (raising) {
                armPivotL.setPower(-power);
                armPivotR.setPower(-power);

            }
            else {
                armPivotL.setPower(power);
                armPivotR.setPower(power);
            }

        }
        armPivotL.setPower(0);
        armPivotR.setPower(0);

    }


    //===================================== UTILITY METHODS ========================================

    public void resetEncoders() {
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();

    }

}
