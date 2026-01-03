package org.firstinspires.ftc.teamcode.Utils.Subsystem;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.*;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class DriveTrain extends SubsystemBase {
    public static double ticks = 67; // how much encoder tick goes per inch
    public static double isBusyTolerance = 3; // change this later
    public static double inchesPerSecond; // find this out for later assuming 0.5 speed on all motors
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public IMU imu;


    public DriveTrain(HardwareMap hardwareMap) {


        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);


        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);


        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        runWithoutEncoders();

//        runWithEncoders();


    }

    public double botHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }


    public void resetIMU() {
        imu.resetYaw();
    }

    public boolean isBusy(double unit) {
        return frontLeftMotor.getCurrentPosition() > (unit*ticks-isBusyTolerance) ||
                backLeftMotor.getCurrentPosition() > (unit*ticks-isBusyTolerance) ||
                frontRightMotor.getCurrentPosition() > (unit*ticks-isBusyTolerance) ||
                backRightMotor.getCurrentPosition() > (unit*ticks-isBusyTolerance);
    }

    public void drivePower(double fl, double bl, double fr, double br){
        frontLeftMotor.setPower(fl);
        backLeftMotor.setPower(bl);
        frontRightMotor.setPower(fr);
        backRightMotor.setPower(br);
    }




    public void stopDrive() {
        drivePower(0,0,0,0);
    }

    public void turntime(double power) {
        drivePower(power,power,-power,-power);
    }

    public void turncctime(double power) {
        drivePower(-power,-power,power,power);
    }




    // Helper method to drive forward
    //assuming 1 unit in meepmeep is
    public void forwardtime(double power) {
        drivePower(power,power,power,power);
    }


    public void backwardtime(double power) {
        drivePower(-power,-power,-power,-power);
    }

    public void runWithoutEncoders(){
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runWithEncoders(){
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void resetEncoders(){
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void encoderRun(){
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


    public void encoderDrive(double power, double flunit, double blunit, double frunit, double brunit){
        resetEncoders();
        encoderTarget(flunit,blunit,frunit,brunit);
        encoderRun();
        drivePower(power,power,power,power);
    }

    public void forward(double power, double unit) {
        encoderDrive(power, unit,unit,unit,unit);
    }


    public void backward(double power, double unit) {
        encoderDrive(power, -unit,-unit,-unit,-unit);
    }



    public void turn(double power, double degrees) {
        encoderDrive(power, degrees,degrees,-degrees,-degrees);

    }


    public void turncc(double power, double degrees) {
        encoderDrive(power, -degrees,-degrees,degrees,degrees);
    }





}







