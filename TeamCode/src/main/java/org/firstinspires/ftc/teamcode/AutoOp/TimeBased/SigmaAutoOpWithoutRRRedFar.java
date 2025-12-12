package org.firstinspires.ftc.teamcode.AutoOp.TimeBased;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class SigmaAutoOpWithoutRRRedFar extends LinearOpMode {

    int stop = 500;

    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor intakeMotor;
    DcMotor outtakeMotor;
    Servo platformRight;
    Servo platformLeft;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware
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
//        platform.scaleRange(0, 1);




        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);




        if (isStopRequested()) return;


        // Wait for start
        waitForStart();
        runtime.reset();




        // === Autonomous Sequence ==
        int x = 1; //shooting
        int y = 1; //shooting




        forward(25);

        turn(90);

        intakeMotor.setPower(0.7);
        forward(30);

        backward(50 - y);

        turncc(90);

        forward(37 - x);

        turn(45);


        turn(135);

        forward(13 - x);

        turncc(90);

        forward(50 - y);

        backward(50 - y);

        turncc(90);

        forward(37 - x);

        turn(45);






        //make everything  stop when runtime is > 30 seconds
        stopAll();




    }


    // Helper to stop all drive motors
    private void stopDrive() {
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);


    }


    // Helper to stop all drive   motors
    private void stopAll() {
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        outtakeMotor.setPower(0);
        intakeMotor.setPower(0);


    }


    // Helper method to turn clockwise
    //assuming it takes 4 seconds for 360 degrees
    private void turn(int degrees) {
        frontLeftMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        frontRightMotor.setPower(-0.5);
        backRightMotor.setPower(-0.5);
        sleep((int) (degrees * (1000 / 90)));  // 360 degrees divided by 4 seconds = 90
        stopDrive();
        sleep(stop);
    }


    // Helper method to turn counterclockwise
    //assuming it takes 4 seconds for 360 degrees
    private void turncc(int degrees) {
        frontLeftMotor.setPower(-0.5);
        backLeftMotor.setPower(-0.5);
        frontRightMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
        sleep((int) (degrees * (1000 / 90)));  // 360 degrees divided by 4 seconds = 90
        stopDrive();
        sleep(stop);
    }




    // Helper method to drive forward
    //assuming 1 unit in meepmeep is
    private void forward(int unit) {
        frontLeftMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
        sleep((int) (unit * (1000 / 5)));
        stopDrive();
        sleep(stop);
    }


    private void backward(int unit) {
        frontLeftMotor.setPower(-0.5);
        backLeftMotor.setPower(-0.5);
        frontRightMotor.setPower(-0.5);
        backRightMotor.setPower(-0.5);
        sleep((int) (unit * (1000 / 5)));
        stopDrive();
        sleep(stop);
    }

    private void shoot(){


        outtakeMotor.setPower(0.7);
        sleep(1500);
        platformRight.setPosition(1);
        platformLeft.setPosition(1);
        sleep(1000);
        platformRight.setPosition(0);
        platformLeft.setPosition(0);
        outtakeMotor.setPower(0);


        stopAll();
    }




    // Helper method to drive in one direction for time (ms)
    private void drive ( double power, int timeMs){
        frontLeftMotor.setPower(power);
        backLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
        sleep(timeMs);
        stopDrive();
    }




//adb connect 192.168.43.1






}

