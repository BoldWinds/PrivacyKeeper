package com.lbw.privacykeeper.utils

import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import privacykeeperv1.R

data class BiometricCheckParameters(
    val context : Context,
    val packageManager: PackageManager,
    val keyguardManager: KeyguardManager
)


class BiometricCheck (
    private val biometricCheckParameters: BiometricCheckParameters,
    val onSuccess : ()->Unit,
    val openPermissionDialog:()->Unit
){

    private var cancellationSignal: CancellationSignal?=null

    private val mainExecutor = ContextCompat.getMainExecutor(biometricCheckParameters.context)


    private val authenticationCallback : BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                onSuccess()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)

            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)

            }

        }


    private fun checkBiometricSupport() : Boolean{
        val keyGuardManager = biometricCheckParameters.keyguardManager

        if(!keyGuardManager.isDeviceSecure){
            return true
        }

        if(ActivityCompat.checkSelfPermission(biometricCheckParameters.context,android.Manifest.permission.USE_BIOMETRIC)!= PackageManager.PERMISSION_GRANTED){
            return false
        }

        return biometricCheckParameters.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)

    }

    //这个是用来启动bio验证滴
    fun launchBiometric(){
        if (checkBiometricSupport()&&Build.VERSION.SDK_INT>30){
            val biometricPrompt = BiometricPrompt.Builder(biometricCheckParameters.context)
                .apply {
                    setTitle(biometricCheckParameters.context.getString(R.string.confirm_transaction))
                    setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }.build()
            biometricPrompt.authenticate(getCancellationSignal(),mainExecutor,authenticationCallback)
        }else{
            //添加密码认证
            openPermissionDialog()
        }

    }


    private fun getCancellationSignal():CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            Toast.makeText(biometricCheckParameters.context,"Authentication Cancelled Signal", Toast.LENGTH_SHORT).show()
        }

        return cancellationSignal as CancellationSignal
    }
}