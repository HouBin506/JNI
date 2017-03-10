#include <jni.h>
#include <syslog.h>

/**
 * 在这个方法中，使用C代码调用JavaSimple中的sayHello方法，使用的是C的反射技术
 */
JNIEXPORT void JNICALL
Java_com_herenit_jnidemo05_JavaSimple_cCallJava_1sayHello(JNIEnv *env, jobject instance) {
    /*1，获取该类的字节码文件
     * jclass      (*FindClass)(JNIEnv*, const char*);
     * char*参数 传入的是全类名(需要把点（.)改成斜线（/）
     *
     * */
    jclass clazz = (*env)->FindClass(env, "com/herenit/jnidemo05/JavaSimple");


    /*2，获取该类的某一个方法对象（jmethodID）
     * jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char* str1, const char* str2);
     * jclass 要调用的java类的字节码
     * str1 要调用的类的方法的方法名
     * str2 对应的这个方法的签名（可以在该类的.class文件所在的根目录下执行命令行：javap -s 全类名（不包括.java））得到
     * */
    jmethodID methodId = (*env)->GetMethodID(env, clazz, "sayHello", "()V");

//    3，根据获取该类的实例对象
    jobject javaSimple = (*env)->AllocObject(env, clazz);
    //4，调用对应的方法
    (*env)->CallVoidMethod(env, javaSimple, methodId);
}

/**
 * C调用java中的带参数的方法
 */
JNIEXPORT jint JNICALL
Java_com_herenit_jnidemo05_JavaSimple_cCallJava_1getSum(JNIEnv *env, jobject instance, jint num1,
                                                        jint num2) {
    jclass clazz = (*env)->FindClass(env, "com/herenit/jnidemo05/JavaSimple");
    jmethodID methodId = (*env)->GetMethodID(env, clazz, "getSum", "(II)I");
    jobject javaSimple = (*env)->AllocObject(env, clazz);
    int result = (*env)->CallIntMethod(env, javaSimple, methodId, num1, num2);
    return result;
}

/**
 * C调Java的 LogString(String text)方法
 */
JNIEXPORT void JNICALL
Java_com_herenit_jnidemo05_JavaSimple_cCallJava_1LogString(JNIEnv *env, jobject instance) {

    jclass clazz = (*env)->FindClass(env, "com/herenit/jnidemo05/JavaSimple");
    jmethodID methodId = (*env)->GetMethodID(env, clazz, "LogString", "(Ljava/lang/String;)V");
    jobject javaSimple = (*env)->AllocObject(env, clazz);

    jstring text = (*env)->NewStringUTF(env, "I am param");
    (*env)->CallVoidMethod(env, javaSimple, methodId, text);
}
/**
 * C调用java的静态方法static void staticFunction(String text)
 */
JNIEXPORT void JNICALL
Java_com_herenit_jnidemo05_JavaSimple_cCallJava_1staticFunction(JNIEnv *env, jobject instance) {
    //1，获取该方法对应的类的字节码文件类
    jclass clazz = (*env)->FindClass(env,"com/herenit/jnidemo05/JavaSimple");
    //2，获取methodId
    jmethodID methodId = (*env)->GetStaticMethodID(env,clazz,"staticFunction","(Ljava/lang/String;)V");
    //3，调用java的静态方法
    jstring text = (*env)->NewStringUTF(env,"I am from C,call Java staticFunction!");
    (*env)->CallStaticVoidMethod(env,clazz,methodId,text);
}