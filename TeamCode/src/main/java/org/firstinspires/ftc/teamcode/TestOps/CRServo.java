package org.firstinspires.ftc.teamcode.TestOps;










import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.Shooting;

@Disabled
@TeleOp
@Config
public class CRServo extends LinearOpMode {





    public static double platformPower = 1;






    FtcDashboard dashboard = FtcDashboard.getInstance();



    private com.qualcomm.robotcore.hardware.CRServo platformLeft;




    boolean lastUp = false;
    boolean lastDown = false;



    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        platformLeft = hardwareMap.get(com.qualcomm.robotcore.hardware.CRServo.class, "leftPlatform");






        waitForStart();
        // Put run blocks here














        if (isStopRequested()) return;
















        while (opModeIsActive() && !isStopRequested()) {
            // Put loop blocks here



            if (gamepad1.right_bumper) {
                platformLeft.setPower(0);
            }




            if (gamepad1.left_bumper) {
                platformLeft.setPower(0);

            }






            if (gamepad1.dpad_up & !lastUp) {

                platformLeft.setPower(platformLeft.getPower() + 0.1);
            }


            if (gamepad1.dpad_down & !lastDown) {
                platformLeft.setPower(platformLeft.getPower() - 0.1);
            }

            lastUp = gamepad1.dpad_up;

            lastDown = gamepad1.dpad_down;





            telemetry.addLine("dpad up: goes up 0.1");
            telemetry.addLine("dpad down: goes down 0.1");
            telemetry.addLine("right bumper: platform = 0");
            telemetry.addLine("left bumper: platform = 1");


            telemetry.addLine("triangle: platform = 1");
            telemetry.addLine("square: platform = 0.7");
            telemetry.addLine("cross: platform = 0.4");
            telemetry.addLine("circle: platform = 0.2");




            telemetry.addData("Platform Left", platformLeft.getPower());




            telemetry.update();


























        }
    }
}

