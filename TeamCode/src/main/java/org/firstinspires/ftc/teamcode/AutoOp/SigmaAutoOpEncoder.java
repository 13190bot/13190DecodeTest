// may work more consistent due to battery stuff with time based auto

package org.firstinspires.ftc.teamcode.AutoOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class SigmaAutoOpEncoder extends LinearOpMode {
    final int ticks = 1000;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor intakeMotor;
    DcMotor outtakeMotor;

//    Servo platform;
//    ServoImplEx platform;


    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        // Initialize hardware
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");
//        platform = hardwareMap.get(ServoImplEx.class, "platform");
//        platform.setPwmRange(new PwmControl.PwmRange(500, 2500));



        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);


        if (isStopRequested()) return;

        // Wait for start
        waitForStart();
        runtime.reset();


        // === Autonomous Sequence ===

        int x = 1; //shooting
        int y = 1; //shooting

// find tick per cm or inch
// or cm per tick


        forward(25);

        turn(90);

        intakeMotor.setPower(0.7);

        forward(30);

        backward(50 - y);

        turncc(90);

        forward(37 - x);

        turn(45);

        //  shoot(3);

        turn(135);

        forward(13 - x);

        turncc(90);

        forward(50 - y);

        backward(50 - y);

        turncc(90);

        forward(37 - x);

        turn(45);

        // shoot(3);

    }


    private void forward(int unit) {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        int target = unit * ticks;

        frontLeftMotor.setTargetPosition(target);
        frontRightMotor.setTargetPosition(target);
        backLeftMotor.setTargetPosition(target);
        backRightMotor.setTargetPosition(target);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);


        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
                backLeftMotor.isBusy() || backRightMotor.isBusy()) {
            idle();
        }


        stopDrive();
    }


    private void backward(int unit) {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        int target = unit * ticks;

        frontLeftMotor.setTargetPosition(-target);
        frontRightMotor.setTargetPosition(-target);
        backLeftMotor.setTargetPosition(-target);
        backRightMotor.setTargetPosition(-target);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);


        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
                backLeftMotor.isBusy() || backRightMotor.isBusy()) {
            idle();
        }


        stopDrive();
    }


    private void turn(int degrees) {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        int target = degrees * ticks;

        frontLeftMotor.setTargetPosition(target);
        frontRightMotor.setTargetPosition(-target);
        backLeftMotor.setTargetPosition(target);
        backRightMotor.setTargetPosition(-target);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);


        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
                backLeftMotor.isBusy() || backRightMotor.isBusy()) {
            idle();
        }


        stopDrive();
    }


    private void turncc(int degrees) {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        int target = degrees * ticks;

        frontLeftMotor.setTargetPosition(-target);
        frontRightMotor.setTargetPosition(target);
        backLeftMotor.setTargetPosition(-target);
        backRightMotor.setTargetPosition(target);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);


        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
                backLeftMotor.isBusy() || backRightMotor.isBusy()) {

            idle();

        }

        stopDrive();
    }


    private void stopDrive() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        if (opModeIsActive() && runtime.time()<29){
          requestOpModeStop();
        }

    }


    private void shoot(){

        outtakeMotor.setPower(1);



    }






}



