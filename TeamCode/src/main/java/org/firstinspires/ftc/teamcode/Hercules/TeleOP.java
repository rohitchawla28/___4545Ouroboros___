package org.firstinspires.ftc.teamcode.Hercules;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
        (name = "HerculesTele", group = "Controlled")

public class TeleOP extends TeleLib {

    public void loop() {
        tankToggle();
        halfSpeed();

        if (arcade) {
            arcadeDrive();

        }
        else {
            tankDrive();

        }

        //threadStates();

        // Manipulator methods
        lift();
        armPivot();
        openDoor();
        closeDoor();
        collect();
        unlock();
        lock();




    }

}
