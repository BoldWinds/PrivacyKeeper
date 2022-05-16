package com.lbw.privacykeeper.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lbw.privacykeeper.PrivacyKeeperApplication
import com.lbw.privacykeeper.utils.Utils


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        //让启动画面显示时间更长一些
        Thread.sleep(500)
        super.onCreate(savedInstanceState)
        val appContainer = (application as PrivacyKeeperApplication).container
        val biometricCheckParameters = (application as PrivacyKeeperApplication).biometricCheckParameters

        setContent {
            PrivacyKeeperApp(appContainer = appContainer, biometricCheckParameters = biometricCheckParameters)
        }
    }

    override fun onDestroy() {
        //在退出APP时删除所有的已解密文件
        super.onDestroy()
        Utils.deleteAllDecrypted(application)
    }

}
