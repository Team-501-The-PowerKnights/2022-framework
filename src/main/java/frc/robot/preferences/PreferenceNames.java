/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.preferences;


import frc.robot.subsystems.SubsystemNames;


/**
 * Add your docs here.
 */
class PreferenceNames {

    public final class Drive {
        public static final String name = SubsystemNames.driveName;
        public static final String pid_P = name + ".P";
        public static final String pid_I = name + ".I";
        public static final String pid_D = name + ".D";
        public static final String pid_F = name + ".F";
    }

}
