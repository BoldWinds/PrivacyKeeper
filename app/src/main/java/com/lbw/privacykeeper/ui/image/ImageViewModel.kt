package com.lbw.privacykeeper.ui.image

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.ui.nav.AppSecondaryScreen
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ImageViewModel(
    private val imageRepository : ImageRepository,
    private val biometricCheckParameters: BiometricCheckParameters,
):ViewModel() {


    fun openBiometricCheck(navController: NavHostController){
        val biometricCheck = BiometricCheck(
            biometricCheckParameters = biometricCheckParameters,
            onSuccess = { navController.navigate(AppSecondaryScreen.Image.route) }
        )
        biometricCheck.launchBiometric()
    }

    private var uri : Uri = Uri.EMPTY

    fun setNewUri(newUri: Uri){
        uri = newUri
    }

    fun saveImage(){
        viewModelScope.launch(Dispatchers.IO) {
            if (uri!= Uri.EMPTY)
                imageRepository.save(uri,uri.toString())
        }
    }

    var filenames : List<String> = mutableListOf<String>()

    fun getFilenames(){
        viewModelScope.launch {
            filenames = imageRepository.readAll()
        }
    }

    private var imageBitmap : ImageBitmap = ImageBitmap(1,1)

    fun setImageBitmap(filename : String){
        viewModelScope.launch {
            imageBitmap = imageRepository.toImageBitmap(filename)
        }
    }

    fun getImageBitmap():ImageBitmap{
        return imageBitmap
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