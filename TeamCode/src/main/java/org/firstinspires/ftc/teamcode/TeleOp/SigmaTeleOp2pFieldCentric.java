package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.Shooting;


@TeleOp
public class SigmaTeleOp2pFieldCentric extends LinearOpMode {

    private DriveTrain drive;
    private Shooting shooting;

    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;


    @Override
    public void runOpMode() throws InterruptedException {

        drive = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);


        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {




            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.options) {
                imu.resetYaw();
            }



            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            drive.frontLeftMotor.setPower(frontLeftPower);
            drive.backLeftMotor.setPower(backLeftPower);
            drive.frontRightMotor.setPower(frontRightPower);
            drive.backRightMotor.setPower(backRightPower);


            if (gamepad2.right_trigger > 0.7) {
                shooting.outtakeMotor.setPower(0.7);
            }
            else if (gamepad2.left_trigger > 0.7) {
                shooting.outtakeMotor.setPower(0.4);
            }
            else {
                shooting.outtakeMotor.setPower(0);
            }


            if (gamepad2.right_bumper) {
                shooting.platformRight.setPosition(1);
                shooting.platformLeft.setPosition(1);

            } else {
                shooting.platformRight.setPosition(0);
                shooting.platformLeft.setPosition(0);
            }






// TELEMETRY

            telemetry.addLine("Intake: Left Bumper");
            telemetry.addLine("Platform: Right Bumper");
            telemetry.addLine("Outtake Power: 0.7 right trigger, 0.4 left trigger");

            telemetry.addData("Platform", shooting.platformRight.getPosition());
            telemetry.addData("Platform", shooting.platformLeft.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());
            telemetry.addData("front left", drive.frontLeftMotor.getPower());
            telemetry.addData("back left", drive.backLeftMotor.getPower());
            telemetry.addData("front right", drive.frontRightMotor.getPower());
            telemetry.addData("back right", drive.backRightMotor.getPower());


            telemetry.update();




        }
    }
}




























