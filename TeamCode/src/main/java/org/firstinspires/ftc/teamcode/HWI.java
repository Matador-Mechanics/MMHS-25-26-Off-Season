package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class HWI { //HWI = Hardware Interface
    private LinearOpMode ExternalLinOp = null;
    private OpMode ExternalOp = null;

    //HWI name = new HWI(this);
    public HWI (OpMode opMode) {
        ExternalOp = opMode;
    }
    public HWI (LinearOpMode opMode) {
        ExternalLinOp = opMode;
    }

    public void init(){
        if (ExternalOp != null) {
            RegOpInit(ExternalOp);
        } else {
            LinOpInit(ExternalLinOp);
        }
    }
    private void RegOpInit(OpMode opMode) {
        //hw initialization etc

        //then hw config
    }
    private void LinOpInit(LinearOpMode opMode) {
        //hw initialization etc
        //motor  = opMode.hardwareMap.get(DcMotor.class, "motor name");

        //then hw config
        //motor.setDirection(DcMotor.Direction.REVERSE);

        //confirmation telemetry
        //OpMode.telemetry.addData(">", "Hardware Initialized");
        //OpMode.telemetry.update();
    }
}
