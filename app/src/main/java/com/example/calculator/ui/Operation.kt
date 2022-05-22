package com.example.calculator.ui


/**
 * Created by Bagdaulet Tleules on 22.05.2022.
 * email: bagdaulettleules@gmail.com
 */
sealed class Operation(val symbol: String){
    object Add: Operation("+")
    object Subtract: Operation("-")
    object Multiply: Operation("*")
    object Divide: Operation("/")
}
