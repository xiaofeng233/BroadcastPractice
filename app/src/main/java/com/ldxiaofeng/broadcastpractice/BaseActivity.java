package com.ldxiaofeng.broadcastpractice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by 13178 on 2018-6-18.
 */

public class BaseActivity extends AppCompatActivity {
    private ForceOffLineReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
    //要求只有栈顶的活动才能收到广播，非栈顶的活动就没必要去接受这条广播，所以写在onResume和onPause里
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ldxiaofeng.broadcastpractice.FORCE_OFFLINE");
        mReceiver = new ForceOffLineReceiver();
        registerReceiver(mReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mReceiver!=null){
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOffLineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            //构建对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("你被强制下线，请重新登录");
            builder.setCancelable(false);//设置对话框不可取消
            //注册确定按钮
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    ActivityCollector.finishAll();//销毁全部活动
                    Intent intent= new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
