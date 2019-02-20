package org.firstinspires.ftc.teamcode.HerculesLibraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    private LinearOpMode opMode;

    private Lift lift;

    public Servo lock;

    private CRServo collectL;
    private CRServo collectR;


    public Intake(LinearOpMode opMode) {
        this.opMode = opMode;

        lift = new Lift(opMode);

        lock = this.opMode.hardwareMap.servo.get("lock");

        collectL = this.opMode.hardwareMap.crservo.get("collectL");
        collectR = this.opMode.hardwareMap.crservo.get("collectR");

        lock.setPosition(0.8);

    }

    public void collect(boolean in, double timeout) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < timeout) {
            // 0.6 power because of VEX 393 Motors
            if (in) {
                collectL.setPower(0.6);
                collectR.setPower(0.6);

            }
            else {
                collectL.setPower(-0.6);
                collectR.setPower(-0.6);

            }

        }

    }

    public void deployMarker() {
        lift.moveLift(1.5, true);

        collect(false, 0.25);

        lift.moveLift(1, false);

    }

}