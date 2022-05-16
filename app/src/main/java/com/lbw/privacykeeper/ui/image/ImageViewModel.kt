package com.lbw.privacykeeper.ui.image

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.lbw.privacykeeper.data.image.ImageRepository
import com.lbw.privacykeeper.ui.nav.AppSecondaryScreen
import com.lbw.privacykeeper.model.UriType
import com.lbw.privacykeeper.model.User
import com.lbw.privacykeeper.utils.BiometricCheck
import com.lbw.privacykeeper.utils.BiometricCheckParameters
import com.lbw.privacykeeper.utils.Utils.Companion.getRandomName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class PrimaryImageViewModel(
    private val imageRepository : ImageRepository,
    private val biometricCheckParameters: BiometricCheckParameters,
    private val navController: NavHostController
):ViewModel() {

    fun openBiometricCheck(){
        val biometricCheck = BiometricCheck(
            biometricCheckParameters = biometricCheckParameters,
            onSuccess = {onSuccess()},
            openPermissionDialog ={openPermissionDialog()}
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
        navController.navigate(AppSecondaryScreen.Image.route)
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
    fun saveImage(){
        viewModelScope.launch(Dispatchers.IO) {
            if (uri!= Uri.EMPTY)
                imageRepository.save(uri,getRandomName(uri, UriType.Image))
        }
    }


    companion object{
        fun provideFactory(
            imageRepository: ImageRepository,
            biometricCheckParameters: BiometricCheckParameters,
            navController: NavHostController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return PrimaryImageViewModel(imageRepository, biometricCheckParameters,navController) as T
            }
        }
    }
}

class SecondaryImageViewModel(
    private val imageRepository: ImageRepository
):ViewModel(){

    var filenames : List<String> by mutableStateOf<List<String>>(mutableListOf<String>())

    fun getFilenames(){
        viewModelScope.launch {
            filenames = imageRepository.readAllFilenames()
        }
    }

    private var oldFilename : String = ""

    fun setOldFilename(filename: String){
        oldFilename = filename
    }

    fun rename(newFilename : String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                imageRepository.renameFile(oldFilename = oldFilename, newFilename = newFilename)
                filenames = imageRepository.readAllFilenames()
            }catch (e : Exception){

            }
        }
    }

    var showDialog by mutableStateOf<Boolean>(false)

    fun openDialog(){
        showDialog = true
    }

    fun closeDialog(){
        showDialog = false
    }

    fun delete(filename: String){
        viewModelScope.launch {
            imageRepository.delete(filename)
            filenames = imageRepository.readAllFilenames()
        }
    }

    companion object{
        fun provideFactory(
            imageRepository: ImageRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return SecondaryImageViewModel(imageRepository) as T
            }
        }
    }
}


class TertiaryImageViewModel(
    private val imageRepository : ImageRepository,
):ViewModel(){

    private var imageBitmap : ImageBitmap = ImageBitmap(1,1)

    fun setImageBitmap(filename : String){
        viewModelScope.launch {
            imageBitmap = imageRepository.getImageBitmap(filename)
        }
    }

    fun getImageBitmap():ImageBitmap{
        return imageBitmap
    }

    companion object{
        fun provideFactory(
            imageRepository: ImageRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("main")
            override fun <T : ViewModel> create(modelClass : Class<T>):T{
                return TertiaryImageViewModel(imageRepository) as T
            }
        }
    }
}