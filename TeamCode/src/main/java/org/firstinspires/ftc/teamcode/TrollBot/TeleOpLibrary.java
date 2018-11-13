package TrollBot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class TeleOpLibrary extends OpMode{

    public DcMotor fr;
    public DcMotor fl;
    public DcMotor br;
    public DcMotor bl;


    public Servo arm;
    public Servo claw;

    public double leftDrive;
    public double rightDrive;

    public void initialize(){
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");

        arm = hardwareMap.get(Servo.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");

        leftDrive = 0;

        arm.setPosition(0);
        claw.setPosition(.5);
    }

    public void tankDrive(){
        leftDrive = -gamepad1.left_stick_y;
        rightDrive = -gamepad1.right_stick_y;

        if (leftDrive > .1){
            fl.setPower(leftDrive);
            bl.setPower(leftDrive);
        }
        else if (leftDrive < .1){
            fl.setPower(-leftDrive);
            bl.setPower(-leftDrive);
        }
        else{
            fl.setPower(0);
            bl.setPower(0);
        }

        if (rightDrive > .1){
            fr.setPower(rightDrive);
            br.setPower(rightDrive);
        }
        else if (rightDrive < .1){
            fr.setPower(-rightDrive);
            br.setPower(-rightDrive);
        }
        else{
            fr.setPower(0);
            br.setPower(0);
        }
    }

    public void moveArm(){
        //left position
        if (gamepad2.dpad_left){
            arm.setPosition(0);
        }

        // center positionor ready to grab position
        if(gamepad2.dpad_up || gamepad2.dpad_down){
            arm.setPosition(.5);
        }

        //right position
        if(gamepad2.dpad_right){
            arm.setPosition(1);
        }
    }

    public void clamp(){
        //open
        if (gamepad2.a){
            claw.setPosition(.7);
        }
        //close
        if(gamepad2.b){
            claw.setPosition(0);
        }
    }


}
