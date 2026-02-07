package org.firstinspires.ftc.teamcode.TestOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
@Config
public class pidv3 extends LinearOpMode {
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double ks = 0.0;
    public static double kv = 0.0;
    public static double TARGET_RPM = 3000;
    public static double TICKS_PER_REV = 28.0;
    public static double TOLERANCE = 25.0;

    public double CURRENTRPM = 0;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        DcMotorEx outtake = (DcMotorEx) hardwareMap.dcMotor.get("outtakeMotor");
        outtake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtake.setDirection(DcMotorSimple.Direction.REVERSE);

        PIDFController pidfController = new PIDFController(kP, kI, kD, 0);
//        SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(ks, kv, 0);

        boolean running = false;

        telemetry.addLine("Outtake PIDF Tuner");
        telemetry.addLine("Press A to start/stop");
        telemetry.addLine("Adjust kP, kI, kD, ks, kv, TARGET_RPM in Dashboard");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.cross && !gamepad1.start) {
                running = !running;
                if (!running) {
                    outtake.setPower(0);
                    pidfController.reset();
                }
                sleep(200); // Debounce
            }

            // Update PIDF coefficients from dashboard
            pidfController.setPIDF(kP, kI, kD, 0);
            pidfController.setTolerance(TOLERANCE);
//            feedforward = new SimpleMotorFeedforward(ks, kv, 0);

            CURRENTRPM = (outtake.getVelocity() / TICKS_PER_REV) * 60.0;

            if (running) {
//                double ffPower = feedforward.calculate(TARGET_RPM);
                double pidCorrection = pidfController.calculate(CURRENTRPM, TARGET_RPM);
                double power = Range.clip(pidCorrection, -1, 1);
                outtake.setPower(power);
                telemetry.addLine("=== RUNNING ===");
                telemetry.addData("Power", "%.3f", power);
                telemetry.addData("Current RPM", "%.0f", CURRENTRPM);
                telemetry.addData("PID", "%.3f", pidCorrection);
            } else {
                telemetry.addLine("=== STOPPED (Press A) ===");
            }
            double error = TARGET_RPM - CURRENTRPM;
            boolean atTarget = Math.abs(error) < TOLERANCE;

            telemetry.addLine();
            telemetry.addData("Target RPM", "%.0f", TARGET_RPM);
            telemetry.addData("Current RPM", "%.0f", CURRENTRPM);
            telemetry.addData("Error", "%.0f", error);
            telemetry.addData("At Target", atTarget ? "YES" : "NO");
            telemetry.addLine();

            telemetry.addLine("--- PIDF Coefficients ---");
            telemetry.addData("kP", kP);
            telemetry.addData("kI", kI);
            telemetry.addData("kD", kD);
//            telemetry.addData("ks", ks);
//            telemetry.addData("kv", kv);

            telemetry.update();
        }

        outtake.setPower(0);
    }
}