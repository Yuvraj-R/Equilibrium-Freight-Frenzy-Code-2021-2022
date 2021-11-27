package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.FtcRobotControllerService;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "")
public class TeleOp extends LinearOpMode {

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

            while(opModeIsActive()){

                // LOOP CODE HERE

                // Gamepad 1
                FRONT_LEFT.setPower(-gamepad1.left_stick_y);
                BACK_LEFT.setPower(-gamepad1.left_stick_y);

                FRONT_RIGHT.setPower(-gamepad1.right_stick_y);
                BACK_RIGHT.setPower(-gamepad1.right_stick_y);
            }
        }

    }
}
