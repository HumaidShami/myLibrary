package com.general.h.dev.employees.util

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object E_Permission {

    @RequiresApi(Build.VERSION_CODES.R)
    fun showAppPermissionDialog(activity: Activity) {
        MaterialAlertDialogBuilder(activity)
            .setTitle("طلب تصريح")
            .setMessage("يجب منح الاذن لحفظ ألتقرير أليومي في ألهاتف")
            .setPositiveButton("منح") { _, _ -> permissions_for_R(activity) }
            .setNegativeButton("رفض" ){ _, _ -> Toast.makeText(activity , " رفض",Toast.LENGTH_SHORT).show() }
            .create()
            .show()
    }

    fun checkPermission_me(activity: Activity): Boolean {
//        activity.applicationContext.packageName.log_("packageName")
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            val result =
                ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
            val result1 =
                ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermission(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            showAppPermissionDialog(activity)
        } else {
            //below android 11
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                2296
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
     // above android 11
     fun  permissions_for_R(activity: Activity) {
        try {
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.addCategory("android.intent.category.DEFAULT")
            intent.data =
                Uri.parse(String.format("package:%s", activity.applicationContext.packageName))
            activity.startActivityForResult(intent, 2296)
        } catch (e: Exception) {
            val intent = Intent()
            intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
            activity.startActivityForResult(intent, 2296)
        }
    }

}