package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

@Config
public class updatedPIDF{
    public PIDFController pidfController;
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kS = 0.0;
    public static double kV = 0.0;
    public static double targetVelocity = 1500;

    public static double tolerance = 10;
    DcMotorEx flywheel;


    SimpleMotorFeedforward feedforward =
            new SimpleMotorFeedforward(kS, kV);

    public void FlywheelPIDF(DcMotorEx flywheel) {
        this.flywheel = flywheel;
        flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        flywheel.setMode(DcMotorEx.ZeroPowerBehavior.FLOAT);
        pidfController = new PIDFController(kP, kI, kD);
        pidfController.setTolerance(tolerance);
    }
    public void setTargetVelocity(double velocity) {
        targetVelocity = velocity;
    }

    public double getCurrentVelocity() {
        return flywheel.getVelocity();
    }

    public void update(){

        double currentVelocity = flywheel.getVelocity();

        double output = pidfController.calculate(currentVelocity, targetVelocity);

        output = Range.clip(output, -1.0, 1.0);

        flywheel.setVelocityPIDFCoefficients(kP, kI, kD);
        feedforward.calculate(10, 20);
    }
}