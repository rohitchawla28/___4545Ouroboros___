package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Func;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;

public class Drivetrain {

    private LinearOpMode opMode;
    private Sensors sensors;

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;

    public Drivetrain(LinearOpMode opMode) throws InterruptedException {

        this.opMode = opMode;

        fl = this.opMode.hardwareMap.dcMotor.get("fl");
        fr = this.opMode.hardwareMap.dcMotor.get("fr");
        bl = this.opMode.hardwareMap.dcMotor.get("bl");
        br = this.opMode.hardwareMap.dcMotor.get("br");

        sensors = new Sensors(opMode, true);

        fl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.FORWARD);

        fl.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        fr.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        bl.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
        br.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
    }

    //================================= UTILITY METHODS ============================================

    public void resetEncoders() {

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        opMode.idle();

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        opMode.idle();
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        opMode.idle();
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        opMode.idle();
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        opMode.idle();
    }

    public void stopMotors() {

        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

    }

    public void startMotors(double power) {

        fl.setPower(power);
        fr.setPower(power);
        bl.setPower(power);
        br.setPower(power);

    }

    public void turn(double power, boolean turnRight) {

        //Turns right
        if (turnRight) {

            fl.setPower(power);
            fr.setPower(-power);
            bl.setPower(power);
            br.setPower(-power);

        }

        // Turns left
        else {

            fl.setPower(-power);
            fr.setPower(power);
            bl.setPower(-power);
            br.setPower(power);

        }

    }

    //====================================== TIME METHODS ==========================================

    public void moveTime(double power, double seconds) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < seconds && opMode.opModeIsActive()) {

            opMode.telemetry.addData("Current Time Left - ", (seconds - time.seconds()));
            opMode.telemetry.update();

            startMotors(power);

        }

        stopMotors();

    }

    public void turnTime(double power, double seconds, boolean turnRight) {

        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < seconds && opMode.opModeIsActive()) {

            opMode.telemetry.addData("Current Time Left - ", (seconds - time.seconds()));
            opMode.telemetry.update();

            turn(power, turnRight);
        }

        stopMotors();

    }

    //====================================== ENCODER METHODS =======================================


    public double getEncoderAvg() {
        int countZeros = 0;

        if (fl.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (fr.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (bl.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (br.getCurrentPosition() == 0) {
            countZeros++;
        }

        opMode.telemetry.addData("Zeros", countZeros);
        opMode.telemetry.update();

        if (countZeros == 4) {
            return 0;
        }

        return (Math.abs(fl.getCurrentPosition()) +
                Math.abs(fr.getCurrentPosition()) +
                Math.abs(bl.getCurrentPosition()) +
                Math.abs(br.getCurrentPosition())) / (4 - countZeros);
    }

    public double getRightEncoderAvg() {
        int countZeros = 0;

        if (fr.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (br.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (countZeros == 2) {
            return 0;
        }

        return Math.abs(fr.getCurrentPosition()) +
                Math.abs(br.getCurrentPosition()) / (2 - countZeros);

    }

    public double getLeftEncoderAvg() {
        int countZeros = 0;

        if (fl.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (bl.getCurrentPosition() == 0) {
            countZeros++;
        }
        if (countZeros == 2) {
            return 0;
        }

        return Math.abs(fl.getCurrentPosition()) +
                Math.abs(bl.getCurrentPosition()) / (2 - countZeros);

    }

    public void moveEncoder(double power, double distance, double timeout) {
        ElapsedTime time = new ElapsedTime();

        resetEncoders();

        double initEncoder = getEncoderAvg();
        time.reset();

        opMode.telemetry.addData("Init Encoder", initEncoder);
        opMode.telemetry.update();


        while (Math.abs(getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {

            startMotors(power);

            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    public void moveEncBadHardwareForward(double power, double distance, double timeout) {
        ElapsedTime time = new ElapsedTime();

        resetEncoders();

        double initEncoder = getEncoderAvg();
        time.reset();

        opMode.telemetry.addData("Init Encoder", initEncoder);
        opMode.telemetry.update();


        while (Math.abs(getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {

            fl.setPower(power);
            fr.setPower(power * 0.75);
            bl.setPower(power);
            br.setPower(power * 0.75);

            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    public void moveEncBadHardwareBackward(double power, double distance, double timeout) {
        ElapsedTime time = new ElapsedTime();

        resetEncoders();

        double initEncoder = getEncoderAvg();
        time.reset();

        opMode.telemetry.addData("Init Encoder", initEncoder);
        opMode.telemetry.update();


        while (Math.abs(getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {

            fl.setPower(power);
            fr.setPower(power);
            bl.setPower(power);
            br.setPower(power);

            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    public void leftTurnEncoder(double power, double distance, double timeout) {
        ElapsedTime time = new ElapsedTime();

        double initEncoder = getRightEncoderAvg();
        time.reset();

        while (((getRightEncoderAvg() - initEncoder) < distance) && opMode.opModeIsActive() && (time.seconds() < timeout)) {

            turn(-power, false);

            opMode.telemetry.addData("Encoder distance left - ", (distance - getRightEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    public void rightTurnEncoder(double power, double distance, double timeout) {

        ElapsedTime time = new ElapsedTime();

        double initEncoder = getLeftEncoderAvg();
        time.reset();

        while ((getLeftEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {

            turn(power, true);

            opMode.telemetry.addData("Encoder distance left - ", (distance - getLeftEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    //===================================== GYRO METHODS ===========================================

    public void turnGyro(double power, double targetAngleChange, boolean turnRight, int timeout) {

        ElapsedTime time = new ElapsedTime();

        double initAngle = sensors.getGyroYaw();
        opMode.telemetry.addData("Initial Angle", initAngle);
        opMode.telemetry.update();

        double currAngleChange = sensors.getGyroYaw() - initAngle;
        opMode.telemetry.addData("CurrAngleChange", currAngleChange);
        opMode.telemetry.update();

        while (Math.abs((sensors.getGyroYaw() - initAngle)) < targetAngleChange && opMode.opModeIsActive() && time.seconds() < timeout) {
            turn(power, turnRight);

            opMode.telemetry.addData("Angle left", targetAngleChange - currAngleChange);
            opMode.telemetry.update();

        }
        stopMotors();
    }

    //====================================== PID METHODS ===========================================

    public void movePI(double distance, double kP, double kI, double timeout) {
        double power;
        double error;
        double proportional;
        double integral = 0;
        double lastRunTime;
        double initEncoder = getEncoderAvg();

        resetEncoders();

        ElapsedTime time = new ElapsedTime();

        time.reset();

        while ((getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {
            lastRunTime = time.seconds();

            error = distance - getEncoderAvg();

            proportional = (error * kP);
            integral += (error * (time.seconds() - lastRunTime) * kI);

            power = proportional + integral;

            startMotors(power);

            opMode.telemetry.addData("P", proportional);
            opMode.telemetry.addData("I", integral);
            opMode.telemetry.update();

            opMode.idle();

        }
        stopMotors();
    }

    public void turnP(double angleChange, boolean turnRight, double kP) {
        double error;
        double power;
        double proportional;
        double initAngle = sensors.getGyroYaw();

        while (Math.abs(sensors.getGyroYaw() - initAngle) < angleChange && opMode.opModeIsActive()) {
            error = angleChange - Math.abs((sensors.getGyroYaw() - initAngle));
            proportional = error * kP;

            power = proportional;

            turn(power, turnRight);

            opMode.telemetry.addData("Angle Left", error);
            opMode.telemetry.update();

            opMode.idle();
        }
        stopMotors();

    }

    public void turnPI(double angleChange, boolean turnRight, double kP, double kI, double timeout) {

        ElapsedTime time = new ElapsedTime();
        ElapsedTime timeoutTimer = new ElapsedTime();

        double error;
        double power;
        double proportional;
        double integral = 0;
        double bias = 0.15;

        double prevRunTime;
        double initAngle = sensors.getGyroYaw();
        boolean isNegative;
        time.reset();
        timeoutTimer.reset();

        while (Math.abs(sensors.getGyroYaw() - (angleChange + initAngle)) > 0 && opMode.opModeIsActive() && timeoutTimer.seconds() < timeout) {
            prevRunTime = time.seconds();

            error = angleChange - (Math.abs(sensors.getGyroYaw() - initAngle));
            proportional = error * kP;
            integral += (error * (time.seconds() - prevRunTime)) * kI;

            power = proportional + integral;
            isNegative = false;

            if (power < 0) {
                isNegative = true;
            }

            if (Math.abs(power) < bias) {
                power = 0;
            }

            if (isNegative) {
                turn(power - bias, turnRight);
            }
            else turn(power + bias, turnRight);

            opMode.telemetry.addData("error ", error);
            opMode.telemetry.addData("bias ", bias);
            opMode.telemetry.addData("P", proportional);
            opMode.telemetry.addData("I", integral);
            opMode.telemetry.addData("Power", power);
            opMode.telemetry.update();

            opMode.idle();
        }
        stopMotors();

    }

    public void turnPID(double angleChange, boolean turnRight, double kP, double kI, double kD, double timeout) {

        ElapsedTime time = new ElapsedTime();
        ElapsedTime timeoutTimer = new ElapsedTime();

        double error;
        double power;
        boolean isNegative;
        double prevRunTime;

        double proportional;
        double integral = 0;
        double derivative;
        double bias = 0.15;

        double initAngle = sensors.getGyroYaw();
        double lastError = angleChange - (Math.abs(sensors.getGyroYaw() - initAngle));

        time.reset();
        timeoutTimer.reset();

        while (Math.abs(sensors.getGyroYaw() - (angleChange + initAngle)) > 0 && opMode.opModeIsActive() && timeoutTimer.seconds() < timeout) {
            prevRunTime = time.seconds();

            error = angleChange - (Math.abs(sensors.getGyroYaw() - initAngle));

            proportional = error * kP;
            integral += (error * (time.seconds() - prevRunTime)) * kI;
            derivative = (error - lastError) / (time.seconds() - lastTime);

            power = proportional + integral;
            isNegative = false;

            if (power < 0) {
                isNegative = true;
            }

            if (Math.abs(power) < bias) {
                power = 0;
            }

            if (isNegative) {
                turn(power - bias, turnRight);
            }
            else turn(power + bias, turnRight);

            opMode.telemetry.addData("error ", error);
            opMode.telemetry.addData("bias ", bias);
            opMode.telemetry.addData("P", proportional);
            opMode.telemetry.addData("I", integral);
            opMode.telemetry.addData("D", derivative);
            opMode.telemetry.addData("Power", power);
            opMode.telemetry.update();

            lastError = error;

            opMode.idle();

        }
        stopMotors();

    }

    //================================= OPENCV METHODS =============================================


    //returns values of gyro axis for testing purposes
    public void composeTelemetryGyro() {
        while (opMode.opModeIsActive()) {

            opMode.telemetry.addData("Gyro Yaw", sensors.getGyroYaw());
//            opMode.telemetry.addData("Gyro Pitch", sensors.getGyroPitch());
//            opMode.telemetry.addData("Gyro Roll", sensors.getGyroRoll());

        }
    }

    public void composeTelemetryEncoders () {
        while (opMode.opModeIsActive()) {
            opMode.telemetry.addData("Encoder Average", getEncoderAvg());
        }
    }

}
