package org.firstinspires.ftc.teamcode;








import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;








@TeleOp
public class TimeBasedTest extends LinearOpMode {
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

int x;

int y;
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();
        // Put run blocks here




        if (isStopRequested()) return;




        while (opModeIsActive()) {





            if (gamepad1.triangle) {
                frontLeftMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                frontRightMotor.setPower(0.5);
                backRightMotor.setPower(0.5);

            }
            else {
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backRightMotor.setPower(0);

            }

            if (gamepad1.left_bumper) {
                frontLeftMotor.setPower(-0.5);
                backLeftMotor.setPower(-0.5);
                frontRightMotor.setPower(0.5);
                backRightMotor.setPower(0.5);

            }
            else {
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backRightMotor.setPower(0);

            }

            if (gamepad1.right_bumper) {
                frontLeftMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                frontRightMotor.setPower(-0.5);
                backRightMotor.setPower(-0.5);

            }
            else {
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backRightMotor.setPower(0);

            }



//
//            if (gamepad1.triangle) {
//                frontLeftMotor.setPower(0.5);
//                backLeftMotor.setPower(0.5);
//                frontRightMotor.setPower(0.5);
//                backRightMotor.setPower(0.5);
//                sleep(x);
//            }
//            else {
//                frontLeftMotor.setPower(0);
//                backLeftMotor.setPower(0);
//                frontRightMotor.setPower(0);
//                backRightMotor.setPower(0);
//
//            }
//
//            if (gamepad1.circle) {
//                frontLeftMotor.setPower(0.5);
//                backLeftMotor.setPower(0.5);
//                frontRightMotor.setPower(-0.5);
//                backRightMotor.setPower(-0.5);
//                sleep(y);
//            }
//            else {
//                frontLeftMotor.setPower(0);
//                backLeftMotor.setPower(0);
//                frontRightMotor.setPower(0);
//                backRightMotor.setPower(0);
//
//            }
//
//            if (gamepad1.square) {
//                frontLeftMotor.setPower(-0.5);
//                backLeftMotor.setPower(-0.5);
//                frontRightMotor.setPower(0.5);
//                backRightMotor.setPower(0.5);
//                sleep(y);
//            }
//            else {
//                frontLeftMotor.setPower(0);
//                backLeftMotor.setPower(0);
//                frontRightMotor.setPower(0);
//                backRightMotor.setPower(0);
//
//            }


//                telemetry.addData("Servo Position", platform.getPosition());
//                telemetry.update();







        }
    }
}















