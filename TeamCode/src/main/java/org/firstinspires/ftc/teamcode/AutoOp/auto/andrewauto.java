//package org.firstinspires.ftc.teamcode.AutoOp.auto;
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//@Autonomous
//@Config
//public class andrewauto {
//
//        public static double TILE_SIZE = 24;
//
//
//        public static Vector2d initialPos = new Vector2d(-45, -52);
//        public static Vector2d scoringPos = new Vector2d(-12, -12);
//
//        public static double initialDegrees = 90-36 + 180;
//        public static double scoringDegrees = 45;
//        public static double lineDegrees = 270;
//
//
//
//        //    public static double lineLength = 22;
//        public static Vector2d firstLinePos = new Vector2d(-12, -32);
//        public static Vector2d secondLinePos = new Vector2d(-12,-22); //todo get the y value
//        public static Vector2d thidLinePos = new Vector2d(-12,0); //todo get the y value (the difference should be same)
////        public static double lengthPerBall = 2;
//
//
////
////        @Override
////        public void runOpMode() throws InterruptedException {
////            initHardware();
////
////            Pose2d initialPose = new Pose2d(initialPos, Math.toRadians(initialDegrees));
////            Pose2d scoringPose = new Pose2d(scoringPos, Math.toRadians(scoringDegrees));
////
////            MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
////            drive.localizer.setPose(initialPose);
////
////            waitForStart();
////            if (isStopRequested()) return;
////
////            new SequentialCommandGroup(
////                    new ActionCommand(drive.actionBuilder(initialPose)
////                            .setReversed(true)
////                            .splineTo(scoringPose.position, scoringPose.heading)
////                            .build()
////                    ),
////
////                    hopperOuttakeRapidFire(),
////
////                    new ActionCommand(drive.actionBuilder(scoringPose)
////                            // 1st line
////                            .afterTime(0, () -> {outtakeSpinning = false;
////                            })
////                            .setReversed(false)
////                            .splineTo(firstLinePos, Math.toRadians(lineDegrees))
////                            .afterTime(0, () -> int
////
////}
////}
