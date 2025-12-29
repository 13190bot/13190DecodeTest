package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
public class updatedPIDF{

    public DcMotorEx flywheel;
    public PIDFController pidfController;

    public double kP = 0.001;
    public double kI = 0.0001;
    public double kD = 0.00001;

    public double targetVelocity = 1500;

    public void FlywheelPIDF(DcMotorEx flywheel) {
        this.flywheel = flywheel;
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheelMotor");

        flywheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        flywheel.setMode(DcMotorEx.ZeroPowerBehavior.FLOAT);
        pidfController = new PIDFController(kP, kI, kD);

    }
    public void setTargetVelocity(double velocity) {
        targetVelocity = velocity;
    }

    public void update(){

        double currentVelocity = flywheel.getVelocity();

        double output = pidfController.calculate(currentVelocity, targetVelocity);

        output = Math.max(-1.0, Math.min(1.0, output));

        flywheel.setVelocity(output);

        telemetry.addData("Target Vel", targetVelocity);
        telemetry.addData("Current Vel", currentVelocity);
        telemetry.update();
    }
}
