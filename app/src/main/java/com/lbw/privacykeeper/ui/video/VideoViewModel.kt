package com.lbw.privacykeeper.ui.video

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lbw.privacykeeper.data.video.VideoRepository
import com.lbw.privacykeeper.ui.nav.AppSecondaryScreen
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import com.lbw.privacykeeper.utils.Utils
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
                videoRepository.save(uri, Utils.getUriName(uri))
        }
    }

    fun rename(newFilename : String){
        viewModelScope.launch(Dispatchers.IO) {
            videoRepository.renameFile(oldFilename = oldFilename, newFilename = newFilename)
            //更新了名字之后要重新获取
            filenames = videoRepository.readAllFilenames()
        }
    }

    var showDialog by mutableStateOf<Boolean>(false)

    fun openDialog(){
        showDialog = true
    }

    fun closeDialog(){
        showDialog = false
    }

    private var oldFilename : String = ""

    fun setOldFilename(filename: String){
        oldFilename = filename
    }

    var filenames : List<String> by mutableStateOf<List<String>>(mutableListOf<String>())

    fun getFilenames(){
        viewModelScope.launch {
            filenames = videoRepository.readAllFilenames()
        }
    }

    fun delete(filename: String){
        viewModelScope.launch {
            videoRepository.delete(filename)
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