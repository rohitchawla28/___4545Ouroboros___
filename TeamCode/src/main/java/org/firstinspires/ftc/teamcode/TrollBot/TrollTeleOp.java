package org.firstinspires.ftc.teamcode.TrollBot;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp (name = "TrollTeleOp", group = "TeleOp")
public class TrollTeleOp extends TeleOpLibrary {

    public void init() {
        initialize();
    }

    public void loop() {
        tankDrive();
        moveArm();
        clamp();
    }
}
