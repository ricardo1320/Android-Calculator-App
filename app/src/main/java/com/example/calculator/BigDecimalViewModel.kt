package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

//ViewModel class, all the code to deal with the app's logic
class BigDecimalViewModel: ViewModel() {

    //--- Variables referring to the widgets ---
    //lateinit: declare a variable as "nullable", it will be initialize later in the program
    //--private lateinit var result: EditText
    //--private lateinit var newNumber: EditText

    //lazy delegation: declare a value as "nullable", it will be initialize later in the program by the function "lazy"
    //--private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    //Variables to hold the operands and type of calculation
    //operand1 can be NULL
    private var operand1: BigDecimal? = null
    private var pendingOperation = "="

    //Mutable live data objects to work in this class and LiveData objects to expose to the observers
    private val result = MutableLiveData<BigDecimal>()
    val stringResult: LiveData<String>
        get() =Transformations.map(result) { it.toString() }

    private val newNumber = MutableLiveData<String>()
    val stringNewNumber: LiveData<String>
        get() = newNumber

    private val operation = MutableLiveData<String>()
    val stringOperation: LiveData<String>
        get() = operation

    //Function to append the the button's caption to NewNumber
    fun digitPressed(caption: String){
        if(newNumber.value != null){
            newNumber.value = newNumber.value + caption
        }else{
            newNumber.value = caption
        }
    }

    //Function operandPressed to pass to the performOperation
    fun operandPressed(op: String){
        try {
            val value = newNumber.value?.toBigDecimal()
            if(value != null){
                performOperation(value, op)
            }
        } catch (e: NumberFormatException) {
            newNumber.value = ""
        }
        pendingOperation = op
        operation.value = pendingOperation
    }

    //Function for when the NEG button is pressed
    fun negPressed(){
        val value = newNumber.value
        if (value.isNullOrEmpty()) {
            newNumber.value = "-"
        } else {
            try {
                var doubleValue = value.toBigDecimal()
                doubleValue *= BigDecimal.valueOf(-1)
                newNumber.value = doubleValue.toString()
            } catch (e: NumberFormatException) {
                //number was "-" or ".", so clear it
                newNumber.value = ""
            }
        }
    }

    //Function for when the CLEAR button is pressed
    fun clearPressed(){
        operand1 = null
        result.value = BigDecimal.valueOf(0.0)
        newNumber.value = ""
        operation.value = "="
    }

    //Function to perform the operation
    private fun performOperation(value: BigDecimal, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
            }

            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == BigDecimal.valueOf(0.0)) {
                    BigDecimal.valueOf(Double.NaN) // handle attempt to divide by zero
                } else {
                    operand1!! / value //!! bang bang = sintax to handle a nullable variable in an operation with a non nullable variable
                }
                "*" -> operand1 = operand1!! * value
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        result.value = operand1
        newNumber.value = ""
    }
}