package org.firstinspires.ftc.teamcode.Subsystem;
import com.qualcomm.robotcore.hardware.*;






public class Shooting {



    DcMotor intakeMotor;
    DcMotor outtakeMotor;
    Servo platform;



    public Shooting(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");
        platform = hardwareMap.get(Servo.class, "platform");



    }







}
