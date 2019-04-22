package org.firstinspires.ftc.teamcode.HerculesLibraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    private LinearOpMode opMode;

    private Lift lift;

    private Servo lock;
    private Servo door;

    public CRServo collectL;
    public CRServo collectR;

    // constructor to intialize servo and collection
    public Intake(LinearOpMode opMode) {
        this.opMode = opMode;

        lift = new Lift(opMode);

        lock = this.opMode.hardwareMap.servo.get("lock");
        door = this.opMode.hardwareMap.servo.get("door");

        collectL = this.opMode.hardwareMap.crservo.get("collectL");
        collectR = this.opMode.hardwareMap.crservo.get("collectR");

        collectL.setDirection(DcMotorSimple.Direction.FORWARD);
        collectR.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void collect(boolean in, double timeout) {
        ElapsedTime time = new ElapsedTime();

        time.reset();

        while (time.seconds() < timeout && opMode.opModeIsActive()) {
            if (in) {
                collectL.setPower(-0.4);
                collectR.setPower(-0.4);

            }
            else {
                collectL.setPower(0.4);
                collectR.setPower(0.4);

            }

        }
        collectL.setPower(0);
        collectR.setPower(0);

    }

    public void deployMarker() {
        lock();

        // raise arm to clear lift from hitting chassis
        lift.moveArm(0.8, 0.5, true);

        // extend lift into the depot
        lift.moveLift(1, 2, true);

        // unlock intake
        // unlock();

        // open door
        openDoor();

        unlock();

        opMode.sleep(600);

        // spin out to deploy marker
        collect(false, 1);

        collectL.setPower(0.6);
        collectR.setPower(0.6);

        opMode.sleep(1000);

        // lift.moveArm(0.8, 0.75, true);

        // retract lift back in
        lift.moveLift(1, 0.5, false);

    }

    // lock position for intake in autonomous
    public void lock() {
        lock.setPosition(0.48);

    }

    // allow intake to be latched for tele-op
    public void unlock() {
        lock.setPosition(0.65);

    }

    public void closeDoor() {
        door.setPosition(0);

    }

    public void openDoor() {
        door.setPosition(0.3);

    }
}