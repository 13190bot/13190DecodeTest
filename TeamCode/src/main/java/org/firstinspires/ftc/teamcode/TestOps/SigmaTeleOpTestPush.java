package org.firstinspires.ftc.teamcode.TeleOp;
















import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
















@TeleOp
public class SigmaTeleOp2p extends LinearOpMode {
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor intakeMotor;








    DcMotor outtakeMotor;
    Servo platformRight;
    Servo platformLeft;






























    @Override
    public void runOpMode() throws InterruptedException {
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");


        platformRight = hardwareMap.get(Servo.class, "platformRight");
        platformLeft = hardwareMap.get(Servo.class, "platformLeft");
//        platform.hardwareMap(new PwmControl.PwmRange(500, 2500));




        // Put initialization blocks here








        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        platformRight.setDirection(Servo.Direction.REVERSE);


        waitForStart();
        // Put run blocks here







        if (isStopRequested()) return;








        while (opModeIsActive() && !isStopRequested()) {
            // Put loop blocks here








            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;














            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftMotorPower = (y + x + rx) / denominator;
            double backLeftMotorPower = (y - x + rx) / denominator;
            double frontRightMotorPower = (y - x - rx) / denominator;
            double backRightMotorPower = (y + x - rx) / denominator;




            frontLeftMotor.setPower(frontLeftMotorPower);
            backLeftMotor.setPower(backLeftMotorPower);
            frontRightMotor.setPower(frontRightMotorPower);
            backRightMotor.setPower(backRightMotorPower);











            if (gamepad2.left_bumper) {
                intakeMotor.setPower(0.7);
            }
            else {
                intakeMotor.setPower(0);
            }






            if (gamepad2.right_trigger > 0.3) {
                outtakeMotor.setPower(0.8);
            }
            else {
                outtakeMotor.setPower(0);
            }

            
            if (gamepad2.left_trigger > 0.3) {
                outtakeMotor.setPower(0.5);
            }
            else {
                outtakeMotor.setPower(0);
            }



            if (gamepad2.right_bumper) {
                telemetry.addLine("platform pressed");
                telemetry.addData("Servo Position2 Right", platformRight.getPosition());
                telemetry.addData("Servo Position2 Left", platformLeft.getPosition());
                platformRight.setPosition(0.3);
                platformLeft.setPosition(0.3);

            }else {
                telemetry.addLine("platform unpressed");
                telemetry.addData("Servo Position2 Right", platformRight.getPosition());
                telemetry.addData("Servo Position2 Left", platformLeft.getPosition());
                platformRight.setPosition(0);
                platformLeft.setPosition(0);

    
            }





            if (gamepad2.triangle) {
            outtakeMotor.setPower(0.8);
            }


            if (gamepad2.square) {
            outtakeMotor.setPower(0.6);
            }

            if (gamepad2.cross) {
            outtakeMotor.setPower(0.4);
            }

            if (gamepad2.circle) {
            outtakeMotor.setPower(0.2);
            }






            if (gamepad2.dpad_left) {
            platformLeft.setPosition(0.35);
            platformRight.setPosition(0.35);
            }

            if (gamepad2.dpad_up) {
            platformLeft.setPosition(0.3);
            platformRight.setPosition(0.3);
            }

            if (gamepad2.dpad_right) {
            platformLeft.setPosition(0.25);
            platformRight.setPosition(0.25);
            }

            if (gamepad2.dpad_down) {
            platformLeft.setPosition(0);
            platformRight.setPosition(0);
            }



            

            
            telemetry.addData("Servo Position2 Right", platformRight.getPosition());
            telemetry.addData("Servo Position2 Left", platformLeft.getPosition());
            telemetry.addData("Outtake", outtakeMotor.getPower());
            telemetry.addData("Intake", intakeMotor.getPower());



                telemetry.update();













        }
    }
}
