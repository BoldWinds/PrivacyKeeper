package com.lbw.privacykeeper.ui.video

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lbw.privacykeeper.data.video.VideoRepository
import com.lbw.privacykeeper.ui.nav.AppSecondaryScreen
import com.lbw.privacykeeper.model.UriType
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import com.lbw.privacykeeper.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrimaryVideoViewModel(
    private val videoRepository: VideoRepository,
    private val biometricCheckParameters: BiometricCheckParameters,
    private val navController: NavHostController
):ViewModel() {

    fun openBiometricCheck(){
        val biometricCheck = BiometricCheck(
            biometricCheckParameters = biometricCheckParameters,
            onSuccess = {
                onSuccess()
            },
            openPermissionDialog = {
                openPermissionDialog()
            }
        )
        biometricCheck.launchBiometric()
    }

    var showPermissionDialog by mutableStateOf(false)

    fun openPermissionDialog(){
        showPermissionDialog = true
    }

    fun closePermissionDialog(){
        showPermissionDialog = false
    }

    fun checkPermission(password: String){
        if (password == user.password){
            onSuccess()
        }
    }

    fun onSuccess(){
        navController.navigate(AppSecondaryScreen.Video.route)
    }

    var user by mutableStateOf(User("",""))

    @JvmName("setUser1")
    fun setUser(u : User){
        user = u
    }


    private var uri : Uri = Uri.EMPTY

    fun setNewUri(newUri: Uri){
        uri = newUri
    }

    fun saveVideo(){
        viewModelScope.launch(Dispatchers.IO) {
            if (uri!= Uri.EMPTY)
                videoRepository.save(uri, Utils.getRandomName(uri, UriType.Video))
        }
    }

    var showLoading by mutableStateOf(false)

    fun openLoading(){
        showLoading = true
    }

    fun closeLoading(){
        showLoading = false
    }


    companion object{
        fun provideFactory(
            videoRepository: VideoRepository,
            biometricCheckParameters: BiometricCheckParameters,
            navController: NavHostController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return PrimaryVideoViewModel(videoRepository, biometricCheckParameters,navController) as T
            }
        }
    }
}


class SecondaryVideoViewModel(
    private val videoRepository: VideoRepository
):ViewModel(){
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
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return SecondaryVideoViewModel(videoRepository) as T
            }
        }
    }
}

class TertiaryVideoViewModel(
    private val videoRepository: VideoRepository
):ViewModel(){

    var isJobFinished by mutableStateOf(false)

    fun openVideo(){
        isJobFinished = true
    }

    private var filename : String = ""

    fun setFilename(name:String){
        filename = name
    }

    fun readVideo(){
        viewModelScope.launch(Dispatchers.IO) {
            videoRepository.read(filename)
        }
    }

    fun deleteDecrypted(){
        viewModelScope.launch {
            videoRepository.deleteDecrypted()
        }
    }

    companion object{
        fun provideFactory(
            videoRepository: VideoRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return TertiaryVideoViewModel(videoRepository) as T
            }
        }
    }
}