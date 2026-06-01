package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class HWIExampleImlementation extends OpMode {
    HWI robot = new HWI(this);

    @Override
    public void init() {
        robot.init();
    }

    @Override
    public void loop() {
        robot.drive(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x, 0.7);
    }
}
