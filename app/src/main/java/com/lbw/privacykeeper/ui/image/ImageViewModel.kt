package com.lbw.privacykeeper.ui.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import com.lbw.privacykeeper.utils.ImageManager

class ImageViewModel(
    private val imageRepository : ImageRepository,
    private val imageManager : ImageManager,
    private val biometricCheckParameters: BiometricCheckParameters
):ViewModel() {

    companion object{
        fun provideFactory(
            imageRepository: ImageRepository,
            imageManager: ImageManager,
            biometricCheckParameters: BiometricCheckParameters
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return ImageViewModel(imageRepository,imageManager, biometricCheckParameters) as T
            }
        }
    }
}