package com.lbw.privacykeeper.ui.video

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lbw.privacykeeper.data.video.VideoRepository
import com.lbw.privacykeeper.ui.nav.AppSecondaryScreen
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoViewModel(
    private val videoRepository: VideoRepository,
    private val biometricCheckParameters: BiometricCheckParameters
):ViewModel() {

    fun openBiometricCheck(navController: NavHostController){
        val biometricCheck = BiometricCheck(
            biometricCheckParameters = biometricCheckParameters,
            onSuccess = { navController.navigate(AppSecondaryScreen.Video.route) }
        )
        biometricCheck.launchBiometric()
    }

    private var uri : Uri = Uri.EMPTY

    fun setNewUri(newUri: Uri){
        uri = newUri
    }

    fun saveVideo(){
        viewModelScope.launch(Dispatchers.IO) {
            if (uri!= Uri.EMPTY)
                videoRepository.save(uri,uri.toString())
        }
    }

    var filenames : List<String> = mutableListOf<String>()

    fun getFilenames(){
        viewModelScope.launch {
            filenames = videoRepository.readAllFilenames()
        }
    }


    companion object{
        fun provideFactory(
            videoRepository: VideoRepository,
            biometricCheckParameters: BiometricCheckParameters
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return VideoViewModel(videoRepository, biometricCheckParameters) as T
            }
        }
    }

}