package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
        (name = "TeleOP", group = "Controlled")

public class TeleOP extends OPMode {

    public void loop() {
        // Driving methods based on driver preference
        arcadeDrive();
        tankDrive();

        halfSpeed();

        // Manipulator methods
        lift();
        armPivot();
        intakePivot();
        door();
        collect();

    }

}
