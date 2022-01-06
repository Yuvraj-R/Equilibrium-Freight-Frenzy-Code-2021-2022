package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "ThreeLevels", group = "")
public class ThreeLevels extends LinearOpMode {

    public DcMotorEx lift;
    public CRServo lift_extender;
    public CRServo lift_open;

    public void runOpMode(){

        lift = hardwareMap.get(DcMotorEx.class, "slides");
        lift_extender = hardwareMap.get(CRServo.class, "bucketextend");
        lift_open = hardwareMap.get(CRServo.class, "bucketdrop");

        waitForStart();

        // 0 for bottom, 1000 for middle, 2000 for top
        rise(2000);
        lift.setVelocity(30);
        drop();
        fall();
    }

    public void rise(int time){
        lift.setVelocity(600);
        sleep(time);
        lift.setVelocity(0);
    }

    public void fall(){
        lift.setVelocity(0);
        sleep(1500);
    }
    public void drop(){
        lift_extender.setPower(-0.1);
        sleep(500);
        lift_open.setPower(-0.8);
        sleep(1000);
        lift_extender.setPower(0);
        sleep(500);
        lift_open.setPower(0);
        sleep(500);
    }

}
