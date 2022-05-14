package com.lbw.privacykeeper.ui.image

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.model.UriType
import com.lbw.privacykeeper.ui.nav.AppTertiaryScreen
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.*
import privacykeeperv1.R


@Composable
fun ImageScreen(
    setUri: (Uri)->Unit,
    saveImage: ()->Unit,
    openBiometricCheck: (NavHostController)->Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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

        Button(onClick = {openBiometricCheck(navController)}) {
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
        val navController = rememberNavController()

        ImageScreen(
            setUri = {},
            saveImage = {},
            openBiometricCheck = {},
            navController = navController
        )
    }
}



@Composable
fun ImageScreen(
    filenames:List<String>,
    showDialog : Boolean,
    navController: NavHostController,
    setOldFilename : (String)->Unit,
    openDialog : ()->Unit,
    closeDialog : ()->Unit,
    rename : (String)->Unit,
    delete : (String)->Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        itemsIndexed(filenames){ _, item->
            ImageOrVideoCard(
                filename = item,
                openImage = {
                    navController.navigate(AppTertiaryScreen.Image.withArgs(it))
                },
                setOldName = setOldFilename,
                openDialog = openDialog,
                delete = delete
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
    }


    var newName by remember {
        mutableStateOf("")
    }

    CustomDialog(
        showDialog = showDialog,
        title = stringResource(id = R.string.rename),
        closeDialog = closeDialog,
        onConfirm = {
            rename(newName)
            newName = ""
        },
        onDismiss = {
            newName = ""
        }
    ) {
        CommonTextField(
            value = newName,
            onValueChange = {newName = it},
            labelText = stringResource(id = R.string.rename)
        )
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

        val navController = rememberNavController()

        ImageScreen(
            filenames = list,
            showDialog = false,
            setOldFilename = {},
            openDialog = {},
            rename = {},
            closeDialog = {},
            navController = navController,
            delete = {}
        )
    }
}

