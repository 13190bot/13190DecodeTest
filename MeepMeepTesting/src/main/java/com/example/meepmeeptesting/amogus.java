package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class amogus {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        int stop = 1;
        int y =1;
        int x =1;
        myBot.runAction(
                myBot.getDrive()
                        .actionBuilder(new Pose2d(0 , 20    , Math.toRadians(180)))







//AMOGUS


                        .waitSeconds(3)



                        .splineTo(new Vector2d(-20 , 0), Math.toRadians(270))
                        .turn(-Math.PI/2)
                        .splineTo(new Vector2d(-30, -5), Math.toRadians(215))
////                        .lineToX(-35)
//                        .splineTo(new Vector2d(x, -y), Math.toRadians(215))
//
//                        .splineTo(new Vector2d(-15, -10), Math.toRadians(270))
//                        .waitSeconds(stop)
//                        // run intake
//                        .lineToY(-50)
//                        .splineTo(new Vector2d(15, -15), Math.toRadians(270))
//                        .waitSeconds(stop)
//                        .lineToY(-30)
//                        .splineToLinearHeading(new Pose2d(x, -y, Math.toRadians(215)), Math.toRadians(0))
//
//                        .splineTo(new Vector2d(15, -15), Math.toRadians(270))
//                        .waitSeconds(stop)
//                        //run intake
//                        .lineToY(-50)
//                        .splineToLinearHeading(new Pose2d(x, -y, Math.toRadians(215)), Math.toRadians(90))
//
































                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_BLACK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}