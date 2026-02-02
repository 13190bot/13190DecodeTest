package org.firstinspires.ftc.teamcode.Utils.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.*;


public class outtake extends SubsystemBase {

    public DcMotorEx outtakeMotor;
    public Servo hoodServo;
    public Servo platformServo;
    public static double power = 1.0;

    public outtake(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeMotor");
        hoodServo = hardwareMap.get(Servo.class, "hoodServo");
        platformServo = hardwareMap.get(Servo.class, "platformServo");
        platformServo.setDirection(Servo.Direction.FORWARD);
        hoodServo.setDirection(Servo.Direction.FORWARD);
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stopOuttake(){
        hoodServo.setPosition(0);
        platformServo.setPosition(0);
    }

    public void shoot() {
        outtakeMotor.setPower(power);
    }

    public void off() {
        outtakeMotor.setPower(0);

        hoodServo.setPosition(0);
        platformServo.setPosition(0);
    }
//    public class Outtake implements Action {
//        private boolean initialized = false;
//ee
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if (!initialized) {
//                outtakeMotor.setPower(1);
//                initialized = true;
//            }
//
//
//            return true;
//        }
//    }
//    //eeee
//    public Action Outtake() {
//        return new outtake.Outtake();
//    }
//
}
