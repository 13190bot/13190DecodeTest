package org.firstinspires.ftc.teamcode.TestOps;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
public class testchassisAuto extends LinearOpMode {

    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;

    @Override
    public void runOpMode() {
        // Initialize hardware
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor =  hardwareMap.get(DcMotor.class, "frontRightMotor");


        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wait for start
        waitForStart();

        if (isStopRequested()) return;

        // === Autonomous Sequence ===
        // Example: Drive forward for 2 seconds
        drive(0.5, 2000);

        // Stop motors
        stopDrive();

        // Example: Drive backward for 1 second
        drive(-0.5, 1000);

        stopDrive();
    }

    // Helper method to drive in one direction for time (ms)
    private void drive(double power, int timeMs) {
        frontLeftMotor.setPower(power);
        backLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backRightMotor.setPower(power);
        sleep(timeMs);
    }

    // Helper to stop all drive motors
    private void stopDrive() {
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}
