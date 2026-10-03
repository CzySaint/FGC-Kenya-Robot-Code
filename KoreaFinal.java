package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Final Controls", group = "Test") // Change the "Final Controls" if u wanna change the name of the OP mode
public class KoreaFinal extends LinearOpMode {

    // 1. HARDWARE VARIABLES
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor climber;
    private DcMotor shooter;
    private DcMotor shooter2;
    private DcMotor intake1;
    private DcMotor intake2;

    
    // 2. POWER TUNING VARIABLES
    double drivePowerLimit = 1.0;      
    double climberPowerLimit = 1.0;    
    double shooterSpeed = 1.0;         // 1.0 in RUN_USING_ENCODER = Max regulated RPM
    double intake1PowerLimit = 0.7;    
    double intake2PowerLimit = 0.8;    // Both the intake1 and intake2 units have been reduced power so as to less consume power when the shooter is running.


    // 3. STATE VARIABLES
    boolean isShooterOn = false; //This one was ffor getting the telemetry data for the shooter on or off. 

    @Override
    public void runOpMode() {

        // 4. HARDWARE MAPPING
      // If you were to add any motor or something, you should make sure the name aligns with what is  written here 
        leftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
        climber = hardwareMap.get(DcMotor.class, "Climber");
        shooter = hardwareMap.get(DcMotor.class, "Shooter");
        shooter2 = hardwareMap.get(DcMotor.class, "Shooter2");
        intake1 = hardwareMap.get(DcMotor.class, "intake1");
        intake2 = hardwareMap.get(DcMotor.class, "intake2");

        // ==========================================
        // 5. MOTOR DIRECTIONS & MODES
        // ==========================================
        // FIXED: Left Motor set to REVERSE so it drives forward when the stick is pushed up.
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        
        shooter.setDirection(DcMotor.Direction.FORWARD);
        shooter2.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        climber.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        telemetry.addData("Status", "Initialized and ready to run");
        telemetry.update();

        waitForStart();

        // ==========================================
        // 6. MAIN TELEOP LOOP
        // ==========================================
        while (opModeIsActive()) {
            
            // --- CONTROLLER 1: DRIVING & CLIMBER ---
            // TANK DRIVE: Left Stick Y for Left Motor, Right Stick Y for Right Motor.
            double leftPower = -gamepad1.left_stick_y;
            double rightPower = -gamepad1.right_stick_y;

            leftMotor.setPower(leftPower * drivePowerLimit);
            rightMotor.setPower(rightPower * drivePowerLimit);

            double climberPower = gamepad1.right_trigger - gamepad1.left_trigger;
            climber.setPower(climberPower * climberPowerLimit);

            // --- CONTROLLER 2: INTAKES & SHOOTER ---
            double intake1Power = gamepad2.right_trigger - gamepad2.left_trigger;
            intake1.setPower(intake1Power * intake1PowerLimit);

            double intake2Power = 0.0;
            if (gamepad2.right_bumper) {
                intake2Power = intake2PowerLimit;
            } else if (gamepad2.left_bumper) {
                intake2Power = -intake2PowerLimit;
            }
            intake2.setPower(intake2Power);

            // Toggle logic for the shooter (O turns ON, Square turns OFF)
            if (gamepad2.b) {
                isShooterOn = true;
            } else if (gamepad2.x) {
                isShooterOn = false;
            }

            if (isShooterOn) {
                shooter.setPower(shooterSpeed);
                shooter2.setPower(shooterSpeed);
            } else {
                shooter.setPower(0.0);
                shooter2.setPower(0.0);
            }

            // ==========================================
            // TELEMETRY OUTPUT
            // ==========================================
            telemetry.addData("Drivetrain", "Left (%.2f) | Right (%.2f)", leftPower, rightPower);
            telemetry.addData("Climber", "Power: %.2f", climberPower);
            telemetry.addData("Intakes", "In1: %.2f | In2: %.2f", intake1Power, intake2Power);
            telemetry.addData("Shooter", "State: %s | Ticks: %d", isShooterOn ? "ON" : "OFF", shooter.getCurrentPosition());
            telemetry.update();
        }
    }
}
