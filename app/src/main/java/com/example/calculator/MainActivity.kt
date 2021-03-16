package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
import androidx.activity.viewModels

//import to access the widgets as any other property in the code
import kotlinx.android.synthetic.main.activity_main.*

/*
private const val STATE_PENDING_OPERATION = "PendingOperation"
private const val STATE_OPERAND1 = "Operand1"
private const val STATE_OPERAND1_STORED = "Operand1_Stored"
*/

//MainActivity only concerned to display the data and respond to user interaction
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Subscribe to View Model
        //val viewModel: BigDecimalViewModel by viewModels()
        val viewModel: CalculatorViewModel by viewModels()
        //Observe the data and update it
        viewModel.stringResult.observe(this, { stringResult -> result.setText(stringResult) })
        viewModel.stringNewNumber.observe(this, { stringNumber -> newNumber.setText(stringNumber) })
        viewModel.stringOperation.observe(this, { stringOperation -> operation.text = stringOperation })

        /*
        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        //Data input buttons
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        //Operations buttons
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
         */

        //Listener OnClickButton
        val listener = View.OnClickListener { v ->
            viewModel.digitPressed((v as Button).text.toString())
//            val b = v as Button
//            newNumber.append(b.text)
        }

        //Assign the listener to the buttons
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        //Listener OnClickButton for the operands
        val opListener = View.OnClickListener { v ->
            viewModel.operandPressed((v as Button).text.toString())
//            val op: String = (v as Button).text.toString()
//            try {
//                val value = newNumber.text.toString().toDouble()
//                performOperation(value, op)
//            } catch (e: NumberFormatException) {
//                newNumber.setText("")
//            }
//            pendingOperation = op
//            operation.text = pendingOperation
        }

        //Assign the listener to the buttons
        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
        
        //Listener for the NEG button
        buttonNeg.setOnClickListener {
            viewModel.negPressed()
//            val value = newNumber.text.toString()
//            if (value.isEmpty()) {
//                newNumber.setText("-")
//            } else {
//                try {
//                    var doubleValue = value.toDouble()
//                    doubleValue *= -1
//                    newNumber.setText(doubleValue.toString())
//                } catch (e: NumberFormatException) {
//                    //number was "-" or ".", so clear it
//                    newNumber.setText("")
//                }
//            }
        }

        //Listener for the CLEAR button
        buttonClear.setOnClickListener {
            viewModel.clearPressed()
//            operand1 = null
//            result.setText("")
//            newNumber.setText("")
//            operation.text = "="
        }

    }

/*
    //Storing and retrieving the app's state when the device is rotated
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(operand1 != null){
            outState.putDouble(STATE_OPERAND1, operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED, true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Validation needed for handle the case "operand1" is null, then should stay as null. And not changed for "0.0" because of the getDouble function.
        operand1 = if(savedInstanceState.getBoolean(STATE_OPERAND1_STORED, false)) {
            savedInstanceState.getDouble(STATE_OPERAND1)
        }else{
            null
        }
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION).toString()
        operation.text = pendingOperation
    }
*/

}