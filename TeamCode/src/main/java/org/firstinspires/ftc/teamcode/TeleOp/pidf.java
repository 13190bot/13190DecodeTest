package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
public class pidf extends OpMode {

//e


    @TeleOp(name = "outtakeMotor PIDF Test", group = "Test")


        // Hardware
        private DcMotorEx outtakeMotor;

        // Encoder info (CHANGE THIS)
        private static final double TICKS_PER_REV = 28.0; // GoBILDA 5202/5203 motor

        // PIDF constants (TUNE THESE)
        private static final double kP = 0.0015;
        private static final double kI = 0.0;
        private static final double kD = 0.00005;
        private static final double kF = 0.00017;

        // Target speed
        private double targetRPM = 4200;

        // Controller
        private PIDFController pidf;

        @Override
        public void init() {

            outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeMotor");

            outtakeMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            outtakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

            pidf = new PIDFController(kP, kI, kD, kF);
            pidf.setTolerance(50); // RPM tolerance
        }

        @Override
        public void loop() {

            // Enable / disable outtakeMotor
            if (gamepad1.a) {
                targetRPM = 4200;
            } else if (gamepad1.b) {
                targetRPM = 0;
            }

            // Get current RPM
            double currentRPM =
                    outtakeMotor.getVelocity() * 60.0 / TICKS_PER_REV;

            // PIDF calculation
            double power = pidf.calculate(currentRPM, targetRPM);

            // Clamp power
            power = Range.clip(power, 0.0, 1.0);

            // Apply power
            outtakeMotor.setPower(power);

            // Telemetry
            telemetry.addData("Target RPM", targetRPM);
            telemetry.addData("Current RPM", currentRPM);
            telemetry.addData("Motor Power", power);
            telemetry.addData("At Speed", pidf.atSetPoint());
            telemetry.update();

    }

}
