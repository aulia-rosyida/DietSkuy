//
// Created by a-rosyida on 30/01/2021.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jint JNICALL
java_id_ac_ui_cs_mobileprogramming_aulia_rosyida_dietskuy_NewJurnalActivity_getTxtLen
    (JNIEnv *env, jobject instance, jstring txt_) {
    const char *txt = env->GetStringUTFChars(txt_, 0);
    int len = strLen(txt);

    return len;
}