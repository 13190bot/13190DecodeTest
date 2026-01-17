package org.firstinspires.ftc.teamcode.TestOps;



import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp
@Config
public class outtakeEncoderTest extends LinearOpMode {


    DcMotorEx outtakeMotor;





    @Override
    public void runOpMode() throws InterruptedException {


        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeMotor");





        waitForStart();

        if (isStopRequested()) return;


        while (opModeIsActive() && !isStopRequested()) {




            if (gamepad1.dpad_up){
                outtakeMotor.setPower(outtakeMotor.getPower() + 0.01);
            }

            if (gamepad1.dpad_down){
                outtakeMotor.setPower(outtakeMotor.getPower() - 0.01);
            }

            if (gamepad1.right_bumper){
                outtakeMotor.setPower(1);
            }
            if (gamepad1.left_bumper){
                outtakeMotor.setPower(0);
            }





            telemetry.addData("outtake power:", outtakeMotor.getPower());
            telemetry.addData("outtake Velocity:", outtakeMotor.getVelocity());



            telemetry.update();






        }
    }
}