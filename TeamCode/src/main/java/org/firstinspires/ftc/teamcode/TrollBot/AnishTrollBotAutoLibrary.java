package TrollBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class AnishTrollBotAutoLibrary extends LinearOpMode{

    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;

    public Servo claw;
    public Servo arm;

    public void initialize() {
        FR = hardwareMap.get(DcMotor.class, "FR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");

        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(Servo.class, "arm");

        FR.setDirection(DcMotor.Direction.FORWARD);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.FORWARD);
        BR.setDirection(DcMotor.Direction.REVERSE);

        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void moveEncoder(double targetPosition, double power)
    {
        while(averageEncoder() < targetPosition && opModeIsActive())
        {
            FR.setPower(power);
            FL.setPower(power);
            BR.setPower(power);
            BL.setPower(power);
        }
    }
    public void turnEncoder(double targetPosition, double power)
    {
        while(averageEncoder() < targetPosition && opModeIsActive())
        {
            FR.setPower(power);

        }

    }

    public double averageEncoder()
    {
        return (FR.getCurrentPosition() + FL.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition())/4;
    }



}
