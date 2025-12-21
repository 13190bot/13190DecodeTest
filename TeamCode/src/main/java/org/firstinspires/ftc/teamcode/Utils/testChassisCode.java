package org.firstinspires.ftc.teamcode.Utils;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp
public class testChassisCode extends LinearOpMode {
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;








    @Override
    public void runOpMode() {
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        // Put initialization blocks here








        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);








        waitForStart();
        // Put run blocks here
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            // Input Reading
            double y = -gamepad1.left_stick_y; // Forward/Backward (Left Stick Y)








            // *** 1. CHANGE: Left Stick X for ROTATION (rx) ***
            double rx = gamepad1.left_stick_x; // Rotate Left/Right (Left Stick X)








            // *** 2. CHANGE: Right Stick X for STRAFE (x) ***
            double x = -gamepad1.right_stick_x * 1.1; // Strafe Left/Right (Right Stick X)








            // Denominator ensures powers stay between -1 and 1
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);








            // Apply the standard Mecanum equations
            double frontLeftMotorPower = (y + x + rx) / denominator; // + rx
            double backLeftMotorPower = (y - x + rx) / denominator;  // + rx
            double frontRightMotorPower = (y - x - rx) / denominator; // - rx
            double backRightMotorPower = (y + x - rx) / denominator; // - rx








            frontLeftMotor.setPower(frontLeftMotorPower);
            backLeftMotor.setPower(backLeftMotorPower);
            frontRightMotor.setPower(frontRightMotorPower);
            backRightMotor.setPower(backRightMotorPower);
        }
    }
}



















