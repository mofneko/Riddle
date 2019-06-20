LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

APP_PLATFORM := android-8

LOCAL_MODULE    := Riddle
LOCAL_SRC_FILES := Riddle.c

LOCAL_LDLIBS := -llog -landroid -lz

include $(BUILD_SHARED_LIBRARY)
