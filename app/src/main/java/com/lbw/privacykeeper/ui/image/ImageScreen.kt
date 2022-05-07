package com.lbw.privacykeeper.ui.image

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lbw.privacykeeper.ui.password.PasswordCard
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.GalleryButton
import com.lbw.privacykeeper.ui.utils.UriType
import privacykeeperv1.R
import kotlin.reflect.KFunction1

@Composable
fun ImageScreen(
    imageViewModel: ImageViewModel
) {
    if(!imageViewModel.permission){
        ImageScreen(
            setUri = imageViewModel::setNewUri,
            saveImage = imageViewModel::saveImage,
            openBiometricCheck = imageViewModel::openBiometricCheck
        )
    }else{
        imageViewModel.getFilenames()
        ImageScreen(filenames = imageViewModel.filenames)
    }
}


@Composable
fun ImageScreen(
    setUri: (Uri)->Unit,
    saveImage: ()->Unit,
    openBiometricCheck: ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.weight(0.4f))

        GalleryButton(
            setUri = setUri, 
            save = { saveImage() }, 
            uriType = UriType.Image
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Button(onClick = {openBiometricCheck()}) {
            Text(text = stringResource(id = R.string.show_image))
        }

        Spacer(modifier = Modifier.weight(0.4f))
    }

}


@Preview
@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageScreen() {
    PrivacyKeeperTheme {
     //   ImageScreen()
    }
}



@Composable
fun ImageScreen(filenames:List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        itemsIndexed(filenames){ _, item->
            
            ImageCard(filename = item)

        }
    }
}

@Preview
@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageScreen2() {
    PrivacyKeeperTheme {
        val list : List<String> = mutableListOf(
            "FirstImage",
            "SecondImage",
            "ThirdImage",
            "FourthImage"
        )

        ImageScreen(list)
    }
}

