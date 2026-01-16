/*
Copyright (c) 2025-2026 Kosei Tsukamoto (@splitlane)

Made for Team 13190 Roblivion

Rewrite and refactor of PlatformTest.java in my ideal style.
*/


package org.firstinspires.ftc.teamcode.TestOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class PlatformTest_KoseiRewrite extends LinearOpMode {
    // Dashboard config variables
    public static double platformPosition = 0; // [0, 1]
    /*
    how much to adjust platformPosition by when dpad up or down is being held
    this should be small, its changing position by like 50 times this per second
     */
    public static double platformAdjustAmount = 0.01;

    // Hardware
    Servo platformRightServo;
    Servo platformLeftServo;


    @Override
    public void runOpMode() throws InterruptedException {
        // Init hardware
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        platformRightServo = hardwareMap.get(Servo.class, "platformRight");
        platformLeftServo = hardwareMap.get(Servo.class, "platformLeft");

        platformRightServo.setDirection(Servo.Direction.REVERSE);

        // Warnings
        telemetry.addLine("DO NOT RUN THIS if you haven't tuned it.");
        telemetry.addLine("");
        telemetry.addLine("Tuning steps:");
        telemetry.addLine("1. Disconnect the middle connection (platform)");
        telemetry.addLine("2. Make sure that servos are free");
        telemetry.addLine("3. Try changing platformPosition, and see if it changes correctly (add a piece of tape to servo head to see better if needed)");
        telemetry.addLine("4. If they move in sync, you are set");
        telemetry.addLine("5. DO NOT move the servos, and attach the middle connection (platform)");
        telemetry.addLine("- Kosei");
        telemetry.update();

        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Platform controls
            if (gamepad1.dpad_up) {
                platformPosition += platformAdjustAmount;
            }
            if (gamepad1.dpad_down) {
                platformPosition -= platformAdjustAmount;
            }

            // Clamp platformPosition to [0, 1]
            if (platformPosition < 0) {
                platformPosition = 0;
            }
            if (platformPosition > 1) {
                platformPosition = 1;
            }

            // Set positions
            platformLeftServo.setPosition(platformPosition);
            platformRightServo.setPosition(platformPosition);

            // Telemetry
            telemetry.addData("platformPosition", platformPosition);
            telemetry.addLine("");
            telemetry.addLine("Controls:");
            telemetry.addLine("- dpad up = increase platformPosition");
            telemetry.addLine("- dpad down = decrease platformPosition");
            telemetry.update();
        }

    }
}