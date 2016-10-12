package com.zksn.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity {


    private Callback.ProgressCallback<File> progressCallback;
    private Callback.Cancelable cancelable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downLoadFile("http://gdown.baidu.com/data/wisegame/00c72602fb47f929/baidushoujizhushouyuan91zhu_16790412.apk", Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "lenovo/lenovo companion - mobile" + "serviceit.apk");
    }

    public void downLoadFile(String url, final String path) {


        RequestParams params = new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(path);
        cancelable = x.http().get(params, new Callback.ProgressCallback<File>() {


            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                Toast.makeText(x.app(), "开始下载", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                BigDecimal b = new BigDecimal((float) current / (float) total);
                float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                Log.e("tag",f1+"===================");

            }

            @Override
            public void onSuccess(File result) {

                Toast.makeText(x.app(), "下载成功", Toast.LENGTH_LONG).show();
                installApp(path);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public void installApp(String filePath) {
        File _file = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(_file),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

    public void start(View view) {
        downLoadFile("http://gdown.baidu.com/data/wisegame/00c72602fb47f929/baidushoujizhushouyuan91zhu_16790412.apk", Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "lenovo/lenovo companion - mobile" + "serviceit.apk");
    }

    public void stop(View view) {

        cancelable.cancel();
    }

}
