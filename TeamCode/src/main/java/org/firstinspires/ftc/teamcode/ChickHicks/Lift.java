package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

    private Servo unhookL;
    private Servo unhookR;

    private Sensors sensors;

    public Lift(LinearOpMode opMode) throws InterruptedException {
        this.opMode = opMode;

        //sensors = new Sensors(this.opMode, true);
        liftL = this.opMode.hardwareMap.dcMotor.get("liftL");
        liftR = this.opMode.hardwareMap.dcMotor.get("liftR");

        armPivotL = this.opMode.hardwareMap.dcMotor.get("armPivotL");
        armPivotR = this.opMode.hardwareMap.dcMotor.get("armPivotR");

        //unhookL = this.opMode.hardwareMap.servo.get("unhookL");
        //unhookR = this.opMode.hardwareMap.servo.get("unhookR");

        liftL.setDirection(DcMotorSimple.Direction.FORWARD);
        liftR.setDirection(DcMotorSimple.Direction.REVERSE);

        armPivotL.setDirection(DcMotorSimple.Direction.FORWARD);
        armPivotR.setDirection(DcMotorSimple.Direction.REVERSE);

        armPivotL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armPivotR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    //===================================  DETATCH METHODS  ====================================

    public void detachRange() {
//            while (sensors.getDistance() > 0.1 && opMode.opModeIsActive()) {
//                liftL.setPower(-0.4);
//                liftR.setPower(-0.4);
//
//            }

    }



    //MAKE SURE THE METHOD KEEPS GOING LONG ENOUGH SO THAT IT RAISES ENOUGH TO TURN OUT
    public void detachTime() {
        ElapsedTime time = new ElapsedTime();
        unhookL.setPosition(0.5);
        unhookR.setPosition(0.5);

        opMode.sleep(2500);

        time.reset();

        while (time.milliseconds() < 1000 && opMode.opModeIsActive()) {
            liftL.setPower(-0.5);
            liftR.setPower(-0.5);

        }
        opMode.sleep(5000);

        time.reset();
        while (time.milliseconds() < 500 && opMode.opModeIsActive()) {
            liftL.setPower(0.3);
            liftR.setPower(0.3);

        }
    }

    public void detachEncoder(Drivetrain drivetrain) {
        ElapsedTime time = new ElapsedTime();

        double initEncoder = (liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2;
        double distance = 750;
        double timeout = 4000;

        resetEncoders();

        time.reset();

        while (((Math.abs((liftL.getCurrentPosition()) + Math.abs(liftR.getCurrentPosition())) / 2) - initEncoder) < distance
                && time.milliseconds() < timeout && opMode.opModeIsActive()) {
            liftL.setPower(-0.4);
            liftR.setPower(-0.4);

            opMode.telemetry.addData("Encoder distance left - ", (distance - ((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2)));
            opMode.telemetry.update();
        }

        while ((int)Math.abs(sensors.getGyroYawR()) > 0) {
            if (sensors.getGyroYawR() > 0) {
                //test PI values
                drivetrain.turnPI(Math.abs(sensors.getGyroYaw()), false, 0.15 / 90, 0.013, 3);

            } else if (sensors.getGyroYawR() < 0) {
                drivetrain.turnPI(Math.abs(sensors.getGyroYaw()), true, 0.15 / 90, 0.013, 3);

            }

        }

    }

    //======================================  LIFT/ARM MOVEMENTS  ==================================

    public void moveLift(boolean extending, int timeout) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            if (extending) {
                liftL.setPower(0.6);
                liftR.setPower(0.6);

            }
            else {
                liftL.setPower(-0.6);
                liftR.setPower(-0.6);
            }

        }

    }

    public void moveArmUp() {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < 1.4) {
            armPivotL.setPower(-0.4);
            armPivotR.setPower(-0.4);

        }
        armPivotL.setPower(0.0);
        armPivotR.setPower(0.0);
        opMode.sleep(1500);

    }

    public void moveArmDown() {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < 1) {
            armPivotL.setPower(-0.3);
            armPivotR.setPower(-0.3);

        }

    }


//        public void extendSampling(Drivetrain drivetrain, Vuforia vuforia) throws InterruptedException {
////            HSL_OpenCVDetection vision = new HSL_OpenCVDetection(opMode, vuforia);
////            vision.process(vuforia.convertToMat());
////
////            opMode.telemetry.addData("Cube Position", TensorFlowDetection.cubePosition);
////            opMode.telemetry.update();
////
////            switch (vision.cubePositionAlt) {
////                case "left" :
////                    drivetrain.turnPI(20, false, 0.33/90, 0.013, 2);
////                    opMode.sleep(500);
////                    moveLift(true, 3000);
////                    opMode.sleep(500);
////                    moveLift(false, 3000);
////                    opMode.sleep(500);
////                    drivetrain.turnPI(20, true, 0.33/90, 0.013, 2);
////                    opMode.sleep(500);
////                    break;
////
////                case "center" :
////                    moveLift(true, 3000);
////                    opMode.sleep(500);
////                    moveLift(false, 3000);
////                    opMode.sleep(500);
////                    break;
////
////                case "right" :
////                    drivetrain.turnPI(20, true, 0.33/90, 0.013, 2);
////                    opMode.sleep(500);
////                    moveLift(true, 3000);
////                    opMode.sleep(500);
////                    moveLift(false, 3000);
////                    opMode.sleep(500);
////                    drivetrain.turnPI(20, false, 0.33/90, 0.013, 2);
////                    opMode.sleep(500);
////                    break;
////
////                case "unknown" :
////                    moveLift(true, 3000);
////                    opMode.sleep(500);
////                    moveLift(false, 3000);
////                    opMode.sleep(500);
////                    break;
////
////            }
////
////        }

    public void resetEncoders() {
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();

    }

}
