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

    // constructor to intialize drive motors and gyro
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

    // set all drive motors to 0
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

    // method receives average of all encoder readings from 4 drive motors
    public double getEncoderAvg() {
        double countZeros = 0.0;

        // if encoder wires gets unplugged and returns 0 it would throw off average
        // throw out zeros so we are unaffected
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

        // return (Math.abs(fl.getCurrentPosition()) + Math.abs(fr.getCurrentPosition())) / 2.0;

    }

    public void moveEncoder(double power, double distance, double timeout) {
        resetEncoders();

        ElapsedTime time = new ElapsedTime();

        // get intial encoder so we can take difference -> doesn't allow last movement to affect next movement
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

        // get initial heading so we can take difference based on that
        double heading = sensors.getGyroYaw();

        time.reset();

        opMode.telemetry.addData("Heading", heading);

        while (Math.abs(getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {
            opMode.telemetry.addData("Current Gyro", sensors.getGyroYaw());

            double error = heading - sensors.getGyroYaw();

            opMode.telemetry.addData("error", error);

            // if robot is turned a lot, instead of taking time to correct back all the way, robot keeps turning to reach intial heading
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

            if (error >= 3) {
                opMode.telemetry.addLine("Too far right");
                opMode.telemetry.update();

                // scale power if going right
                fl.setPower(power * 0.8);
                fr.setPower(power * 1.25);
                bl.setPower(power * 0.8);
                br.setPower(power * 1.25);

            } else if (error <= -3) {
                opMode.telemetry.addLine("Too far left");
                opMode.telemetry.update();

                // scale power if going left
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

    public void moveWall(double power, double distance, boolean leftWall, double timeout) {
        resetEncoders();

        ElapsedTime time = new ElapsedTime();

        // get intial encoder so we can take difference -> doesn't allow last movement to affect next movement
        double initEncoder = getEncoderAvg();

        time.reset();

        while (Math.abs(getEncoderAvg() - initEncoder) < distance && time.seconds() < timeout && opMode.opModeIsActive()) {
            if (leftWall) {
                fl.setPower(power);
                fr.setPower(power * 1.25);
                bl.setPower(power);
                br.setPower(power * 1.25);

            }
            else {
                fl.setPower(power * 1.25);
                fr.setPower(power);
                bl.setPower(power * 1.25);
                br.setPower(power);

            }

            opMode.telemetry.addData("Encoder distance left", (distance - getEncoderAvg()));
            opMode.telemetry.update();

        }
        stopMotors();

    }

    // method used to scale one side of chassis because uneven amounts of friction
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

    // method to turn simply using threshold with no control
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

            // calculate proportional, integral, derivative variables for sum of motor power

            // as error decreases proportional decreases
            proportional = error * kP;

            // use Riemann sums to calculate accumulating area under turning curve
            // length (error) * width (new time) of each rectangle
            integral += (error * (time.seconds() - prevRunTime)) * kI;

            // slope of line at a point, calculated with secant lines
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
            opMode.telemetry.addData("Yaw (First Angle)", sensors.getGyroYaw());
            opMode.telemetry.addData("Pitch (Second Angle)", sensors.getGyroPitch());
            opMode.telemetry.addData("Roll (Third Angle)", sensors.getGyroRoll());
            opMode.telemetry.update();

        }

    }

    public void composeTelemetryEncoders () {
        while (opMode.opModeIsActive()) {
            opMode.telemetry.addData("FL", fl.getCurrentPosition());
            opMode.telemetry.addData("FR", fr.getCurrentPosition());
            opMode.telemetry.addData("BL", bl.getCurrentPosition());
            opMode.telemetry.addData("BR", br.getCurrentPosition());

            opMode.telemetry.update();

        }

    }

}
