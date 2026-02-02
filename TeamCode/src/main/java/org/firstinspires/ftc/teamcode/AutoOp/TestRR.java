package org.firstinspires.ftc.teamcode.AutoOp;

import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.HolonomicController;
import com.acmerobotics.roadrunner.MecanumKinematics;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.MotorFeedforward;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.TimeTrajectory;
import com.acmerobotics.roadrunner.TimeTurn;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TurnConstraints;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.ftc.DownsampledWriter;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.FlightRecorder;
import com.acmerobotics.roadrunner.ftc.LazyImu;
import com.acmerobotics.roadrunner.ftc.LynxFirmware;
import com.acmerobotics.roadrunner.ftc.OTOSIMU;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.acmerobotics.roadrunner.ftc.RawEncoder;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.arcrobotics.ftclib.gamepad.*;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.RoadRunner.*;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.lang.Math;
import java.util.List;

@Disabled
@Autonomous
public class TestRR extends LinearOpMode {

    private Shooting shooting;

    private AprilTagCV aprilTagCV;

    OTOSLocalizer localizer;

    private AprilTagData tag20;
    private AprilTagData tag24;
    int pattern;
    //1 = GPP, 2 = PGP, 3 = PPG cuz thats where the green is








    @Override
    public void runOpMode() throws InterruptedException {


        shooting = new Shooting(hardwareMap);
        aprilTagCV = new AprilTagCV(hardwareMap);

        tag20 = new AprilTagData(20);
        tag24 = new AprilTagData(24);


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(70, 37, 0));
//        localizer = new TwoDeadWheelLocalizer(hardwareMap, new Pose2d(70, 37, 0));



        CommandScheduler.getInstance().schedule(
                new SequentialCommandGroup(


                )








        );

        waitForStart();

        if (isStopRequested()) return;

        CommandScheduler.getInstance().run();




        Action shoot = new SequentialAction(

                new InstantAction(() -> shooting.outtakeMotor.setPower(1)),
                new ParallelAction(new InstantAction(()-> shooting.platformServo.setPosition(1))
                )
        );




        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(62, 20, Math.toRadians(180)))


//Red far from goal, head is inttake back is outtake
//only 2 balls can fit in the robot

                        .stopAndAdd(new InstantAction(() -> shooting.intakeMotor.setPower(0.7)))

                        .afterTime(3,new InstantAction(()-> shooting.outtakeMotor.setPower(1)))



                        .stopAndAdd(shoot)


                        .splineTo(new Vector2d(37, 30), Math.toRadians(90))
//                        run intake
                        .splineTo(new Vector2d(37, 35), Math.toRadians(90))
                        .splineToLinearHeading(new Pose2d(0, 0, Math.toRadians(135)), Math.toRadians(135))

                        .splineTo(new Vector2d(37, 30), Math.toRadians(90))
//                        run intake
                        .lineToY(40)
                        .splineTo(new Vector2d(37, 60), Math.toRadians(0))
                        .lineToX(45)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(135))

                        .splineTo(new Vector2d(37, 30), Math.toRadians(90))
                        .lineToY(40)
                        .splineTo(new Vector2d(37, 60), Math.toRadians(0))
//                        run intake
                        .lineToX(55)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(135))

                        .splineTo(new Vector2d(15, 30), Math.toRadians(90))
//                        run intake
                        .lineToY(35)
                        .splineToLinearHeading(new Pose2d(0, 0,Math.toRadians(135)), Math.toRadians(0))

                        .splineTo(new Vector2d(15, 30), Math.toRadians(90))
//                        run intake
                        .lineToY(50)
                        .splineTo(new Vector2d(-15, 10), Math.toRadians(90))
                        .lineToY(30)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(135))

                        .splineTo(new Vector2d(-15, 30), Math.toRadians(90))
//                        run intake
                        .lineToY(50)
                        .splineTo(new Vector2d(0, 0), Math.toRadians(135))

                        .turnTo(Math.toRadians(90))
                        .lineToY(50)

                        .build()
        );





























        telemetry.addLine("Intake: Left Bumper");
        telemetry.addLine("Platform: Right Bumper");
        telemetry.addLine("Outtake Power: 0.7 right trigger, 0.4 left trigger");

        telemetry.addData("Platform", shooting.platformServo.getPosition());
        telemetry.addData("Outtake", shooting.outtakeMotor.getPower());
        telemetry.addData("Intake", shooting.intakeMotor.getPower());
        telemetry.addData("front left", drive.leftFront.getPower());
        telemetry.addData("back left", drive.leftBack.getPower());
        telemetry.addData("front right", drive.rightFront.getPower());
        telemetry.addData("back right", drive.rightBack.getPower());


        telemetry.update();




    }
 }
// Blue Close

//CommandScheduler.getInstance().schedule(
//        new SequentialCommandGroup(
//)
//);
//
//waitForStart();
//
//if (isStopRequested()) return;
//
//CommandScheduler.getInstance().run();
//
//Action shoot = new SequentialAction(
//        new InstantAction(() -> shooting.outtakeMotor.setPower(1)),
//        new ParallelAction(
//                new InstantAction(() -> shooting.platformRight.setPosition(1)),
//                new InstantAction(() -> shooting.platformLeft.setPosition(1))
//        )
//);
//
//Actions.runBlocking(
//        drive.actionBuilder(new Pose2d(-58.05327 + 8, -58.05327 + 8, Math.toRadians(180)))
//
//
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .splineTo(new Vector2d(-12, -40.65 + 8), Math.toRadians(270))
//        .splineTo(new Vector2d(-12, -55.35 - 8), Math.toRadians(270))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .splineTo(new Vector2d(12, -40.65 + 8), Math.toRadians(270))
//        .splineTo(new Vector2d(12, -55.35 - 8), Math.toRadians(270))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .splineTo(new Vector2d(36, -40.65 + 8), Math.toRadians(270))
//        .splineTo(new Vector2d(36, -55.35 - 8), Math.toRadians(270))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .build()
//);

//
//
//
//
// Blue Far
//
 //
 //
//CommandScheduler.getInstance().schedule(
//        new SequentialCommandGroup(
//)
//);
//
//waitForStart();
//
//if (isStopRequested()) return;
//
//CommandScheduler.getInstance().run();
//
//Action shoot = new SequentialAction(
//        new InstantAction(() -> shooting.outtakeMotor.setPower(1)),
//        new ParallelAction(
//                new InstantAction(() -> shooting.platformRight.setPosition(1)),
//                new InstantAction(() -> shooting.platformLeft.setPosition(1))
//        )
//);
//
//Actions.runBlocking(
//        drive.actionBuilder(new Pose2d(60, -12, Math.toRadians(180)))
//
//
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .splineTo(new Vector2d(-12, -40.65 + 8), Math.toRadians(270))
//        .splineTo(new Vector2d(-12, -55.35 - 8), Math.toRadians(270))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .splineTo(new Vector2d(12, -40.65 + 8), Math.toRadians(270))
//        .splineTo(new Vector2d(12, -55.35 - 8), Math.toRadians(270))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .splineTo(new Vector2d(36, -40.65 + 8), Math.toRadians(270))
//        .splineTo(new Vector2d(36, -55.35 - 8), Math.toRadians(270))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(135))
//
//        .build()
//);

// Red Far

//CommandScheduler.getInstance().schedule(
//        new SequentialCommandGroup(
//)
//);
//
//waitForStart();
//
//if (isStopRequested()) return;
//
//CommandScheduler.getInstance().run();
//
//Action shoot = new SequentialAction(
//        new InstantAction(() -> shooting.outtakeMotor.setPower(1)),
//        new ParallelAction(
//                new InstantAction(() -> shooting.platformRight.setPosition(1)),
//                new InstantAction(() -> shooting.platformLeft.setPosition(1))
//        )
//);
//
//Actions.runBlocking(
//        drive.actionBuilder(new Pose2d(60, 12, Math.toRadians(0)))
//
//
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .splineTo(new Vector2d(36, 32.65), Math.toRadians(90))
//        .splineTo(new Vector2d(36, 63.35), Math.toRadians(90))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .splineTo(new Vector2d(12, 32.65), Math.toRadians(90))
//        .splineTo(new Vector2d(12, 63.35), Math.toRadians(90))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .splineTo(new Vector2d(-12, 32.65), Math.toRadians(90))
//        .splineTo(new Vector2d(-12, 63.35), Math.toRadians(90))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .build()
//);

// Red Close

//CommandScheduler.getInstance().schedule(
//        new SequentialCommandGroup(
//)
//);
//
//waitForStart();
//
//if (isStopRequested()) return;
//
//CommandScheduler.getInstance().run();
//
//Action shoot = new SequentialAction(
//        new InstantAction(() -> shooting.outtakeMotor.setPower(1)),
//        new ParallelAction(
//                new InstantAction(() -> shooting.platformRight.setPosition(1)),
//                new InstantAction(() -> shooting.platformLeft.setPosition(1))
//        )
//);
//
//Actions.runBlocking(
//        drive.actionBuilder( new Pose2d(-58.05327 + 8, 50.05327, Math.toRadians(180)))
//
//
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .splineTo(new Vector2d(-12, 32.65), Math.toRadians(90))
//        .splineTo(new Vector2d(-12, 63.35), Math.toRadians(90))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .splineTo(new Vector2d(12, 32.65), Math.toRadians(90))
//        .splineTo(new Vector2d(12, 63.35), Math.toRadians(90))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .splineTo(new Vector2d(36, 32.65), Math.toRadians(90))
//        .splineTo(new Vector2d(36, 63.65), Math.toRadians(90))
//        .splineTo(new Vector2d(0, 0), Math.toRadians(315))
//
//        .build()
//);


