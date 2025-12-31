package org.firstinspires.ftc.teamcode.AutoOp;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.Shooting;

import org.firstinspires.ftc.teamcode.Utils.Pattern;




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


    @Override
    public void runOpMode() throws InterruptedException {





        // Initialize hardware
        drive = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);




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
        drive.forward(0.5,inches);
        while (drive.isBusy()){
            telemetry.addData("moving forward", inches);
            telemetry.addData("final position", inches/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }

    }

    public void backward(double inches){
        drive.backward(0.5, inches);
        while (drive.isBusy()){
            telemetry.addData("moving backward", inches);
            telemetry.addData("final position", inches/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }

    public void turn(double degrees){
        drive.turn(0.5, degrees);
        while (drive.isBusy()){
            telemetry.addData("turning clockwise", degrees);
            telemetry.addData("final position", degrees/DriveTrain.ticks);
            getMotorPosition();
            telemetry.update();
        }
    }

    public void turncc(double degrees){
        drive.turncc(0.5, degrees);
        while (drive.isBusy()){
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

