package org.firstinspires.ftc.teamcode.TeleOp;
















import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.arcrobotics.ftclib.gamepad.*;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;













@TeleOp
public class SigmaTeleOp2pToggle extends LinearOpMode {

    private DriveTrain drive;
    private Shooting shooting;
    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;

    //made timer cuz sleep kills entire program for the time
    private ElapsedTime runtime = new ElapsedTime();





































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

        drive = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);




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


            drive.frontLeftMotor.setPower(frontLeftMotorPower);
            drive.backLeftMotor.setPower(backLeftMotorPower);
            drive.frontRightMotor.setPower(frontRightMotorPower);
            drive.backRightMotor.setPower(backRightMotorPower);




            // player 2 toggle
            // left bumper = intake
            // right bumper = platform servo
            // triangle, square, cross, circle for 1, 0.8, 0.65, 0.5 speed respectively = OUTTAKE


            if (gamepad2.left_bumper && !lastLB) {
                intakeOn = !intakeOn;
                shooting.intakeMotor.setPower(intakeOn ? 0.7 : 0);
                telemetry.addData("intake", shooting.intakeMotor.getPower());


            }
            lastLB = gamepad2.left_bumper;



//
//            if (gamepad2.right_trigger > 0.5) {
//
//                stopDrive();
//                outtakeOn = true;
//                platformOn = true;
//                shooting.outtakeMotor.setPower(0.75);
//                shootnumber++;
//                while (shooting.outtakeMotor.isBusy()){
//                    telemetry.addData("shooting", shootnumber);
//                    telemetry.addData("Outtake", outtakeMotor.getPower());
//
//                }
//                platformLeft.setPosition(1);
//                platformRight.setPosition(1);
////                telemetry.addData("Platform", platform.getPosition());
//
//            } else if (gamepad2.left_trigger > 0.5) {
//
//                stopDrive();
//                outtakeOn = true;
//                platformOn = true;
//                outtakeMotor.setPower(0.5);
//                shootnumber++;
//                while (outtakeMotor.isBusy()) {
//                    telemetry.addData("shooting", shootnumber);
//                    telemetry.addData("Outtake", outtakeMotor.getPower());
//
//                }
//                platformLeft.setPosition(1);
//                platformRight.setPosition(1);
////                telemetry.addData("Platform", platform.getPosition());
//
//            } else
                if (gamepad2.right_bumper && !lastRB) {
                platformOn = !platformOn;
                shooting.platformLeft.setPosition(platformOn ? 1 : 0);
                shooting.platformRight.setPosition(platformOn ? 1 : 0);
            }


            lastRB = gamepad2.right_bumper;















//toggle for triangle square cross circle (4 if statements) : press a button consecutively then it stop motors
//checks for whether we pressed the button, whether outtake is currently moving, and adds telemetry




            //TEST WHAT HAPPENS WHEN 2 BUTTONS ARE PRESSED AT THE SAME TIME!!!!

            if (gamepad2.triangle && !lastTriangle) {
                toggleOuttake(0.9);
            }
            else if (gamepad2.square && !lastSquare) {
                toggleOuttake(0.7);
            }
            else if (gamepad2.cross && !lastCross) {
                toggleOuttake(0.5);
            }
            else if (gamepad2.circle && !lastCircle) {
                toggleOuttake(0.3);
            }





            //made a function of this


//            if (gamepad2.triangle && !lastTriangle) {
//
//                  if (outtakeOn) {
//
//                      if (outtakeMotor.getPower() == 1){
//                          outtakeOn = false;
//                          outtakeMotor.setPower(0);
//
//                      } else{
//                          outtakeMotor.setPower(1);
//
//                      }
//
//                } else{
//                    outtakeOn = true;
//                    outtakeMotor.setPower(1);
//                }
//
//                  telemetry.addData("outtake", outtakeMotor.getPower());
//
//            }






            // USE ONLY IF THE STATEMENT ABOVE DOESN'T WORK - TESTED CODE


//            if (gamepad2.triangle && !lastTriangle) {
//                outtakeOn = !outtakeOn;
//                outtakeMotor.setPower(outtakeOn ? 1.0 : 0);
//                telemetry.addData("outtake", outtakeMotor.getPower());
//            }
//            lastTriangle = gamepad2.triangle;




// used for toggle


            lastTriangle = gamepad2.triangle;
            lastSquare = gamepad2.square;
            lastCross = gamepad2.cross;
            lastCircle = gamepad2.circle;








            // if anything breaks just press this to reset intake outtake platform motors


            if (gamepad2.dpad_left){
                shooting.intakeMotor.setPower(0);
                intakeOn = false;
                inttakereset++;
                telemetry.addData("intake reset", inttakereset);


            }


            if (gamepad2.dpad_up){
                shooting.outtakeMotor.setPower(0);
                outtakeOn = false;
                outtakereset++;
                telemetry.addData("outtake reset", outtakereset);
            }


            if (gamepad2.dpad_right){
                shooting.platformLeft.setPosition(0);
                shooting.platformRight.setPosition(0);

                platformOn = false;
                platformreset++;
                telemetry.addData("platform reset", platformreset);
            }


            if (gamepad2.dpad_down){
                shooting.intakeMotor.setPower(0);
                shooting.outtakeMotor.setPower(0);
                shooting.platformLeft.setPosition(0);
                shooting.platformRight.setPosition(0);


                intakeOn = false;
                outtakeOn = false;
                platformOn = false;


                allreset++;
                telemetry.addData("all reset", allreset);
            }










            telemetry.addLine("Intake: Left Bumper");
            telemetry.addLine("Platform: Right Bumper");
            telemetry.addLine("Outtake Power: 0.9 triangle, 0.7 square, 0.5 cross, 0.3 circle");
            telemetry.addLine("dpad reset: left intake, up outtake, right platform, down everything");

            telemetry.addData("Platform", shooting.platformRight.getPosition());
            telemetry.addData("Platform", shooting.platformLeft.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());
            telemetry.addData("front left", drive.frontLeftMotor.getPower());
            telemetry.addData("back left", drive.backLeftMotor.getPower());
            telemetry.addData("front right", drive.frontRightMotor.getPower());
            telemetry.addData("back left", drive.frontRightMotor.getPower());


            telemetry.update();


























        }
    }





    private void toggleOuttake(double power) {

        // If the power changed, always turn the motor on with the new power
        if (power != lastOuttakePower) {
            outtakeOn = true;
            shooting.outtakeMotor.setPower(power);
            lastOuttakePower = power;
        }
        // Otherwise, toggle on/off
        else {
            outtakeOn = !outtakeOn;
            shooting.outtakeMotor.setPower(outtakeOn ? power : 0);
        }

        telemetry.addData("outtake", shooting.outtakeMotor.getPower());
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






}































