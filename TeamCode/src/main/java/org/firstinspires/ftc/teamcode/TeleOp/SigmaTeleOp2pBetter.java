package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;


@TeleOp
public class SigmaTeleOp2pBetter extends LinearOpMode {

    private DriveTrain drive;
    private Shooting shooting;
    private boolean platformOn = false;
    private boolean outtakeOn = false;
    private double lastOuttakePower = 0;
    private boolean intakeOn = false;


    private boolean rumble = false;
    private static double MAX_TICKS = 2500;

    private ElapsedTime rumbleTime = new ElapsedTime();


    private boolean rightTriggerPressed;
    private boolean leftTriggerPressed;


    @Override
    public void runOpMode() throws InterruptedException {

        drive = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);



        Gamepad currentGamepad2 = new Gamepad();


        Gamepad previousGamepad2 = new Gamepad();


        waitForStart();

        if (isStopRequested()) return;

        rumbleTime.reset();
        while (opModeIsActive() && !isStopRequested()) {


            previousGamepad2.copy(currentGamepad2);

            currentGamepad2.copy(gamepad2);








//gotta change this back to rx = right stick and x= left stick

            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double rx = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double x = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftMotorPower = (y + x + rx) / denominator;
            double backLeftMotorPower = (y - x + rx) / denominator;
            double frontRightMotorPower = (y - x - rx) / denominator;
            double backRightMotorPower = (y + x - rx) / denominator;

            drive.frontLeftMotor.setPower(frontLeftMotorPower);
            drive.backLeftMotor.setPower(backLeftMotorPower);
            drive.frontRightMotor.setPower(frontRightMotorPower);
            drive.backRightMotor.setPower(backRightMotorPower);


//            if (gamepad2.triangle && !lastTriangle) {
//                toggleOuttake(0.9);
//            }
//            else if (gamepad2.square && !lastSquare) {
//                toggleOuttake(0.7);







            leftTriggerPressed = currentGamepad2.left_trigger > 0.7 && !(previousGamepad2.left_trigger > 0.7);

            rightTriggerPressed = currentGamepad2.right_trigger > 0.7 && !(previousGamepad2.right_trigger > 0.7);

            if (leftTriggerPressed){
                toggleOuttake(0.8);
           }else if (rightTriggerPressed){
                toggleOuttake(1);
            }

            if (intakeOn) {
                shooting.intakeMotor.setPower(0.7);
            } else {
                shooting.intakeMotor.setPower(0);
            }

// Outtake motor
            if (outtakeOn) {
                shooting.outtakeMotor.setPower(0.8);
            } else {
                shooting.outtakeMotor.setPower(0);
            }


            if (currentGamepad2.cross && !previousGamepad2.cross){
                platformOn = !platformOn;
            }


            if (currentGamepad2.circle && !previousGamepad2.circle){
                intakeOn = !intakeOn;
            }






            if (( Math.abs(shooting.outtakeMotor.getVelocity() - shooting.outtakeMotor.getPower() * MAX_TICKS) < Shooting.outtakeTolerance ) && rumbleTime.seconds()>1 && shooting.outtakeMotor.getPower()>0.3){
                rumble = true;
                rumbleTime.reset();
            }

            if (rumble){
                rumble = false;
                gamepad2.rumble(500);
                gamepad2.setLedColor(29, 67, 107, 1000);
            }







            if (platformOn) {
                shooting.platformRight.setPosition(1);
                shooting.platformLeft.setPosition(1);
            }else{
                shooting.platformRight.setPosition(0);
                shooting.platformLeft.setPosition(0);
            }

// TELEMETRY

            telemetry.addData("backleft", drive.backLeftMotor.getPortNumber());
            telemetry.addData("backright", drive.backRightMotor.getPortNumber());
            telemetry.addData("frontleft", drive.frontLeftMotor.getPortNumber());
            telemetry.addData("frontright", drive.frontRightMotor.getPortNumber());

            telemetry.addData("outtake", shooting.outtakeMotor.getPortNumber());
            telemetry.addData("intake", shooting.intakeMotor.getPortNumber());
            telemetry.addData("rightservo", shooting.platformRight.getPortNumber());
            telemetry.addData("leftservo", shooting.platformLeft.getPortNumber());



            telemetry.addLine("Intake: O");
            telemetry.addLine("Platform: X");
            telemetry.addLine("Outtake Power: 1 for right trigger, 0.8 for left trigger");

            if (platformOn){
                telemetry.addLine("platform ON");
            }else{
                telemetry.addLine("platform OFF");
            }

            if (intakeOn){
                telemetry.addLine("intake ON");
            }else {
                telemetry.addLine("intake OFF");
            }

            if (outtakeOn){
                telemetry.addLine("outtake ON");
            }else {
                telemetry.addLine("outtake OFF");
            }



            telemetry.addData("Outtake power", shooting.outtakeMotor.getPower());
            telemetry.addData("Outtake velocity", shooting.outtakeMotor.getVelocity());
            telemetry.addData("front left", drive.frontLeftMotor.getPower());
            telemetry.addData("back left", drive.backLeftMotor.getPower());
            telemetry.addData("front right", drive.frontRightMotor.getPower());
            telemetry.addData("back right", drive.backRightMotor.getPower());


            telemetry.update();




        }
    }




    private void toggleOuttake(double power) {

        if (power != lastOuttakePower) {
            outtakeOn = true;
            shooting.outtakeMotor.setPower(power);
            lastOuttakePower = power;
        }
        else {
            outtakeOn = !outtakeOn;
            shooting.outtakeMotor.setPower(outtakeOn ? power : 0);
        }

    }

}




























