package org.firstinspires.ftc.teamcode.AutoOp;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.Shooting;




@Autonomous
public class SigmaTimeBasedPick extends LinearOpMode {

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
  
init();
      
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

        telemetry.addData("Location", location);
        telemetry.addLine("0: RED FAR, 1: RED CLOSE, 2: BLUE FAR, 3: BLUE CLOSE");
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




        // Wait for start
        waitForStart();
        runtime.reset();




        // === Autonomous Sequence ==
        int x = 1; //shooting
        int y = 1; //shooting




        sleep(waiting);

        drive.forward(25);

        drive.turn(90);

        shooting.intakeMotor.setPower(0.7);

        drive.forward(30);

        drive.backward(50 - y);

        drive.turncc(90);

        drive.forward(37 - x);

        drive.turn(45);

        drive.turn(135);

        drive.forward(13 - x);

        drive.turncc(90);

        drive.forward(50 - y);

        drive.backward(50 - y);

        drive.turncc(90);

        drive.forward(37 - x);

        drive.turn(45);






        //make everything  stop when runtime is > 30 seconds
        drive.stopDrive();
        shooting.stopShooting();


    }







}

