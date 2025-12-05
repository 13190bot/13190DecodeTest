package org.firstinspires.ftc.teamcode;








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
//    ServoImplEx platform;















    @Override
    public void runOpMode() {
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");

//        platform = hardwareMap.get(ServoImplEx.class, "platform");
//        platform.setPwmRange(new PwmControl.PwmRange(500, 2500));


        // Put initialization blocks here




        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();
        // Put run blocks here




        if (isStopRequested()) return;




        while (opModeIsActive()) {
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



            if (gamepad2.right_bumper) {
                outtakeMotor.setPower(1);
            }
            else {
                outtakeMotor.setPower(0);
            }


            //            if (gamepad2.triangle) {
//                telemetry.addLine("triangle pressed");
//                telemetry.addData("Servo Position", platform.getPosition());
//                telemetry.update();
//                platform.setPosition(1);
//






        }
    }
}















