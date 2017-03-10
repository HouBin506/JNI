package com.herenit.jnidemo05;

import android.util.Log;

/**
 * Created by HouBin on 2017/3/8.
 */

public class JavaSimple {
    static {
        System.loadLibrary("native-lib");
    }

    private static final String TAG = JavaSimple.class.getSimpleName();


    public void sayHello(){
        Log.e(TAG,"C调用Java的无参数无返回值方法：sayHello()");
    }

    public int getSum(int a, int b){
        return a+b;
    }

    public void LogString(String text){
        Log.e(TAG,"C调用Java的String参数方法：LogString(String text)---"+text);
    }

    public static void staticFunction(String text){
        Log.e(TAG,"C调用Java的静态String参数方法：staticFunction(String text)---"+text);
    }

    public native void cCallJava_sayHello();
    public native int cCallJava_getSum(int num1, int num2);
    public native void cCallJava_LogString();
    public native void cCallJava_staticFunction();
}
