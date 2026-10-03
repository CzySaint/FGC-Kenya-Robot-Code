# FGC-Kenya-Robot-Code
# 🤖 FTC TeleOp — KoreaFinal Controls

Main **TeleOp (driver-controlled)** code for the FTC robot, implemented in [`KoreaFinal.java`](KoreaFinal.java).

This OpMode is designed for a **two-driver setup** and provides control for:

* 🛞 Tank drivetrain
* 🧗 Climber mechanism
* 📥 Dual-motor intake system
* 🎯 Mirrored dual-motor shooter

---

## 📋 Table of Contents

* [Hardware Configuration](#️-hardware-configuration)
* [Gamepad Controls](#-gamepad-controls)
* [Installation & Setup](#-installation--setup)
* [Troubleshooting](#️-troubleshooting)

---

## ⚙️ Hardware Configuration

For the OpMode to initialize correctly, all devices must be configured on the **Driver Hub / Robot Configuration** using the exact names below.

> **⚠️ Important:** Hardware configuration names are **case-sensitive**.

### DC Motors

| Function           | Driver Hub Config Name | Direction | Encoder / Notes                        |
| ------------------ | ---------------------- | --------- | -------------------------------------- |
| **Left Drive**     | `LeftMotor`            | `FORWARD` | Runs without encoder                   |
| **Right Drive**    | `RightMotor`           | `FORWARD` | Runs without encoder                   |
| **Climber**        | `Climber`              | `FORWARD` | Runs without encoder                   |
| **Main Shooter**   | `Shooter`              | `FORWARD` | `RUN_USING_ENCODER` — regulated RPM    |
| **Second Shooter** | `Shooter2`             | `REVERSE` | Mirrored to main shooter; uses encoder |
| **First Intake**   | `intake1`              | `FORWARD` | Runs without encoder                   |
| **Second Intake**  | `intake2`              | `FORWARD` | Maximum power capped at 80%            |

> **Note:** `intake1` and `intake2` intentionally use a lowercase **`i`**.

---

## 🎮 Gamepad Controls

The robot uses a standard **two-driver FTC configuration**.

### 🕹️ Gamepad 1 — Driver

Controls the **drivetrain** and **climber**.

| Input                     | Function                      |
| ------------------------- | ----------------------------- |
| **Left Stick — Up/Down**  | Left drive motor              |
| **Right Stick — Up/Down** | Right drive motor             |
| **Left Trigger**          | Climber down / negative power |
| **Right Trigger**         | Climber up / positive power   |

---

### 🎮 Gamepad 2 — Operator

Controls the **intake system** and **dual shooters**.

| Input                                  | Function                         |
| -------------------------------------- | -------------------------------- |
| **Left Trigger**                       | Intake 1 reverse                 |
| **Right Trigger**                      | Intake 1 forward                 |
| **Left Bumper**                        | Intake 2 reverse — capped at 80% |
| **Right Bumper**                       | Intake 2 forward — capped at 80% |
| **B Button** *(Circle on PlayStation)* | Turn shooters **ON**             |
| **X Button** *(Square on PlayStation)* | Turn shooters **OFF**            |

---

## 🚀 Installation & Setup

### 1. Open the FTC Project

Open your FTC Android Studio project or access the **OnBotJava** browser dashboard.

### 2. Navigate to the TeamCode Directory

```text
TeamCode/
└── src/
    └── main/
        └── java/
            └── org/
                └── firstinspires/
                    └── ftc/
                        └── teamcode/
```

Place `KoreaFinal.java` inside the `teamcode` package.

### 3. Build the Project

Compile/build the project to make sure the OpMode has no errors.

### 4. Select the OpMode

On the Driver Station / Driver Hub:

1. Open the **Test** group in the TeleOp list.
2. Select **Final Controls**.
3. Press **INIT**.
4. Confirm that telemetry displays:

```text
Initialized and ready to run
```

5. Press **START** when the match begins.

---

## 🛠️ Troubleshooting

### `Cannot find symbol: class DCMotor`

Java is case-sensitive.

Make sure your code uses:

```java
DcMotor
```

and **not**:

```java
DCMotor
```

---

### 🤖 Robot Drives Backward

If pushing the joysticks forward causes the robot to drive backward, reverse the direction of the affected drive motor(s).

For example:

```java
motor.setDirection(DcMotor.Direction.REVERSE);
```

The correct direction depends on the robot's physical gearing, motor orientation, and chain/belt setup.

---

### 🔄 Shooter Motors Are Fighting Each Other

`Shooter2` is configured as:

```java
Shooter2.setDirection(DcMotor.Direction.REVERSE);
```

This assumes the second shooter motor is physically mirrored relative to the main shooter.

If the two motors are rotating in opposite directions when they should be working together, check the physical mounting and change the motor direction to:

```java
Shooter2.setDirection(DcMotor.Direction.FORWARD);
```

---

## 📌 Quick Reference

### Driver 1

**Drivetrain**

* Left Stick → Left Motor
* Right Stick → Right Motor

**Climber**

* Left Trigger → Down
* Right Trigger → Up

### Driver 2

**Intake**

* Left Trigger → Intake 1 Reverse
* Right Trigger → Intake 1 Forward
* Left Bumper → Intake 2 Reverse
* Right Bumper → Intake 2 Forward

**Shooter**

* `B` → ON
* `X` → OFF

---

## 📄 OpMode

**File:** `KoreaFinal.java`
**Type:** TeleOp
**Configuration:** Two-driver FTC setup
**Drivetrain:** Tank drive
**Shooter:** Dual motor
**Intake:** Dual motor
**Climber:** Single motor

---

### ⚠️ Before Competition

Always verify the following before running the robot:

* Hardware names exactly match the configuration above.
* Motor directions match the robot's physical setup.
* Both shooter motors rotate in the correct direction.
* Intake motors rotate correctly in both directions.
* Climber controls move in the intended direction.
* `Final Controls` initializes successfully without hardware-map errors.
* The robot behaves correctly during a controlled practice test before entering a match.
