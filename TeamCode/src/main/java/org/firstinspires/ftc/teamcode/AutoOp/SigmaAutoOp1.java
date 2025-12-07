package org.firstinspires.ftc.teamcode.AutoOp;

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
//        ElapsedTime runtime = new ElapsedTime();
//
// IMU imu;
//
//imu = hardwareMap.get(IMU.class, "imu");
//imu.initialize();
//double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Set motor directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);



        if (isStopRequested()) return;

        // Wait for start
        waitForStart();

//        runtime.reset();

        // === Autonomous Sequence ===


//Outtake
//



//        outtakeMotor.setPower(1);
//        sleep(1000);
//        outtakeMotor.setPower(0);
//
//        // Run intake for 1 second
//        intakeMotor.setPower(0.7);
//        sleep(1000);
//        intakeMotor.setPower(0);
//
//        // Move servo platform up and down
//        platform.setPosition(1);
//        sleep(700);
//        platform.setPosition(0);



//           .waitSeconds(3)
//
//                .strafeToLinearHeading(new Vector2d(37, 30), Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(37)
//                .strafeToLinearHeading(new Vector2d(x, y), Math.toRadians(135))
//                .waitSeconds(shoot)
//
//
//
//
//                .strafeToLinearHeading(new Vector2d(37, 30), Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(40)
//                .strafeToLinearHeading(new Vector2d(37, 60), Math.toRadians(0))
//                .waitSeconds(stop)
//                .lineToX(50)
//                .strafeToLinearHeading(new Vector2d(x, y), Math.toRadians(135))
//                .waitSeconds(shoot)
//
//                .strafeToLinearHeading(new Vector2d(37, 30), Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(40)
//                .strafeToLinearHeading(new Vector2d(37, 60), Math.toRadians(0))
//                .waitSeconds(stop)
//                .lineToX(62)
//                .strafeToLinearHeading(new Vector2d(x, y), Math.toRadians(135))
//                .waitSeconds(shoot)
//
//                .strafeToLinearHeading(new Vector2d(15, 30), Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(40)
//                .strafeToLinearHeading(new Vector2d(x, y), Math.toRadians(135))
//                .waitSeconds(shoot)
//
//                .strafeToLinearHeading(new Vector2d(15, 30), Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(50)
//                .strafeToLinearHeading(new Vector2d(-15, 10), Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(40)
//                .strafeToLinearHeading(new Vector2d(x, y), Math.toRadians(135))
//                .waitSeconds(shoot)
//
//                .strafeToLinearHeading(new Vector2d(-15, 30), Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(50)
//                .strafeToLinearHeading(new Vector2d(x, y), Math.toRadians(135))
//                .waitSeconds(shoot)
//
//                .turnTo(Math.toRadians(90))
//                .waitSeconds(stop)
//                .lineToY(50)

//
//() -> autoTimer.seconds() < 30


    }
}
