package ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift {

        private LinearOpMode opMode;
        private ElapsedTime runTime;

        private DcMotor liftL;
        private DcMotor liftR;

        private Servo lockLiftL;
        private Servo lockLiftR;


        public Lift(LinearOpMode opMode) throws InterruptedException {

            this.opMode = opMode;
            runTime = new ElapsedTime();

            liftL = this.opMode.hardwareMap.get(DcMotor.class , "liftL");
            liftR = this.opMode.hardwareMap.get(DcMotor.class, "liftR");

            lockLiftL = this.opMode.hardwareMap.get(Servo.class, "lockLiftL");
            lockLiftR = this.opMode.hardwareMap.get(Servo.class, "lockLiftR");

            liftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            liftL.setDirection(DcMotorSimple.Direction.FORWARD);
            liftR.setDirection(DcMotorSimple.Direction.REVERSE);

            //set position to hold robot
            lockLiftL.setPosition(0.2);
            lockLiftR.setPosition(0.55);
        }

        public void unlock() {

            lockLiftL.setPosition(0.4);
            lockLiftR.setPosition(0.25);

            opMode.sleep(2000);

        }

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
            unlock();

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

        public void detachEncoder() {
            double initEncoder = (liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2;
            double distance = 500;
            double timeout = 4000;

            //let go off robot
            unlock();

            runTime.reset();

            while ((((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2) - initEncoder) < distance && runTime.milliseconds() < timeout && opMode.opModeIsActive()) {
                liftL.setPower(0.4);
                liftR.setPower(0.4);

                opMode.telemetry.addData("Encoder distance left - ", (distance - ((liftL.getCurrentPosition() + liftR.getCurrentPosition()) / 2)));
                opMode.telemetry.update();
            }
        }

}
