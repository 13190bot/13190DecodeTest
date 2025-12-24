package org.firstinspires.ftc.teamcode.TestOps;

<<<<<<< HEAD

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;




=======
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;


>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
@TeleOp
public class ShootingTest extends LinearOpMode {


<<<<<<< HEAD


=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
    private Shooting shooting;
    public static double platformPower = 1;


<<<<<<< HEAD


    @Override
    public void runOpMode() throws InterruptedException {


=======
    @Override
    public void runOpMode() throws InterruptedException {

>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
        shooting = new Shooting(hardwareMap);





<<<<<<< HEAD





        waitForStart();


        if (isStopRequested()) return;


=======
        waitForStart();

        if (isStopRequested()) return;

>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
        while (opModeIsActive() && !isStopRequested()) {



<<<<<<< HEAD



=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            if (gamepad1.left_bumper){
                shooting.intakeMotor.setPower(0.7);
            }

<<<<<<< HEAD

            if (gamepad1.right_bumper){
                shooting.platformRight.setPosition(platformPower);
                shooting.platformRight.setPosition(platformPower);
            }


=======
            if (gamepad1.right_bumper){
            shooting.platformRight.setPosition(platformPower);
            shooting.platformRight.setPosition(platformPower);
            }

>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            if (gamepad1.dpad_up) {
                shooting.outtakeMotor.setPower(shooting.outtakeMotor.getPower()+0.1);
            }

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            if (gamepad1.dpad_down) {
                shooting.outtakeMotor.setPower(shooting.outtakeMotor.getPower()-0.1);
            }

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            telemetry.addLine("Left Bumper: intake = 0.7");
            telemetry.addLine("Right Bumper: platform power smth");
            telemetry.addLine("dpad up: outtake + 0.1");
            telemetry.addLine("dpad up: outtake - 0.1");

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            telemetry.addData("Platform Right", shooting.platformRight.getPosition());
            telemetry.addData("Platform Left", shooting.platformLeft.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            telemetry.addData("Outtake encoder", shooting.outtakeMotor.getCurrentPosition());
            telemetry.addData("Intake encoder", shooting.intakeMotor.getCurrentPosition());


<<<<<<< HEAD


=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            telemetry.update();




<<<<<<< HEAD




=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
        }
    }
}




























<<<<<<< HEAD





























=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
