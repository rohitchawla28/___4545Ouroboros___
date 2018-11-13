package ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    private LinearOpMode opMode;

    private DcMotor extend;

    private CRServo collectL;
    private CRServo collectR;

    private Servo tiltL;
    private Servo tiltR;



    public Intake(LinearOpMode opMode) {

        this.opMode = opMode;

        collectL = this.opMode.hardwareMap.get(CRServo.class, "collectL");
        collectR = this.opMode.hardwareMap.get(CRServo.class, "collectR");
        tiltL = this.opMode.hardwareMap.get(Servo.class, "tiltL");
        tiltR = this.opMode.hardwareMap.get(Servo.class, "tiltR");
        extend = this.opMode.hardwareMap.get(DcMotor.class, "collectL");

    }

    public void startCollect(double power, boolean intake) {

         // Sucks in
        if (intake) {

            collectL.setPower(-0.7);
            collectR.setPower(0.7);

         }

         //Spits out
        else if(!intake) {
            collectL.setPower(0.7);
            collectR.setPower(-0.7);
        }

    }

    public void stopCollect() {

        collectL.setPower(0);
        collectR.setPower(0);

    }

    public void extend(double distanceOut, boolean in) {

        double initEncoder = extend.getCurrentPosition();
        double accuracy = 0/* TEST VALUE */;

        // Extends out
        if (in) {
            while (extend.getCurrentPosition() < (distanceOut - accuracy)) {
                extend.setPower(0.8);
            }
        }

         // Retracts back
        else if (!in) {
            while (-(extend.getCurrentPosition()) < (initEncoder - accuracy)) {
                extend.setPower(-0.6);
            }
        }

    }

    public void tiltUnfold() {

        tiltL.setPosition(-0.75);
        tiltR.setPosition(0.75);

    }

    //holds mineral so it doesn't fall out
    public void tiltHold() {

        tiltL.setPosition(-0.5);
        tiltR.setPosition(0.5);

    }

}