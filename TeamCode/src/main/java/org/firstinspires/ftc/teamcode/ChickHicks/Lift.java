package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Math.E;

public class Lift {

        private LinearOpMode opMode;
        private ElapsedTime runTime;

        private DcMotor liftL;
        private DcMotor liftR;

        //private Servo lockLiftL;
        //private Servo lockLiftR;

        private Sensors sensors;


        public Lift(LinearOpMode opMode) throws InterruptedException {

            this.opMode = opMode;
            runTime = new ElapsedTime();

            sensors = new Sensors(this.opMode, true);
            liftL = this.opMode.hardwareMap.get(DcMotor.class , "liftL");
            liftR = this.opMode.hardwareMap.get(DcMotor.class, "liftR");

            //lockLiftR = this.opMode.hardwareMap.get(Servo.class, "lockLiftR");

            liftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            liftL.setDirection(DcMotorSimple.Direction.FORWARD);
            liftR.setDirection(DcMotorSimple.Direction.REVERSE);
            //set position to hold robot
//            lockLiftL.setPosition(0.2);
//            lockLiftR.setPosition(0.55);
        }

//        public void unlock() {
//
//            //lockLiftL.setPosition(0.4);
////            lockLiftR.setPosition(0.25);
//
//            opMode.sleep(2000);
//
//        }

        public void detachRange() {

//            //let go off robot
//            lockLiftL.setPosition(0.5);
//            lockLiftR.setPosition(0.5);
//
//            while(sensors.getDistance < TEST VALUE && opMode.opModeIsActive()) {
//                liftL.setPower(0.4);
//                liftR.setPower(0.4);
//            }

        }

        //MAKE SURE THE METHOD KEEPS GOING LONG ENOUGH SO THAT IT RAISES ENOUGH TO TURN OUT
        public void detachTime() {
            int time = 2500;
//            unlock();

            runTime.reset();

            while (runTime.milliseconds() < time && opMode.opModeIsActive()) {

                liftL.setPower(-0.3);
                liftR.setPower(-0.3);

            }
        }

        public void shortUp() {
            int time = 1000;
            runTime.reset();

            while (runTime.milliseconds() < time && opMode.opModeIsActive()) {

                liftL.setPower(-0.6);
                liftR.setPower(-0.6);

            }
        }

        public void shortDown() {
            int time = 1500;
            runTime.reset();

            while (runTime.milliseconds() < time && opMode.opModeIsActive()) {
                liftL.setPower(0.6);
                liftR.setPower(0.6);
            }

        }

        public void detachEncoder(Drivetrain drivetrain) {
            double initEncoder = (liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2;
            double distance = 500;
            double timeout = 4000;

            //let go off robot
            //unlock();

            runTime.reset();

            while ((((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2) - initEncoder) < distance && runTime.milliseconds() < timeout && opMode.opModeIsActive()) {
                liftL.setPower(-0.4);
                liftR.setPower(-0.4);
                while ((int)Math.abs(sensors.getGyroYawR()) > 0) {
                    if (sensors.getGyroYawR() > 0){
                        drivetrain.turnGyro(0.3, Math.abs(sensors.getGyroYaw()), false, 3 );
                    }
                    else if (sensors.getGyroYawR() < 0){
                        drivetrain.turnGyro(0.3, Math.abs(sensors.getGyroYaw()), true, 3 );
                    }
                }
                opMode.telemetry.addData("Encoder distance left - ", (distance - ((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2)));
                opMode.telemetry.update();
            }
        }
        public void detachCorrection()
        {
            //while()
        }

}
