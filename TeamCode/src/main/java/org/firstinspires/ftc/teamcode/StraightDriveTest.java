package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "StraightDriveTest", group = "")
public class StraightDriveTest extends LinearOpMode {

    public DcMotor FRONT_LEFT;
    public DcMotor FRONT_RIGHT;
    public DcMotor BACK_LEFT;
    public DcMotor BACK_RIGHT;

    public void runOpMode(){

        // Attach hardware variables to software variables

        FRONT_LEFT = hardwareMap.get(DcMotor.class, "frontleft");
        FRONT_RIGHT = hardwareMap.get(DcMotor.class, "frontright");
        BACK_LEFT = hardwareMap.get(DcMotor.class, "backleft");
        BACK_RIGHT = hardwareMap.get(DcMotor.class, "backright");

        // Set motor direction

        FRONT_LEFT.setDirection(DcMotorSimple.Direction.REVERSE);
        BACK_LEFT.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if(opModeIsActive()){

            setAll(0.5);
            sleep(1500);
            setAll(0);
        }

    }

    public void setAll(double power){
        FRONT_LEFT.setPower(power);
        FRONT_RIGHT.setPower(power);
        BACK_LEFT.setPower(power);
        BACK_RIGHT.setPower(power);
    }
}
