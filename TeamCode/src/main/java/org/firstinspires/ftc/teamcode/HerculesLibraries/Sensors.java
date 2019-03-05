package org.firstinspires.ftc.teamcode.HerculesLibraries;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Sensors {

    // importing LinearOpMode for our usage
    private LinearOpMode opMode;

    // initalizing gyro
    public BNO055IMU gyro;
    private  Orientation angles;
    Acceleration gravity;
    private BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    // constructor for initializing Sensors class
    public Sensors(LinearOpMode opMode, boolean IMUenabled) throws InterruptedException {
        this.opMode = opMode;

        // gyro init
        if (IMUenabled){
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            gyro = this.opMode.hardwareMap.get(BNO055IMU.class, "imu");
            gyro.initialize(parameters);

        }

    }

    // Note: Due to positioning of REV Hub, yaw is the 1st Angle, roll 3rd Angle, and pitch 2nd Angle.

    public void updateGyroValues() {
        angles = gyro.getAngularOrientation();

    }

    public void updateGyroR() {
        angles = gyro.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX);

    }

    // method to return yaw of robot for all turns
    public double getGyroYaw() {
        updateGyroValues();
        return angles.firstAngle;

    }

    public double getGyroYawR() {
        return -angles.firstAngle;

    }

    public double getGyroPitch() {
        updateGyroValues();
        return angles.secondAngle;

    }

    public double getGyroRoll() {
        updateGyroValues();
        return angles.thirdAngle;

    }
    
}
