package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;

public class Intake {

    private LinearOpMode opMode;

    private DcMotor extend;
    private Servo marker;


    public Intake(LinearOpMode opMode) {

        this.opMode = opMode;

        marker = this.opMode.hardwareMap.servo.get("marker");

        extend = this.opMode.hardwareMap.dcMotor.get("extend");

        marker.setPosition(0.7);

    }

    public void extendTime(double time) {

        ElapsedTime runTime = new ElapsedTime();

        while (runTime.seconds() < time && opMode.opModeIsActive()) {

            extend.setPower(0.8);

        }
    }

    public void retractTime(double time){

        ElapsedTime runTime = new ElapsedTime();

        while (runTime.seconds() < time && opMode.opModeIsActive()) {

            extend.setPower(-0.8);

        }

    }

    public void markerOut() {

        marker.setPosition(0.1);

        opMode.sleep(400);

    }

    //================================= SAMPLING ===================================================
    public void extendSampling(Drivetrain drivetrain) {
        TensorFlowDetection vision = new TensorFlowDetection(opMode);
        vision.sample();

        opMode.telemetry.addData("Cube Position", TensorFlowDetection.cubePosition);
        opMode.telemetry.update();

        switch(cubePosition)
        {
            case "left" :
                drivetrain.turnGyro(0.4, 20, false, 4);
                opMode.sleep(400);
                extendTime(1.25);
                opMode.sleep(400);
                retractTime(1.25);
                opMode.sleep(400);
                drivetrain.turnGyro(0.4,20, true,4);
                opMode.sleep(400);
                break;

            case "center" :
                extendTime(1.25);
                opMode.sleep(400);
                retractTime(1.25);
                opMode.sleep(400);
                break;

            case "right" :
                drivetrain.turnGyro(0.4, 20, true, 4);
                opMode.sleep(400);
                extendTime(1.25);
                opMode.sleep(400);
                retractTime(1.25);
                opMode.sleep(400);
                drivetrain.turnGyro(0.4,20, false,4);
                opMode.sleep(400);
                break;

            default :
                extendTime(1.25);
                opMode.sleep(400);
                retractTime(1.25);
                opMode.sleep(400);
                break;
        }
    }
}