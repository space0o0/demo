package com.example.space.test.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.space.test.callback.exitCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统工具
 * Created by space on 16/3/25.
 */
public class sysUtils {

    /**
     * 在主页调用，用于退出软件
     *
     * @param activity
     * @param exitcallback 退出的相关操作
     */
    public static void exit(final Activity activity, final exitCallBack exitcallback) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提示");
        builder.setMessage("确定退出该软件？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
                exitcallback.onExit();
                exitApp();
            }
        });
        builder.create().show();
    }

    /**
     * 从任意activity中退出app
     * 同时退出openIM
     */
    public static void exitApp() {
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(0);
    }

    /**
     * 收到消息的提示音
     *
     * @param context
     * @throws IOException
     */
    public static void soundRing(Context context) throws IOException {
        MediaPlayer mp = new MediaPlayer();
        mp.reset();
        mp.setDataSource(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        mp.prepare();
        mp.start();
    }

    public static void showNotification() {

//        NotificationManager nm = (NotificationManager) ETFApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Notification notification = new Notification.Builder(ETFApplication.getInstance())
//                .setTicker("ticker").build();
////        YTFjrToast.show(ETFApplication.getInstance(),"show notification!");
//        nm.notify(1, notification);

    }

    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    /**
     * 复制文本至剪贴板
     * @param content 需要复制的内容
     * @param showToast 复制成功后的提示
     */
    public static void copyText(String content,String showToast){
//        ClipboardManager cm= (ClipboardManager) ETFApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
//        cm.setPrimaryClip(ClipData.newPlainText("text",content));
//        YtfjrToast.show(ETFApplication.getInstance(),showToast);
    }

    /**
     * 判断是否需要隐藏输入框
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 跳转到中国银行手机银行
     */
//    public static void goToBocMobile(final Activity activity){
//        if (sysUtils.isAppInstalled(ETFApplication.getInstance(), constant.BOC_MOBILE_PAGENAME)) {
//            // TODO: 16/6/28 跳转到中国银行手机银行
//            Intent intent = new Intent();
//            PackageManager packageManager = activity.getPackageManager();
//            intent = packageManager.getLaunchIntentForPackage(constant.BOC_MOBILE_PAGENAME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            activity.startActivity(intent);
//        } else {
//            YtfjrToast.show(activity,"请安装中国银行手机银行app！");
//            // TODO: 16/6/28 跳转到中国银行手机银行下载地址
//            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
//            builder.setTitle("提示")
//                    .setMessage("该手机没有安装中国银行手机银行，是否前往下载")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent=new Intent();
//                            intent.setAction("android.intent.action.VIEW");
//                            Uri content_url = Uri.parse(constant.BOC_MOBILE_DOWNLOAD_URL);
//                            intent.setData(content_url);
//                            activity.startActivity(intent);
//
//                        }
//                    })
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
//                    .show();
//        }
//    }
}
