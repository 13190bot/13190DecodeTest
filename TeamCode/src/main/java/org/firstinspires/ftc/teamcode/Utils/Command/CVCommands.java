package org.firstinspires.ftc.teamcode.Utils.Command;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.Shooting;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.AprilTagCV;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;


public class CVCommands extends CommandBase{
    public final Shooting shooting;

    public ElapsedTime runtime;

    public CVCommands(Shooting shooting) {
        this.shooting = shooting;
        this.runtime = new ElapsedTime();
        addRequirements(shooting);
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
