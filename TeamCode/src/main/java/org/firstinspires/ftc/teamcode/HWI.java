package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.RobotLog;

import java.util.ArrayList;

public class HWI { //HWI = Hardware Interface
    private LinearOpMode ExternalLinOp;
    private OpMode ExternalOp;
    private boolean isLinear;

    private static DcMotor leftBack, rightBack, leftFront, rightFront, leftLauncher, rightLauncher, intakeMotor, lift;
    private static CRServo launchLiftRight, launchLiftLeft;
    private static Servo scoop, turnTableServo, backDoor, frontDoor;
    private static GoBildaPinpointDriver pinpoint;
    private static Limelight3A limelight;
    @SuppressWarnings("FieldCanBeLocal")
    private static TouchSensor topBump, bottomBump, intakeBump1, intakeBump2;
    private static ArrayList<CRServo> leds;

    //HWI name = new HWI(this);
    public HWI (OpMode opMode, LinearOpMode linOpMode) {
        if (linOpMode == null & opMode != null) {
            ExternalOp = opMode;

        } else if (linOpMode != null) {
            ExternalLinOp = linOpMode;
        } else {
            RobotLog.ii("HWI", "OpMode not provided, please check HWI's initialization statement.");
        }
    }

    public void init() {
        if (isLinear) {
            
        } else {

        }
    }
}
