package com.czyzewski.zendeskhometask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.czyzewski.zendeskhometask.domain.usecase.GetTicketsListUseCase
import com.czyzewski.zendeskhometask.feature.ticketlist.TicketsScreen
import com.czyzewski.zendeskhometask.ui.theme.ZendeskHomeTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var usecase: GetTicketsListUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZendeskHomeTaskTheme {
                TicketsScreen()
            }
        }
    }
}
