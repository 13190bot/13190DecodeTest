package org.firstinspires.ftc.teamcode.Utils.Subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

@Config
public class OuttakeSubsystem extends SubsystemBase {
    DcMotorEx outtake;
    PIDFController pidf;
    SimpleMotorFeedforward feedforward;
    private double targetRPM;
    private double currentPower;
    public static double TICKS_PER_REV = 28.0;
    public static double TOLERANCE = 25.0; // 25 RPM is close enough
    public static double kP = 0.5;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double ks = 0.2;
    public static double kv = 0.0;

    public OuttakeSubsystem(DcMotorEx outtake) {
        this.outtake = outtake;
        outtake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        outtake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        pidf = new PIDFController(kP, kI, kD, 0);
        pidf.setTolerance(TOLERANCE);
        feedforward = new SimpleMotorFeedforward(ks, kv, 0);
        targetRPM = 0;
        currentPower = 0;
    }

    @Override
    public void periodic() {
        double currentRPM = getCurrentRPM();
        double feedforwardPower = feedforward.calculate(targetRPM);
        double pidCorrection = pidf.calculate(currentRPM, targetRPM);
        currentPower = feedforwardPower + pidCorrection;
        currentPower = Range.clip(currentPower, -1, 1);
        outtake.setPower(currentPower);
    }
//e
    public double getCurrentRPM() {
        double ticksPerSecond = outtake.getVelocity();
        double revolutionsPerSecond = ticksPerSecond / TICKS_PER_REV;
        return revolutionsPerSecond * 60.0;
    }

    public double getTargetRPM() {
        return targetRPM;
    }

    public double getCurrentPower() {
        return currentPower;
    }

    public void setRPM(double rpm) {
        targetRPM = Range.clip(rpm, 0, Double.POSITIVE_INFINITY);
        pidf.setSetPoint(targetRPM);
    }

    public boolean atTarget() {
        return pidf.atSetPoint();
    }

    public double getError() {
        return targetRPM - getCurrentRPM();
    }

    public void updateCoeffs() {
        pidf = new PIDFController(kP, kI, kD, 0);
        pidf.setTolerance(TOLERANCE);
        feedforward = new SimpleMotorFeedforward(ks, kv, 0);
    }

    public void stop() {
        outtake.setPower(0);
        targetRPM = 0;
        currentPower = 0;
        pidf.reset();
    }
}
