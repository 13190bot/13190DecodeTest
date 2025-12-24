package org.firstinspires.ftc.teamcode.TestOps;

<<<<<<< HEAD

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


=======
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

<<<<<<< HEAD

import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;




=======
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;


>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
@TeleOp
public class IntakeOuttakeTest extends LinearOpMode {


<<<<<<< HEAD


=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
    DcMotor intakeMotor;
    DcMotor outtakeMotor;




<<<<<<< HEAD




    @Override
    public void runOpMode() throws InterruptedException {


=======
    @Override
    public void runOpMode() throws InterruptedException {

>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");




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
            if (gamepad1.triangle){
                intakeMotor.setPower(0.7);
            }

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            if (gamepad1.left_bumper) {
                outtakeMotor.setPower(1);
            }

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            if (gamepad1.right_bumper) {
                outtakeMotor.setPower(0);
            }

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            if (gamepad1.dpad_up) {
                outtakeMotor.setPower(outtakeMotor.getPower()+0.1);
            }

<<<<<<< HEAD

=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            if (gamepad1.dpad_down) {
                outtakeMotor.setPower(outtakeMotor.getPower()-0.1);
            }



<<<<<<< HEAD



=======
>>>>>>> a0bbf905b93dd7c8326ba25365218c30e5f026ac
            telemetry.addLine("left bumper: outtake = 1");
            telemetry.addLine("right bumper: outtake = 0");
            telemetry.addLine("dpad up: outtake power + 0.1");
            telemetry.addLine("dpad down: outtake power - 0.1");
            telemetry.addLine("triangle: intake = 0.7");

<<<<<<< HEAD

            telemetry.addData("intake power:", intakeMotor.getPower());
            telemetry.addData("outtake power:", outtakeMotor.getPower());


=======
            telemetry.addData("intake power:", intakeMotor.getPower());
            telemetry.addData("outtake power:", outtakeMotor.getPower());

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
