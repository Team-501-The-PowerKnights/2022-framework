/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;


import org.slf4j.Logger;

import riolog.RioLogger;


/**
 * Provides implementation of <code>IWheelColorSensor</code> for the
 * <i>Proto-Bot</i> which is based on the REV Robotics color sensor.
 */
class ProtoWheelColorSensor extends SuitcaseWheelColorSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(ProtoWheelColorSensor.class.getName());

    ProtoWheelColorSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
