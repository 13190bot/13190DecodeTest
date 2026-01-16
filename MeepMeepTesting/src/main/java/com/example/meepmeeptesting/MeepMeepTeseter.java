package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class AmongUs {
    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        bot.runAction(
                bot.getDrive().actionBuilder(new Pose2d(0, 0, 0))
                        .lineTo(new Vector2d(0, 20))
                        .lineTo(new Vector2d(10, 20))
                        .lineTo(new Vector2d(10, 0))
                        .lineTo(new Vector2d(20, 0))
                        .lineTo(new Vector2d(20, 20))
                        .lineTo(new Vector2d(30, 20))
                        .lineTo(new Vector2d(30, 0))
                        .lineTo(new Vector2d(30, 50))
                        .lineTo(new Vector2d(0, 50))
                        .splineTo(new Vector2d(-10, 70), Math.toRadians(180))
                        .splineTo(new Vector2d(40, 70), Math.toRadians(0))
                        .lineTo(new Vector2d(40, 30))
                        .lineTo(new Vector2d(55, 30))
                        .lineTo(new Vector2d(55, 55))
                        .lineTo(new Vector2d(40, 55))
                        .lineTo(new Vector2d(40, 30))
                        .lineTo(new Vector2d(40, 0))
                        .lineTo(new Vector2d(0, 0))
                        .lineTo(new Vector2d(10, 35))
                        .lineTo(new Vector2d(25, 35))
                        .lineTo(new Vector2d(25, 45))
                        .lineTo(new Vector2d(10, 45))
                        .lineTo(new Vector2d(10, 35))
                        .build()
        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(true)
                .addEntity(bot)
                .start();
    }
}