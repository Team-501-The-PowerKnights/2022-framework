/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.wheelcolor;


import frc.robot.utils.PKColor;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Provides implementation of <code>IWheelColorSensor</code> which has no sensor
 * or other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
class StubWheelColorSensor extends BaseWheelColorSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubWheelColorSensor.class.getName());

    StubWheelColorSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public PKColor getColor() {
        // Stub doesn't implement this - returns an invalid color
        return PKColor.invalidTarget;
    }

    @Override
    public double getConfidence() {
        // Stub doesn't implement this - returns 0.0
        return 0.0;
    }

}
