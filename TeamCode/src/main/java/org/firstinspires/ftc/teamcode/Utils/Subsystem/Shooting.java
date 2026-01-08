package org.firstinspires.ftc.teamcode.Utils.Subsystem;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.command.SubsystemBase;






public class Shooting extends SubsystemBase {



    public DcMotor intakeMotor;
    public DcMotorEx outtakeMotor;
    public Servo platformRight;
    public Servo platformLeft;

    public PIDController pidController;
    public static double kP = 0.0;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kS = 0.0;
    public static double kV = 0.0;
    public static double targetVelocity = 1500;
    public static double tolerance = 10;

    public Shooting(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeMotor = hardwareMap.get(DcMotorEx.class, "outtakeMotor");
        platformRight = hardwareMap.get(Servo.class, "platformRight");
        platformLeft = hardwareMap.get(Servo.class, "platformLeft");
        platformRight.setDirection(Servo.Direction.REVERSE);
    }
    public void stopShooting(){
        intakeMotor.setPower(0);
        outtakeMotor.setPower(0);
        platformLeft.setPosition(0);
        platformRight.setPosition(0);
    }
}
