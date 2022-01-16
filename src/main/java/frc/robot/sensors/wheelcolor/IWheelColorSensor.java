/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/
package frc.robot.sensors.wheelcolor;


import frc.robot.sensors.ISensor;
import frc.robot.utils.PKColor;


/**
 * Add your docs here.
 **/
public interface IWheelColorSensor extends ISensor {

    /**
     * Returns the color currently detected by the sensor.
     * 
     * @return Color the sensor is seeing
     */
    public PKColor getColor();

    /**
     * Returns the confidence associated with the last query for the currently
     * detected color.
     * 
     * @return Confidence in the returned color
     */
    public double getConfidence();

    /**
     * 
     * @return if the sensor senses that the wheel is on blue
     */
    public boolean isBlue();

    /**
     * 
     * @return if the sensor senses that the wheel is on green
     */
    public boolean isGreen();

    /**
     * 
     * @return if the sensor senses that the wheel is on yellow
     */
    public boolean isYellow();

    /**
     * 
     * @return if the sensor senses that the wheel is on red
     */
    public boolean isRed();

}
