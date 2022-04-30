package com.lbw.privacykeeper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrivacyKeeperTheme(mainViewModel.themeMode){

                //检查配置文件，进行设置


                if(mainViewModel.showGuidance){
                    GuideScreen(
                        mainViewModel.showGuidance
                    ) { mainViewModel.closeGuidance() }
                }else{
                    mainViewModel.openMain()
                }

                if(mainViewModel.showMain){
                    MainScreen(mainViewModel)
                }
            }
        }
    }
}


