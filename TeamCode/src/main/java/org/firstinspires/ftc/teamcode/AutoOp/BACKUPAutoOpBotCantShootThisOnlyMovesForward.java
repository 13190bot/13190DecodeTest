package org.firstinspires.ftc.teamcode.AutoOp;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;


@Autonomous
public class BACKUPAutoOpBotCantShootThisOnlyMovesForward extends LinearOpMode {

    int stop = 500;

    private DriveTrain drive;






    @Override
    public void runOpMode() throws InterruptedException {



        drive = new DriveTrain(hardwareMap);



        if (isStopRequested()) return;



        // Wait for start
        waitForStart();





        // === Autonomous Sequence ==
        sleep(5000);

        drive.forwardtime(0.5);

        sleep(1500);

        drive.forwardtime(0.5);





    }
}

