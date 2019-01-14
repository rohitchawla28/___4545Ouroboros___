package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.OPMode;

//@Disabled
@TeleOp
        (name = "ServoTesting", group = "Auto")

public class ServoPositions extends OpMode {

    private Servo door;
    private Servo intakePivotL;
    private Servo intakePivotR;
    private Servo unhookL;
    private Servo unhookR;
    private CRServo collectL;
    private CRServo collectR;

    private boolean doorOn;

    private int position = 0;

    public void init() {
        // door = hardwareMap.servo.get("door");
        //intakePivotL = hardwareMap.servo.get("intakePivotL");
//        intakePivotR = hardwareMap.servo.get("intakePivotR");
//        collectL = hardwareMap.crservo.get("collectL");
//        collectR = hardwareMap.crservo.get("collectR");
        //unhookL = hardwareMap.servo.get("unhookL");
        //unhookR = hardwareMap.servo.get("unhookR");

        intakePivotL.setPosition(0.5);

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void loop() {
        if (gamepad1.dpad_up) {
            position += .1;
            intakePivotL.setPosition(position);

            telemetry.addData("Servo Position", position);
            telemetry.update();

        }

        if (gamepad1.dpad_down) {
            position -= 0.1;
            intakePivotL.setPosition(position);

            telemetry.addData("Servo Position", position);
            telemetry.update();

        }

    }

}
