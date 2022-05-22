package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.Screen
import com.example.calculator.ui.CalculatorViewModel
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.MediumGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp
                Screen(
                    state = state,
                    onAction = { viewModel.onAction(it) },
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MediumGray)
                        .padding(16.dp)
                )
            }
        }
    }
}
