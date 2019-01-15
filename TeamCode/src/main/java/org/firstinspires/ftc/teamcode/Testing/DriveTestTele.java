package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.OPMode;

@Disabled
@TeleOp
        (name = "DriveTest", group = "Controlled")

public class DriveTestTele extends OPMode {

    public void loop() {
        //tankDrive();
        arcadeDrive();

    }

}