package org.firstinspires.ftc.teamcode.TestOps;
















import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
















@TeleOp
public class TestPlatform extends LinearOpMode {


    Servo platformRight;
    Servo platformLeft;


    public static double platformPower = 1;


























    @Override
    public void runOpMode() throws InterruptedException {


        platformRight = hardwareMap.get(Servo.class, "platformRight");
        platformLeft = hardwareMap.get(Servo.class, "platformLeft");


        platformRight.setDirection(Servo.Direction.REVERSE);


        waitForStart();
        // Put run blocks here







        if (isStopRequested()) return;








        while (opModeIsActive() && !isStopRequested()) {
            // Put loop blocks here


            if (gamepad1.triangle) {
            platformLeft.setPosition(1);
            platformRight.setPosition(1);
            }

            if (gamepad1.square) {
            platformLeft.setPosition(0.7);
            platformRight.setPosition(0.7);
            }

            if (gamepad1.cross) {
            platformLeft.setPosition(0.4);
            platformRight.setPosition(0.4);
            }

            if (gamepad1.circle) {
            platformLeft.setPosition(0.2);
            platformRight.setPosition(0.2);
            }

            if (gamepad1.right_bumper) {
                platformLeft.setPosition(0);
                platformRight.setPosition(0);
            }


            if (gamepad1.left_bumper) {
                platformLeft.setPosition(platformPower);
                platformRight.setPosition(platformPower);
            }



            if (gamepad1.dpad_up) {
            platformLeft.setPosition(platformLeft.getPosition()+0.1);
            platformRight.setPosition(platformRight.getPosition()+0.1);
            }

            if (gamepad1.dpad_down) {
                platformLeft.setPosition(platformLeft.getPosition()-0.1);
                platformRight.setPosition(platformRight.getPosition()-0.1);
            }



            telemetry.addLine("dpad up: goes up 0.1");
            telemetry.addLine("dpad down: goes down 0.1");
            telemetry.addLine("right bumper: platform = 0");
            telemetry.addLine("left bumper: platform = 1");

            telemetry.addLine("triangle: platform = 1");
            telemetry.addLine("square: platform = 0.7");
            telemetry.addLine("cross: platform = 0.4");
            telemetry.addLine("circle: platform = 0.2");


            telemetry.addData("Platform Right", platformRight.getPosition());
            telemetry.addData("Platform Left", platformLeft.getPosition());


                telemetry.update();













        }
    }
}
