package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class ColorTest extends LinearOpMode {
    // Define a variable for our color sensor
    ColorSensor color;
    DistanceSensor distance;

    @Override
    public void runOpMode() {
        // Get the color sensor from hardwareMap
        color = hardwareMap.get(ColorSensor.class, "Color");
        distance = hardwareMap.get(DistanceSensor.class, "Color");

        // Wait for the Play button to be pressed
        waitForStart();

        // While the Op Mode is running, update the telemetry values.
        while (opModeIsActive()) {
            telemetry.addData("Red", color.red());
            telemetry.addData("Green", color.green());
            telemetry.addData("Blue", color.blue());
            telemetry.addData("Luma", color.alpha());
            telemetry.addData("Distance", distance.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}