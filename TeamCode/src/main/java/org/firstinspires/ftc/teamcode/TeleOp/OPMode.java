package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


public abstract class OPMode extends OpMode {

    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    public DcMotor armPivotL;
    public DcMotor armPivotR;
    public DcMotor liftL;
    public DcMotor liftR;

    public Servo door;
    public Servo collectPivotL;
    public Servo collectPivotR;

    public CRServo collectL;
    public CRServo collectR;

    double leftDrive = gamepad1.left_stick_y;
    double rightDrive = gamepad1.right_stick_y;

    @Override
    public void init() {

        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        armPivotL = hardwareMap.dcMotor.get("armPivotL");
        armPivotR = hardwareMap.dcMotor.get("armPivotR");
        liftL = hardwareMap.dcMotor.get("liftL");
        liftR = hardwareMap.dcMotor.get("liftR");

        fl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.FORWARD);

        armPivotL.setDirection(DcMotor.Direction.REVERSE);
        armPivotR.setDirection(DcMotor.Direction.FORWARD);
        liftL.setDirection(DcMotor.Direction.REVERSE);
        liftR.setDirection(DcMotor.Direction.FORWARD);

        collectL.setDirection(DcMotor.Direction.REVERSE);
        collectR.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addLine("Initialized");
        telemetry.update();

    }

    public void tankDrive() {
        if (leftDrive > .08 || leftDrive < -0.8) {
            fl.setPower(leftDrive);
            bl.setPower(leftDrive);
        } else {
            fl.setPower(0);
            bl.setPower(0);
        }

        if (rightDrive > 0.8 || rightDrive < -0.8) {
            fr.setPower(rightDrive);
            br.setPower(rightDrive);
        } else {
            fr.setPower(0);
            br.setPower(0);
        }
    }

    public void intakePivot() {
        
    }

    public void lift() {

        double liftPower = gamepad2.right_stick_y;

        if (liftPower > .1) {
            liftL.setPower(liftPower);
            liftR.setPower(liftPower);
        }

        else if (liftPower < -.1) {
            liftL.setPower(liftPower);
            liftR.setPower(liftPower);
        }
        else {
            liftL.setPower(0);
            liftR.setPower(0);
        }
    }



    public void collect() {

        while (gamepad1.left_bumper) {

            collectL.setPower(0.7);
            collectR.setPower(0.7);

        }
        while (gamepad1.right_bumper) {

            collectL.setPower(-0.6);
            collectR.setPower(-0.6);

        }

        collectR.setPower(0);
        collectL.setPower(0);

    }


    //================================== UTILITY METHODS ===========================================

    public void resetEncoders() {

        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}