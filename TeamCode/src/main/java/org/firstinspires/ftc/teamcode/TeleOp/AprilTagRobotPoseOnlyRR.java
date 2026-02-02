package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.AutoOp.SigmaTimeBasedPick;
import org.firstinspires.ftc.teamcode.Utils.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.teamcode.Utils.Pattern;

import java.util.ArrayList;
import java.util.List;



@Disabled
@TeleOp
public class AprilTagRobotPoseOnlyRR extends LinearOpMode {

    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    private MecanumDrive drive;
    private Shooting shooting;
    private ElapsedTime runtime = new ElapsedTime();

    private AprilTagCV aprilTagCV;


    GamepadEx gamepadEx1;
    GamepadEx gamepadEx2;

    private AprilTagData tag20;
    private AprilTagData tag24;


    double xValue;
    double yValue;





    boolean lastRB = false;



    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(11.8, 61.7, Math.toRadians(90)); // change this
        drive = new MecanumDrive(hardwareMap, initialPose); // use RR if time to tune
        shooting = new Shooting(hardwareMap);
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        aprilTagCV = new AprilTagCV(hardwareMap);

        tag20 = new AprilTagData(20);
        tag24 = new AprilTagData(24);






        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {

            shooting.updatePID();



            TelemetryPacket packet = new TelemetryPacket();

            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;

            dash.sendTelemetryPacket(packet);










            if (SigmaTimeBasedPick.alliance == Pattern.alliance.RED) {

                telemetry.addLine("alliance: RED");

                if (tag24.visible) {

                    if (gamepad1.right_bumper && !lastRB) {
// red alliance tag 24


                        Action tab2 = drive.actionBuilder(tag24.pose2d)
                                .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(135)), Math.toRadians(135))
                                .build();


                        runningActions.add(new SequentialAction(
                                new InstantAction(() -> shooting.intakeMotor.setPower(0.7)),
                                tab2

                        ));
                    }
                } else {

                    if (gamepad1.right_bumper) {
                        drive.leftFront.setPower(0.5);
                        drive.leftBack.setPower(0.5);
                        drive.rightFront.setPower(-0.5);
                        drive.rightBack.setPower(-0.5);
                    }
                }
            }





            else if (SigmaTimeBasedPick.alliance == Pattern.alliance.BLUE) {
                telemetry.addLine("alliance: BLUE");

                if (tag20.visible) {

                    if (gamepad1.right_bumper && !lastRB) {

                        // using tag 20 and on blue alliance test it later
                        // this to make a circle and curves upwards
                        // new Pose2d(tag20.posX,tag20.posY,Math.toRadians(0))


                        Action tab1 = drive.actionBuilder(tag20.pose2d)
                                .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(225)), Math.toRadians(135))
                                .build();


                        runningActions.add(new SequentialAction(
                                new InstantAction(() -> shooting.intakeMotor.setPower(0.7)),
                                tab1

                        ));
                    }
                } else {

                    if (gamepad1.right_bumper) {
                        drive.leftFront.setPower(0.5);
                        drive.leftBack.setPower(0.5);
                        drive.rightFront.setPower(-0.5);
                        drive.rightBack.setPower(-0.5);
                    }
                }

            }




            else{
                telemetry.addLine("alliance: UNDEFINED");
                telemetry.addLine("select alliance");
                telemetry.addLine("Gamepad 2 dpad up: red, down: blue");
                if (gamepad2.dpad_up){
                    SigmaTimeBasedPick.alliance = Pattern.alliance.RED;
                }


                if (gamepad2.dpad_down){
                    SigmaTimeBasedPick.alliance = Pattern.alliance.BLUE;
                }
            }


            lastRB = gamepad2.right_bumper;








//            CommandScheduler.getInstance().run();

            aprilTagCV.currentDetections.clear();

            List<AprilTagDetection> detections = aprilTagCV.aprilTag.getDetections();

            aprilTagCV.currentDetections.addAll(detections);

            tag20.visible = false;
            tag24.visible = false;
//            telemetryAprilTag();

            for (AprilTagDetection detection : aprilTagCV.currentDetections) {

                if (detection.metadata != null) {
                    telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                    telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                    telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                    telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));


                    if (detection.id == 20){
                        tag20.robotPose = detection.robotPose;
                        tag20.robotX = detection.robotPose.getPosition().x;
                        tag20.robotY = detection.robotPose.getPosition().y;
                        tag20.robotHeading = Math.toDegrees(detection.robotPose.getOrientation().getYaw());
                        tag20.pose2d = new Pose2d(tag20.robotX, tag20.robotY, detection.robotPose.getOrientation().getYaw());


                        tag20.visible = true;
                        tag20.yaw = detection.ftcPose.yaw;

                    }else if (detection.id == 24) {
                        tag24.robotPose = detection.robotPose;
                        tag24.robotX = detection.robotPose.getPosition().x;
                        tag24.robotY = detection.robotPose.getPosition().y;
                        tag24.robotHeading = detection.robotPose.getOrientation().getYaw();
                        tag24.pose2d = new Pose2d(tag24.robotX, tag24.robotY, detection.robotPose.getOrientation().getYaw());



                        tag24.visible = true;
                        tag24.yaw = detection.ftcPose.yaw;

                    }
//                    else {
//                        pattern = detection.id-20;
//                        patternFound = true;
//                        // or just get the pattern from auto lol
//                    }



                } else {
                    telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                    telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));

                }


            }









// TELEMETRY




            telemetry.addLine("Gamepad 1 apriltag  RB: RR");
            telemetry.addLine("Intake: Left Bumper");
            telemetry.addLine("Platform: Right Bumper");
            telemetry.addLine("Outtake Power: 0.7 right trigger, 0.4 left trigger");
            telemetry.addData("Platform", shooting.platformServo.getPosition());
            telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
            telemetry.addData("Intake", shooting.intakeMotor.getPower());
            telemetry.addData("front left", drive.leftFront.getPower());
            telemetry.addData("back left", drive.leftBack.getPower());
            telemetry.addData("front right", drive.rightFront.getPower());
            telemetry.addData("back left", drive.leftBack.getPower());


            telemetry.update();




        }
    }





    public void getMotorPosition(){
        telemetry.addData("fl", drive.leftFront.getCurrentPosition());
        telemetry.addData("fr",drive.rightFront.getCurrentPosition());
        telemetry.addData("bl",drive.leftBack.getCurrentPosition());
        telemetry.addData("br",drive.rightBack.getCurrentPosition());
    }


//    private void telemetryAprilTag() {
//
//
//        List<AprilTagDetection> currentDetections = aprilTagCV.aprilTag.getDetections();
//        telemetry.addData("# AprilTags Detected", currentDetections.size());
//
//        // Step through the list of detections and display info for each one.
//        for (AprilTagDetection detection : currentDetections) {
//            if (detection.metadata != null) {
//                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
//                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
//                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
//                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
//            } else {
//                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
//                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
//            }
//        }   // end for() loop
//
//        // Add "key" information to telemetry
//        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
//        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
//        telemetry.addLine("RBE = Range, Bearing & Elevation");
//
//    }   // end method telemetryAprilTag()


}




