package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.RobotMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class ExpCraterDepotCrater extends LinearOpMode { //I might be able to put this class in another file, idk but that'd be great
    private ElapsedTime runtime = new ElapsedTime(); //Also note, I may still need to add the WhileOpModeIsActive to every loop
    RobotMap robot = new RobotMap();
    double lidPos = 1;

    
    public void downLift(double time) {
        runtime.reset();
        robot.lift.setPower(-1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Retracting Body!",runtime);
            telemetry.update();
            if(!robot.touch.getState()) {
                break;
            }
        }
        robot.lift.setPower(0);
    }

    public void upLift(double time) {
        runtime.reset();
        robot.lift.setPower(1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Extending Body!",runtime);
            telemetry.update();
            }
        robot.lift.setPower(0);
    }    

    public void hookOut(double time) {
        runtime.reset();
        robot.hook.setPower(-0.4);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Rotating Hook Out!", runtime);
            telemetry.update();
        }
        robot.hook.setPower(0);
    }
    
    public void hookIn(double time) {
        runtime.reset();
        robot.hook.setPower(0.4);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Rotating Hook In!", runtime);
            telemetry.update();
        }
        robot.hook.setPower(0);
    }

    public void leftForward(double time) {
        runtime.reset();
        robot.rightDrive.setPower(1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Moving Right Tread Forward!", runtime);
            telemetry.update();
        }
        robot.rightDrive.setPower(0);
    }

    public void leftBackward(double time) {
        runtime.reset();
        robot.rightDrive.setPower(-1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Moving Right Tread Backward!", runtime);
            telemetry.update();
        }
        robot.rightDrive.setPower(0);
    }

    public void rightForward(double time) {
        runtime.reset();
        robot.leftDrive.setPower(1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Moving Left Tread Forward!", runtime);
            telemetry.update();
        }
        robot.leftDrive.setPower(0);
    }

    public void rightBackward(double time) {
        runtime.reset();
        robot.leftDrive.setPower(-1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Moving Left Tread Backward!", runtime);
            telemetry.update();
        }
        robot.leftDrive.setPower(0);
    }

    public void armForward(double time) {
        runtime.reset();
        robot.arm.setPower(0.5); //Note I don't know which way is positive or negative
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Arm Forward!", runtime);
            telemetry.update();
        }
        robot.arm.setPower(0);
    }

    public void armBackward(double time) {
        runtime.reset();
        robot.arm.setPower(-0.5); //Note I don't know which way is positive or negative
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Arm Backward!", runtime);
            telemetry.update();
        }
        robot.arm.setPower(0);
    }

    public void rotateRight(double time) {
        runtime.reset();
        robot.rightDrive.setPower(1);
        robot.leftDrive.setPower(-1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Rotating Left!", runtime);
            telemetry.update();
        }    
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }

    public void rotateLeft(double time) {
        runtime.reset();
        robot.rightDrive.setPower(-1);
        robot.leftDrive.setPower(1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Rotating Right!", runtime);
            telemetry.update();
        }    
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }

    public void goForward(double time) {
        runtime.reset();
        robot.leftDrive.setPower(1);
        robot.rightDrive.setPower(1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Driving Forward!", runtime);
            telemetry.update();
        }    
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }

    public void goBackward(double time) {
        runtime.reset();
        robot.leftDrive.setPower(-1);
        robot.rightDrive.setPower(-1);
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Driving Backward!", runtime);
            telemetry.update();
        }    
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
    }

    public void pause(double time) {
        runtime.reset();
        while(opModeIsActive() && runtime.seconds() < time) {
            telemetry.addData("Waiting!", runtime);
            telemetry.update();
        }
    }

    public void closeLid() {
        lidPos = 1;
        robot.lid.setPosition(lidPos); //closes
    }

    public void openLid() {
        lidPos = 0;
        robot.lid.setPosition(lidPos); //opens
    }


    public void runOpMode() {
        waitForStart();
        robot.init(hardwareMap);
        lidPos = 1;  //sets lid position to close
        robot.lid.setPosition(lidPos);
        
        
        upLift(11); //Lower from lander
        hookOut(0.1);
        pause(0.1);
        downLift(5.5);
        leftForward(0.4);
        
        leftBackward(0.25); //Jerk
        leftForward(0.25);
        leftBackward(0.25);
        pause(0.2);

        rotateLeft (0.9); //Go to depot
        goForward(1.5);
        
        armForward(0.9);
        openLid();
        
        pause(0.7);
        
        armBackward(0.1);
        closeLid();
        armBackward(0.4);
        
        
        rotateLeft(1.25);
        goForward(1.5); //theoretically in the opposing crater
        armBackward(0.5);
        goForward(1.5);
        armBackward(0.5);
        goForward(1.25);

        
        


    }
}

