package org.firstinspires.ftc.teamcode.TestOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;


@TeleOp
public class DriveTest extends LinearOpMode {

    private DriveTrain drive;

    GamepadEx gamepadEx1;


    boolean forwards = false;
    boolean backwards = false;

    boolean fr = false;
    boolean br = false;
    boolean fl = false;
    boolean bl = false;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new DriveTrain(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);





        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {




            if (gamepad1.dpad_up){
                forwards = true;
                backwards = false;
            }

            if (gamepad1.dpad_down){
                forwards = false;
                backwards = true;
            }


            if (gamepad1.dpad_right){
                if (forwards){
                    if (!fr) {
                        drive.frontRightMotor.setPower(1);
                        fr = true;
                    }else{
                        drive.frontRightMotor.setPower(0);
                        fr = false;
                    }

                }else if (backwards) {
                    if (!br) {
                        drive.backRightMotor.setPower(1);
                        br = true;
                    } else {
                        drive.backRightMotor.setPower(0);
                        br = false;
                    }
                }
                forwards = false;
                backwards = false;
            }

            if (gamepad1.dpad_left){
                if (forwards){

                    if (!fl) {
                        drive.frontLeftMotor.setPower(1);
                        fl = true;
                    } else {
                        drive.frontLeftMotor.setPower(0);
                        fl = false;
                    }


                }else if (backwards){

                    if (!bl) {
                        drive.backLeftMotor.setPower(1);
                        bl = true;
                    } else {
                        drive.backLeftMotor.setPower(0);
                        bl = false;
                    }
                }
                forwards = false;
                backwards = false;
            }


// TELEMETRY


            telemetry.addData("forwards", forwards);
            telemetry.addData("backwards", backwards);

            telemetry.addData("front left", drive.frontLeftMotor.getPower());
            telemetry.addData("back left", drive.backLeftMotor.getPower());
            telemetry.addData("front right", drive.frontRightMotor.getPower());
            telemetry.addData("back right", drive.backRightMotor.getPower());

            telemetry.addData("front left encoders", drive.frontLeftMotor.getCurrentPosition());
            telemetry.addData("back left encoders", drive.backLeftMotor.getCurrentPosition());
            telemetry.addData("front right encoders", drive.frontRightMotor.getCurrentPosition());
            telemetry.addData("back right encoders", drive.backRightMotor.getCurrentPosition());

            telemetry.update();




        }
    }
}




























