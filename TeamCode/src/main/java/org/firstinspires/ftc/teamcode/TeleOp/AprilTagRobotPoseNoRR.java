package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.arcrobotics.ftclib.command.*;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutoOp.SigmaTimeBasedPick;
import org.firstinspires.ftc.teamcode.Utils.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.teamcode.Utils.Pattern;

import java.util.ArrayList;
import java.util.List;



@Disabled
@TeleOp
public class AprilTagRobotPoseNoRR extends LinearOpMode {


    private DriveTrain driveTrain;
    private Shooting shooting;
    private ElapsedTime runtime = new ElapsedTime();

    private AprilTagCV aprilTagCV;


    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;

    private AprilTagData tag20;
    private AprilTagData tag24;


    double xValue;
    double yValue;







    @Override
    public void runOpMode() throws InterruptedException {
        driveTrain = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        aprilTagCV = new AprilTagCV(hardwareMap);

        tag20 = new AprilTagData(20);
        tag24 = new AprilTagData(24);






        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {




            CommandScheduler.getInstance().run();


            //periodically happening

            //            shooting.updatePID();

//            aprilTagCV.currentDetections.clear();
//
//            List<AprilTagDetection> detections = aprilTagCV.aprilTag.getDetections();
//
//            aprilTagCV.currentDetections.addAll(detections);



            tag20.visible = false;
            tag24.visible = false;

            for (AprilTagDetection detection : aprilTagCV.currentDetections) {

                if (detection.metadata != null) {
                    telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                    telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                    telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                    telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));


                    if (detection.id == 20){
                        tag20.robotPose = detection.robotPose;
                        tag20.robotX = detection.robotPose.getPosition().x;
                        tag20.robotY = detection.robotPose.getPosition().y;
                        tag20.robotHeading = Math.toDegrees(detection.robotPose.getOrientation().getYaw());
                        tag20.pose2d = new Pose2d(tag20.robotX, tag20.robotY, detection.robotPose.getOrientation().getYaw());


                        tag20.visible = true;
                        tag20.yaw = detection.ftcPose.yaw;

                    }else if (detection.id == 24) {
                        tag24.robotPose = detection.robotPose;
                        tag24.robotX = detection.robotPose.getPosition().x;
                        tag24.robotY = detection.robotPose.getPosition().y;
                        tag24.robotHeading = detection.robotPose.getOrientation().getYaw();
                        tag24.pose2d = new Pose2d(tag24.robotX, tag24.robotY, detection.robotPose.getOrientation().getYaw());



                        tag24.visible = true;
                        tag24.yaw = detection.ftcPose.yaw;

                    }



                } else {
                    telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                    telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));

                }


            }









            if (SigmaTimeBasedPick.alliance == Pattern.alliance.RED){

                telemetry.addLine("alliance: RED");
                if (tag24.visible){
//HAVE IT SHOOT THE BALL AFTER MOVEMENT FOR BOTH BUMPERS

                    if (gamepad1.left_bumper) {
                        forward(-tag24.robotX);
                        moveRight(-tag24.robotY);

                        turn(tag24.yaw);
                    }



                    if (gamepad1.right_bumper) {




                        xValue = Math.sqrt(1- Math.pow((tag24.robotY/tag24.robotX),2));
                        yValue = Math.sqrt(Math.pow((tag24.robotY/tag24.robotX),2));


                        double denominator = Math.max(Math.abs(yValue) + Math.abs(xValue), 1);
                        double frontLeftMotorPower = (yValue + xValue) / denominator;
                        double backLeftMotorPower = (yValue - xValue) / denominator;
                        double frontRightMotorPower = (yValue - xValue) / denominator;
                        double backRightMotorPower = (yValue + xValue) / denominator;

                        driveTrain.frontLeftMotor.setPower(frontLeftMotorPower);
                        driveTrain.backLeftMotor.setPower(backLeftMotorPower);
                        driveTrain.frontRightMotor.setPower(frontRightMotorPower);
                        driveTrain.backRightMotor.setPower(backRightMotorPower);
                        double sleeptime = (Math.sqrt( Math.pow((tag24.robotX),2) + Math.pow((tag24.robotX),2) )) * 1000;
                        //divide by inches per seconds a motors with power 1 can travel
                        sleep((int) sleeptime);

                        turn(tag24.yaw);



                    }

                }else {

                    if (gamepad1.right_bumper || gamepad1.left_bumper) {
                        driveTrain.frontLeftMotor.setPower(0.5);
                        driveTrain.backLeftMotor.setPower(0.5);
                        driveTrain.frontRightMotor.setPower(-0.5);
                        driveTrain.backRightMotor.setPower(-0.5);
                    }
                }
            }











            else if (SigmaTimeBasedPick.alliance == Pattern.alliance.BLUE){
                telemetry.addLine("alliance: BLUE");

//HAVE IT SHOOT THE BALL AFTER MOVEMENT FOR BOTH BUMPERS
                if (tag20.visible){



                    if (gamepad1.left_bumper) {
                        forward(-tag20.robotX);
                        moveRight(-tag20.robotY);

                        turn(tag20.yaw);
                    }

                    if (gamepad1.right_bumper) {

                        // slope = tag20.robotY/tag20.robotX


                        //distance formula = Math.sqrt( Math.pow((tag20.robotX),2) + Math.pow((tag20.robotX),2) )



                        xValue = Math.sqrt(1- Math.pow((tag20.robotY/tag20.robotX),2));
                        yValue = Math.sqrt(Math.pow((tag20.robotY/tag20.robotX),2));


                        double denominator = Math.max(Math.abs(yValue) + Math.abs(xValue), 1);
                        double frontLeftMotorPower = (yValue + xValue) / denominator;
                        double backLeftMotorPower = (yValue - xValue) / denominator;
                        double frontRightMotorPower = (yValue - xValue) / denominator;
                        double backRightMotorPower = (yValue + xValue) / denominator;

                        driveTrain.frontLeftMotor.setPower(frontLeftMotorPower);
                        driveTrain.backLeftMotor.setPower(backLeftMotorPower);
                        driveTrain.frontRightMotor.setPower(frontRightMotorPower);
                        driveTrain.backRightMotor.setPower(backRightMotorPower);
                        double sleeptime = (Math.sqrt( Math.pow((tag20.robotX),2) + Math.pow((tag20.robotX),2) )) * 1000;
                        //divide by inches per seconds a motors with power 1 can travel
                        sleep((int) sleeptime);

                        turn(tag20.yaw);



                    }






                }
//                else if (tag24.visible){}
                else{

                    if (gamepad1.right_bumper || gamepad1.left_bumper) {
                        driveTrain.frontLeftMotor.setPower(0.5);
                        driveTrain.backLeftMotor.setPower(0.5);
                        driveTrain.frontRightMotor.setPower(-0.5);
                        driveTrain.backRightMotor.setPower(-0.5);
                    }

                }


            }

//delete this later make 2 teleops for alliance
            else{
                telemetry.addLine("alliance: UNDEFINED");
                telemetry.addLine("select alliance");
                telemetry.addLine("Gamepad 2 dpad up: red, down: blue");
                if (gamepad2.dpad_up){
                    SigmaTimeBasedPick.alliance = Pattern.alliance.RED;
                }


                if (gamepad2.dpad_down){
                    SigmaTimeBasedPick.alliance = Pattern.alliance.BLUE;
                }
            }


//            double denominator = Math.max(Math.abs(y) + Math.abs(x), 1);
//            double frontLeftMotorPower = (y + x) / denominator;
//            double backLeftMotorPower = (y - x) / denominator;
//            double frontRightMotorPower = (y - x) / denominator;
//            double backRightMotorPower = (y + x) / denominator;



//maybe make a button to switch to field centric

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

            driveTrain.frontLeftMotor.setPower(frontLeftMotorPower);
            driveTrain.backLeftMotor.setPower(backLeftMotorPower);
            driveTrain.frontRightMotor.setPower(frontRightMotorPower);
            driveTrain.backRightMotor.setPower(backRightMotorPower);





            if (gamepad2.right_trigger > 0.7) {
                shooting.outtakeMotor.setPower(0.7);
            }
            else if (gamepad2.left_trigger > 0.7) {
                shooting.outtakeMotor.setPower(0.4);
            }
            else {
                shooting.outtakeMotor.setPower(0);
            }


            if (gamepad2.right_bumper) {
                shooting.platformRight.setPosition(1);
                shooting.platformLeft.setPosition(1);

            } else {
                shooting.platformRight.setPosition(0);
                shooting.platformLeft.setPosition(0);
            }

//make command for shooting balls




// TELEMETRY




            telemetry.addLine("Gamepad 1 apriltag LB: sideways then up,  RB: straight line");
            telemetry.addLine("Intake: Left Bumper");
            telemetry.addLine("Platform: Right Bumper");
            telemetry.addLine("Outtake Power: 0.7 right trigger, 0.4 left trigger");
            telemetry.addData("Platform R", shooting.platformRight.getPosition());
            telemetry.addData("Platform L", shooting.platformLeft.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());
            telemetry.addData("front left", driveTrain.frontLeftMotor.getPower());
            telemetry.addData("back left", driveTrain.backLeftMotor.getPower());
            telemetry.addData("front right", driveTrain.frontRightMotor.getPower());
            telemetry.addData("back right", driveTrain.backRightMotor.getPower());


            telemetry.update();




        }
    }




    public void calculate(double y, double x){
        double denominator = Math.max(Math.abs(y) + Math.abs(x), 1);
        double frontLeftMotorPower = (y + x) / denominator;
        double backLeftMotorPower = (y - x) / denominator;
        double frontRightMotorPower = (y - x) / denominator;
        double backRightMotorPower = (y + x) / denominator;
    }



    public void moveRight(double inches){
        runtime.reset();

        driveTrain.encoderDrive(0.5, inches, -inches, -inches, inches);
        while (driveTrain.isBusy(inches) || runtime.seconds() > inches/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("moving right", inches);
            telemetry.addData("final position", inches/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }




    public void forward(double inches){
        runtime.reset();
        driveTrain.forward(0.5,inches);
        while (driveTrain.isBusy(inches) || runtime.seconds() > inches/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("moving forward", inches);
            telemetry.addData("final position", inches/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }

    }

    public void backward(double inches){
        runtime.reset();
        driveTrain.backward(0.5, inches);
        while (driveTrain.isBusy(inches) || runtime.seconds() > inches/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("moving backward", inches);
            telemetry.addData("final position", inches/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }

    public void turn(double degrees){
        runtime.reset();
        driveTrain.turn(0.5, degrees);
        while (driveTrain.isBusy(degrees) || runtime.seconds() > degrees/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("turning clockwise", degrees);
            telemetry.addData("final position", degrees/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }

    public void turncc(double degrees){
        runtime.reset();
        driveTrain.turncc(0.5, degrees);
        while (driveTrain.isBusy(degrees) || runtime.seconds() > degrees/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("turning counterclockwise", degrees);
            telemetry.addData("final position", degrees/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }



    public void getMotorPosition(){
        telemetry.addData("fl", driveTrain.frontLeftMotor.getCurrentPosition());
        telemetry.addData("fr",driveTrain.frontRightMotor.getCurrentPosition());
        telemetry.addData("bl",driveTrain.backLeftMotor.getCurrentPosition());
        telemetry.addData("br",driveTrain.backRightMotor.getCurrentPosition());
    }




}




