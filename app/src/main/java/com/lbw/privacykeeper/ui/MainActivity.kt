package com.lbw.privacykeeper.ui

import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.lbw.privacykeeper.PrivacyKeeperApplication



class MainActivity : ComponentActivity() {
    private var cancellationSignal:CancellationSignal?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as PrivacyKeeperApplication).container
        setContent {
            PrivacyKeeperApp(appContainer = appContainer)

        }

    }

    private val authenticationCallback : BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@MainActivity,"Authentication Succeeded",Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@MainActivity,"Authentication Error Code: $errorCode",Toast.LENGTH_SHORT).show()

            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)

            }
        }

    private fun checkBiometricSupport() : Boolean{
        val keyGuardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if(!keyGuardManager.isDeviceSecure){
            return true
        }

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)!=PackageManager.PERMISSION_GRANTED){
            return false
        }

        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)

    }


    //这个是用来启动bio验证滴
    private fun launchBiometric(){
        if (checkBiometricSupport()){
            val biometricPrompt = BiometricPrompt.Builder(this)
                .apply {
                    setTitle("title")
                    setSubtitle("SubTitle")
                    setDescription("Description")
                    setNegativeButton("Button",mainExecutor) { _, _ ->
                        Toast.makeText(
                            this@MainActivity,
                            "Authentication Cancelled",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.build()

            biometricPrompt.authenticate(getCancellationSignal(),mainExecutor,authenticationCallback)
        }
    }

    private fun getCancellationSignal():CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            Toast.makeText(this,"Authentication Cancelled Signal",Toast.LENGTH_SHORT).show()
        }

        return cancellationSignal as CancellationSignal
    }
}


