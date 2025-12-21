package org.firstinspires.ftc.teamcode.Utils.Subsystem;
import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.command.SubsystemBase;


public class DriveTrain extends SubsystemBase {
    public final int ticks = 67;
    public final int stoptime = 1000;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;



    public DriveTrain(HardwareMap hardwareMap) {


        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }



    public boolean Busy() {
        return frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy();
    }

    public void DrivePower(double fl, double bl, double fr, double br){
        frontLeftMotor.setPower(fl);
        backLeftMotor.setPower(bl);
        frontRightMotor.setPower(fr);
        backRightMotor.setPower(br);
    }




    public void stopDrive() {
        DrivePower(0,0,0,0);
    }

    public void turntime(int degrees) {
        DrivePower(0.5,0.5,-0.5,-0.5);
    }

    public void turncctime(int degrees) {
        DrivePower(-0.5,-0.5,0.5,0.5);
    }




    // Helper method to drive forward
    //assuming 1 unit in meepmeep is
    public void forwardtime(int unit) {
        DrivePower(0.5,0.5,0.5,0.5);
    }


    public void backwardtime(int unit) {
        DrivePower(-0.5,-0.5,-0.5,-0.5);
    }

    public void resetEncoders(){
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void EncoderRun(){
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void encoderTarget(double fl, double bl, double fr, double br){
        int fltarget = (int) (fl * ticks);
        int bltarget = (int) (bl * ticks);
        int frtarget = (int) (fr * ticks);
        int brtarget = (int) (br * ticks);

        frontLeftMotor.setTargetPosition(fltarget);
        backLeftMotor.setTargetPosition(bltarget);
        frontRightMotor.setTargetPosition(frtarget);
        backRightMotor.setTargetPosition(brtarget);
    }


    public void encoderDrive(double flunit, double blunit, double frunit, double brunit){
        resetEncoders();
        encoderTarget(flunit,blunit,frunit,brunit);
        EncoderRun();
        DrivePower(0.5,0.5,0.5,0.5);
    }

    public void forward(double unit) {
        encoderDrive(unit,unit,unit,unit);
    }


    public void backward(double unit) {
        encoderDrive(-unit,-unit,-unit,-unit);
    }



    public void turn(int degrees) {
        encoderDrive(degrees,degrees,-degrees,-degrees);

    }


    public void turncc(int degrees) {
        encoderDrive(-degrees,-degrees,degrees,degrees);
    }





}







