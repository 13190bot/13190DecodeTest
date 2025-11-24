package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class SigmaAutoOp1 extends LinearOpMode {

    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor intakeMotor;
    DcMotor outtakeMotor;
    Servo platform;

    @Override
    public void runOpMode() {
        // Initialize hardware
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");
        platform = hardwareMap.get(Servo.class, "platform");
        platform.scaleRange(0,1);


        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Wait for start
        waitForStart();

        if (isStopRequested()) return;

        // === Autonomous Sequence ===
        ElapsedTime autoTimer = new ElapsedTime();


//Outtake

        outtakeMotor.setPower(1);
        sleep(1000);
        outtakeMotor.setPower(0);

        // Run intake for 1 second
        intakeMotor.setPower(1);
        sleep(1000);
        intakeMotor.setPower(0);

        // Move servo platform up and down
        platform.setPosition(1);
        sleep(700);
        platform.setPosition(0);

//  STOP AFTER 30 SECONDS      () -> autoTimer.seconds() < 30


    }
}
