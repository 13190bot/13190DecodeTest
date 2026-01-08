package org.firstinspires.ftc.teamcode.Utils.Subsystem;

import com.acmerobotics.roadrunner.Pose2d;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;


public class AprilTagData{

    public final int id;

    public Pose3D robotPose;
    public Pose2d pose2d;
    public double robotX;
    public double robotY;
    public double robotHeading;


    public double posX;
    public double posY;
    public double posZ;

    public double yaw;
    public double pitch;
    public double roll;
    public double bearing;

    public boolean visible = false;

    public AprilTagData(int id) {
        this.id = id;
    }

}
