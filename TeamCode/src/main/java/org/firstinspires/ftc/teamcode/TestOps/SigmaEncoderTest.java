package org.firstinspires.ftc.teamcode.TestOps;
















import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
















@TeleOp
public class SigmaEncoderTest extends LinearOpMode {
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor intakeMotor;








    DcMotor outtakeMotor;




















    @Override
    public void runOpMode() throws InterruptedException {
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");












        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);






        waitForStart();








        if (isStopRequested()) return;


        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);




        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        boolean lastLB = false;

        int TrianglePressed = 0;
        int LBPressed = 0;
        int RBPressed = 0;

        while (opModeIsActive() && !isStopRequested()) {






            telemetry.addData("frontRight", frontRightMotor.getPower());


            telemetry.addData("backRight", backRightMotor.getPower());


            telemetry.addData("backLeft", backLeftMotor.getPower());


            telemetry.addData("frontLeft", frontLeftMotor.getPower());






            if (gamepad1.triangle) {
                frontLeftMotor.setPower(0.5);
                backLeftMotor.setPower(0.5);
                frontRightMotor.setPower(0.5);
                backRightMotor.setPower(0.5);
                TrianglePressed++;




                telemetry.addData("Triangle pressed", TrianglePressed);


            }else {
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
                RBPressed++;




                telemetry.addData("RB pressed", RBPressed);


            }else {
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
                LBPressed++;




                telemetry.addData("LB pressed", LBPressed);


            }else {
                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backRightMotor.setPower(0);
            }



            if (gamepad1.left_bumper && !lastLB) {


                frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);




                frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


                telemetry.addData("button pressed", "6");




            }
            lastLB = gamepad1.left_bumper;


            telemetry.update();


        }
    }
}



