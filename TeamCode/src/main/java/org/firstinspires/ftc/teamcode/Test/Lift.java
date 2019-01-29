package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hercules.TeleLib;

@Disabled
@TeleOp
        (name = "ElevatorTest", group = "Controlled")

public class Lift extends TeleLib {

    public void loop() {
        lift();

    }

}
