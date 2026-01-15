package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTeseter {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(
                myBot.getDrive()
                        .actionBuilder(new Pose2d(62, 20, Math.toRadians(180)))
                        .lineToX(62 - 27)
                        .turn(-Math.toRadians(90))
                        .lineToY(20 + 10)
                        .splineToLinearHeading(new Pose2d(0,0,Math.toRadians(135)), Math.PI)
                        //shoot

                        .build()
                        

//
//                forward(27);
//
//        turn(90);
//
//
//        // intake
//        forward(20);
//
//        backward(44);
//
//        turncc(90);
//
//        forward(38);
//
//        turn(45);
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_BLACK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
