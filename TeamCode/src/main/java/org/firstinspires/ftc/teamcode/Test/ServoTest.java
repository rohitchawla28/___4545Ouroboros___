package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
        (name = "ServoPositionTest", group = "Auto")

public class ServoTest extends LinearOpMode {

    private Servo door;
    private Servo intakePivotL;
    private Servo intakePivotR;

    private CRServo collectL;
    private CRServo collectR;

    private boolean bool = true;

    public void initialize() {
        door = hardwareMap.servo.get("door");

        intakePivotL = hardwareMap.servo.get("intakePivotL");
        intakePivotR = hardwareMap.servo.get("intakePivotR");

        collectL = hardwareMap.crservo.get("collectL");
        collectR = hardwareMap.crservo.get("collectR");

        collectL.setDirection(CRServo.Direction.FORWARD);
        collectR.setDirection(CRServo.Direction.REVERSE);

        // this is close to marker deployment
        //intakePivotR.setPosition(0.15);

        // set servo movement in intialization and another in the actual running section

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void runOpMode() throws InterruptedException{
        initialize();

        waitForStart();

        while (bool) {
            collectL.setPower(0.5);
            collectR.setPower(0.5);

        }

        // sleep makes sure program doesn't end before servo moves to desired position

    }

}


