package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
        (name = "HerculesTele", group = "Controlled")

public class TeleOP extends TeleLib {

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
        collect();
        lockLift();


    }

}
