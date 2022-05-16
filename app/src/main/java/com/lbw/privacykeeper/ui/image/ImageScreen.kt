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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.model.UriType
import com.lbw.privacykeeper.ui.nav.AppTertiaryScreen
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme
import com.lbw.privacykeeper.ui.utils.*
import com.lbw.privacykeeper.utils.Utils
import privacykeeperv1.R


@Composable
fun ImageScreen(
    setUri: (Uri)->Unit,
    saveImage: ()->Unit,
    openBiometricCheck: ()->Unit,
    showPermissionDialog : Boolean,
    closePermissionDialog : ()->Unit,
    checkPermission : (String)->Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.weight(0.2f))

        Text(
            text = stringResource(id = R.string.secure_privacy),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight(2),
        )

        Spacer(modifier = Modifier.weight(0.15f))

        GalleryButton(
            setUri = setUri, 
            save = {
                saveImage()
                Utils.showToast(
                    context = context,
                    text = context.getString(R.string.save_succeed)
                )
            },
            uriType = UriType.Image
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Button(onClick = {openBiometricCheck()}) {
            Text(text = stringResource(id = R.string.show_image))
        }

        Spacer(modifier = Modifier.weight(0.4f))
    }

    PermissionDialog(
        showPermissionDialog = showPermissionDialog,
        closePermissionDialog = closePermissionDialog,
        checkPermission =  checkPermission
    )

}


@Preview
@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewImageScreen() {
    PrivacyKeeperTheme {

        ImageScreen(
            setUri = {},
            saveImage = {},
            openBiometricCheck = {},
            showPermissionDialog =false,
            closePermissionDialog ={},
            checkPermission ={}
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

    RenameDialog(
        showDialog = showDialog,
        closeDialog = closeDialog,
        rename = rename
    )
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

