package org.firstinspires.ftc.teamcode.TestOps;
































import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
































@TeleOp
@Config
public class PlatformTest extends LinearOpMode {




    Servo platformRight;
    Servo platformLeft;




    public static double platformPower = 1;








































    FtcDashboard dashboard = FtcDashboard.getInstance();








    boolean lastUp = false;
    boolean lastDown = false;

    boolean lastRight = false;
    boolean lastLeft = false;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
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



            if (gamepad1.dpad_right & !lastRight){
                if (platformRight.getDirection() == Servo.Direction.FORWARD){
                    platformRight.setDirection(Servo.Direction.REVERSE);

                }else{
                    platformRight.setDirection(Servo.Direction.FORWARD);
                }



            }


            if (gamepad1.dpad_left & !lastLeft){
                if (platformLeft.getDirection() == Servo.Direction.FORWARD){
                    platformLeft.setDirection(Servo.Direction.REVERSE);

                }else{
                    platformLeft.setDirection(Servo.Direction.FORWARD);
                }
            }



            if (gamepad1.dpad_up & !lastUp) {
                platformLeft.setPosition(platformLeft.getPosition()+0.1);
                platformRight.setPosition(platformRight.getPosition()+0.1);
            }


            if (gamepad1.dpad_down & !lastDown) {
                platformLeft.setPosition(platformLeft.getPosition()-0.1);
                platformRight.setPosition(platformRight.getPosition()-0.1);
            }

            lastUp = gamepad1.dpad_up;

            lastDown = gamepad1.dpad_down;


            lastRight = gamepad1.dpad_right;

            lastLeft = gamepad1.dpad_left;

            telemetry.addData("Platform Right", platformRight.getPosition());
            telemetry.addData("Platform Left", platformLeft.getPosition());
            telemetry.addData("Platform Right Direction:", platformRight.getDirection());
            telemetry.addData("Platform Left Direction:", platformLeft.getDirection());



            telemetry.addLine("dpad up: goes up 0.1");
            telemetry.addLine("dpad down: goes down 0.1");
            telemetry.addLine("right bumper: platform = 0");
            telemetry.addLine("left bumper: platform = 1");


            telemetry.addLine("triangle: platform = 1");
            telemetry.addLine("square: platform = 0.7");
            telemetry.addLine("cross: platform = 0.4");
            telemetry.addLine("circle: platform = 0.2");









            telemetry.update();


























        }
    }
}

