package com.example.insurancecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.insurancecalculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        binding.apply{
            calcButton.setOnClickListener(){
                myData.premiumAmt = "%.2f".format(getPremium().toString())
                display()

            }

            resetButton.setOnClickListener(){
                ageSpinner.setSelection(0)
                genderRadioGroup.clearCheck()
                smokerCheckbox.setChecked(false)
                insuranceAmt.text = "0.00"
            }
            display()
        }
    }
    fun display(){
        insuranceAmt.text = myData.premiumAmt
    }
    fun getPremium(): Double{
        return when(ageSpinner.selectedItemPosition){
            0       -> 60.00
            1       -> 70.00 +
                        (if(maleButton.isChecked) 50.00 else 0.00) +
                        (if(smokerCheckbox.isChecked) 100.00 else 0.00)
            2       -> 90.00 +
                        (if(maleButton.isChecked) 100.00 else 0.00) +
                        (if(smokerCheckbox.isChecked) 150.00 else 0.00)
            3       -> 120.00 +
                        (if(maleButton.isChecked) 150.00 else 0.00) +
                        (if(smokerCheckbox.isChecked) 200.00 else 0.00)
            4       -> 150.00 +
                        (if(maleButton.isChecked) 200.00 else 0.00) +
                        (if(smokerCheckbox.isChecked) 250.00 else 0.00)
            else    -> 150.00 +
                    (if(maleButton.isChecked) 200.00 else 0.00) +
                    (if(smokerCheckbox.isChecked) 300.00 else 0.00)
        }
    }
}
