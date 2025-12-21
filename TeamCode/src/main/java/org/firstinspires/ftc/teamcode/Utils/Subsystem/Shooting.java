package org.firstinspires.ftc.teamcode.Utils.Subsystem;
import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.command.SubsystemBase;






public class Shooting extends SubsystemBase {



    DcMotor intakeMotor;
    DcMotor outtakeMotor;
    Servo platformRight;
    Servo platformLeft;



    public Shooting(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");
        platformRight = hardwareMap.get(Servo.class, "platformRight");
        platformLeft = hardwareMap.get(Servo.class, "platformLeft");
    }







}
