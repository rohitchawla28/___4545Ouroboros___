package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeleOp.OPMode;

@TeleOp
        (name = "ArcadeDrive", group = "Controlled")

public class DriveTestTele extends OPMode {

    public void loop() {
        //tankDrive();
        arcadeDrive();

    }

}