#include <stdbool.h>
#include <jni.h>
#include <unistd.h>
#include <stdio.h>

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    return JNI_VERSION_1_4;
}

jboolean Java_com_nekolaboratory_Riddle_Core_checkDebuggerAttach(JNIEnv *env, jobject instance) {
    const int bufsize = 1024;
    char filename[bufsize];
    char line[bufsize];
    int pid = getpid();
    sprintf(filename, "/proc/%d/status", pid);
    FILE *file = fopen(filename, "r");
    while (fgets(line, bufsize, file)) {
        if (strncmp(line, "TracerPid", 9) == 0) {
            int status = atoi(&line[10]);
            if (status != 0) {
                fclose(file);
                return true;
            }
            break;
        }
    }
    fclose(file);
    return false;
}