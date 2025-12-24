package org.firstinspires.ftc.teamcode.TestOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
public class AprilTag20GoodPosition extends LinearOpMode {

    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;

    VisionPortal visionPortal;
    AprilTagProcessor aprilTag;

    @Override
    public void runOpMode() {

        frontLeftMotor  = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        backLeftMotor   = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor  = hardwareMap.get(DcMotor.class, "backRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        aprilTag = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(aprilTag);

        waitForStart();

        boolean foundAprilTag = false;

        while (opModeIsActive() && !foundAprilTag) {
            List<AprilTagDetection> detections = aprilTag.getDetections();

            for (AprilTagDetection tag : detections) {
                if (tag.id == 20) {
                    foundAprilTag = true;
                    break;
                }
            }

            if (!foundAprilTag) {
                setDrive(0, 0, 0.3);
            } else {
                setDrive(0, 0, 0);
            }
        }

        setDrive(0, 0.5, 0);
        sleep(3500);

        setDrive(0, 0, 0);
    }

    void setDrive(double x, double y, double rx) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        double frontLeftMotorPower = (y + x + rx) / denominator;
        double backLeftMotorPower   = (y - x + rx) / denominator;
        double frontRightMotorPower = (y - x - rx) / denominator;
        double backRightMotorPower  = (y + x - rx) / denominator;

        frontLeftMotor.setPower(frontLeftMotorPower);
        backLeftMotor.setPower(backLeftMotorPower);
        frontRightMotor.setPower(frontRightMotorPower);
        backRightMotor.setPower(backRightMotorPower);
    }
}
