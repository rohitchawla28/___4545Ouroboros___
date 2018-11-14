package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*

   Created by
           - Avi Gupta
           - 10/1/18

 */


@Autonomous
        (name = "TensorFlowTest", group = "Auto")

public class AviGupta extends LinearOpMode {

    MachineLearningAvi machineLearningAvi = new MachineLearningAvi();

    public void runOpMode() {

        machineLearningAvi.start();

        waitForStart();

        machineLearningAvi.learn(1);
        machineLearningAvi.close();

    }

}
