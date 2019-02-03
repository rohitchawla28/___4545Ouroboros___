package org.firstinspires.ftc.teamcode.HerculesLibraries;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {

    private LinearOpMode opMode;

    public Servo intakePivotL;
    public Servo intakePivotR;

//    private CRServo collectL;
//    private CRServo collectR;


    public Intake(LinearOpMode opMode) {
        this.opMode = opMode;

        // collectL = this.opMode.hardwareMap.crservo.get("collectL");
        // collectR = this.opMode.hardwareMap.crservo.get("collectR");

        intakePivotL = this.opMode.hardwareMap.servo.get("intakePivotL");
        intakePivotR = this.opMode.hardwareMap.servo.get("intakePivotR");

    }
//
//    public void collect(boolean in) {
//        // 0.6 power because of VEX 393 Motors
//        if (in) {
//            collectL.setPower(0.6);
//            collectR.setPower(0.6);
//
//        }
//        else {
//            collectL.setPower(-0.6);
//            collectR.setPower(-0.6);
//
//        }
//
//    }

    public void setIntakePivotMarkerDeploymentPosition() {
       intakePivotL.setPosition(0.7);
       intakePivotR.setPosition(0.2);

    }

    public void setIntakePivotDepositPosition() {
        intakePivotL.setPosition(0.9);
        intakePivotR.setPosition(0);

    }

}