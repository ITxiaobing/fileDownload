package com.zksn.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity", "demoone");
        HttpUtils http = new HttpUtils();
        HttpHandler handler = http.download("http://download.lenovo.com/test/mobileapp/pre-load/LCM_829UAT_PRE_0818.apk",
                Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/xiaobao.apk", true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
                true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
                new RequestCallBack<File>() {

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onLoading(long total, long current,
                                          boolean isUploading) {


                    }

                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
//
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });
    }

}
