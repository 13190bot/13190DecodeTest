package org.firstinspires.ftc.teamcode.TestOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
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

    FtcDashboard dashboard = FtcDashboard.getInstance();

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new MecanumDrive(hardwareMap, new Pose2d(70, 37, 0));
        shooting = new Shooting(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());



        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {


            drive.updatePoseEstimate();
            shooting.updatePID();



            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            drive.setDrivePowers(

                    new PoseVelocity2d(
                            new Vector2d(x, y),
                            rx
                    )
            );


            if (gamepad2.right_bumper){
                shooting.intakeMotor.setPower(0.7);
            }
            if (gamepad2.left_bumper){
                shooting.intakeMotor.setPower(0);
            }

            if (gamepad2.dpad_up){
                shooting.intakeMotor.setPower(0);
                shooting.outtakeMotor.setPower(0);
                shooting.platformRight.setPosition(0);
                shooting.platformLeft.setPosition(0);
            }

            if (gamepad2.square){
                shooting.outtakeMotor.setPower(1);
            }else if (gamepad2.cross){
                shooting.outtakeMotor.setPower(0);
            }

            if (gamepad2.triangle) {
                shooting.platformRight.setPosition(1);
                shooting.platformLeft.setPosition(1);

            } else if (gamepad2.circle){
                shooting.platformRight.setPosition(0);
                shooting.platformLeft.setPosition(0);
            }

            telemetry.addLine("gamepad 2");
            telemetry.addLine("intake: 0.7 right bumper, 0 left bumper");
            telemetry.addLine("outtake: 1 square, 0 cross");
            telemetry.addLine("platform: 1 triangle, 0 circle");
            telemetry.addLine("everything stops: dpad up");
            telemetry.addLine("gamepad 1: right bumper = road runner shoot");
            telemetry.addData("xValue", drive.localizer.getPose().position.x);
            telemetry.addData("yValue", drive.localizer.getPose().position.y);
            telemetry.addData("heading", drive.localizer.getPose().heading);

            telemetry.addData("outtake vel", shooting.outtakeMotor.getCurrentPosition());







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



//drive.updatePoseEstimate or smth
//            drive.localizer.getPose().position.x
//
//            drive.localizer.getPose().position.y
//
//
//            Math.toDegrees(drive.localizer.getPose().heading.toDouble())

// TELEMETRY




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




























