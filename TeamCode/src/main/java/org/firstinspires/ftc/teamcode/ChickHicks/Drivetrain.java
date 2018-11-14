package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Func;

public class Drivetrain {

    private LinearOpMode opMode;
    private Sensors sensors;

    private ElapsedTime runTime = new ElapsedTime();

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

    public void moveTime(double power, double time) {

        runTime.reset();

        while (runTime.seconds() < time && opMode.opModeIsActive()) {

            opMode.telemetry.addData("Current Time Left - ", (time - runTime.seconds()));
            opMode.telemetry.update();

            startMotors(power);

        }

        stopMotors();

    }

    public void turnTime(double power, double time, boolean turnRight) {

        runTime.reset();

        while (runTime.seconds() < time && opMode.opModeIsActive()) {

            opMode.telemetry.addData("Current Time Left - ", (time - runTime.seconds()));
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

        resetEncoders();

        double initEncoder = getEncoderAvg();
        runTime.reset();

        opMode.telemetry.addData("Init Encoder", initEncoder);
        opMode.telemetry.update();


        while (Math.abs(getEncoderAvg() - initEncoder) < distance && runTime.seconds() < timeout && opMode.opModeIsActive()) {

            startMotors(power);

            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    public void moveEncBadHardwareForward(double power, double distance, double timeout) {

        resetEncoders();

        double initEncoder = getEncoderAvg();
        runTime.reset();

        opMode.telemetry.addData("Init Encoder", initEncoder);
        opMode.telemetry.update();


        while (Math.abs(getEncoderAvg() - initEncoder) < distance && runTime.seconds() < timeout && opMode.opModeIsActive()) {

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

        resetEncoders();

        double initEncoder = getEncoderAvg();
        runTime.reset();

        opMode.telemetry.addData("Init Encoder", initEncoder);
        opMode.telemetry.update();


        while (Math.abs(getEncoderAvg() - initEncoder) < distance && runTime.seconds() < timeout && opMode.opModeIsActive()) {

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

        double initEncoder = getRightEncoderAvg();
        runTime.reset();

        while (((getRightEncoderAvg() - initEncoder) < distance) && opMode.opModeIsActive() && (runTime.seconds() < timeout)) {

            turn(-power, false);

            opMode.telemetry.addData("Encoder distance left - ", (distance - getRightEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    public void rightTurnEncoder(double power, double distance, double timeout) {

        double initEncoder = getLeftEncoderAvg();
        runTime.reset();

        while ((getLeftEncoderAvg() - initEncoder) < distance && runTime.seconds() < timeout && opMode.opModeIsActive()) {

            turn(power, true);

            opMode.telemetry.addData("Encoder distance left - ", (distance - getLeftEncoderAvg()));
            opMode.telemetry.update();

        }

        stopMotors();

    }

    //===================================== GYRO METHODS ===========================================

    public void turnGyro(double power, double targetAngleChange, boolean turnRight, int timeout) {

        runTime.reset();

        double initAngle = sensors.getGyroYaw();
        opMode.telemetry.addData("Initial Angle", initAngle);
        opMode.telemetry.update();

        double currAngleChange = sensors.getGyroYaw() - initAngle;
        opMode.telemetry.addData("CurrAngleChange", currAngleChange);
        opMode.telemetry.update();

        while (Math.abs((sensors.getGyroYaw() - initAngle)) < targetAngleChange && opMode.opModeIsActive() && runTime.seconds() < timeout) {

            turn(power, turnRight);

            opMode.telemetry.addData("Angle left", targetAngleChange - currAngleChange);
            opMode.telemetry.update();

        }

        stopMotors();

    }


    //====================================== PID METHODS ===========================================

    public void movePID(double power, double distance, double kP, double kI, double kD, double accuracy, double timeout, double bias) {

        double error;
        double proportional;
        double integral = 0;
        double derivative;
        double lastRunTime;
        double lastError = distance - getEncoderAvg();
        double initEncoder = getEncoderAvg();

        resetEncoders();

        ElapsedTime runTime = new ElapsedTime();

        runTime.reset();

        while ((getEncoderAvg() - initEncoder) < (distance - accuracy) && runTime.seconds() < timeout && opMode.opModeIsActive()) {

            lastRunTime = runTime.seconds();

            error = distance - getEncoderAvg();

            proportional = (error * kP);
            integral += (error * (runTime.seconds() - lastRunTime) * kI);
            derivative = ((error - lastError) / (runTime.seconds() - lastRunTime)) * kD;

            power = proportional + integral - derivative + bias;

            startMotors(power);

            opMode.telemetry.addData("Distance Remaining: ", error + "");
            opMode.telemetry.update();

            lastError = error;

            opMode.idle();

        }

        stopMotors();

    }

    public void moveGyroStabPID(double power, double distance, double kP, double kI, double kD, double gyrokP, double accuracy, double timeout, double bias) {

        double error;
        double proportional;
        double integral = 0;
        double derivative;
        double lastRunTime;
        double lastError = distance - getEncoderAvg();
        double initEncoder = getEncoderAvg();
        double heading = sensors.getGyroYaw();

        resetEncoders();

        ElapsedTime runTime = new ElapsedTime();

        runTime.reset();

        while ((getEncoderAvg() - initEncoder) < (distance - accuracy) && runTime.seconds() < timeout && opMode.opModeIsActive()) {

            lastRunTime = runTime.seconds();

            error = distance - getEncoderAvg();

            proportional = (error * kP);
            integral += (error * (runTime.seconds() - lastRunTime) * kI);
            derivative = ((error - lastError) / (runTime.seconds() - lastRunTime)) * kD;

            power = proportional + integral - derivative + bias;

            startMotors(power * gyrokP);

            opMode.telemetry.addData("Distance Remaining: ", error + "");
            opMode.telemetry.update();

            lastError = error;

            opMode.idle();

        }

        stopMotors();

    }

    public void turnPID(double power, double angle, boolean turnRight, double kP, double kI, double kD, double accuracy, double timeout, double bias) {

        double error;
        double proportional;
        double integral = 0;
        double derivative;
        double lastRunTime;
        double lastError = angle - sensors.getGyroYaw();

        ElapsedTime runTime = new ElapsedTime();

        runTime.reset();

        while (sensors.getGyroYaw() < (angle - accuracy) && runTime.seconds() < timeout && opMode.opModeIsActive()) {

            lastRunTime = runTime.seconds();

            error = angle - sensors.getGyroYaw();

            proportional = (error * kP);
            integral += (error * (runTime.seconds() - lastRunTime) * kI);
            derivative = ((error - lastError) / (runTime.seconds() - lastRunTime)) * kD;

            power = proportional + integral - derivative + bias;

            turn(power, turnRight);

            opMode.telemetry.addData("Angle Remaining: ", error + "");
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




    //================================= MOTION PROFILING METHODS ===================================

}
