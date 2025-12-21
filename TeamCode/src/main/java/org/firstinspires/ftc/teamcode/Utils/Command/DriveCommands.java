package org.firstinspires.ftc.teamcode.Utils.Command;
import org.firstinspires.ftc.teamcode.Utils.Subsystem.DriveTrain;
import com.arcrobotics.ftclib.command.CommandBase;


public class DriveCommands extends CommandBase{
private final DriveTrain driveTrain;

    public DriveCommands(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }


//    // Helper to stop all drive motors
//    public void stopDrive() {
//        frontLeftMotor.setPower(0);
//        backLeftMotor.setPower(0);
//        frontRightMotor.setPower(0);
//        backRightMotor.setPower(0);
//
//
//    }
//
//
//
//    // Helper method to turn clockwise
//    //assuming it takes 4 seconds for 360 degrees
//    public void turntime(int degrees) {
//        frontLeftMotor.setPower(0.5);
//        backLeftMotor.setPower(0.5);
//        frontRightMotor.setPower(-0.5);
//        backRightMotor.setPower(-0.5);
//        stopDrive();
//    }
//
//
//    // Helper method to turn counterclockwise
//    //assuming it takes 4 seconds for 360 degrees
//    public void turncctime(int degrees) {
//        frontLeftMotor.setPower(-0.5);
//        backLeftMotor.setPower(-0.5);
//        frontRightMotor.setPower(0.5);
//        backRightMotor.setPower(0.5);
//        stopDrive();
//    }
//
//
//
//
//    // Helper method to drive forward
//    //assuming 1 unit in meepmeep is
//    private void forwardtime(int unit) {
//        frontLeftMotor.setPower(0.5);
//        backLeftMotor.setPower(0.5);
//        frontRightMotor.setPower(0.5);
//        backRightMotor.setPower(0.5);
//        stopDrive();
//    }
//
//
//    private void backwardtime(int unit) {
//        frontLeftMotor.setPower(-0.5);
//        backLeftMotor.setPower(-0.5);
//        frontRightMotor.setPower(-0.5);
//        backRightMotor.setPower(-0.5);
//        stopDrive();
//    }
//
//
//
//
//
//
//    // ENCODERS
//    private void forward(int unit) {
//        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//
//        int target = unit * ticks;
//
//        frontLeftMotor.setTargetPosition(target);
//        frontRightMotor.setTargetPosition(target);
//        backLeftMotor.setTargetPosition(target);
//        backRightMotor.setTargetPosition(target);
//
//        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeftMotor.setPower(0.5);
//        frontRightMotor.setPower(0.5);
//        backLeftMotor.setPower(0.5);
//        backRightMotor.setPower(0.5);
//
//
//        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
//                backLeftMotor.isBusy() || backRightMotor.isBusy()) {
////            idle();
//        }
//
//
//        stopDrive();
//    }
//
//
//    private void backward(int unit) {
//        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//
//        int target = unit * ticks;
//
//        frontLeftMotor.setTargetPosition(-target);
//        frontRightMotor.setTargetPosition(-target);
//        backLeftMotor.setTargetPosition(-target);
//        backRightMotor.setTargetPosition(-target);
//
//        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeftMotor.setPower(0.5);
//        frontRightMotor.setPower(0.5);
//        backLeftMotor.setPower(0.5);
//        backRightMotor.setPower(0.5);
//
//
//        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
//                backLeftMotor.isBusy() || backRightMotor.isBusy()) {
////            idle();
//        }
//
//
//        stopDrive();
//    }
//
//
//
//
//
//
//
//    private void turn(int degrees) {
//        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//
//        int target = degrees * ticks;
//
//        frontLeftMotor.setTargetPosition(target);
//        frontRightMotor.setTargetPosition(-target);
//        backLeftMotor.setTargetPosition(target);
//        backRightMotor.setTargetPosition(-target);
//
//        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeftMotor.setPower(0.5);
//        frontRightMotor.setPower(0.5);
//        backLeftMotor.setPower(0.5);
//        backRightMotor.setPower(0.5);
//
//
//        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
//                backLeftMotor.isBusy() || backRightMotor.isBusy()) {
////            idle();
//        }
//
//
//        stopDrive();
//    }
//
//
//    private void turncc(int degrees) {
//        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//
//        int target = degrees * ticks;
//
//        frontLeftMotor.setTargetPosition(-target);
//        frontRightMotor.setTargetPosition(target);
//        backLeftMotor.setTargetPosition(-target);
//        backRightMotor.setTargetPosition(target);
//
//        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeftMotor.setPower(0.5);
//        frontRightMotor.setPower(0.5);
//        backLeftMotor.setPower(0.5);
//        backRightMotor.setPower(0.5);
//
//
//        while (frontLeftMotor.isBusy() || frontRightMotor.isBusy() ||
//                backLeftMotor.isBusy() || backRightMotor.isBusy()) {
//
////            idle();
//
//        }
//
//        stopDrive();
//    }
//
//
//    private void stopDrive() {
//        frontLeftMotor.setPower(0);
//        frontRightMotor.setPower(0);
//        backLeftMotor.setPower(0);
//        backRightMotor.setPower(0);
//
//        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//
////        if (opModeIsActive() && runtime.time()<29){
////            requestOpModeStop();
////        }
//
//    }




}
