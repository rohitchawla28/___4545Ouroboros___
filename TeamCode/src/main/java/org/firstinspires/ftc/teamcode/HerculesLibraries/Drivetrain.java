package org.firstinspires.ftc.teamcode.HerculesLibraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    //====================================== ENCODER METHODS =======================================


    public double getEncoderAvg() {
        double countZeros = 0.0;

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

        if (countZeros == 4) {
            return 0;
        }

        return (Math.abs(fl.getCurrentPosition()) +
                Math.abs(fr.getCurrentPosition()) +
                Math.abs(bl.getCurrentPosition()) +
                Math.abs(br.getCurrentPosition())) / (4.0 - countZeros);

    }

    public void moveEncoder(double power, double distance, double timeout) {
        resetEncoders();

        ElapsedTime time = new ElapsedTime();

        double initEncoder = getEncoderAvg();

        time.reset();

        while (Math.abs(getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {
            startMotors(power);

            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAvg()));
            opMode.telemetry.update();

        }
        stopMotors();

    }

    public void moveGyroStab(double power, double distance, double timeout) {
        resetEncoders();

        ElapsedTime time = new ElapsedTime();

        double initEncoder = getEncoderAvg();
        double heading = sensors.getGyroYaw();

        time.reset();

        opMode.telemetry.addData("Heading", heading);

        while (Math.abs(getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {
            opMode.telemetry.addData("Current Gyro", sensors.getGyroYaw());

            double error = heading - sensors.getGyroYaw();

            opMode.telemetry.addData("error", error);

            // calculate error in -179 to +180 range
            if (error > 180)  {
                error -= 360;

            }
            if (error <= -180) {
                error += 360;

            }

            // if we are way off course, instead of continuing to move, we stop and turn back to the correct angle
//            if (Math.abs(error) > 10) {
//                stopMotors();
//
//                if (error >= 0) {
//                    turnPID(Math.abs(error), false, 0.3 / (Math.abs(error)), 0.003, 0.008 / (Math.abs(error)), 3);
//
//                }
//                else if (error <= 0) {
//                    turnPID(Math.abs(error), true, 0.3 / (Math.abs(error)), 0.003, 0.008 / (Math.abs(error)), 3);
//
//                }
//
//            }
//            else {
            if (error >= 1) {
                opMode.telemetry.addLine("Too far right");
                opMode.telemetry.update();

                fl.setPower(power * 0.8);
                fr.setPower(power * 1.25);
                bl.setPower(power * 0.8);
                br.setPower(power * 1.25);

            } else if (error <= -1) {
                opMode.telemetry.addLine("Too far left");
                opMode.telemetry.update();

                fl.setPower(power * 1.25);
                fr.setPower(power * 0.8);
                bl.setPower(power * 1.25);
                br.setPower(power * 0.8);
            }
            else {
                opMode.telemetry.addLine("On track");
                opMode.telemetry.update();

                startMotors(power);

            }

        }
        stopMotors();

    }

    public void moveEncBadHardwareForward(double power, double distance, double timeout) {
        resetEncoders();

        ElapsedTime time = new ElapsedTime();

        double initEncoder = getEncoderAvg();

        time.reset();

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

    //===================================== GYRO METHODS ===========================================

    public void turnGyro(double power, double angleChange, boolean turnRight, double timeout) {
        ElapsedTime time = new ElapsedTime();

        double initAngle = sensors.getGyroYaw();

        time.reset();

        while (Math.abs((sensors.getGyroYaw() - initAngle)) < angleChange && opMode.opModeIsActive() && time.seconds() < timeout) {
            turn(power, turnRight);

            opMode.telemetry.addData("Angle left", (angleChange - Math.abs((sensors.getGyroYaw() - initAngle))));
            opMode.telemetry.update();

        }
        stopMotors();

    }

    //====================================== PID METHODS ===========================================

    public void turnPI(double angleChange, boolean turnRight, double kP, double kI, double timeout) {
        ElapsedTime time = new ElapsedTime();
        ElapsedTime timeoutTimer = new ElapsedTime();

        double error;
        double power;
        boolean isNegative;

        double proportional;
        double integral = 0;
        double bias = 0.08;

        double prevRunTime;

        double initAngle = sensors.getGyroYaw();

        time.reset();
        timeoutTimer.reset();

        while (Math.floor(Math.abs(sensors.getGyroYaw() - (angleChange + initAngle))) > 1 && timeoutTimer.seconds() < timeout && opMode.opModeIsActive()) {
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

            opMode.telemetry.addData("error", error);
            opMode.telemetry.addData("bias ", bias);
            opMode.telemetry.addData("P", proportional);
            opMode.telemetry.addData("I", integral);
            opMode.telemetry.addData("power", power);
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

        double proportional;
        double integral = 0;
        double derivative;

        double prevRunTime;

        double initAngle = sensors.getGyroYaw();
        double lastError = angleChange - Math.abs(sensors.getGyroYaw() - initAngle);

        time.reset();
        timeoutTimer.reset();

        while (Math.abs(sensors.getGyroYaw() - (angleChange + initAngle)) > 1 && timeoutTimer.seconds() < timeout && opMode.opModeIsActive()) {
            // ((getGyroYaw() - initAngle) - angleChange) != 0
            prevRunTime = time.seconds();

            error = angleChange - Math.abs(sensors.getGyroYaw() - initAngle);

            proportional = error * kP;
            integral += (error * (time.seconds() - prevRunTime)) * kI;
            derivative = ((error - lastError) / (time.seconds() - prevRunTime)) * kD;

            power = proportional + integral + derivative;

            turn(power, turnRight);

            opMode.telemetry.addData("error ", error);
            opMode.telemetry.addData("P", proportional);
            opMode.telemetry.addData("I", integral);
            opMode.telemetry.addData("D", derivative);
            opMode.telemetry.addData("power", power);
            opMode.telemetry.update();

            lastError = error;

            opMode.idle();

        }
        stopMotors();

    }

    //=======================================  TELEMETRY METHODS ===================================

    //returns values of gyro axis for testing purposes
    public void composeTelemetryGyro() {
        while (opMode.opModeIsActive()) {
            opMode.telemetry.addData("Yaw", sensors.getGyroYaw());
            opMode.telemetry.addData("Pitch", sensors.getGyroPitch());
            opMode.telemetry.addData("Roll", sensors.getGyroRoll());
            opMode.telemetry.update();

        }

    }

    public void composeTelemetryEncoders () {
        while (opMode.opModeIsActive()) {
            opMode.telemetry.addData("Encoder Average", getEncoderAvg());
            opMode.telemetry.update();

        }

    }

}
