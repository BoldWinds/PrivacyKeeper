package com.lbw.privacykeeper.ui.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.utils.BiometricCheckParameters

class ImageViewModel(
    private val imageRepository : ImageRepository,
    private val biometricCheckParameters: BiometricCheckParameters
):ViewModel() {




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