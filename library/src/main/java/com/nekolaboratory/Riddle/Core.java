package com.nekolaboratory.Riddle;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

import static android.os.AsyncTask.Status.RUNNING;

/**
 * @author Yusuke Arakawa
 */

public class Core {

    static {
        System.loadLibrary("Riddle");
    }

    private RiddleCallback callback;
    private boolean detectionEnable;
    private int loopDeley;
    private DebuggerAttachDetectionTask debuggerAttachDetectionTask;

    private Core() {
    }

    public Core(int loopDeley, RiddleCallback callback) {
        setLoopDeley(loopDeley);
        setCallback(callback);
    }

    public native boolean checkDebuggerAttach();

    public int getLoopDeley() {
        return loopDeley;
    }

    public void setLoopDeley(int loopDeley) {
        this.loopDeley = loopDeley;
    }

    public boolean isDetectionEnable() {
        return detectionEnable;
    }

    public RiddleCallback getCallback() {
        return callback;
    }

    public void setCallback(RiddleCallback callback) {
        this.callback = callback;
    }

    public void start() {
        if (!isDetectionEnable()) {
            detectionEnable = true;
            startDetection();
        }
    }

    public void stop() {
        if (isDetectionEnable()) {
            detectionEnable = false;
            debuggerAttachDetectionTask.cancel(true);
        }
    }

    private void startDetection() {
        if (debuggerAttachDetectionTask != null && debuggerAttachDetectionTask.getStatus() == RUNNING) {
            return;
        }
        debuggerAttachDetectionTask = new DebuggerAttachDetectionTask(new DebuggerAttachDetectionCallBack() {
            @Override
            public void complete() {
                if (isDetectionEnable()) {
                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            startDetection();
                        }
                    }, getLoopDeley());
                }
            }
        });
        debuggerAttachDetectionTask.execute();
    }

    interface DebuggerAttachDetectionCallBack {
        void complete();
    }

    class DebuggerAttachDetectionTask extends AsyncTask<Void, Void, Boolean> {

        private DebuggerAttachDetectionCallBack debuggerAttachDetectionCallBack;

        public DebuggerAttachDetectionTask(DebuggerAttachDetectionCallBack debuggerAttachDetectionCallBack) {
            this.debuggerAttachDetectionCallBack = debuggerAttachDetectionCallBack;
        }

        @Override
        protected void onPreExecute() {
            if (!isDetectionEnable()) {
                this.cancel(true);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return checkDebuggerAttach();
        }

        @Override
        protected void onPostExecute(Boolean isDetect) {
            if (isDetect) {
                getCallback().onDetect();
            }
            this.debuggerAttachDetectionCallBack.complete();
        }
    }
}
