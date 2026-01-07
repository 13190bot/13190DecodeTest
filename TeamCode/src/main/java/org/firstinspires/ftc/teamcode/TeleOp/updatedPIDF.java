package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

public class updatedPIDF{
    public PIDFController pidfController;
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kS = 0.0;
    public static double kV = 0.0;
    public static double targetVelocity = 1500;

    public static double tolerance = 10;
    DcMotorEx outtakeMotor;
//e

    SimpleMotorFeedforward feedforward;

    public updatedPIDF(DcMotorEx flywheel, double initialVelocity) {
        this.outtakeMotor = flywheel;
        this.feedforward = new SimpleMotorFeedforward(kS, kV);
        flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flywheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        flywheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        pidfController = new PIDFController(kP, kI, kD, 0, initialVelocity, flywheel.getVelocity());
        pidfController.setTolerance(tolerance);
    }
    public void setTargetVelocity(double velocity) {
        targetVelocity = velocity;
    }

    public double getCurrentVelocity() {
        return outtakeMotor.getVelocity();
    }

    public void update(){

        double currentVelocity = outtakeMotor.getVelocity();

        double output = pidfController.calculate(currentVelocity, targetVelocity) + feedforward.calculate(targetVelocity);

        output = Range.clip(output, -1.0, 1.0);

        outtakeMotor.setPower(output);
    }

    public void updateCoeff() {
        outtakeMotor.setVelocityPIDFCoefficients(kP, kI, kD, 0);
        this.feedforward = new SimpleMotorFeedforward(kS, kV);
        pidfController.setTolerance(tolerance);
    }
}