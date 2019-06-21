package com.nekolaboratory.Riddle;

/**
 * @author Yusuke Arakawa
 */

public class Riddle {

    Core riddleCore;

    public void initialize(int loopDeley, RiddleCallback callback) {
        riddleCore = new Core(loopDeley, callback);
    }

    public void start() {
        riddleCore.start();
    }

    public void stop() {
        riddleCore.stop();
    }
}
