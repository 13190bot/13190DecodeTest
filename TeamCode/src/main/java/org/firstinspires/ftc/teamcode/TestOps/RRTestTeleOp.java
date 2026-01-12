package org.firstinspires.ftc.teamcode.TestOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;

import java.util.ArrayList;
import java.util.List;


@TeleOp
public class RRTestTeleOp extends LinearOpMode {

    private MecanumDrive drive;
    private FtcDashboard dash = FtcDashboard.getInstance();

    private Shooting shooting;

    GamepadEx gamepadEx1;
    private List<Action> runningActions = new ArrayList<>();

    GamepadEx gamepadEx2;


    @Override
    public void runOpMode() throws InterruptedException {

        drive = new MecanumDrive(hardwareMap, new Pose2d(70, 37, 0));
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

            drive.leftFront.setPower(frontLeftMotorPower);
            drive.leftBack.setPower(backLeftMotorPower);
            drive.rightFront.setPower(frontRightMotorPower);
            drive.rightBack.setPower(backRightMotorPower);


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







            TelemetryPacket packet = new TelemetryPacket();

            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;

            dash.sendTelemetryPacket(packet);


            if (gamepad1.right_bumper){
                runningActions.add(new InstantAction(()->shooting.shoot()));
            }





// TELEMETRY


            telemetry.addData("xValue", drive.localizer.getPose().position.x);
            telemetry.addData("yValue", drive.localizer.getPose().position.x);
            telemetry.addData("heading", drive.localizer.getPose().heading);



            telemetry.addLine("Intake: Left Bumper");
            telemetry.addLine("Platform: Right Bumper");
            telemetry.addLine("Outtake Power: 0.7 right trigger, 0.4 left trigger");

            telemetry.addData("Platform", shooting.platformRight.getPosition());
            telemetry.addData("Platform", shooting.platformLeft.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());
            telemetry.addData("front left", drive.leftFront.getPower());
            telemetry.addData("back left", drive.leftBack.getPower());
            telemetry.addData("front right", drive.rightFront.getPower());
            telemetry.addData("back right", drive.rightBack.getPower());


            telemetry.update();




        }
    }
}




























