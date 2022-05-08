package com.lbw.privacykeeper.ui.video

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.lbw.privacykeeper.ui.utils.GalleryButton
import com.lbw.privacykeeper.ui.utils.UriType
import privacykeeperv1.R

@Composable
fun VideoScreen(
    setUri: (Uri)->Unit,
    saveVideo: ()->Unit,
    openBiometricCheck: (NavHostController)->Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.weight(0.4f))

        GalleryButton(
            setUri = setUri,
            save = { saveVideo() },
            uriType = UriType.Image
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Button(onClick = {openBiometricCheck(navController)}) {
            androidx.compose.material3.Text(text = stringResource(id = R.string.show_image))
        }

        Spacer(modifier = Modifier.weight(0.4f))
    }

}