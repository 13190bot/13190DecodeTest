package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;

import com.arcrobotics.ftclib.command.*;


import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;


@TeleOp
public class CommandTeleOp extends CommandOpMode  {

    private DriveTrain drive;
    private Shooting shooting;

    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;
    RevIMU imu;

    @Override
    public void initialize(){

        drive = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        imu = new RevIMU(hardwareMap);


        telemetry.addLine("initalized");
        telemetry.update();
    }



    @Override
    public void run(){
        double heading = imu.getHeading();





        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {

            CommandScheduler.getInstance().run();


            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftMotorPower = (y + x + rx) / denominator;
            double backLeftMotorPower = (y - x + rx) / denominator;
            double frontRightMotorPower = (y - x - rx) / denominator;
            double backRightMotorPower = (y + x - rx) / denominator;

            drive.frontLeftMotor.setPower(frontLeftMotorPower);
            drive.backLeftMotor.setPower(backLeftMotorPower);
            drive.frontRightMotor.setPower(frontRightMotorPower);
            drive.backRightMotor.setPower(backRightMotorPower);


// TELEMETRY

            telemetry.addData("Platform", shooting.platformRight.getPosition());
            telemetry.addData("Platform", shooting.platformLeft.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());
            telemetry.addData("front left", drive.frontLeftMotor.getPower());
            telemetry.addData("back left", drive.backLeftMotor.getPower());
            telemetry.addData("front right", drive.frontRightMotor.getPower());
            telemetry.addData("back left", drive.frontRightMotor.getPower());


            telemetry.update();




        }
    }
}




























