package org.firstinspires.ftc.teamcode.TestOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;



@TeleOp
@Config
public class ShootingTest extends LinearOpMode {


    private Shooting shooting;
    public static double platformPower = 1;

    boolean lastUp = false;
    boolean lastDown = false;

    boolean lastRB = false;
    boolean lastLB = false;


    FtcDashboard dashboard = FtcDashboard.getInstance();



    @Override
    public void runOpMode() throws InterruptedException {

        shooting = new Shooting(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {


            if (gamepad1.left_bumper && !lastLB){
                shooting.intakeMotor.setPower(0.7);
            }else {
                shooting.intakeMotor.setPower(0);
            }


            lastLB = gamepad1.left_bumper;


            if (gamepad1.right_bumper && !lastRB){

                if (shooting.platformRight.getPosition() == platformPower || shooting.platformLeft.getPosition() == platformPower){
                    shooting.platformRight.setPosition(0);
                    shooting.platformRight.setPosition(0);
                }else {
                    shooting.platformRight.setPosition(platformPower);
                    shooting.platformRight.setPosition(platformPower);
                }


            }

            lastRB = gamepad1.right_bumper;



            if (gamepad1.dpad_up && !lastUp) {
                shooting.outtakeMotor.setPower(shooting.outtakeMotor.getPower()+0.1);
            }

            if (gamepad1.dpad_down && !lastDown) {
                shooting.outtakeMotor.setPower(shooting.outtakeMotor.getPower()-0.1);
            }

            lastUp = gamepad1.dpad_up;

            lastDown = gamepad1.dpad_down;

            telemetry.addLine("Left Bumper: intake = 0.7");
            telemetry.addLine("Right Bumper: platform power smth");
            telemetry.addLine("dpad up: outtake + 0.1");
            telemetry.addLine("dpad up: outtake - 0.1");

            telemetry.addData("Platform Right", shooting.platformRight.getPosition());
            telemetry.addData("Platform Left", shooting.platformLeft.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());

            telemetry.addData("Outtake encoder", shooting.outtakeMotor.getCurrentPosition());
            telemetry.addData("Intake encoder", shooting.intakeMotor.getCurrentPosition());

            telemetry.update();


        }
    }
}