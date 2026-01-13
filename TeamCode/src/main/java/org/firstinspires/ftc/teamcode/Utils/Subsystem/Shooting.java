package org.firstinspires.ftc.teamcode.Utils.Subsystem;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.Range;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;


public class Shooting extends SubsystemBase {



    public DcMotor intakeMotor;
    public DcMotorEx outtakeMotor;
    public Servo platformRight;
    public Servo platformLeft;

    public PIDFController pidfController;
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kS = 0.0;
    public static double kV = 0.0;

    public final static double conversionAmount = 60/28;
    public static double targetVelocity = 1500;
    public static double tolerance = 10;
    public static double outtakeTolerance = 0.05;
    SimpleMotorFeedforward feedforward;


    public Shooting(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeMotor");
        platformRight = hardwareMap.get(Servo.class, "platformRight");
        platformLeft = hardwareMap.get(Servo.class, "platformLeft");
        platformRight.setDirection(Servo.Direction.REVERSE);


        feedforward = new SimpleMotorFeedforward(kS, kV);
        outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        outtakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        pidfController = new PIDFController(kP, kI, kD, 0, outtakeMotor.getVelocity(), outtakeMotor.getVelocity());
        pidfController.setTolerance(tolerance);

    }
    public void stopShooting(){
        intakeMotor.setPower(0);
        outtakeMotor.setPower(0);
        platformLeft.setPosition(0);
        platformRight.setPosition(0);
    }



    public void setTargetVelocity(double RPM) {
        targetVelocity = RPM * conversionAmount;
    }

    public double getCurrentVelocity() {
        return outtakeMotor.getVelocity();
        //Sarvesh madullapalli pro advice ^^^
    }



    @Override
    public void periodic(){

        double currentVelocity = outtakeMotor.getVelocity();

        double output = pidfController.calculate(currentVelocity, targetVelocity) + feedforward.calculate(targetVelocity);

        output = Range.clip(output, -1.0, 1.0);

        outtakeMotor.setPower(output);
    }


    public void updatePID(){

        double currentVelocity = outtakeMotor.getVelocity();

        double output = pidfController.calculate(currentVelocity, targetVelocity) + feedforward.calculate(targetVelocity);

        output = Range.clip(output, -1.0, 1.0);

        outtakeMotor.setPower(output);
    }

    public void updateCoeff() {
        outtakeMotor.setVelocityPIDFCoefficients(kP, kI, kD, 0);
        feedforward = new SimpleMotorFeedforward(kS, kV);
        pidfController.setTolerance(tolerance);
    }






    public class Shoot implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                outtakeMotor.setPower(1);
                initialized = true;
            }

            double vel = outtakeMotor.getVelocity();
            packet.put("outtakeVelocity", vel);
            updatePID();


            if (vel>0.95){
                platformRight.setPosition(1);
                platformLeft.setPosition(1);
                return false;
            }

            return true;
        }
    }

    public Action shoot() {
        return new Shooting.Shoot();
    }
}