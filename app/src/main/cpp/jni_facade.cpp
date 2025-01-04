//
// Created by littlefogcat on 2025/1/4.
//

#include "jni.h"
#include "jni_facade.h"
#include "fibonacci/fibonacci.h"

extern "C"
JNIEXPORT jobject JNICALL
Java_tech_xiaoniu_myndkstudy_NdkBridge_computeFibonacciNative(
        JNIEnv *env,
        jobject thiz,
        jint n
) {
    // compute the result and convert it to jint before passing back to Java
    jint result = static_cast<jint>(computeFibonacci(n)); // 将int 强转为 jint
    // construct an instance of FibonacciResult object defined in Java code
    jclass resultClass = env->FindClass("tech/xiaoniu/myndkstudy/FibonacciResult"); // 找到Java类
    jmethodID constructor = env->GetMethodID(resultClass, "<init>", "(II)V"); // 找到构造方法
    jobject resultObj = env->NewObject(resultClass, constructor, n, result);
    return resultObj;
}