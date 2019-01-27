package org.firstinspires.ftc.teamcode.PracticeBot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public abstract class GarrettTrollAutoLibrary extends LinearOpMode {
    public DcMotor fl;
    public DcMotor bl;
    public DcMotor fr;
    public DcMotor br;

    public Servo arm;
    public Servo claw;

    public BNO055IMU gyro;
    Orientation angles;
    Acceleration gravity;
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    public void initialize() {
        fl = hardwareMap.get(DcMotor.class, "fl");
        bl = hardwareMap.get(DcMotor.class, "bl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        br = hardwareMap.get(DcMotor.class, "br");

        arm = hardwareMap.get(Servo.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");

        gyro = hardwareMap.get(BNO055IMU.class, "gyro");
        gyro.initialize(parameters);

        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        fl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.FORWARD);

        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveEncoder(double dis, double power, double timeLimit) {
        double encoderStart = averageTicks();
        double timeStart = System.currentTimeMillis();

        while(averageTicks() - encoderStart < dis && opModeIsActive() &&
                (System.currentTimeMillis() - timeStart) < timeLimit){
            fr.setPower(power);
            fl.setPower(power);
            bl.setPower(power);
            br.setPower(power);
        }
    }

    public void turn(double dis, double power, double timeLimit) {
        double encoderStart = averageTicks();
        double timeStart = System.currentTimeMillis();

        while(averageTicks() - encoderStart < dis && opModeIsActive() &&
                (System.currentTimeMillis() - timeStart) < timeLimit) {
            fr.setPower(-power);
            fl.setPower(power);
            bl.setPower(power);
            br.setPower(-power);
        }
    }

    public double averageTicks() {
        int zeros = 0;

        if(fr.getCurrentPosition() == 0)
            zeros += 1;
        if(bl.getCurrentPosition() == 0)
            zeros += 1;
        if(fl.getCurrentPosition() == 0)
            zeros += 1;
        if(br.getCurrentPosition() == 0)
            zeros += 1;

        if (zeros == 4){
            return 0;
        }

        return (Math.abs(fr.getCurrentPosition()) + Math.abs(bl.getCurrentPosition()) +
                Math.abs(br.getCurrentPosition()) + Math.abs(fl.getCurrentPosition())) / (4 - zeros);
    }

}