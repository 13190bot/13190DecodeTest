package org.firstinspires.ftc.teamcode.TestOps;




import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.Shooting;


@TeleOp
@Config
public class pid extends LinearOpMode {








    public void updatePID(){

        double currentVelocity = outtakeMotor.getVelocity();

        double output = pidfController.calculate(currentVelocity, targetVelocity) + feedforward.calculate(targetVelocity);

        output = Range.clip(output, -1.0, 1.0);

        outtakeMotor.setPower(output);
    }
    public void updateCoeff() {
        pidfController.setPIDF(kP, kI, kD, 0);
        feedforward = new SimpleMotorFeedforward(kS, kV);
        pidfController.setTolerance(tolerance);
        pidfController.setSetPoint(setPoint);
    }
    public double getCurrentVelocity() {
        return outtakeMotor.getVelocity();
        //Sarvesh madullapalli pro advice ^^^
    }
    public void setTargetVelocity(double RPM) {
        targetVelocity = RPM * conversionAmount;
    }


    FtcDashboard dashboard = FtcDashboard.getInstance();




    public DcMotor intakeMotor;
    public DcMotorEx outtakeMotor;
    public Servo platformRight;
    public Servo platformLeft;

    public PIDFController pidfController;
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kF = 0.0;
    public static double kS = 0.0;
    public static double kV = 0.0;

    public final static double conversionAmount = (double) 28 /60;
    public double targetVelocity = 0;
    public static double tolerance = 100;
    public static double setPoint = 1000;
    public static double outtakeTolerance = 0.05;
    SimpleMotorFeedforward feedforward;


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());





        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeMotor");
        platformRight = hardwareMap.get(Servo.class, "platformRight");
        platformLeft = hardwareMap.get(Servo.class, "platformLeft");
        platformRight.setDirection(Servo.Direction.FORWARD);
        platformLeft.setDirection(Servo.Direction.REVERSE);


        feedforward = new SimpleMotorFeedforward(kS, kV);
        outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        outtakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        pidfController = new PIDFController(kP, kI, kD, 0);
        pidfController.setTolerance(tolerance);
        pidfController.setSetPoint(setPoint);

        waitForStart();


        if (isStopRequested()) return;



        while (opModeIsActive() && !isStopRequested()) {




            updatePID();
            updateCoeff();




























            setTargetVelocity(1);

            if (gamepad1.circle) {
                setTargetVelocity(1000);
            }

            if (gamepad1.triangle) {
                setTargetVelocity(2000);
            }
            if (gamepad1.square) {
                setTargetVelocity(3000);
            }
            if (gamepad1.cross) {
                setTargetVelocity(4000);
            }
            if (gamepad1.right_bumper) {
                setTargetVelocity(0);
            }
            if (gamepad1.left_bumper) {
                setTargetVelocity(500);
            }


            if (gamepad1.dpad_up){
                setTargetVelocity(targetVelocity + 1000);
            }

            if (gamepad1.dpad_down){
                setTargetVelocity(targetVelocity - 1000);
            }











            telemetry.addLine("right bumper: 0");
            telemetry.addLine("left bumper: 500");
            telemetry.addData("P: ", kP);
            telemetry.addData("I: ", kI);
            telemetry.addData("D: ", kD);
            telemetry.addData("setpoint: ", setPoint);
//e

            telemetry.addLine("triangle: 2k");
            telemetry.addLine("square: 3k");
            telemetry.addLine("cross: 4k");
            telemetry.addLine("circle: 1k");









            telemetry.update();


























        }
    }
}

