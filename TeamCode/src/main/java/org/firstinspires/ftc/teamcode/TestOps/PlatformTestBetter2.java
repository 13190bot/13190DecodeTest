package org.firstinspires.ftc.teamcode.TestOps;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class PlatformTestBetter2 extends LinearOpMode {

    Servo platformServo;

    public static double platformPower = 1;

    FtcDashboard dashboard = FtcDashboard.getInstance();

    double lowerRangeLeft = 0;
    double higherRangeLeft = 1;
    double lowerRangeRight = 0;
    double higherRangeRight = 1;

    boolean lastUpTwo = false;
    boolean lastDownTwo = false;
    boolean lastTriangle = false;
    boolean lastCross = false;
    boolean lastSquareTwo = false;
    boolean lastCircleTwo = false;
    boolean lastL1 = false;
    boolean lastR1 = false;

    boolean lastRight = false;
    boolean lastLeft = false;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        platformServo = hardwareMap.get(Servo.class, "platformServo");

        platformServo.setDirection(Servo.Direction.FORWARD);

        platformServo.scaleRange(lowerRangeLeft, higherRangeLeft);

        waitForStart();

        platformServo.setPosition(0);

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {

            // Range Adjustments
            if (gamepad2.dpad_up && !lastUpTwo) {
                lowerRangeLeft += 0.025; // Increase left lower
            }
            if (gamepad2.triangle && !lastTriangle) {
                lowerRangeRight += 0.025; // Increase right lower
            }
            if (gamepad2.cross && !lastCross) {
                lowerRangeLeft -= 0.025; // Decrease left lower
            }
            if (gamepad2.circle && !lastCircleTwo) {
                lowerRangeRight -= 0.025; // Decrease right lower
            }
            if (gamepad2.dpad_down && !lastDownTwo) {
                higherRangeLeft -= 0.025; // Decrease left higher
            }
            if (gamepad2.square && !lastSquareTwo) {
                higherRangeRight += 0.025; // Increase right higher
            }
            if (gamepad2.left_bumper && !lastL1) {
                higherRangeLeft += 0.025; // Increase left higher
            }
            if (gamepad2.right_bumper && !lastR1) {
                higherRangeRight -= 0.025; // Decrease right higher
            }

            // Save the last states
            lastUpTwo = gamepad2.dpad_up;
            lastDownTwo = gamepad2.dpad_down;
            lastTriangle = gamepad2.triangle;
            lastCross = gamepad2.cross;
            lastSquareTwo = gamepad2.square;
            lastCircleTwo = gamepad2.circle;
            lastL1 = gamepad2.left_bumper;
            lastR1 = gamepad2.right_bumper;

            // Apply the ranges
            platformServo.scaleRange(lowerRangeLeft, higherRangeLeft);


            if (gamepad2.dpad_left && !lastLeft) {
                if (platformServo.getDirection() == Servo.Direction.FORWARD) {
                    platformServo.setDirection(Servo.Direction.REVERSE);
                } else {
                    platformServo.setDirection(Servo.Direction.FORWARD);
                }
            }

            lastRight = gamepad2.dpad_right;
            lastLeft = gamepad2.dpad_left;


            if (gamepad1.right_bumper){
                platformServo.setPosition(1);

            }


            if (gamepad1.left_bumper){
                platformServo.setPosition(0);
            }


            // Telemetry
            telemetry.addData("Left Servo Range", "Lower: %.3f, Higher: %.3f", lowerRangeLeft, higherRangeLeft);
            telemetry.addData("Right Servo Range", "Lower: %.3f, Higher: %.3f", lowerRangeRight, higherRangeRight);
            telemetry.addData("Platform Left Position", platformServo.getPosition());
            telemetry.addData("Left Direction", platformServo.getDirection());
            telemetry.update();
        }
    }
}
