package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@Autonomous
public class RedLowerAuto extends LinearOpMode
{
    public enum ElementPosition
    {
        LEFT,
        CENTER,
        RIGHT
    }

    OpenCvInternalCamera Cam;
    SkystoneDeterminationPipeline pipeline;

    public DcMotorEx FL;
    public DcMotorEx FR;
    public DcMotorEx BL;
    public DcMotorEx BR;
    public DcMotorEx lift;
    public CRServo lift_extender;
    public CRServo lift_open;
    public CRServo rightspinner;
    public CRServo leftspinner;
    public CRServo rightextend;
    public CRServo leftextend;
    public ElementPosition pos;

    @Override
    public void runOpMode()
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        Cam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        pipeline = new SkystoneDeterminationPipeline();
        Cam.setPipeline(pipeline);
        Cam.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

        Cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                Cam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode){}
        });

        BR = hardwareMap.get(DcMotorEx.class, "backright");
        BL = hardwareMap.get(DcMotorEx.class, "backleft");
        FR = hardwareMap.get(DcMotorEx.class, "frontright");
        FL = hardwareMap.get(DcMotorEx.class, "frontleft");
        lift = hardwareMap.get(DcMotorEx.class, "slides");
        lift_extender = hardwareMap.get(CRServo.class, "bucketextend");
        lift_open = hardwareMap.get(CRServo.class, "bucketdrop");
        rightspinner = hardwareMap.get(CRServo.class, "rightspinner");
        leftspinner = hardwareMap.get(CRServo.class, "leftspinner");
        rightextend = hardwareMap.get(CRServo.class, "rightextend");
        leftextend = hardwareMap.get(CRServo.class, "leftextend");

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        for (int i = 0; i < 10; i++);
        {
            pos = pipeline.getAnalysis();
            sleep(100);
        }

        lift_extender.setPower(0.5);

        move(-585);
        runtopos();
        power(0.25);
        while(FL.isBusy()){}
        power(0);
        reset();

        turn(-560);
        runtopos();
        power(0.25);
        while(FL.isBusy()){}
        power(0);
        reset();

        move(-950);
        runtopos();
        power(0.15);
        while(FL.isBusy()){}
        power(0);
        reset();

        turn(560);
        runtopos();
        power(0.25);
        while(FL.isBusy()){}
        power(0);
        reset();

        if (pos == ElementPosition.LEFT) {
            rise(0);
        }else if (pos == ElementPosition.CENTER){
            rise(1000);
        }else{
            rise(2000);
        }
        lift.setVelocity(30);
        drop();
        fall();

        lift_extender.setPower(0.5);

        sleep(500);

        turn(-580);
        runtopos();
        power(0.25);
        while(FL.isBusy()){}
        power(0);
        reset();

        move(3100);
        runtopos();
        power(0.25);
        while(FL.isBusy()){}
        power(0);
        reset();

    }
    //Forward towards intake
    public void move(int ticks)
    {
        FL.setTargetPosition(ticks);
        FR.setTargetPosition(ticks);
        BL.setTargetPosition(ticks);
        BR.setTargetPosition(ticks);
    }

    //Toward right facing intake
    public void turn(int ticks)
    {
        FL.setTargetPosition(ticks);
        FR.setTargetPosition(-ticks);
        BL.setTargetPosition(ticks);
        BR.setTargetPosition(-ticks);
    }

    public void power(double power)
    {
        FL.setPower(power);
        FR.setPower(power);
        BL.setPower(power);
        BR.setPower(power);
    }

    public void reset()
    {
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runtopos()
    {
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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

    public void drop() {
        lift_extender.setPower(-0.5);
        sleep(500);
        lift_open.setPower(-0.6);
        sleep(1000);
        lift_extender.setPower(0);
        sleep(500);
        lift_open.setPower(0);
        sleep(500);
    }

    public static class SkystoneDeterminationPipeline extends OpenCvPipeline
    {
        static final Scalar RED = new Scalar(255, 0, 0);
        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(60,160);
        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(240,160);
        static final int REGION_WIDTH = 20;
        static final int REGION_HEIGHT = 20;

        Point region1_pointA = new Point(REGION1_TOPLEFT_ANCHOR_POINT.x, REGION1_TOPLEFT_ANCHOR_POINT.y);
        Point region1_pointB = new Point(REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH, REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
        Point region2_pointA = new Point(REGION2_TOPLEFT_ANCHOR_POINT.x, REGION2_TOPLEFT_ANCHOR_POINT.y);
        Point region2_pointB = new Point(REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH, REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

        Mat region1_Cb, region2_Cb;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        int avg1, avg2;
        int min;
        private volatile ElementPosition position = ElementPosition.LEFT;

        void inputToCb(Mat input)
        {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 1);
        }

        @Override
        public void init(Mat firstFrame)
        {
            inputToCb(firstFrame);

            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
            region2_Cb = Cb.submat(new Rect(region2_pointA, region2_pointB));
        }

        @Override
        public Mat processFrame(Mat input)
        {
            inputToCb(input);

            avg1 = (int) Core.mean(region1_Cb).val[0];
            avg2 = (int) Core.mean(region2_Cb).val[0];

            Imgproc.rectangle(
                    input,
                    region1_pointA,
                    region1_pointB,
                    RED,
                    2);
            Imgproc.rectangle(
                    input,
                    region2_pointA,
                    region2_pointB,
                    RED,
                    2);


            if (avg1 <= 110)
            {
                min = 1;
            }
            else if (avg2 <= 110)
            {
                min = 2;
            }
            else
            {
                min = 3;
            }

            if(min == 1)
            {
                position = ElementPosition.LEFT;
            }
            else if(min == 2)
            {
                position = ElementPosition.CENTER;
            }
            else if(min == 3)
            {
                position = ElementPosition.RIGHT;
            }

            return input;
        }

        public ElementPosition getAnalysis()
        {
            return position;
        }
        public int[] getAvgs()
        {
            int[] avgs= {avg1, avg2};
            return avgs;
        }
    }
}