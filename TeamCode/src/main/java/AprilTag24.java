package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
public class AprilTag24PoseBased extends LinearOpMode {

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

        boolean aligned = false;

        while (opModeIsActive() && !aligned) {
            List<AprilTagDetection> detections = aprilTag.getDetections();

            for (AprilTagDetection tag : detections) {
                if (tag.id == 24) {
                    double x = tag.pose.x;
                    double y = tag.pose.y;
                    double z = tag.pose.z;
                    double yaw   = tag.pose.yaw;
                    double pitch = tag.pose.pitch;
                    double roll  = tag.pose.roll;

                    telemetry.addData("Tag x", x);
                    telemetry.addData("Tag y", y);
                    telemetry.addData("Tag z", z);
                    telemetry.addData("Yaw", yaw);
                    telemetry.addData("Pitch", pitch);
                    telemetry.addData("Roll", roll);
                    telemetry.update();

                    if (z > 0.5) {
                        frontLeftMotor.setPower(0.3);
                        frontRightMotor.setPower(0.3);
                        backLeftMotor.setPower(0.3);
                        backRightMotor.setPower(0.3);
                    }
                    else if (z < 0.3) {
                        frontLeftMotor.setPower(-0.3);
                        frontRightMotor.setPower(-0.3);
                        backLeftMotor.setPower(-0.3);
                        backRightMotor.setPower(-0.3);
                    }
                    else if (x > 0.05) {
                        frontLeftMotor.setPower(0.2);
                        frontRightMotor.setPower(-0.2);
                        backLeftMotor.setPower(0.2);
                        backRightMotor.setPower(-0.2);
                    }
                    else if (x < -0.05) {
                        frontLeftMotor.setPower(-0.2);
                        frontRightMotor.setPower(0.2);
                        backLeftMotor.setPower(-0.2);
                        backRightMotor.setPower(0.2);
                    }
                    else {
                        frontLeftMotor.setPower(0);
                        frontRightMotor.setPower(0);
                        backLeftMotor.setPower(0);
                        backRightMotor.setPower(0);
                        aligned = true;
                    }

                    break;
                }
            }

            if (!aligned) {
                frontLeftMotor.setPower(0.1);
                frontRightMotor.setPower(0.1);
                backLeftMotor.setPower(0.1);
                backRightMotor.setPower(0.1);
            }
        }

        frontLeftMotor.setPower(0.5);
        frontRightMotor.setPower(0.5);
        backLeftMotor.setPower(0.5);
        backRightMotor.setPower(0.5);
        sleep(2000);

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
    }
}
