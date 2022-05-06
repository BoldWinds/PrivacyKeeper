package com.lbw.privacykeeper.utils

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat


class ImageManager(
    val context : Context
) {
    private var imageUri : Uri? = null

    fun getImageUri():Uri?{
        return imageUri
    }

    @Composable
    fun OpenGalleryButton(){
        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                galleryLauncher.launch("image/*")
            } else {
                Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }

        Button(
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context, android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) -> {
                        galleryLauncher.launch("image/*")
                    }
                    else -> {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
            }
        ){
            Text(text = "保存图片")
        }
    }

}