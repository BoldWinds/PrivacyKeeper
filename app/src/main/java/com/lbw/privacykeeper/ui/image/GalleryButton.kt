package com.lbw.privacykeeper.ui.image

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.utils.Utils.Companion.showToast
import privacykeeperv1.R


@Composable
fun GalleryButton(
    setUri : (Uri?)->Unit,
    saveImage : ()->Unit
){
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        setUri(uri)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            galleryLauncher.launch("image/*")
        } else {
            showToast(true,context, context.getString(R.string.permission_denied))
        }
    }

    Button(
        onClick = {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context, android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {
                    galleryLauncher.launch("image/*")
                    saveImage()
                }
                else -> {
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ){
        Text(
            text = stringResource(id = R.string.save_image),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewGalleryButton() {
    PrivacyKeeperTheme {
        GalleryButton(
            setUri = {},
            saveImage = {}
        )
    }
}
