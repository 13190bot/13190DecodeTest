package org.firstinspires.ftc.teamcode.Utils.Command;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import com.arcrobotics.ftclib.command.CommandBase;

public class TeleOpCommand extends CommandBase {




    private final DriveTrain drive;
    private final Gamepad gamepad;

    public TeleOpCommand(DriveTrain drive, Gamepad gamepad) {
        this.drive = drive;
        this.gamepad = gamepad;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x * 1.1;
        double rx = -gamepad.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        drive.drivePower(((y + x + rx)/denominator),
                ((y - x + rx)/denominator),
                ((y - x - rx)/denominator),
                ((y + x - rx)/denominator));
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopDrive();
    }
}