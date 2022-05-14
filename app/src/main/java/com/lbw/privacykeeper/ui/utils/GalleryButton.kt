package com.lbw.privacykeeper.ui.utils

import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.lbw.privacykeeper.model.UriType
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.utils.Utils.Companion.showToast
import privacykeeperv1.R


//通过uriType来决定打开的资源类型
@Composable
fun GalleryButton(
    setUri: (Uri)->Unit,
    save: ()->Unit,
    uriType: UriType
){
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if(uri!=null) {
            setUri(uri)
            save()
        }
    }



    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            if (uriType== UriType.Image){
                galleryLauncher.launch("image/*")
            }else{
                galleryLauncher.launch("video/*")
            }
        } else {
            showToast(
                context,
                context.getString(R.string.permission_denied)
            )
        }
    }

    val text = if(uriType== UriType.Image)   stringResource(id = R.string.save_image)
                else    stringResource(id = R.string.save_video)

    Button(
        onClick = {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context, android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {
                    if (uriType== UriType.Image){
                        galleryLauncher.launch("image/*")
                    }else{
                        galleryLauncher.launch("video/*")
                    }
                }
                else -> {
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ){

        Text(
            text = text,
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
            setUri = {_:Uri->},
            save = {},
            uriType = UriType.Image
        )
    }
}
