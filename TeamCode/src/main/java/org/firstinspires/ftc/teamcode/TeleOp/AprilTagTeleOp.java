package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;


import org.firstinspires.ftc.teamcode.Utils.Pattern;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;

import org.firstinspires.ftc.teamcode.AutoOp.SigmaTimeBasedPick;

@TeleOp
public class AprilTagTeleOp extends LinearOpMode {

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




        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {



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





//have aim for when alliance is red or alliance is blue

            if (SigmaTimeBasedPick.alliance == Pattern.alliance.RED){

            }else if (SigmaTimeBasedPick.alliance == Pattern.alliance.BLUE){

            }else{
               //backup if it doesn't work

            }





// TELEMETRY

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




























