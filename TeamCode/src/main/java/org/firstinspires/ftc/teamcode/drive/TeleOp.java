package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.ftccommon.FtcRobotControllerService;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "")
public class TeleOp extends LinearOpMode {

    public DcMotor FRONT_LEFT;
    public DcMotor FRONT_RIGHT;
    public DcMotor BACK_LEFT;
    public DcMotor BACK_RIGHT;

    public DcMotor lift;

    public DcMotor intakeMotor;

    // Servos
    public Servo dropdown;

    public CRServo lift_extender;
    public CRServo lift_open;

    public CRServo rightExtend;
    public CRServo rightSpinner;
    public CRServo leftExtend;
    public CRServo leftSpinner;

    public Servo cone;

    // Other
    public boolean isRightExtendOpen = false;
    public boolean isDropExtended = false;

    public void runOpMode(){

        // Attach hardware variables to software variables

        FRONT_LEFT = hardwareMap.get(DcMotor.class, "frontleft");
        FRONT_RIGHT = hardwareMap.get(DcMotor.class, "frontright");
        BACK_LEFT = hardwareMap.get(DcMotor.class, "backleft");
        BACK_RIGHT = hardwareMap.get(DcMotor.class, "backright");

        lift = hardwareMap.get(DcMotor.class, "slides");

        intakeMotor = hardwareMap.get(DcMotor.class, "intakemotor");

        dropdown = hardwareMap.get(Servo.class, "intakeramp");

        lift_extender = hardwareMap.get(CRServo.class, "bucketextend");
        lift_open = hardwareMap.get(CRServo.class, "bucketdrop");

        rightExtend = hardwareMap.get(CRServo.class, "rightextend");
        rightSpinner = hardwareMap.get(CRServo.class, "rightspinner");
        leftExtend = hardwareMap.get(CRServo.class, "leftextend");
        leftSpinner = hardwareMap.get(CRServo.class, "leftspinner");

        cone = hardwareMap.get(Servo.class, "cone");


        // Set motor direction

        FRONT_LEFT.setDirection(DcMotorSimple.Direction.REVERSE);
        BACK_LEFT.setDirection(DcMotorSimple.Direction.REVERSE);
        leftSpinner.setDirection(DcMotorSimple.Direction.REVERSE);

        // Lift encoder

        waitForStart();

        if(opModeIsActive()){

            while(opModeIsActive()){
                telemetry.addData("Is Intake Extended?", isDropExtended);
                telemetry.update();

                // LOOP CODE HERE

                // Gamepad 1

                //Drivetrain

                FRONT_LEFT.setPower(-gamepad1.left_stick_y);
                BACK_LEFT.setPower(-gamepad1.left_stick_y);

                FRONT_RIGHT.setPower(-gamepad1.right_stick_y);
                BACK_RIGHT.setPower(-gamepad1.right_stick_y);

                // Carousel spinners

                if (gamepad1.right_bumper){
                    //rightExtendTogglePos();
                    rightExtend.setPower(0.75);
                    leftExtend.setPower(0.15);
                }else{
                    rightExtend.setPower(0.3);
                    leftExtend.setPower(0.6);
                }
                rightSpinner.setPower(0.7 * gamepad1.right_trigger);
                leftSpinner.setPower(0.7 * gamepad1.right_trigger);

                //Gamepad 2

                // intake

                intakeMotor.setPower(-gamepad2.left_stick_y);

                if (gamepad2.x)
                {
                    isDropExtended = !isDropExtended;
                    sleep(200);
                }
                if (isDropExtended)
                {
                    dropdown.setPosition(2);
                }
                else
                {
                    dropdown.setPosition(0.2);
                }

                // lift

                // Option 1

                if (-gamepad2.right_stick_y >= 0.01){
                    lift.setPower(-0.7 * gamepad2.right_stick_y);
                }
                if (-gamepad2.right_stick_y <= -0.01){
                    lift.setPower(-0.25 * gamepad2.right_stick_y);
                }


                // Option 2

                //lift.setPower(-0.5 * gamepad2.right_stick_y);

                // lift open

                if (-gamepad2.right_trigger != 0){
                    lift_open.setPower(-0.6);
                }else{
                    lift_open.setPower(0);
                }

                // lift entender
                lift_extender.setPower(-gamepad2.left_trigger);
            }
        }

    }

    public void rightExtendTogglePos(){
        if (isRightExtendOpen){
            isRightExtendOpen = false;
        }else{
            isRightExtendOpen = true;
        }

        if (isRightExtendOpen){
            rightExtend.setPower(0.75);
        }else{
            rightExtend.setPower(0.3);
        }
    }
}
