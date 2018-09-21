package com.example.skyhuang.permissiondemo.utils.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;


import java.util.ArrayList;

/**
 * Created by skyHuang on 2018/8/27.
 * 动态请求权限工具
 */

public class PermissionUtil {


    /**
     * 判断传入的权限是否需要授予权限
     *
     * @param context
     * @param permission
     * @return true 已经有该权限了
     */

    public static boolean checkPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 弹出对话框请求权限
     *
     * @param activity
     * @param permissions
     * @param requestCode
     */

    public static void requestPermissions(Activity activity, Fragment fragment, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (fragment != null) {
                fragment.requestPermissions(permissions, requestCode);
            } else {
                activity.requestPermissions(permissions, requestCode);
            }
        }
    }


    /**
     * 返回缺失的权限
     *
     * @param context
     * @param permissions
     * @return 返回缺少的权限，null 意味着没有缺少权限
     */
    public static String[] getDeniedPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> deniedPermissionList = new ArrayList<>();
            for (String permission : permissions) {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissionList.add(permission);
                }
            }
            int size = deniedPermissionList.size();
            if (size > 0) {
                return deniedPermissionList.toArray(new String[deniedPermissionList.size()]);
            }
        }
        return null;
    }

}
