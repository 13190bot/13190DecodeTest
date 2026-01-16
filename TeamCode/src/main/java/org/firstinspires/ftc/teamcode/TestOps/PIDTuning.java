package org.firstinspires.ftc.teamcode.TestOps;




import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.Shooting;


@TeleOp
@Config
public class PIDTuning extends LinearOpMode {













    FtcDashboard dashboard = FtcDashboard.getInstance();


    public Shooting shooting;





    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());


        shooting = new Shooting(hardwareMap);



        shooting.updatePID();

        waitForStart();


        if (isStopRequested()) return;



        while (opModeIsActive() && !isStopRequested()) {




            shooting.updatePID();
            shooting.updateCoeff();




























            shooting.setTargetVelocity(1);

            if (gamepad1.circle) {
                shooting.setTargetVelocity(1000);
            }

            if (gamepad1.triangle) {
                shooting.setTargetVelocity(2000);
            }
            if (gamepad1.square) {
                shooting.setTargetVelocity(3000);
            }
            if (gamepad1.cross) {
                shooting.setTargetVelocity(4000);
            }
            if (gamepad1.right_bumper) {
                shooting.setTargetVelocity(0);
            }
            if (gamepad1.left_bumper) {
                shooting.setTargetVelocity(500);
            }


            if (gamepad1.dpad_up){
                shooting.setTargetVelocity(shooting.targetVelocity + 1000);
            }

            if (gamepad1.dpad_down){
                shooting.setTargetVelocity(shooting.targetVelocity - 1000);
            }











            telemetry.addLine("right bumper: 0");
            telemetry.addLine("left bumper: 500");
            telemetry.addData("P: ", Shooting.kP);
            telemetry.addData("I: ", Shooting.kI);
            telemetry.addData("D: ", Shooting.kD);
            telemetry.addData("setpoint: ", Shooting.setPoint);
//e

            telemetry.addLine("triangle: 2k");
            telemetry.addLine("square: 3k");
            telemetry.addLine("cross: 4k");
            telemetry.addLine("circle: 1k");









            telemetry.update();


























        }
    }
}

