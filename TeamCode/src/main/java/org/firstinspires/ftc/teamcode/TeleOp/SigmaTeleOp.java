package org.firstinspires.ftc.teamcode.TeleOp;




import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;




@TeleOp
public class SigmaTeleOp extends LinearOpMode {
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    //DcMotor intakeMotor;


    //DcMotor outtakeMotor;
    //ServoImplEx platform;





    @Override
    public void runOpMode() throws InterruptedException {
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        //intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        // outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");
        //platform = hardwareMap.get(ServoImplEx.class, "platform");
        //platform.setPwmRange(new PwmControl.PwmRange(500, 2500));
        // Put initialization blocks here




        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();
        // Put run blocks here


        if (isStopRequested()) return;


        while (opModeIsActive() && !isStopRequested()) {
            // Put loop blocks here


            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;




            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftMotorPower = (y + x + rx) / denominator;
            double backLeftMotorPower = (y - x + rx) / denominator;
            double frontRightMotorPower = (y - x - rx) / denominator;
            double backRightMotorPower = (y + x - rx) / denominator;






//            if (gamepad1.left_bumper) {
//                //intakeMotor.setPower(1);
//            }
//            else {
//                //intakeMotor.setPower(0);
//            }
//            outtakeMotor.setPower(1);

//
//            if (gamepad1.x) {
//                telemetry.addLine("X pressed");
//                //telemetry.addData("Servo Position", platform.getPosition());
//                telemetry.update();
//                // platform.setPosition(0.5);
//            } else {
//                telemetry.addLine("X released");
//                //telemetry.addData("Servo Position", platform.getPosition());
//                telemetry.update();
//                //  platform.setPosition(0);
//            }


            frontLeftMotor.setPower(frontLeftMotorPower);
            backLeftMotor.setPower(backLeftMotorPower);
            frontRightMotor.setPower(frontRightMotorPower);
            backRightMotor.setPower(backRightMotorPower);
        }
    }
}







