package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class MeepMeepTestingSpline1 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        int shoot = 2;
        int stop = 1;
        int x =0; //shooting place
        int y =0; //shooting place
//ADD A 30 SEC TIMER ON A WHILE LOOP FOR AUTOOP

        myBot.runAction(
                myBot.getDrive()
                        .actionBuilder(new Pose2d(62, 20, Math.toRadians(180)))


















//Red far from goal, head is inttake back is outtake
//only 2 balls can fit in the robot

                        .waitSeconds(3)

                        .splineTo(new Vector2d(37, 30), Math.toRadians(90))
                        .waitSeconds(stop)
//                        run intake
                        .splineTo(new Vector2d(37, 35), Math.toRadians(90))
                        .waitSeconds(stop)
                        .splineToLinearHeading(new Pose2d(x, y, Math.toRadians(135)), Math.toRadians(135))
                        .waitSeconds(shoot)

                        .splineTo(new Vector2d(37, 30), Math.toRadians(90))
                        .waitSeconds(stop)
//                        run intake
                        .lineToY(40)
                        .splineTo(new Vector2d(37, 60), Math.toRadians(0))
                        .waitSeconds(stop)
                        .lineToX(45)
                        .splineTo(new Vector2d(x, y), Math.toRadians(135))
                        .waitSeconds(shoot)

                        .splineTo(new Vector2d(37, 30), Math.toRadians(90))
                        .waitSeconds(stop)
                        .lineToY(40)
                        .splineTo(new Vector2d(37, 60), Math.toRadians(0))
                        .waitSeconds(stop)
//                        run intake
                        .lineToX(55)
                        .splineTo(new Vector2d(x, y), Math.toRadians(135))
                        .waitSeconds(shoot)

                        .splineTo(new Vector2d(15, 30), Math.toRadians(90))
                        .waitSeconds(stop)
//                        run intake
                        .lineToY(35)
                        .splineTo(new Vector2d(x, y), Math.toRadians(135))
                        .waitSeconds(shoot)

                        .splineTo(new Vector2d(15, 30), Math.toRadians(90))
                        .waitSeconds(stop)
//                        run intake
                        .lineToY(50)
                        .splineTo(new Vector2d(-15, 10), Math.toRadians(90))
                        .waitSeconds(stop)
                        .lineToY(30)
                        .splineTo(new Vector2d(x, y), Math.toRadians(135))
                        .waitSeconds(shoot)

                        .splineTo(new Vector2d(-15, 30), Math.toRadians(90))
                        .waitSeconds(stop)
//                        run intake
                        .lineToY(50)
                        .splineTo(new Vector2d(x, y), Math.toRadians(135))
                        .waitSeconds(shoot)

                        .turnTo(Math.toRadians(90))
                        .waitSeconds(stop)
                        .lineToY(50)
//
//































                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_BLACK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}