package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.*;

@TeleOp
public class SigmaTeleOp2pBetter2 extends LinearOpMode {

    private DriveTrain drive;
    private Shooting shooting;

    private outtake OuttakeSubsystem;

    private boolean platformOn = false;
    private boolean intakeOn = false;
    private boolean outtakeOn = false;

    private boolean rumble = false;
    private static double MAX_TICKS = 2500;

    private ElapsedTime rumbleTime = new ElapsedTime();

    private boolean leftTriggerPressed;
    private boolean rightTriggerPressed;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new DriveTrain(hardwareMap);
        shooting = new Shooting(hardwareMap);

        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        waitForStart();
        if (isStopRequested()) return;

        rumbleTime.reset();

        while (opModeIsActive() && !isStopRequested()) {

            previousGamepad2.copy(currentGamepad2);
            currentGamepad2.copy(gamepad2);

            double y = gamepad1.left_stick_y;
            double rx = -gamepad1.right_stick_x * 1.1;
            double x = -gamepad1.left_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            drive.frontLeftMotor.setPower((y + x + rx) / denominator);
            drive.backLeftMotor.setPower((y - x + rx) / denominator);
            drive.frontRightMotor.setPower((y - x - rx) / denominator);
            drive.backRightMotor.setPower((y + x - rx) / denominator);

            leftTriggerPressed = currentGamepad2.left_trigger > 0.7 && !(previousGamepad2.left_trigger > 0.7);

            rightTriggerPressed = currentGamepad2.right_trigger > 0.7 && !(previousGamepad2.right_trigger > 0.7);

            if (leftTriggerPressed) {
                intakeOn = !intakeOn;
            }

            if (rightTriggerPressed) {
                outtakeOn = !outtakeOn;
            }

            shooting.intakeMotor.setPower(intakeOn ? 1 : 0);
            shooting.outtakeMotor.setPower(outtakeOn ? -1 : 0);

            if (currentGamepad2.cross && !previousGamepad2.cross) {
                platformOn = !platformOn;
            }

            if (platformOn) {
                shooting.platformServo.setPosition(1);
            } else {
                shooting.platformServo.setPosition(0);
            }

            if (Math.abs(shooting.outtakeMotor.getVelocity() - shooting.outtakeMotor.getPower() * MAX_TICKS) < Shooting.outtakeTolerance
                    && rumbleTime.seconds() > 1
                    && shooting.outtakeMotor.getPower() > 0.3) {

                rumble = true;
                rumbleTime.reset();
            }

            if (rumble) {
                rumble = false;
                gamepad2.rumble(500);
                gamepad2.setLedColor(29, 67, 107, 1000);
            }

            telemetry.addLine("Left Trigger: Intake @ 0.7");
            telemetry.addLine("Right Trigger: Outtake @ 0.8");
            telemetry.addLine("Platform: X");

            telemetry.addData("Intake", intakeOn ? "ON" : "OFF");
            telemetry.addData("Outtake", outtakeOn ? "ON" : "OFF");
            telemetry.addData("Platform", platformOn ? "ON" : "OFF");
            telemetry.addData("Platform pos", shooting.platformServo.getPosition());

            telemetry.addData("Outtake Power", shooting.outtakeMotor.getPower());
            telemetry.addData("Outtake Velocity", shooting.outtakeMotor.getVelocity());

            telemetry.update();
        }
    }
}
