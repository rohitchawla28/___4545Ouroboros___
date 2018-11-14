package org.firstinspires.ftc.teamcode.TrollBot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;



public abstract class TrollAutoLibrary extends LinearOpMode {

    //declare variables
    public DcMotor fr;
    public DcMotor fl;
    public DcMotor br;
    public DcMotor bl;

    public Servo arm;
    public Servo claw;

    public DistanceSensor range;

    public ColorSensor colorSensor;

    //gyro
    public BNO055IMU gyro;
    Orientation angles;
    Acceleration gravity;
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    public void initialize() {

        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");

        arm = hardwareMap.get(Servo.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");

        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");

        gyro = hardwareMap.get(BNO055IMU.class, "gyro");
        gyro.initialize(parameters);

        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        fr.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.FORWARD);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetEncoders();

        arm.setPosition(0);
        claw.setPosition(0);

        waitForStart();
    }

    public void stopMotors() {
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
    }

    public void startMotors(double power) {
        fr.setPower(power);
        fl.setPower(power);
        br.setPower(power);
        bl.setPower(power);
    }

    public void resetEncoders() {
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void turn(double power) {
        //positive means right, negative turns left
            fr.setPower(-power);
            fl.setPower(power);
            br.setPower(-power);
            bl.setPower(power);
    }

    //============================ TIME MOVEMENT ===============================

    public void moveTime(double power, double time) {
        //if() for toggle time auto methods

        double timeStart = System.currentTimeMillis();

        while(System.currentTimeMillis() - timeStart < time) {
            startMotors(power);
        }

        stopMotors();
    }

    public void turnTime(double power, double time) {

        //if() for toggle time auto methods

        double timeStart = System.currentTimeMillis();

        while(System.currentTimeMillis() - timeStart < time) {
            turn(power);
        }

        stopMotors();
    }


    //============================ ENCODER ONLY ================================

    public double getEncoderAvg() {
        int countZeros = 4;

        if (fr.getCurrentPosition() == 0) {
            countZeros--;
        }

        if (fl.getCurrentPosition() == 0) {
            countZeros--;
        }

        if (br.getCurrentPosition() == 0) {
            countZeros--;
        }

        if (bl.getCurrentPosition() == 0) {
            countZeros--;
        }

        if (countZeros == 0)
            return 0;

        return (fr.getCurrentPosition() + fl.getCurrentPosition() +
                br.getCurrentPosition() + bl.getCurrentPosition()/countZeros);
    }

    //used for turning right
    public double getLeftEncoderAvg() {
        int countZeros = 2;

        if (fl.getCurrentPosition() == 0) {
            countZeros--;
        }

        if (bl.getCurrentPosition() == 0) {
            countZeros--;
        }

        if (countZeros == 0)
            return 0;

        return (fl.getCurrentPosition() + bl.getCurrentPosition()/countZeros);
    }

    //used for turning left
    public double getRightEncoderAvg() {
        int countZeros = 2;

        if (fr.getCurrentPosition() == 0){
            countZeros--;
        }

        if (br.getCurrentPosition() == 0){
            countZeros--;
        }

        if (countZeros == 0)
            return 0;

        return (fr.getCurrentPosition() + br.getCurrentPosition()/countZeros);
    }

    public void moveEncoder(double power, double distance, double timeLimit) {
        double encoderStart = getEncoderAvg();
        double timeStart = System.currentTimeMillis();

        while((getEncoderAvg() - encoderStart < distance) &&
                opModeIsActive() && (System.currentTimeMillis() - timeStart < timeLimit)) {
            fr.setPower(power);
            fl.setPower(power);
            br.setPower(power);
            bl.setPower(power);
        }

        stopMotors();
    }

    public void leftTurnEncoder(double power, double distance, double timeLimit) {
        double encoderStart = getRightEncoderAvg();
        double timeStart = System.currentTimeMillis();

        while((getRightEncoderAvg() - encoderStart < distance) &&
                opModeIsActive() && (System.currentTimeMillis() - timeStart < timeLimit)) {
            turn(-power);
        }

        stopMotors();
    }

    public void rightTurnEncoder(double power, double distance, double timeLimit) {
        double encoderStart = getLeftEncoderAvg();
        double timeStart = System.currentTimeMillis();

        while((getLeftEncoderAvg() - encoderStart < distance) &&
                opModeIsActive() && ((System.currentTimeMillis() - timeStart) < timeLimit)) {
            turn(power);
        }

        stopMotors();
    }

    public void turnPID(double power, double targetAngle) {

    }

    //============================ GYRO ===========================================

    public void updateValues() {
        angles = gyro.getAngularOrientation();
    }

    public double getGyroYaw() {
        updateValues();

        return angles.firstAngle;
    }

    public double getGyroRoll() {
        updateValues();

        return angles.secondAngle;
    }

    public double getGyroPitch() {
        updateValues();

        return angles.thirdAngle;
    }

    //============================ GYRO MOVEMENT ==================================

    public void gyroTurn(double power, double angle) {
        if (getGyroYaw() < angle) {

        }
    }

    public void moveGyroEncoderCorrection() {

    }

    public void moveGyroRangeCorrection() {

    }
    //============================ RANGE SENSOR MOVE ==============================

    //uses range sensor to move
    public void move_range() {
        /* gets rangeSensor distance
        range.getDistance(DistanceUnit.INCH);
         */
    }


    //============================ MANIPULATORS ================================

    //moves relic arm
    public void move_arm(double servo_position) {
        arm.setPosition(servo_position);
    }

    //closes relic servo
    public void grab() {
        claw.setPosition(0);
    }

    //opens relic servo
    public void release() {
        claw.setPosition(.5);
    }


    //========================== COLOR SENSOR =====================================

//    public double getColor() {
//
//    }




    //=========================== TELEMETRY ========================================

    //returns values of gyro axis for testing purposes
    public void composeTelemetry() {
        telemetry.addLine()
                .addData("gyroYaw", new Func<String>() {
                    public String value() {
                        return "gyro yaw: " + getGyroYaw();
                    }
                });

        telemetry.addLine()
                .addData("gyroPitch", new Func<String>() {
                    public String value() {
                        return "gyro pitch: " + getGyroPitch();
                    }
                });

        telemetry.addLine()
                .addData("gyroRoll", new Func<String>() {
                    public String value() {
                        return "gyro roll: " + getGyroRoll();
                    }
                });
    }

}




