package cn.edu.hpu.yuan.yuannews.base.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yuan on 16-5-8.
 * BaseActivity 基础类：
 * 功能:
 * 1.初始化toolbar
 * 2.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
