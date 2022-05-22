package com.example.calculator.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


/**
 * Created by Bagdaulet Tleules on 22.05.2022.
 * email: bagdaulettleules@gmail.com
 */
class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(State())
        private set

    fun onAction(action: Action) {
        when (action) {
            Action.Clear -> onClear()
            Action.Decimal -> onDecimal()
            Action.Delete -> onDelete()
            is Action.Number -> onNumber(action.number)
            is Action.Operation -> onOperation(action.operation)
            Action.Result -> onResult()
        }
    }

    private fun onClear() {
        state = State()
    }

    private fun onDecimal() {
        if (state.operation == null && !state.left.contains(".") && state.left.isNotBlank()) {
            state = state.copy(left = state.left + ".")
            return
        }

        if (!state.right.contains(".") && state.right.isNotBlank()) {
            state = state.copy(right = state.right + ".")
        }
    }

    private fun onDelete() {
        when {
            state.right.isNotBlank() -> state = state.copy(right = state.right.dropLast(1))
            state.operation != null -> state = state.copy(operation = null)
            state.left.isNotBlank() -> state = state.copy(left = state.left.dropLast(1))
        }

    }

    private fun onNumber(number: Int) {
        if (state.operation == null) {
            if (state.left.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(left = state.left + number)
            return
        }

        if (state.right.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(right = state.right + number)
    }

    private fun onOperation(operation: Operation) {
        if (state.left.isBlank()) return

        state = state.copy(operation = operation)
    }

    private fun onResult() {
        val left = state.left.toDoubleOrNull()
        val right = state.right.toDoubleOrNull()
        if (left != null && right != null) {
            val result = when (state.operation) {
                Operation.Add -> left + right
                Operation.Divide -> if (right == 0.0) "ERROR" else left / right
                Operation.Multiply -> left * right
                Operation.Subtract -> left - right
                null -> TODO()
            }

            if (result == "ERROR") {
                onClear()
                return
            }

            state = state.copy(
                left = result.toString().take(15),
                right = "",
                operation = null
            )
        }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

}