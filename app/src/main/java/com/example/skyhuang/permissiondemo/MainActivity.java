package com.example.skyhuang.permissiondemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.skyhuang.permissiondemo.utils.permission.PermissionHelper;
import com.example.skyhuang.permissiondemo.utils.permission.PermissionInterface;
import com.example.skyhuang.permissiondemo.utils.permission.PermissionUtil;

/**
 * Created by skyHuang on 2018/9/21.
 */

public class MainActivity extends AppCompatActivity implements PermissionInterface {

    private PermissionHelper permissionHelper;
    private int permissionType;
    private final int WRITE_CONTACTS = 1;
    private final int GET_CONTACTS = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionHelper = new PermissionHelper(this, null, this);
        Button writeContactsBtn = findViewById(R.id.write_contacts_btn);
        Button getContactsBtn = findViewById(R.id.get_contacts_btn);
        writeContactsBtn.setOnClickListener(v -> {
            if (!PermissionUtil.checkPermission(getParent(), Manifest.permission.WRITE_CONTACTS)) {
                permissionType = WRITE_CONTACTS;
                permissionHelper.checkPermissions();
            } else {
                Toast.makeText(this, "Application has WRITE_CONTACTS permission", Toast.LENGTH_SHORT).show();
            }
        });

        getContactsBtn.setOnClickListener(v -> {
            if (!PermissionUtil.checkPermission(getParent(), Manifest.permission.GET_ACCOUNTS)) {
                permissionType = GET_CONTACTS;
                permissionHelper.checkPermissions();
            } else {
                Toast.makeText(this, "Application has GET_ACCOUNTS permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getPermissionsRequestCode() {
        return 0;
    }

    @Override
    public String[] getPermissions() {
        if (permissionType == GET_CONTACTS) {
            return new String[]{Manifest.permission.GET_ACCOUNTS};
        } else {
            return new String[]{Manifest.permission.WRITE_CONTACTS};
        }
    }

    @Override
    public void requestPermissionsSuccess() {
        Toast.makeText(this, "App acquisition permission succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestPermissionsFail() {
        Toast.makeText(this, "App acquisition permission failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.requestPermissionsResult(requestCode, permissions, grantResults);
    }
}
