package com.example.expensetracker.presentation.addTransaction

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.presentation.UiEvents
import com.example.expensetracker.presentation.theme.GREEN
import com.example.expensetracker.presentation.theme.RED
import com.example.expensetracker.util.Constant
import com.example.expensetracker.util.showToast
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AETransactionScreen(
    vm: AETransactionViewModel = hiltViewModel(),
    onNavigateUpClick: () -> Unit
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val context = LocalContext.current
    val transactionName by vm.name.collectAsState()
    val transactionAmount by vm.amount.collectAsState()
    val transactionMode by vm.transactionMode.collectAsState()
    val transactionType by vm.transactionType.collectAsState()
    val transactionDate by vm.transactionDate.collectAsState()
    val transactionDescription by vm.description.collectAsState()
    val backgroundColor =
        if (transactionType == Constant.TransactionType.INCOME) GREEN else RED


    LaunchedEffect(key1 = true) {
        vm.uiEventFlow.collectLatest {
            when (it) {
                is UiEvents.ShowToast -> {
                    showToast(context = context, message = it.message, isLenShort = it.isLenShort)
                }
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        TopSection(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight / 2.6).dp),
            transactionType = transactionType,
            topSectionHeight = screenHeight / 2.6,
            date = transactionDate,
            amount = transactionAmount,
            onAmountChange = {
                vm.onEvents(AETransactionEvent.OnAmountChange(it))
            },
            onNavigateUpClick = onNavigateUpClick
        )

        BottomSection(
            bottomSectionHeight = (screenHeight / 1.4).dp,
            selectedMode = transactionMode,
            name = transactionName,
            description = transactionDescription,
            onModeChange = {
                vm.onEvents(AETransactionEvent.OnModeChange(it))
            },
            onNameChange = {
                vm.onEvents(AETransactionEvent.OnNameChange(it))
            },
            onDescriptionChange = {
                vm.onEvents(AETransactionEvent.OnDescriptionChange(it))
            },
            onSaveBtnClick = {
                vm.onEvents(AETransactionEvent.OnSaveBtnClick)
            }
        )

    }

}

@Composable
private fun TopSection(
    modifier: Modifier = Modifier,
    transactionType: String,
    topSectionHeight: Double,
    date: String,
    amount: String,
    onAmountChange: (String) -> Unit,
    onNavigateUpClick: () -> Unit
) {
    Column(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = Color.White,
                    modifier = Modifier
                        .weight(0.1f)
                        .clickable { onNavigateUpClick() }
                        .size(30.dp)
                )
                Text(
                    text = transactionType,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(0.8f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .weight(0.1f)
                        .size(30.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = date,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height((topSectionHeight / 5.5).dp))

        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(text = "How much?", color = Color.White.copy(alpha = 0.7f), fontSize = 18.sp)
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "₹", color = Color.White, fontSize = 75.sp)
                Spacer(modifier = Modifier.width(10.dp))
                BasicTextField(
                    value = amount,
                    onValueChange = {
                        onAmountChange(it)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 75.sp
                    )
                )
            }
        }
    }

}

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    bottomSectionHeight: Dp,
    selectedMode: String,
    name: String,
    description: String,
    onModeChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveBtnClick: () -> Unit
) {

    val modeOptions = listOf(Constant.TransactionMode.CASH, Constant.TransactionMode.ONLINE)

    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(bottomSectionHeight)
                .clip(RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                .background(Color.White)
        )
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            label = {
                Text(
                    text = "Name"
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { onDescriptionChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 20.dp),
            label = {
                Text(
                    text = "Description"
                )
            }
        )

        Spacer(modifier = Modifier.height(30.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Mode:", fontSize = 18.sp)
            modeOptions.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = it == selectedMode,
                        onClick = {
                            onModeChange(it)
                        }
                    )
                    Text(text = it, fontSize = 15.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onSaveBtnClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Save")
        }

    }
}