package com.general.mylibrary

import android.app.Activity
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object TestDailog {

    fun showADialog(activity: Activity) {
        MaterialAlertDialogBuilder(activity)
            .setTitle("طلب تصريح")
            .setMessage("يجب منح الاذن لحفظ ألتقرير أليومي في ألهاتف")
            .setPositiveButton("منح") { _, _ ->  Toast.makeText(activity , " منح", Toast.LENGTH_SHORT).show()  }
            .setNegativeButton("رفض" ){ _, _ -> Toast.makeText(activity , " رفض", Toast.LENGTH_SHORT).show() }
            .create()
            .show()
    }
}