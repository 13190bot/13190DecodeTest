package org.firstinspires.ftc.teamcode.TestOps;
































import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
































@TeleOp
@Config
public class PlatformTestBetter extends LinearOpMode {




    Servo platformRight;
    Servo platformLeft;




    public static double platformPower = 1;








































    FtcDashboard dashboard = FtcDashboard.getInstance();


//RIGHT PLATFORM IS REVERSED
    //LEFT IS NORAML:

    public double lowerRange =0;
    public double higherRange =1;


    boolean lastSquare = false;
    boolean lastCircle = false;


    boolean lastUp = false;
    boolean lastDown = false;

    boolean lastRight = false;
    boolean lastLeft = false;


    boolean lastDownTwo = false;
    boolean lastUpTwo = false;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        platformRight = hardwareMap.get(Servo.class, "platformRight");
        platformLeft = hardwareMap.get(Servo.class, "platformLeft");




        platformRight.setDirection(Servo.Direction.REVERSE);
        platformLeft.setDirection(Servo.Direction.FORWARD);





        platformLeft.scaleRange(0,1);
        platformRight.scaleRange(0,1);



        waitForStart();
        // Put run blocks here









        platformLeft.setPosition(0);
        platformRight.setPosition(0);




        if (isStopRequested()) return;
















        while (opModeIsActive() && !isStopRequested()) {
            // Putloop blocks here




            if (gamepad2.triangle) {
                platformLeft.setPosition(1);
                platformRight.setPosition(1);
            }







            if (gamepad2.right_bumper) {
                platformLeft.setPosition(0);
                platformRight.setPosition(0);
            }




            if (gamepad2.left_bumper) {
                platformLeft.setPosition(platformPower);
                platformRight.setPosition(platformPower);
            }



            if (gamepad2.dpad_right & !lastRight){
                if (platformRight.getDirection() == Servo.Direction.FORWARD){
                    platformRight.setDirection(Servo.Direction.REVERSE);

                }else{
                    platformRight.setDirection(Servo.Direction.FORWARD);
                }



            }


            if (gamepad2.dpad_left & !lastLeft){
                if (platformLeft.getDirection() == Servo.Direction.FORWARD){
                    platformLeft.setDirection(Servo.Direction.REVERSE);

                }else{
                    platformLeft.setDirection(Servo.Direction.FORWARD);
                }
            }



            if (gamepad2.dpad_up & !lastUp) {
                platformLeft.setPosition(platformLeft.getPosition()+0.025);
                platformRight.setPosition(platformRight.getPosition()+0.025);
            }


            if (gamepad2.dpad_down & !lastDown) {
                platformLeft.setPosition(platformLeft.getPosition()-0.025);
                platformRight.setPosition(platformRight.getPosition()-0.025);
            }


            if (gamepad2.square & !lastSquare) {
                platformLeft.setPosition(platformLeft.getPosition()+0.1);
            }
            if (gamepad2.circle & !lastCircle) {
                platformRight.setPosition(platformRight.getPosition()+0.1);
            }



            lastUp = gamepad2.dpad_up;

            lastDown = gamepad2.dpad_down;


            lastRight = gamepad2.dpad_right;

            lastLeft = gamepad2.dpad_left;


            lastCircle = gamepad2.circle;

            lastSquare = gamepad2.square;

            telemetry.addData("Platform Right", platformRight.getPosition());
            telemetry.addData("Platform Left", platformLeft.getPosition());
            telemetry.addData("Platform Right Direction:", platformRight.getDirection());
            telemetry.addData("Platform Left Direction:", platformLeft.getDirection());





            if (gamepad2.dpad_up && !lastUpTwo){
                lowerRange += 0.025;
            }


            if (gamepad2.dpad_down && !lastDownTwo){
                higherRange -= 0.025;
            }


            lastUpTwo = gamepad2.dpad_up;

            lastDownTwo = gamepad2.dpad_down;


            platformLeft.scaleRange(lowerRange,higherRange);
            platformRight.scaleRange(lowerRange,higherRange);





            telemetry.addData("lowerRange", lowerRange);
            telemetry.addData("higherRange", higherRange);


            if (platformRight.getDirection() == Servo.Direction.REVERSE){
                telemetry.addLine("right reversed");


            }else if (platformLeft.getDirection() == Servo.Direction.REVERSE){
                telemetry.addLine("left reversed");


            }


            telemetry.addLine("dpad up: goes up 0.1");
            telemetry.addLine("dpad down: goes down 0.1");
            telemetry.addLine("dpad right: right reversed");
            telemetry.addLine("dpad left: left reversed");


            telemetry.addLine("right bumper: platform = 0");
            telemetry.addLine("left bumper: platform = 1");
            telemetry.addLine("square: left goes up 0.1");
            telemetry.addLine("circle: right goes up 0.1");



            telemetry.addLine("triangle: platform = 1");
            telemetry.addLine("square: platform = 0.7");
            telemetry.addLine("cross: platform = 0.4");
            telemetry.addLine("circle: platform = 0.2");









            telemetry.update();


























        }
    }
}

