package com.example.task2gdsc

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.task2gdsc.databinding.FragmentMenu1Binding

class Menu1Fragment : Fragment() {
    private lateinit var binding:FragmentMenu1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    data class DeviceInformation(
        var name:String = "",
        var model:String = "",
        var sdkVersion:String = "",
        var androidVersion:String = ""
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenu1Binding.inflate(layoutInflater)
        val deviceInfo = getInfoDevice()
        updateUI(deviceInfo)

        return binding.root
    }

    private fun updateUI(deviceInfo:DeviceInformation) {
        binding.tvDeviceName.text = deviceInfo.name
        binding.tvDeviceModel.text = deviceInfo.model
        binding.tvDeviceSdkVersion.text = deviceInfo.sdkVersion
        binding.tvDeviceAndroidVersion.text = deviceInfo.androidVersion
    }

    private fun getInfoDevice(): DeviceInformation {
        val product = Build.PRODUCT
        val versionRelease = Build.VERSION.RELEASE
        val sdkInt = Build.VERSION.SDK_INT
        val nameDevice = Build.DEVICE
        return DeviceInformation(nameDevice, product, sdkInt.toString(), versionRelease)
    }

}