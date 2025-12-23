package org.firstinspires.ftc.teamcode.Utils.Command;

import android.graphics.Color;
import android.hardware.Sensor;

import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.SensorColor;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class ColorSensorCommands extends CommandBase
{
    SensorColor sensorColor;


    public ColorSensorCommands(SensorColor sensorColor) {
        this.sensorColor = sensorColor;
        addRequirements(sensorColor);
    }



//    public void detect() {
//        try {
//            sensorColor.runSample(); // actually execute the sample
//        } finally {
//            // On the way out, *guarantee* that the background is reasonable. It doesn't actually start off
//            // as pure white, but it's too much work to dig out what actually was used, and this is good
//            // enough to at least make the screen reasonable again.
//            // Set the panel back to the default color
//            sensorColor.relativeLayout.post(new Runnable() {
//                public void run() {
//                    sensorColor.relativeLayout.setBackgroundColor(Color.WHITE);
//                }
//            });
//        }
//    }

}
