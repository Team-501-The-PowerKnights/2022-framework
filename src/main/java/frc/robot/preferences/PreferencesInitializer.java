/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.preferences;


import java.util.ArrayList;
import java.util.stream.Collectors;

import edu.wpi.first.wpilibj.Preferences;

import frc.robot.preferences.PreferenceNames.*;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class PreferencesInitializer {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(PreferencesInitializer.class.getName());

    public static void initialize() {
        logger.info("initializing");

        /*
         * Drive
         */

        if (!Preferences.containsKey(Drive.pid_P)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_P);
            Preferences.setDouble(Drive.pid_P, 0.0);
        }

        if (!Preferences.containsKey(Drive.pid_I)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_I);
            Preferences.setDouble(Drive.pid_I, 0.0);
        }

        if (!Preferences.containsKey(Drive.pid_D)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_D);
            Preferences.setDouble(Drive.pid_D, 0.0);
        }

        if (!Preferences.containsKey(Drive.pid_F)) {
            logger.warn("{} doesn't exist; creating with default", Drive.pid_F);
            Preferences.setDouble(Drive.pid_F, 0.0);
        }


        logger.info("preferences as initialized:\n");
        listPreferences();

        logger.info("initialized");
    }

    public static void listPreferences() {
        StringBuilder buf = new StringBuilder();
        buf.append(" preferences:");
        for (String key : Preferences.getKeys().stream().collect(Collectors.toCollection(ArrayList::new))) {
            buf.append("\n..."); // logger gobbles up leading spaces
            buf.append(key).append(" = ").append(Preferences.getDouble(key, 3171960.));
        }
        logger.info(buf.toString());
    }

}