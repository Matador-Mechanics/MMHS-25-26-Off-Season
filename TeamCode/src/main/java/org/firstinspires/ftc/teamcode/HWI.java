package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;
import java.util.Arrays;

public class HWI { //HWI = Hardware Interface
    private LinearOpMode ExternalLinOp;
    private OpMode ExternalOp;
    private boolean isLinear;

    private DcMotor leftBack, rightBack, leftFront, rightFront, leftLauncher, rightLauncher, intakeMotor, lift;
    private CRServo launchLiftRight, launchLiftLeft;
    private Servo scoop, turnTableServo, backDoor, frontDoor;
    private GoBildaPinpointDriver pinpoint;
    private Limelight3A limelight;
    @SuppressWarnings("FieldCanBeLocal")
    private TouchSensor topBump, bottomBump, intakeBump1, intakeBump2;
    private ArrayList<CRServo> leds;
    private CRServo LED1;

    //HWI name = new HWI(this);
    public HWI (OpMode opMode, LinearOpMode linOpMode) {
        if (linOpMode == null & opMode != null) {
            ExternalOp = opMode;
            isLinear = false;
        } else if (linOpMode != null) {
            ExternalLinOp = linOpMode;
            isLinear = true;
        } else {
            RobotLog.ii("HWI", "OpMode not provided, please check HWI's initialization statement.");
        }
    }

    public void init() {
        if (isLinear) {

        } else {
            //Drive Definitions
            leftBack = ExternalOp.hardwareMap.get(DcMotor.class, "leftBack");
            rightBack = ExternalOp.hardwareMap.get(DcMotor.class, "rightBack");
            leftFront = ExternalOp.hardwareMap.get(DcMotor.class, "leftFront");
            rightFront = ExternalOp.hardwareMap.get(DcMotor.class, "rightFront");
            //Intake Definitions
            intakeMotor = ExternalOp.hardwareMap.get(DcMotor.class, "intakeMotor");
            frontDoor = ExternalOp.hardwareMap.get(Servo.class, "goofyAhhhhFrontDoor");
            intakeBump1 = ExternalOp.hardwareMap.get(TouchSensor.class, "intakeBump1");
            intakeBump2 = ExternalOp.hardwareMap.get(TouchSensor.class, "intakeBump2");
            //Launcher Definitions
            leftLauncher = ExternalOp.hardwareMap.get(DcMotor.class, "leftLauncher");
            rightLauncher = ExternalOp.hardwareMap.get(DcMotor.class, "rightLauncher");
            launchLiftRight = ExternalOp.hardwareMap.get(CRServo.class, "launchLiftRight");
            launchLiftLeft = ExternalOp.hardwareMap.get(CRServo.class, "launchLiftLeft");
            topBump = ExternalOp.hardwareMap.get(TouchSensor.class, "TopBump");
            bottomBump = ExternalOp.hardwareMap.get(TouchSensor.class, "BottomBump");
            backDoor = ExternalOp.hardwareMap.get(Servo.class, "backDoor");
            scoop = ExternalOp.hardwareMap.get(Servo.class, "scoop");
            //Lift/Skis Definition
            lift = ExternalOp.hardwareMap.get(DcMotor.class, "lift");
            //Turntable Definition
            turnTableServo = ExternalOp.hardwareMap.get(Servo.class, "turnTableServo");
            //Pinpoint Definition
            pinpoint = ExternalOp.hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
            //LED Definition
            LED1 = ExternalOp.hardwareMap.get(CRServo.class, "Led1");
            //Limelight Definition
            limelight = ExternalOp.hardwareMap.get(Limelight3A.class, "limelight");
            //Drive Config
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setDirection(DcMotor.Direction.REVERSE);
            rightFront.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            //Intake Config
            intakeMotor.setDirection(DcMotor.Direction.REVERSE);
            //Launcher Config
            leftLauncher.setDirection(DcMotor.Direction.REVERSE);
            rightLauncher.setDirection(DcMotor.Direction.FORWARD);
            leftLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            rightLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            leftLauncher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightLauncher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            launchLiftRight.setDirection(CRServo.Direction.REVERSE);
            launchLiftLeft.setDirection(CRServo.Direction.FORWARD);
            //Lift/Skis Config
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //Odometry Config
            pinpoint.initialize(); //Initializes odometry for use in code
            pinpoint.update();
            //LED Config
            leds = new ArrayList<>(Arrays.asList(null, LED1)); //creates a list of LEDs for ledManager to use
            //Limelight Config/Setup
            limelight.pipelineSwitch(0); //Sets the config the limelight should use
            limelight.setPollRateHz(100); //Limelight data polling rate
            limelight.start(); //Initializes limelight for use in code
        }
    }
    public void drive(double linear, double lateral, double rotational, double speed) {
        speed = Utils.clamp(speed, 0, 1);
        leftFront.setPower(((linear + lateral) - rotational) * speed);
        leftBack.setPower((linear - lateral - rotational) * speed);
        rightFront.setPower(((linear + lateral) + rotational) * speed);
        rightBack.setPower(((linear - lateral) + rotational) * speed);
    }
    private static class Utils{
        private static double clamp(double value, double min, double max){
            if (value < min) {
                return min;
            } else if (value > max) {
                return max;
            } else return value;
        }
    }
}
