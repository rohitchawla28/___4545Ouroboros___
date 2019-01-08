package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.OpenCVDetection;
import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;

public class Intake {

    private LinearOpMode opMode;

    private CRServo collectL;
    private CRServo collectR;


    public Intake(LinearOpMode opMode) {
        this.opMode = opMode;

        collectL = this.opMode.hardwareMap.crservo.get("collectL");
        collectR = this.opMode.hardwareMap.crservo.get("collectR");

    }

    public void collect(boolean in) {
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

    public void markerOut() {
        ElapsedTime time =  new ElapsedTime();

        time.reset();

        while (time.seconds() < 2) {
            collectL.setPower(-0.6);
            collectR.setPower(-0.6);

        }

    }

}