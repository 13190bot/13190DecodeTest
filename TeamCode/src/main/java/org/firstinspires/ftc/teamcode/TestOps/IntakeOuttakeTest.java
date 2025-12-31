package org.firstinspires.ftc.teamcode.TestOps;



import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;



@TeleOp
@Config
public class IntakeOuttakeTest extends LinearOpMode {


    DcMotor intakeMotor;
    DcMotor outtakeMotor;
    public static double outtakePower = 1;





    @Override
    public void runOpMode() throws InterruptedException {


        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");





        waitForStart();

        if (isStopRequested()) return;


        while (opModeIsActive() && !isStopRequested()) {




            if (gamepad1.triangle){
                intakeMotor.setPower(0.7);
            }


            if (gamepad1.left_bumper) {
                outtakeMotor.setPower(outtakePower);
            }

            if (gamepad1.right_bumper) {
                outtakeMotor.setPower(0);
            }

            if (gamepad1.dpad_up) {
                outtakeMotor.setPower(outtakeMotor.getPower()+0.1);
            }

            if (gamepad1.dpad_down) {
                outtakeMotor.setPower(outtakeMotor.getPower()-0.1);
            }


            telemetry.addLine("left bumper: outtake = 1");
            telemetry.addLine("right bumper: outtake = 0");
            telemetry.addLine("dpad up: outtake power + 0.1");
            telemetry.addLine("dpad down: outtake power - 0.1");
            telemetry.addLine("triangle: intake = 0.7");



            telemetry.addData("intake power:", intakeMotor.getPower());
            telemetry.addData("outtake power:", outtakeMotor.getPower());


            telemetry.update();






        }
    }
}