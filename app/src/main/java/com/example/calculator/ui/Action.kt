package com.example.calculator.ui


/**
 * Created by Bagdaulet Tleules on 22.05.2022.
 * email: bagdaulettleules@gmail.com
 */
sealed class Action {
    object Clear : Action()
    object Delete : Action()
    object Decimal : Action()
    object Result : Action()
    data class Number(val number: Int) : Action()
    data class Operation(val operation: com.example.calculator.ui.Operation) : Action()
}
