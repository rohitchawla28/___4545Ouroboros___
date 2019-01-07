package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.OPMode;

@Disabled
@TeleOp
        (name = "PivotTest", group = "Controlled")

public class Pivot extends OPMode {

    public void loop() {
        armPivot();

    }

}
