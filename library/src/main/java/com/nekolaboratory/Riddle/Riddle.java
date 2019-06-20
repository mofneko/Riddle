package com.nekolaboratory.Riddle;

/**
 * @author Yusuke Arakawa
 */

public class Riddle {

    Core stellaCore;

    public void initialize(int loopDeley, RiddleCallback callback) {
        stellaCore = new Core(loopDeley, callback);
    }

    public void start() {
        stellaCore.start();
    }

    public void stop() {
        stellaCore.stop();
    }
}
