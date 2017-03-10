package com.herenit.jnidemo05;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * C代码中调用java某一个类中的方法，用到了C的反射技术
 */
public class MainActivity extends Activity {

    private JavaSimple javaSimple;
    private EditText et_num1,et_num2;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        javaSimple = new JavaSimple();
        et_num1 = (EditText) findViewById(R.id.et_num1);
        et_num2 = (EditText) findViewById(R.id.et_num2);
        tv_result = (TextView) findViewById(R.id.tv_sum);
    }

    /**
     * 使用反射技术调用某一个类的某个方法
     * @param view
     */
    public void reflectOnclick(View view){
        try {
            //1，获取该类的字节码文件
            Class clazz = JavaSimple.class;
            //2，根据获取该类的实例对象
            Object javaSimple = clazz.newInstance();
            //3，获取该类的某一个方法对象（Method）
            Method mGetSum = clazz.getDeclaredMethod("getSum",int.class,int.class);
            //4，调用对应的方法
            int result = (int) mGetSum.invoke(javaSimple,3,2);
            Toast.makeText(this, "反射调用成功：result="+result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击按钮，用C调用java的JavaSimple的sayHello方法
     * Log日志看现象
     * @param view
     */
    public void cCallJava_sayHello(View view){
        javaSimple.cCallJava_sayHello();
    }

    /**
     * 点击按钮，C调用java的JavaSimple的getSum方法
     * 界面操作看现象
     * @param view
     */
    public void cCallJava_getSum(View view){
        String str1 = et_num1.getText().toString().trim();
        String str2 = et_num2.getText().toString().trim();
        if(!TextUtils.isEmpty(str1) && !TextUtils.isEmpty(str2)){
            int num1 = Integer.valueOf(str1);
            int num2 = Integer.valueOf(str2);
            int result = javaSimple.cCallJava_getSum(num1,num2);
            tv_result.setText(""+result);
        }
    }

    /**
     * 点击按钮，C调用java的JavaSimple的LogString方法
     * 从log日志看现象
     * @param view
     */
    public void cCallJava_LogString(View view){
        javaSimple.cCallJava_LogString();
    }

    /**
     * 点击按钮，C调用java的JavaSimple的staticFunction静态方法
     * 从Log日志看现象
     * @param view
     */
    public void cCallJava_staticFunction(View view){
        javaSimple.cCallJava_staticFunction();
    }
}
