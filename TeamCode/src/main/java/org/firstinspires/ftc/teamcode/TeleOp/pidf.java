import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="FlywheelPIDF")
public class pidf extends LinearOpMode {

    private DcMotorEx flywheel;
    private PIDFController pidfController;

    // Tuned PIDF constants for your flywheel e
    private static final double kP = 0.001;
    private static final double kI = 0.0001;
    private static final double kD = 0.00001;
    private static final double kF = 0.0002;

    // Target velocity (encoder ticks per second)
    private double targetVelocity = 1500;

    @Override
    public void runOpMode() {

        flywheel = hardwareMap.get(DcMotorEx.class, "flywheelMotor");

        // Set motor to run using encoder for velocity feedback
        flywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        // Create PIDFController with flywheel-specific gains
        pidfController = new PIDFController(new double[]{kP, kI, kD, kF});

        waitForStart();

        while (opModeIsActive()) {

            // Current velocity reading from encoder
            double currentVelocity = flywheel.getVelocity();

            // Calculate the new power using PIDF controller
            double powerOutput = pidfController.calculate(currentVelocity, targetVelocity);

            // Optional: clamp power to safe range
            powerOutput = Math.max(-1.0, Math.min(1.0, powerOutput));

            // Apply the calculated power to the flywheel
            flywheel.setPower(powerOutput);

            telemetry.addData("Target Vel", targetVelocity);
            telemetry.addData("Current Vel", currentVelocity);
            telemetry.addData("Power", powerOutput);
            telemetry.update();
        }
    }
}
