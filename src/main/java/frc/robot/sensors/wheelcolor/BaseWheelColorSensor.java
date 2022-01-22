/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/
package frc.robot.sensors.wheelcolor;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKColor;

import riolog.PKLogger;
import riolog.RioLogger;


abstract class BaseWheelColorSensor implements IWheelColorSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseWheelColorSensor.class.getName());

    protected static final String myName = TelemetryNames.WheelColor.name;

    // Last retreived color (in raw form from sensor)
    protected Color color;

    BaseWheelColorSensor() {
        logger.info("constructing");

        // Seed first instance so won't be null
        color = Color.kBlack;

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // Call getColor first so that the state of the sensor is updated
        SmartDashboard.putString(TelemetryNames.WheelColor.match, getColor().name);
        SmartDashboard.putNumber(TelemetryNames.WheelColor.confidence, getConfidence());
        SmartDashboard.putString(TelemetryNames.WheelColor.color, color.toString());
    }

    @Override
    public boolean isBlue() {
        return getColor().equals(PKColor.blueTarget);
    }

    @Override
    public boolean isGreen() {
        return getColor().equals(PKColor.greenTarget);
    }

    @Override
    public boolean isRed() {
        return getColor().equals(PKColor.redTarget);
    }

    @Override
    public boolean isYellow() {
        return getColor().equals(PKColor.yellowTarget);
    }

}
