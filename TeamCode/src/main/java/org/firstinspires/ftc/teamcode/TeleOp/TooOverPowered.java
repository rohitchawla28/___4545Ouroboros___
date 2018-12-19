package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
        (name = "TooOverPowered", group = "Controlled")

public class TooOverPowered extends OPMode {

    public void loop() {

        lift();
        extend();
    }
}
