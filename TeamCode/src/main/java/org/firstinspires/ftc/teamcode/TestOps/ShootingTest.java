package org.firstinspires.ftc.teamcode.TestOps;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;




@TeleOp
public class ShootingTest extends LinearOpMode {




    private Shooting shooting;
    public static double platformPower = 1;




    @Override
    public void runOpMode() throws InterruptedException {


        shooting = new Shooting(hardwareMap);










        waitForStart();


        if (isStopRequested()) return;


        while (opModeIsActive() && !isStopRequested()) {






            if (gamepad1.left_bumper){
                shooting.intakeMotor.setPower(0.7);
            }


            if (gamepad1.right_bumper){
                shooting.platformRight.setPosition(platformPower);
                shooting.platformRight.setPosition(platformPower);
            }


            if (gamepad1.dpad_up) {
                shooting.outtakeMotor.setPower(shooting.outtakeMotor.getPower()+0.1);
            }


            if (gamepad1.dpad_down) {
                shooting.outtakeMotor.setPower(shooting.outtakeMotor.getPower()-0.1);
            }


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

























































