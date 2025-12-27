package org.firstinspires.ftc.teamcode.Utils.Command;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;


public class DriveCommands extends CommandBase{
public final DriveTrain driveTrain;

public ElapsedTime runtime;

    public DriveCommands(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        this.runtime = new ElapsedTime();
        addRequirements(driveTrain);
    }


    // goes initialize -> execute -> isfinished, and then back to execute if isfinished = false

@Override
    public void initialize(){
//runs once

    }

@Override
    public void execute(){
//repeatedly runs if isfinished is false
    }

@Override
    public boolean isFinished() {
    //stops execute if true

    return runtime.time() > 3;
    }


}
