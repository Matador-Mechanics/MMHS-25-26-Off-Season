package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.*;
import com.pedropathing.ivy.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class IvyTest extends OpMode {

	private DcMotor leftLauncher, rightLauncher;
	TouchSensor intakeBump1;

	@Override
	public void init() {
		Scheduler.reset();

		leftLauncher = hardwareMap.get(DcMotor.class, "leftLauncher");
		rightLauncher = hardwareMap.get(DcMotor.class, "rightLauncher");
		intakeBump1 = hardwareMap.get(TouchSensor.class, "intakeBump1");

		Command raiseArm = Command.build()
				.setExecute(() -> launcher(0.5))
				.setDone(() -> !intakeBump1.isPressed())
				.setEnd(endCondition -> launcher(0))
				.requiring(leftLauncher, rightLauncher);
	}

	@Override
	public void loop() {

	}
	public void launcher(double Power){
		leftLauncher.setPower(Power);
		rightLauncher.setPower(Power);
	}
}
