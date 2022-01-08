package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class newdrivetest extends LinearOpMode
{
    public DcMotor FRONT_LEFT;
    public DcMotor FRONT_RIGHT;
    public DcMotor BACK_LEFT;
    public DcMotor BACK_RIGHT;

    public void runOpMode() {
        FRONT_LEFT = hardwareMap.get(DcMotor.class, "frontleft");
        FRONT_RIGHT = hardwareMap.get(DcMotor.class, "frontright");
        BACK_LEFT = hardwareMap.get(DcMotor.class, "backleft");
        BACK_RIGHT = hardwareMap.get(DcMotor.class, "backright");

        FRONT_LEFT.setDirection(DcMotorSimple.Direction.REVERSE);
        BACK_LEFT.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()){
            while (opModeIsActive()){
                if (gamepad1.left_stick_y > 0.02 || gamepad1.left_stick_y < -0.02) {
                    FRONT_LEFT.setPower(-gamepad1.left_stick_y);
                    FRONT_RIGHT.setPower(-gamepad1.left_stick_y);
                    BACK_LEFT.setPower(-gamepad1.left_stick_y);
                    BACK_RIGHT.setPower(-gamepad1.left_stick_y);
                }
                else if (gamepad1.right_stick_y > 0.02 || gamepad1.right_stick_y < -0.02) {
                    FRONT_LEFT.setPower(-gamepad1.left_stick_y);
                    FRONT_RIGHT.setPower(-gamepad1.left_stick_y);
                    BACK_LEFT.setPower(gamepad1.left_stick_y);
                    BACK_RIGHT.setPower(gamepad1.left_stick_y);
                }
                else if (gamepad1.right_stick_x > 0.02) {
                    FRONT_LEFT.setPower(-gamepad1.left_stick_y);
                    FRONT_RIGHT.setPower(0);
                    BACK_LEFT.setPower(-gamepad1.left_stick_y);
                    BACK_RIGHT.setPower(0);
                }
                else if (gamepad1.right_stick_x < -0.02) {
                    FRONT_LEFT.setPower(0);
                    FRONT_RIGHT.setPower(-gamepad1.left_stick_y);
                    BACK_LEFT.setPower(0);
                    BACK_RIGHT.setPower(-gamepad1.left_stick_y);
                }
                else {
                    FRONT_LEFT.setPower(0);
                    FRONT_RIGHT.setPower(0);
                    BACK_LEFT.setPower(0);
                    BACK_RIGHT.setPower(0);
                }
            }
        }
    }
}
