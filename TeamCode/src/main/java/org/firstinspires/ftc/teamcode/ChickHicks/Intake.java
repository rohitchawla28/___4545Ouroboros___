package org.firstinspires.ftc.teamcode.ChickHicks;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.ChickHicks.Vision.TensorFlowDetection.cubePosition;

public class Intake {

    private LinearOpMode opMode;

    private DcMotor extend;

    private CRServo collectFL;
    private CRServo collectFR;
    private CRServo collectBL;
    private CRServo collectBR;

    private Servo gate;


    public Intake(LinearOpMode opMode) {

        this.opMode = opMode;

        collectFL = this.opMode.hardwareMap.crservo.get("collectFL");
        collectFR = this.opMode.hardwareMap.crservo.get("collectFR");
        collectBL = this.opMode.hardwareMap.crservo.get("collectBL");
        collectBR = this.opMode.hardwareMap.crservo.get("collectBR");

        gate = this.opMode.hardwareMap.servo.get("gate");

        extend = this.opMode.hardwareMap.dcMotor.get("extend");

    }

    public void startCollect(boolean in) {

         // Sucks in
        if (in) {

            collectFL.setPower(0.7);
            collectFR.setPower(-0.7);
            collectBL.setPower(0.7);
            collectBR.setPower(-0.7);

         }

         //Spits out
        else if(!in) {
            collectFL.setPower(-0.7);
            collectFR.setPower(0.7);
            collectBL.setPower(-0.7);
            collectBR.setPower(0.7);
        }

    }

    public void stopCollect() {

        collectFL.setPower(0);
        collectFR.setPower(0);
        collectBL.setPower(0);
        collectBR.setPower(0);

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

    //================================= SAMPLING ===================================================
    public void extendSampling(Drivetrain drivetrain){
        while(cubePosition != 0) {
            switch(cubePosition)
            {
                case 1:
                    drivetrain.turnGyro(0.3, 13, false, 4);
                    extend(300, true);
                    drivetrain.turnGyro(0.3,13, true,4);
                    cubePosition = 0;
                    break;
                case 2:
                    extend(300, true);
                    cubePosition = 0;
                    break;
                case 3:
                    drivetrain.turnGyro(0.3, 13, true, 4);
                    extend(300, true);
                    drivetrain.turnGyro(0.3,13, false,4);
                    cubePosition = 0;

                    break;

                default:
                    cubePosition = 2;

            }
        }
        extend(300, false);
    }
}