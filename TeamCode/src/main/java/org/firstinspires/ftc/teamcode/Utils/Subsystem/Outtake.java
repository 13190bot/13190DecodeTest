package org.firstinspires.ftc.teamcode.Utils.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;

public class Outtake extends SubsystemBase {

    private DcMotor outtakeMotor;
    public static double power = 1.0;

    public Outtake(HardwareMap hardwareMap) {
        outtakeMotor = hardwareMap.get(DcMotor.class, "outtakeMotor");
        outtakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void shoot() {
        outtakeMotor.setPower(power);
    }

    public void off() {
        outtakeMotor.setPower(0);
    }
}
