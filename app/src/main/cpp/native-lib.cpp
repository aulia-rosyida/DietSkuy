//
// Created by a-rosyida on 30/01/2021.
//

#include <jni.h>
#include <string>
#include <string.h>

extern "C"
JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_aulia_1rosyida_dietskuy_NewJurnalActivity_getTxtLen(JNIEnv *env,
                                                                                       jobject thiz,
                                                                                       jstring txt) {
    // TODO: implement getTxtLen()
    const char* txt2 = env->GetStringUTFChars(txt, 0);
    int len = strlen(txt2);

    return len;
}