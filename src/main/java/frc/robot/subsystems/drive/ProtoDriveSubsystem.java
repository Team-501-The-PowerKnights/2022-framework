/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.drive;


import java.util.List;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;

import riolog.PKLogger;
import riolog.RioLogger;


class ProtoDriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ProtoDriveSubsystem.class.getName());

    /**
     * Drive Constants
     */
    private static final double s = 0.0645; // Volts
    private static final double v = 2.84; // VoltSeconds Per Meter
    private static final double a = 0.28; // VoltSecondsSquared Per Meter
    private static final double p = 2.53; // Drive Velocity
    private static final double trackWidth = 0.616; // Meters
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;
    private static final double maxSpeed = 3.04; // Meters Per Second
    private static final double maxAcceleration = 0.5; // Meters Per Second Squared
    private static final boolean leftReversed = false;
    private static final boolean rightReversed = false;
    private static final double wheelRadius = 0.1524; // Meters
    private static final double beltGearing = 1;
    private static final double gearboxGearing = 10.71; // Standard AndyMark KoP chassis Toughbox Mini gearing

    // Voltage constraint for trajectory following
    private final DifferentialDriveVoltageConstraint autoVoltageConstraint;

    // Trajectory configuration for trajectory following
    private final TrajectoryConfig trajectoryConfig;

    /**
     * Mechanisms and Sensors
     */

    private final CANSparkMax leftFrontMotor;
    private final CANSparkMax leftRearMotor;
    private final CANSparkMax rightFrontMotor;
    private final CANSparkMax rightRearMotor;

    private final MotorControllerGroup left;
    private final MotorControllerGroup right;

    private final RelativeEncoder leftEncoder;
    private final RelativeEncoder rightEncoder;

    private final IGyroSensor nav;

    private final DifferentialDrive drive;
    private final DifferentialDriveKinematics driveKinematics;
    private final DifferentialDriveOdometry driveOdometry;

    ProtoDriveSubsystem() {
        logger.info("constructing");

        leftFrontMotor = new CANSparkMax(23, MotorType.kBrushless);
        leftRearMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightFrontMotor = new CANSparkMax(20, MotorType.kBrushless);
        rightRearMotor = new CANSparkMax(21, MotorType.kBrushless);

        left = new MotorControllerGroup(leftFrontMotor, leftRearMotor);
        right = new MotorControllerGroup(rightFrontMotor, rightRearMotor);

        left.setInverted(false);
        right.setInverted(true);

        leftEncoder = leftFrontMotor.getEncoder();
        rightEncoder = rightFrontMotor.getEncoder();

        nav = GyroFactory.getInstance();

        drive = new DifferentialDrive(left, right);
        driveKinematics = new DifferentialDriveKinematics(trackWidth);
        driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(nav.getAngle()));

        autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(s, v, a),
                driveKinematics, 10);

        trajectoryConfig = new TrajectoryConfig(maxSpeed, maxAcceleration).setKinematics(driveKinematics)
                .addConstraint(autoVoltageConstraint);

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        driveOdometry.update(Rotation2d.fromDegrees(nav.getAngle()), leftEncoder.getPosition(),
                rightEncoder.getPosition());
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        drive.tankDrive(0, 0);
    }

    @Override
    public void setBrake(boolean brakeOn) {
        if (brakeOn) {
            leftFrontMotor.setIdleMode(IdleMode.kBrake);
            leftRearMotor.setIdleMode(IdleMode.kBrake);
            rightFrontMotor.setIdleMode(IdleMode.kBrake);
            rightRearMotor.setIdleMode(IdleMode.kBrake);
        } else {
            leftFrontMotor.setIdleMode(IdleMode.kCoast);
            leftRearMotor.setIdleMode(IdleMode.kCoast);
            rightFrontMotor.setIdleMode(IdleMode.kCoast);
            rightRearMotor.setIdleMode(IdleMode.kCoast);
        }
    }

    /*
     * Drive constraint values
     */

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        drive.arcadeDrive(hmiSpeed, hmiTurn);
    }

    @Override
    public void followPath(final Pose2d start, final List<Translation2d> interiorWaypoints, final Pose2d end) {

        // Create trajectory to follow
        final Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, end,
                trajectoryConfig);

        // return the RamseteCommand to run
        CommandScheduler.getInstance()
                .schedule(new RamseteCommand(trajectory, this::getPose, new RamseteController(ramseteB, ramseteZeta),
                        new SimpleMotorFeedforward(s, v, a), driveKinematics, this::getVelocity,
                        new PIDController(p, 0, 0), new PIDController(p, 0, 0), this::tankDriveVolts, this));
    }

    protected double convertInchesToEncoderClicks(double inches) {
        return inches * (1 / 12) // Conversion to feet
                * 3.281 // Conversion to meters
                * (1 / (2 * Math.PI * wheelRadius)) // Convert to wheel revolutions (Circumference)
                * (beltGearing) // Convert to output shaft revolutions (Belt gearing)
                * (1 / gearboxGearing); // Convert to motor revolutions (TB Mini gearing)
    }

    /*
     * RAMSETE Methods
     */

    /**
     * 
     * @param leftVolts
     * @param rightVolts
     */
    private void tankDriveVolts(final double leftVolts, final double rightVolts) {
        leftFrontMotor.setVoltage(leftVolts * (leftReversed ? -1 : 1));
        rightFrontMotor.setVoltage(rightVolts * (rightReversed ? -1 : 1));
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    private Pose2d getPose() {
        return driveOdometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    private DifferentialDriveWheelSpeeds getVelocity() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void swap() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getEncoderClicks() {
        // TODO Auto-generated method stub
        return 0;
    }

}