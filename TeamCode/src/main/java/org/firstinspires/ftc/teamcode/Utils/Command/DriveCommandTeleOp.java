package org.firstinspires.ftc.teamcode.Utils.Command;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import com.arcrobotics.ftclib.command.CommandBase;

public class DriveCommandTeleOp extends CommandBase{
    public final DriveTrain driveTrain;

    public DriveCommandTeleOp(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void execute(){

    }

    @Override
    public boolean isFinished() {
        //stops execute if true
        return false;
    }


}
