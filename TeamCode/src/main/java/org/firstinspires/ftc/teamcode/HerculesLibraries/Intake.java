package org.firstinspires.ftc.teamcode.HerculesLibraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        collectL.setDirection(DcMotorSimple.Direction.FORWARD);
        collectR.setDirection(DcMotorSimple.Direction.REVERSE);

        lock.setPosition(0.8);

    }

    public void collect(boolean in, double timeout) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            if (in) {
                collectL.setPower(-0.6);
                collectR.setPower(-0.6);

            }
            else {
                collectL.setPower(0.6);
                collectR.setPower(0.6);

            }

        }

    }

    public void deployMarker() {
        lift.moveArm(0.5, true);

        opMode.telemetry.addLine("Completed arm up movement");
        opMode.telemetry.update();

        lift.moveLift(1.2, true);

        opMode.telemetry.addLine("Completed extending out movement");
        opMode.telemetry.update();

        collect(false, 1);

        opMode.telemetry.addLine("Completed collect out movement");
        opMode.telemetry.update();

        opMode.sleep(750);

        lift.moveLift(0.5, false);

        opMode.telemetry.addLine("Completed retracting lift movement");
        opMode.telemetry.update();

    }

}