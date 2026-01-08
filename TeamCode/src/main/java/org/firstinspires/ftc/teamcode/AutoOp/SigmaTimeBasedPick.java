package org.firstinspires.ftc.teamcode.AutoOp;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;

import org.firstinspires.ftc.teamcode.Utils.Pattern;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


@Autonomous
public class SigmaTimeBasedPick extends LinearOpMode {



    public static Pattern.alliance alliance;
    public static Pattern.startingLocation startingLocation;
    public static Pattern.motif motif;
    int stop = 500;

    int waiting = 3;


    int location;

    // 0 = RED FAR
    // 1 = RED CLOSE
    // 2 = BLUE FAR
    // 3 = BLUE CLOSE


    private DriveTrain drive;
    private Shooting shooting;
    private ElapsedTime runtime = new ElapsedTime();
    private AprilTagCV aprilTagCV;
    private ColorSensor colorSensor;

    private Pattern.ballColor[] ballColors = new Pattern.ballColor[3];

    int ballNumber = 1;

    public static boolean patternFound = false;


    @Override
    public void runOpMode() throws InterruptedException {





        // Initialize hardware
        drive = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);
        aprilTagCV = new AprilTagCV(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);
        colorSensor.lightOn();


        if (isStopRequested()) return;


        int AAA=0;

        int BBB = 0;

        int CCC = 0;

        int DDD = 0;

        while (DDD == 0 && !isStopRequested()) {



            while (AAA == 0 && !isStopRequested()) {

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

            while (BBB == 0 && !isStopRequested()){
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


            if (location == 0 || location == 1){
                alliance = Pattern.alliance.RED;
            } else if (location == 2 || location == 3){
                alliance = Pattern.alliance.BLUE;
            }

            if (location == 0 || location == 2){
                startingLocation = Pattern.startingLocation.FAR;
            } else if (location == 1 || location == 3){
                startingLocation = Pattern.startingLocation.CLOSE;
            }


            telemetry.addData("Alliance", alliance);
            telemetry.addData("Location", startingLocation);
            telemetry.addData("delay selected in seconds", waiting/1000);
            telemetry.addLine("Is this correct? Circle for Yes, Cross for No.");
            telemetry.update();

            while (CCC == 0 && !isStopRequested()){
                if (gamepad1.cross) {
                    AAA = 0;
                    BBB = 0;
                    CCC++;
                }

                if (gamepad1.circle) {
                    CCC++;
                    DDD++;
                }



            }
            CCC = 0;

        }






        waitForStart();




        int x = 1; //shooting
        int y = 1; //shooting



        runtime.reset();

        sleep(waiting);

        if (alliance == Pattern.alliance.RED){

            if (startingLocation == Pattern.startingLocation.FAR){

            }

            if (startingLocation == Pattern.startingLocation.CLOSE){

            }

        }else if (alliance == Pattern.alliance.BLUE){

            if (startingLocation == Pattern.startingLocation.FAR){

            }

            if (startingLocation == Pattern.startingLocation.CLOSE){

            }
        }else{
            //if it doesn't work... just go forwards
        }

// shove this somewhere in like a loop to get motif
//        then change patternFound and motif = Pattern.motif
        List<AprilTagDetection> detections = aprilTagCV.aprilTag.getDetections();

        aprilTagCV.currentDetections.addAll(detections);





//        color sensor

        colorSensor.detectColors();
        colorSensor.checkColor();

        if (colorSensor.ballVisible){





            if (ballNumber % 3 == 0){

                ballColors[0] = colorSensor.ballColor;

            }else if (ballNumber % 3 == 1){

                ballColors[1] = colorSensor.ballColor;

            }else if (ballNumber % 3 == 2){

                ballColors[2] = colorSensor.ballColor;

            }

            colorSensor.ballVisible = false;
            ballNumber++; // or just add one when smth rotates for accuracy

        }








        forward(25);

        turn(90);

        shooting.intakeMotor.setPower(0.7);

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
        drive.stopDrive();
        shooting.stopShooting();


    }




    public void forward(double inches){
        runtime.reset();
        drive.forward(0.5,inches);
        while (drive.isBusy(inches) || runtime.seconds() > inches/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("moving forward", inches);
            telemetry.addData("final position", inches/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }

    }

    public void backward(double inches){
        runtime.reset();
        drive.backward(0.5, inches);
        while (drive.isBusy(inches) || runtime.seconds() > inches/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("moving backward", inches);
            telemetry.addData("final position", inches/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }

    public void turn(double degrees){
        runtime.reset();
        drive.turn(0.5, degrees);
        while (drive.isBusy(degrees) || runtime.seconds() > degrees/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("turning clockwise", degrees);
            telemetry.addData("final position", degrees/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }

    public void turncc(double degrees){
        runtime.reset();
        drive.turncc(0.5, degrees);
        while (drive.isBusy(degrees) || runtime.seconds() > degrees/DriveTrain.inchesPerSecond*0.95){
            telemetry.addData("turning counterclockwise", degrees);
            telemetry.addData("final position", degrees/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }


    public void getMotorPosition(){
        telemetry.addData("fl", drive.frontLeftMotor.getCurrentPosition());
        telemetry.addData("fr",drive.frontRightMotor.getCurrentPosition());
        telemetry.addData("bl",drive.backLeftMotor.getCurrentPosition());
        telemetry.addData("br",drive.backRightMotor.getCurrentPosition());
    }


}

