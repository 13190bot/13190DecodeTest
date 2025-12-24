package org.firstinspires.ftc.teamcode.TestOps;
















import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;














@TeleOp
public class SigmaTimeBasedTest extends LinearOpMode {

    DriveTrain drive;



    @Override
    public void runOpMode() throws InterruptedException {

        drive = new DriveTrain(hardwareMap);







        waitForStart();
        // Put run blocks here








        if (isStopRequested()) return;








        while (opModeIsActive() && !isStopRequested()) {


            if (gamepad1.dpad_up) {
                drive.forwardtime(1);

            }else if (gamepad1.dpad_down) {
                drive.backwardtime(1);

            }else if (gamepad1.dpad_left) {
                drive.turntime(1);

            }else if (gamepad1.dpad_left){
                drive.turncctime(1);
                
            }else{
                drive.stopDrive();
            }

            telemetry.addData("front left", drive.frontLeftMotor.getPower());
            telemetry.addData("back left", drive.backLeftMotor.getPower());
            telemetry.addData("front right", drive.frontRightMotor.getPower());
            telemetry.addData("back left", drive.frontRightMotor.getPower());

            telemetry.addData("front left encoders", drive.frontLeftMotor.getCurrentPosition());
            telemetry.addData("back left encoders", drive.backLeftMotor.getCurrentPosition());
            telemetry.addData("front right encoders", drive.frontRightMotor.getCurrentPosition());
            telemetry.addData("back left encoders", drive.frontRightMotor.getCurrentPosition());

                telemetry.update();














        }
    }
}































