/* Copyright (c) 2018 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.ChickHicks.Vision;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class TFHang {
    private LinearOpMode opMode;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AcD8BwX/////AAABmQfyyiD3b0tXiwsm/UX+fHkiPPZJQu55dY7HGrCBT84yc2dP8K+9mWY/3l3gcOKEmSvG+xB9UTPZRTzLqONEuj4hrYpRZtfz6wDkC4IWUvxdgh3+On8UHBaue+CJveRpqla8XZtgMJUqzE3Mxt4QBk3SFkh815rM08JJ11a4XsZrxD4ZDVI6XcsrBmWFub8E/+weoU5gweajvJcE5tzVyLn7IaaYyshx9CHJdS0ObM29e3tHbVJjpwsU/zuoEEoXNRUL++LR0j8z6KY7WQvnsf0PyZXIpu6/tvFR1/WMn74Rc7IkWdO3sdiRQL3i96/rhOeAvQfjlg1VJhEyWKXqqLfQSJrOQSCKegayB4KFCXZf";
    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    public static String cubePosition;

    public TFHang (LinearOpMode opMode) {
        this.opMode = opMode;
        cubePosition = "";
    }

    public void initialize() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();

        }
        else {
            opMode.telemetry.addLine("TFOD Init Failed");

        }

    }

    public void sample() {
        if (opMode.opModeIsActive()) {
            if (tfod != null) {
                tfod.activate();

            }

            while (cubePosition.equals("") && opMode.opModeIsActive()) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

                    if (updatedRecognitions != null) {
                        opMode.telemetry.addData("Object Detected", updatedRecognitions.size());

                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();

                                }
                                else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();

                                }
                                else {
                                    silverMineral2X = (int) recognition.getLeft();

                                }

                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    opMode.telemetry.addData("Gold Mineral Position", "Left");

                                }
                                else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    opMode.telemetry.addData("Gold Mineral Position", "Right");

                                }
                                else {
                                    opMode.telemetry.addData("Gold Mineral Position", "Center");

                                }

                            }

                        }

                        opMode.telemetry.update();

                    }

                }

            }

        }

        tfod.shutdown();

    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    private void initTfod() {
        int tfodMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);

    }
}
