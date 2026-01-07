package org.firstinspires.ftc.teamcode.Utils.Subsystem;

import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Utils.Pattern;

public class ColorSensor extends SubsystemBase{


    public Pattern.ballColor ballColor;
    public boolean ballVisible;
    public NormalizedColorSensor colorSensor;
    public View relativeLayout;
    final float[] hsvValues = new float[3];

    public float H_cv;
    public float S_cv;
    public float V_cv;

    public ColorSensor (HardwareMap hardwareMap){
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorSensor");

        //might have to tune
        //higher = more sensitive to color = higher values
        colorSensor.setGain(1);


    }

    public void lightOn() {
        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight) colorSensor).enableLight(true);
        }
    }


    //can only detect 1 ball at a time
    //use both detectcolors and checkcolor
    public void detectColors() {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();


        Color.colorToHSV(colors.toColor(), hsvValues);

//multiplied it cuz it's different for CV and i just used aarav's values

        H_cv = hsvValues[0] / 2.0f;
        S_cv = hsvValues[1] * 255.0f;
        V_cv = hsvValues[2] * 255.0f;
    }

    public void checkColor(){

        if (H_cv >130 && H_cv <160 && S_cv > 80 && V_cv > 80){
            ballColor = Pattern.ballColor.PURPLE;
            ballVisible = true;
        }else if (H_cv >40 && H_cv <80 && S_cv > 80 && V_cv > 80){
            ballColor = Pattern.ballColor.GREEN;
            ballVisible = true;
        }else{
            ballVisible = false;
        }

    }




}
