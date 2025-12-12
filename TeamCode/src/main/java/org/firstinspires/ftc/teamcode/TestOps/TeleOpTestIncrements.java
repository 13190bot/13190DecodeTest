package org.firstinspires.ftc.teamcode.TestOps;
















import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;


/*
INCREMENTS
 */













@TeleOp
public class TeleOpTestIncrements extends LinearOpMode {
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor intakeMotor;


    //made timer cuz sleep kills entire program for the time
    private ElapsedTime runtime = new ElapsedTime();









    DcMotor outtakeMotor;
    Servo platformRight;
    Servo platformLeft;































//for the intake and outtake toggle


    boolean lastLB = false;
    boolean intakeOn = false;




    boolean outtakeOn = false;
    boolean lastTriangle = false;


    boolean lastSquare = false;


    boolean lastCross = false;


    boolean lastCircle = false;










//for the platform toggle


    boolean lastRB;
    boolean platformOn = false;


    int outtakereset =0;

    double lastOuttakePower = 0;

    int inttakereset =0;


    int platformreset =0;


    int allreset =0;

    int shootnumber =0;






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


        platformRight.setDirection(Servo.Direction.REVERSE);
        platformRight.setPosition(0);
        platformLeft.setPosition(0);








        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);


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




            // player 2 toggle
            // left bumper = intake
            // right bumper = platform servo
            // triangle, square, cross, circle for 1, 0.8, 0.65, 0.5 speed respectively = OUTTAKE


            if (gamepad2.left_bumper && !lastLB) {
                intakeOn = !intakeOn;
                intakeMotor.setPower(intakeOn ? 0.7 : 0);
                telemetry.addData("intake", intakeMotor.getPower());


            }
            lastLB = gamepad2.left_bumper;




            if (gamepad2.right_trigger > 0.5) {

                stopDrive();
                outtakeOn = true;
                platformOn = true;
                outtakeMotor.setPower(0.75);
                shootnumber++;
                while (outtakeMotor.isBusy()){
                    telemetry.addData("shooting", shootnumber);
                    telemetry.addData("Outtake", outtakeMotor.getPower());

                }
                platformLeft.setPosition(0.3);
                platformRight.setPosition(0.3);
//                telemetry.addData("Platform", platform.getPosition());

            } else if (gamepad2.left_trigger > 0.5) {

                stopDrive();
                outtakeOn = true;
                platformOn = true;
                outtakeMotor.setPower(0.5);
                shootnumber++;
                while (outtakeMotor.isBusy()) {
                    telemetry.addData("shooting", shootnumber);
                    telemetry.addData("Outtake", outtakeMotor.getPower());

                }
                platformLeft.setPosition(0.3);
                platformRight.setPosition(0.3);
//                telemetry.addData("Platform", platform.getPosition());

            } else if (gamepad2.right_bumper && !lastRB) {
                platformOn = !platformOn;
                platformLeft.setPosition(platformOn ? 0.3 : 0);
                platformRight.setPosition(platformOn ? 0.3 : 0);
//                telemetry.addData("Platform", platform.getPosition());
            }


            lastRB = gamepad2.right_bumper;















            if (gamepad2.triangle && !lastTriangle) {
                outtakeMotor.setPower(outtakeMotor.getCurrentPosition() + 0.1);                
                
            }
            else if (gamepad2.square && !lastSquare) {

                outtakeMotor.setPower(outtakeMotor.getCurrentPosition() + 0.3);                
                
            }
            else if (gamepad2.cross && !lastCross) {

            outtakeMotor.setPower(outtakeMotor.getCurrentPosition() - 0.1);                
                
                
            }
            else if (gamepad2.circle && !lastCircle) {

            outtakeMotor.setPower(outtakeMotor.getCurrentPosition() - 0.3);                
                
                
                
            }



// used for toggle


            lastTriangle = gamepad2.triangle;
            lastSquare = gamepad2.square;
            lastCross = gamepad2.cross;
            lastCircle = gamepad2.circle;








            // if anything breaks just press this to reset intake outtake platform motors


            if (gamepad2.dpad_left){
                intakeMotor.setPower(0);
                intakeOn = false;
                inttakereset++;
                telemetry.addData("intake reset", inttakereset);


            }


            if (gamepad2.dpad_up){
                outtakeMotor.setPower(0);
                outtakeOn = false;
                outtakereset++;
                telemetry.addData("outtake reset", outtakereset);
            }


            if (gamepad2.dpad_right){
                platformLeft.setPosition(0);
                platformRight.setPosition(0);

                platformOn = false;
                platformreset++;
                telemetry.addData("platform reset", platformreset);
            }


            if (gamepad2.dpad_down){
                intakeMotor.setPower(0);
                outtakeMotor.setPower(0);
                platformLeft.setPosition(0);
                platformRight.setPosition(0);


                intakeOn = false;
                outtakeOn = false;
                platformOn = false;


                allreset++;
                telemetry.addData("all reset", allreset);
            }










            telemetry.update();


























        }
    }





    private void toggleOuttake(double power) {

        // If the power changed, always turn the motor on with the new power
        if (power != lastOuttakePower) {
            outtakeOn = true;
            outtakeMotor.setPower(power);
            lastOuttakePower = power;
        }
        // Otherwise, toggle on/off
        else {
            outtakeOn = !outtakeOn;
            outtakeMotor.setPower(outtakeOn ? power : 0);
        }

        telemetry.addData("outtake", outtakeMotor.getPower());
    }




//    private void toggleouttake(double Power) {
//
//
//
//
//            if (outtakeOn) {
//
//
//                if (outtakeMotor.getPower() == Power) {
//                    outtakeOn = false;
//                    outtakeMotor.setPower(0);
//
//
//                } else {
//                    outtakeMotor.setPower(Power);
//
//
//                }
//
//
//            } else {
//                outtakeOn = true;
//                outtakeMotor.setPower(Power);
//            }
//
//
//            telemetry.addData("outtake", outtakeMotor.getPower());
//
//
//        }


    private void stopDrive() {
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);


    }




}
