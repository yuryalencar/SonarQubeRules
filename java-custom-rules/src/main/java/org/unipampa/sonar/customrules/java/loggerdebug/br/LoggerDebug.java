/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.sonar.customrules.java.loggerdebug.br;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;

/**
 *
 * @author YURY
 */
public class LoggerDebug {

    // Utilizado para verificar se esta acontecendo o Debug do sistema.
    public static final boolean DEBUG = true;
    public static final Logger LOGGER = Logger.getLogger("org.unipampa.sonar.customrules.java.loggerdebug.br");
    public static final Level LEVEL = Level.INFO;

    public static boolean initializeLogger(String logFileName) {
        try {
            LOGGER.addHandler(new FileHandler(logFileName, Integer.MAX_VALUE, 1));
            LOGGER.setLevel(LEVEL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
