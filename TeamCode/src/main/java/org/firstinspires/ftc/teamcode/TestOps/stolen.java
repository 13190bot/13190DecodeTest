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

@TeleOp(name = "Outtake PIDF Tuner", group = "Test")
@Config
public class stolen extends LinearOpMode {
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double ks = 0.0;
    public static double kv = 0.0;
    public static double TARGET_RPM = 3000;
    public static double TICKS_PER_REV = 28.0;
    public static double TOLERANCE = 25.0;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        DcMotorEx outtake = (DcMotorEx) hardwareMap.dcMotor.get("outtake");
        outtake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtake.setDirection(DcMotorSimple.Direction.REVERSE);

        PIDFController pidf = new PIDFController(kP, kI, kD, 0);
        SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(ks, kv, 0);

        boolean running = false;

        telemetry.addLine("Outtake PIDF Tuner");
        telemetry.addLine("Press A to start/stop");
        telemetry.addLine("Adjust kP, kI, kD, ks, kv, TARGET_RPM in Dashboard");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double speedMultiplier = 1.0;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator * speedMultiplier;
            double backLeftPower = (y - x + rx) / denominator * speedMultiplier;
            double frontRightPower = (y - x - rx) / denominator * speedMultiplier;
            double backRightPower = (y + x - rx) / denominator * speedMultiplier;
            DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
            DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
            DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
            DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");
            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            // Toggle on A press
            if (gamepad1.a && !gamepad1.start) {
                running = !running;
                if (!running) {
                    outtake.setPower(0);
                    pidf.reset();
                }
                sleep(200); // Debounce
            }

            // Update PIDF coefficients from dashboard
            pidf.setPIDF(kP, kI, kD, 0);
            pidf.setTolerance(TOLERANCE);
            feedforward = new SimpleMotorFeedforward(ks, kv, 0);

            double currentRPM = (outtake.getVelocity() / TICKS_PER_REV) * 60.0;

            if (running) {
                double ffPower = feedforward.calculate(TARGET_RPM);
                double pidCorrection = pidf.calculate(currentRPM, TARGET_RPM);
                double power = Range.clip(ffPower + pidCorrection, -1, 1);
                outtake.setPower(power);

                telemetry.addLine("=== RUNNING ===");
                telemetry.addData("Power", "%.3f", power);
                telemetry.addData("FF", "%.3f", ffPower);
                telemetry.addData("PID", "%.3f", pidCorrection);
            } else {
                telemetry.addLine("=== STOPPED (Press A) ===");
            }

            double error = TARGET_RPM - currentRPM;
            boolean atTarget = Math.abs(error) < TOLERANCE;

            telemetry.addLine();
            telemetry.addData("Target RPM", "%.0f", TARGET_RPM);
            telemetry.addData("Current RPM", "%.0f", currentRPM);
            telemetry.addData("Error", "%.0f", error);
            telemetry.addData("At Target", atTarget ? "YES" : "NO");
            telemetry.addLine();

            telemetry.addLine("--- PIDF Coefficients ---");
            telemetry.addData("kP", kP);
            telemetry.addData("kI", kI);
            telemetry.addData("kD", kD);
            telemetry.addData("ks", ks);
            telemetry.addData("kv", kv);

            telemetry.update();
        }

        outtake.setPower(0);
    }
}
