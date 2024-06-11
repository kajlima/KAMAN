package dev.fathoor.kaman.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import dev.fathoor.core.domain.model.report.ReportList
import dev.fathoor.core.domain.model.report.UserReport
import dev.fathoor.core.util.UIState
import dev.fathoor.kaman.navigation.screen.Nav
import dev.fathoor.kaman.presentation.theme.FontPlusJakarta
import dev.fathoor.kaman.util.convertMillisToDate

@Composable
fun HomeContent(
    session: Long,
    stateHome: UIState<List<ReportList>>,
    stateLogout: UIState<Unit>,
    onRefresh: () -> Unit,
    onLogout: () -> Unit,
    onSubmit: (UserReport) -> Unit,
    navController: NavHostController
) {
    val listState = rememberLazyListState()
    var isModalHidden by remember { mutableStateOf(true) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""
    var isDateModalHidden by remember { mutableStateOf(true) }
    var date by rememberSaveable { mutableStateOf("01/01/2024") }
    var isLocationModalHidden by remember { mutableStateOf(true) }
    var location by rememberSaveable { mutableStateOf("") }
    var incident by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Beranda",
                        style = TextStyle(
                            color = Color(0xFF192C54),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                },
                navigationIcon = {
                    Box(Modifier.padding(horizontal = 20.dp)) {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onRefresh()
                                },
                            tint = Color(0xFF192C54)
                        )
                    }
                },
                actions = {
                    Box(Modifier.padding(horizontal = 20.dp)) {
                        Icon(
                            imageVector = Icons.Outlined.ExitToApp,
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onLogout()
                                },
                            tint = Color(0xFFFF1300)
                        )

                        when (stateLogout) {
                            is UIState.Success -> {
                                navController.navigate(Nav.Splash.route) {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }

                            else -> {}
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFFFFFF)
                )
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = listState.isScrollingUp().value,
                enter = fadeIn(
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
                ),
                exit = fadeOut(
                    animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
                )
            ) {
                FloatingActionButton(
                    onClick = {
                        isModalHidden = false
                    },
                    shape = RoundedCornerShape(16.dp),
                    containerColor = Color(0xFF2B65D8),
                    contentColor = Color(0xFFF0F8FE)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Create,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        },
        containerColor = Color(0xFFF0F8FE)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFF71B7F9))
                )
                Spacer(Modifier.height(4.dp))

                when (stateHome) {
                    is UIState.Success -> {
                        LazyColumn(state = listState) {
                            items(items = stateHome.data) { report ->
                                ReportCard(report = report)
                            }
                        }
                    }

                    UIState.Loading, UIState.Empty, is UIState.Error -> {}
                }
            }
        }
    }

    AnimatedVisibility(
        visible = !isModalHidden,
        enter = fadeIn(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        ),
        exit = fadeOut(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        )
    ) {
        Dialog(onDismissRequest = { isModalHidden = true }) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(Color(0xFF3477EB)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        IconButton(
                            onClick = { isModalHidden = true }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null,
                                tint = Color(0xFFF0F8FE)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF0F8FE))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Tanggal Kejadian",
                                    style = TextStyle(
                                        color = Color(0xFF192C54),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        fontFamily = FontPlusJakarta
                                    )
                                )
                                Text(
                                    text = date,
                                    modifier = Modifier.clickable {
                                        isDateModalHidden = false
                                    },
                                    style = TextStyle(
                                        color = Color(0xFF192C54),
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        fontFamily = FontPlusJakarta
                                    )
                                )
                            }
                            Spacer(Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Tempat Kejadian",
                                    style = TextStyle(
                                        color = Color(0xFF192C54),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        fontFamily = FontPlusJakarta
                                    )
                                )
                                Text(
                                    text = if (location == "") "Lokasi Kejadian" else location,
                                    modifier = Modifier.clickable {
                                        isLocationModalHidden = false
                                    },
                                    style = TextStyle(
                                        color = Color(0xFF192C54),
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        fontFamily = FontPlusJakarta
                                    )
                                )
                            }
                            Spacer(Modifier.height(16.dp))
                            Row {
                                Text(
                                    text = "Detail Kejadian",
                                    style = TextStyle(
                                        color = Color(0xFF192C54),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp,
                                        fontFamily = FontPlusJakarta
                                    )
                                )
                            }
                            Spacer(Modifier.height(10.dp))
                            TextField(
                                value = incident,
                                onValueChange = { input: String ->
                                    incident = input
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                                    .border(width = 0.5.dp, color = Color(0xFFE0E0E0)),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    fontFamily = FontPlusJakarta
                                ),
                                placeholder = {
                                    Text(
                                        text = "Tambahkan laporan...",
                                        style = TextStyle(
                                            color = Color(0xFFBDBDBD),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 12.sp,
                                            fontFamily = FontPlusJakarta
                                        )
                                    )
                                },
                                shape = RoundedCornerShape(4.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color(0xFFF9FCFF),
                                    unfocusedContainerColor = Color(0xFFF9FCFF),
                                )
                            )
                            Spacer(Modifier.height(24.dp))
                            Button(
                                onClick = {
                                    if (date.isNotEmpty() && location.isNotEmpty() && incident.isNotEmpty()) {
                                        val report = UserReport(
                                            id = session,
                                            incidentDate = date,
                                            location = location,
                                            report = incident
                                        )

                                        onSubmit(report)
                                        onRefresh()
                                        isModalHidden = true

                                        date = "01/01/2024"
                                        location = ""
                                        incident = ""
                                    }
                                },
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonColors(
                                    containerColor = Color(0xFF2B65D8),
                                    contentColor = Color(0xFFF0F8FE),
                                    disabledContainerColor = Color(0xFF8A8A8E),
                                    disabledContentColor = Color(0xFFFFFFFF)
                                )
                            ) {
                                Text(
                                    text = "Kirim Laporan",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        fontFamily = FontPlusJakarta
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = !isDateModalHidden,
        enter = fadeIn(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        ),
        exit = fadeOut(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        )
    ) {
        val onDismiss = { isDateModalHidden = true }
        DatePickerDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Box(modifier = Modifier
                    .padding(end = 32.dp, bottom = 24.dp)
                    .clickable {
                        date = if (selectedDate != "") selectedDate else date
                        onDismiss()
                    }
                ) {
                    Text(
                        text = "OK",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                }
            },
            dismissButton = {
                Box(modifier = Modifier
                    .padding(end = 32.dp, bottom = 24.dp)
                    .clickable { onDismiss() }
                ) {
                    Text(
                        text = "Cancel",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = FontPlusJakarta
                        )
                    )
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    AnimatedVisibility(
        visible = !isLocationModalHidden,
        enter = fadeIn(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        ),
        exit = fadeOut(
            animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
        )
    ) {
        val onDismiss = { isLocationModalHidden = true }
        Dialog(onDismissRequest = { isLocationModalHidden = true }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                TextField(
                    value = location,
                    onValueChange = { input: String ->
                        location = input
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        fontFamily = FontPlusJakarta
                    ),
                    placeholder = {
                        Text(
                            text = "Lokasi Kejadian",
                            style = TextStyle(
                                color = Color(0xFFBDBDBD),
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                fontFamily = FontPlusJakarta
                            )
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { onDismiss() }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Check,
                                contentDescription = null,
                                tint = Color(0xFF49454F)
                            )
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(4.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF9FCFF),
                        unfocusedContainerColor = Color(0xFFF9FCFF),
                    )
                )
            }
        }
    }
}

@Composable
private fun LazyListState.isScrollingUp(): State<Boolean> {
    return produceState(initialValue = true) {
        var lastIndex = 0
        var lastScroll = Int.MAX_VALUE
        snapshotFlow {
            firstVisibleItemIndex to firstVisibleItemScrollOffset
        }.collect { (currentIndex, currentScroll) ->
            if (currentIndex != lastIndex || currentScroll != lastScroll) {
                value = currentIndex < lastIndex ||
                        (currentIndex == lastIndex && currentScroll < lastScroll)
                lastIndex = currentIndex
                lastScroll = currentScroll
            }
        }
    }
}
