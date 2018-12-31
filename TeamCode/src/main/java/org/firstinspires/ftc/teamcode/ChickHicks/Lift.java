package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.OpenCVDetection;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.Vuforia;

import static java.lang.Math.E;

public class Lift {

        private LinearOpMode opMode;

        private DcMotor liftL;
        private DcMotor liftR;

        private Sensors sensors;

        public Lift(LinearOpMode opMode) throws InterruptedException {
            this.opMode = opMode;

            sensors = new Sensors(this.opMode, true);
            liftL = this.opMode.hardwareMap.dcMotor.get("liftL");
            liftR = this.opMode.hardwareMap.dcMotor.get("liftR");

            liftL.setDirection(DcMotorSimple.Direction.FORWARD);
            liftR.setDirection(DcMotorSimple.Direction.REVERSE);

        }

        //===================================  DETATCH METHODS  ====================================

        public void detachRange() {
            while (sensors.getDistance() > 0.1 && opMode.opModeIsActive()) {
                liftL.setPower(-0.4);
                liftR.setPower(-0.4);

            }

        }

        //MAKE SURE THE METHOD KEEPS GOING LONG ENOUGH SO THAT IT RAISES ENOUGH TO TURN OUT
        public void detachTime() {
            ElapsedTime time = new ElapsedTime();
            int timeout = 3500;

            time.reset();

            while (time.milliseconds() < timeout && opMode.opModeIsActive()) {
                liftL.setPower(-0.3);
                liftR.setPower(-0.3);

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

        //======================================  LIFT MOVEMENTS  ==================================

        public void moveLift(boolean extending, int timeout) {
            ElapsedTime time = new ElapsedTime();

            time.reset();

            while (time.milliseconds() < timeout && opMode.opModeIsActive()) {
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

        public void extendSampling(Drivetrain drivetrain, Vuforia vuforia) throws InterruptedException {
            OpenCVDetection vision = new OpenCVDetection(opMode, vuforia);
            vision.process(vuforia.convertToMat());

            opMode.telemetry.addData("Cube Position", TensorFlowDetection.cubePosition);
            opMode.telemetry.update();

            switch (vision.cubePositionAlt) {
                case "left" :
                    drivetrain.turnPI(20, false, 0.33/90, 0.013, 2);
                    opMode.sleep(500);
                    moveLift(true, 3000);
                    opMode.sleep(500);
                    moveLift(false, 3000);
                    opMode.sleep(500);
                    drivetrain.turnPI(20, true, 0.33/90, 0.013, 2);
                    opMode.sleep(500);
                    break;

                case "center" :
                    moveLift(true, 3000);
                    opMode.sleep(500);
                    moveLift(false, 3000);
                    opMode.sleep(500);
                    break;

                case "right" :
                    drivetrain.turnPI(20, true, 0.33/90, 0.013, 2);
                    opMode.sleep(500);
                    moveLift(true, 3000);
                    opMode.sleep(500);
                    moveLift(false, 3000);
                    opMode.sleep(500);
                    drivetrain.turnPI(20, false, 0.33/90, 0.013, 2);
                    opMode.sleep(500);
                    break;

                case "unknown" :
                    moveLift(true, 3000);
                    opMode.sleep(500);
                    moveLift(false, 3000);
                    opMode.sleep(500);
                    break;

            }

        }

        public void resetEncoders() {
            liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            opMode.idle();
            liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            opMode.idle();

        }

}
