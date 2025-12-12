package org.firstinspires.ftc.teamcode.AutoOp;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



/*





*/



@Autonomous
public class SigmaTimeBasedPick extends LinearOpMode {

    int stop = 500;

    int waiting = 3;


    int location;

    // 0 = RED FAR
    // 1 = RED CLOSE
    // 2 = BLUE FAR
    // 3 = BLUE CLOSE


    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor intakeMotor;
    DcMotor outtakeMotor;
    Servo platform;


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
        platform = hardwareMap.get(Servo.class, "platform");
//        platform.scaleRange(0, 1);






        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);




        if (isStopRequested()) return;


        int AAA=0;

        int BBB = 0;


init();

        while (AAA == 0) {

            telemetry.addLine("Pick Delay seconds... Triangle: 0, Circle: 3, Cross: 5, Square: 7");
            telemetry.update();
            if (gamepad1.triangle) {
                waiting = 0;
                AAA++;
            }
            if (gamepad1.circle) {
                waiting = 3000;
                AAA++;
            }
            if (gamepad1.cross) {
                waiting = 5000;
                AAA++;
            }
            if (gamepad1.square) {
                waiting = 7000;
                AAA++;
            }

        }

        while (BBB == 0){
            telemetry.addData("delay selected in seconds", waiting/1000);
            telemetry.addLine("Select place from the goal: Triangle: RED FAR, Circle: RED CLOSE, Cross: BLUE FAR, Square: BLUE CLOSE");
            telemetry.update();
            // 0 = RED FAR
            // 1 = RED CLOSE
            // 2 = BLUE FAR
            // 3 = BLUE CLOSE


            if (gamepad1.triangle) {
                location = 0;
                BBB++;
            }
            if (gamepad1.circle) {
                location = 1;
                BBB++;
            }
            if (gamepad1.cross) {
                location = 2;
                BBB++;
            }
            if (gamepad1.square) {
                location = 3;
                BBB++;
            }

        }

        telemetry.addData("Location", location);
        telemetry.addLine("0: RED FAR, 1: RED CLOSE, 2: BLUE FAR, 3: BLUE CLOSE");
        telemetry.addData("delay selected in seconds", waiting/1000);
        telemetry.update();






        // Wait for start
        waitForStart();
        runtime.reset();




        // === Autonomous Sequence ==
        int x = 1; //shooting
        int y = 1; //shooting




        sleep(waiting);

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
        platform.setPosition(1);
        sleep(1000);
        platform.setPosition(0);
        outtakeMotor.setPower(0);


        stopAll();
    }










}

