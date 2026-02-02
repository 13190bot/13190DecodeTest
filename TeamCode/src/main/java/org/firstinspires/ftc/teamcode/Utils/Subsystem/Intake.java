package org.firstinspires.ftc.teamcode.Utils.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;

public class Intake extends SubsystemBase {

    public DcMotor intakeMotor;
    public static double power = 0.8;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void IntakeOn() {
        intakeMotor.setPower(power);
    }

    public void IntakeOff() {
        intakeMotor.setPower(0);
    }
}
