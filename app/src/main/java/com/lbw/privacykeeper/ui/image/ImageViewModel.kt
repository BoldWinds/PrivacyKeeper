package com.lbw.privacykeeper.ui.image

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(
    private val imageRepository : ImageRepository,
    private val biometricCheckParameters: BiometricCheckParameters
):ViewModel() {

    //访问图片库的权限
    var permission by mutableStateOf(false)

    fun openBiometricCheck(){
        val biometricCheck = BiometricCheck(
            biometricCheckParameters = biometricCheckParameters,
            onSuccess = { permission = true }
        )
        biometricCheck.launchBiometric()
    }

    private var uri : Uri? = null

    fun setNewUri(newUri: Uri){
        uri = newUri
    }

    fun saveImage(){
        if(uri != null){
            viewModelScope.launch(Dispatchers.IO) {
                imageRepository.save(uri!!,uri.toString())
            }
        }else{
            Log.d("error","uri is null")
        }
    }

    var filenames : List<String> = mutableListOf<String>()

    fun getFilenames(){
        viewModelScope.launch {
            filenames = imageRepository.readAll()
        }
    }

    private var bitmap : ImageBitmap? = null

    fun getImageBitmap(filename : String){
        viewModelScope.launch {
            bitmap = imageRepository.toImageBitmap(filename)
        }
    }


    companion object{
        fun provideFactory(
            imageRepository: ImageRepository,
            biometricCheckParameters: BiometricCheckParameters
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return ImageViewModel(imageRepository, biometricCheckParameters) as T
            }
        }
    }
}