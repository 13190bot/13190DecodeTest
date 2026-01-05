package org.firstinspires.ftc.teamcode.TestOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



import org.firstinspires.ftc.teamcode.Utils.Subsystem.ColorSensor;


@TeleOp
public class ColorSensorTest extends LinearOpMode {

    private ColorSensor colorSensor;



    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = new ColorSensor(hardwareMap);





        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()) {


            colorSensor.detectColors();
            colorSensor.checkColor();



            telemetry.addData("H value", colorSensor.H_cv);
            telemetry.addData("S value", colorSensor.S_cv);
            telemetry.addData("V value", colorSensor.V_cv);

            telemetry.addData("ball visible?", colorSensor.ballVisible);

            if (colorSensor.ballVisible) {
                telemetry.addData("color", colorSensor.ballColor);
            }

            telemetry.update();




        }
    }
}




























