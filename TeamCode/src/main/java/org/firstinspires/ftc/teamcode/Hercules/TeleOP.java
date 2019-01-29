package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hercules.OPMode;

@TeleOp
        (name = "TeleOp", group = "Controlled")

public class TeleOP extends OPMode {

    public void loop() {
        // Driving methods based on driver preference
        arcadeDrive();
        // tankDrive();

        halfSpeed();

        // Manipulator methods
        lift();
        armPivot();
        intakePivot();
        door();
        // collect();
        // unhook();
        lockLift();


    }

}
