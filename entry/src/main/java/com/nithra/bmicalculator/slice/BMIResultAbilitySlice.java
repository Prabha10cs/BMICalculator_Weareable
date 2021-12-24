package com.nithra.bmicalculator.slice;

import com.nithra.bmicalculator.ResourceTable;
import com.nithra.bmicalculator.utils.Constants;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;

public class BMIResultAbilitySlice extends AbilitySlice {
    private Text mTxtBmiValue, mTxtstatus, mTxtname, mTxtStatusDetails, mTxtHeightValue, mTxtWeightValue;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_bmiresult);
        initView();
        getIntentData(intent);
    }

    /**
     * Init View.
     */
    private void initView() {
        mTxtBmiValue = (Text) findComponentById(ResourceTable.Id_text_bmi);
        mTxtstatus = (Text) findComponentById(ResourceTable.Id_text_status);
        mTxtname = (Text) findComponentById(ResourceTable.Id_text_name);
        mTxtHeightValue = (Text) findComponentById(ResourceTable.Id_text_heightvaluere);
        mTxtWeightValue = (Text) findComponentById(ResourceTable.Id_text_weightvaluere);
        mTxtStatusDetails = (Text) findComponentById(ResourceTable.Id_text_statusdetails);
    }

    /**
     * Get intent data from schedule ability.
     */
    private void getIntentData(Intent intent) {
        if (intent.getParams().hasParam(Constants.BMI_Value) && intent.getParams().hasParam(Constants.BMI_STATUS) && intent.getParams().hasParam(Constants.NAME) && intent.getParams().hasParam(Constants.Height) && intent.getParams().hasParam(Constants.Weight)) {
            String name = intent.getStringParam(Constants.NAME);
            String height = intent.getStringParam(Constants.Height);
            String weight = intent.getStringParam(Constants.Weight);
            String bmivalue = intent.getStringParam(Constants.BMI_Value);
            String bmistatus = intent.getStringParam(Constants.BMI_STATUS);
            setData(name, height, weight, bmivalue, bmistatus);
        }
    }

    /**
     * Set Data
     */
    private void setData(String name, String height, String weight, String bmivalue, String bmistatus) {
        mTxtname.setText(name);
        mTxtHeightValue.setText(height);
        mTxtWeightValue.setText(weight);
        mTxtBmiValue.setText("BMI: " + bmivalue);
        mTxtstatus.setText(bmistatus);
        if (bmistatus.contains("Healthy")) {
            mTxtstatus.setTextColor(Color.GREEN);
            mTxtStatusDetails.setText("You are Healthy! , you lower risk of serious health problems");
        } else if (bmistatus.contains("Underweight")) {
            mTxtstatus.setTextColor(Color.BLUE);
            mTxtStatusDetails.setText("You are Underweight, eat a variety of foods that give you the nutrition");
        } else if (bmistatus.contains("Overweight")) {
            mTxtstatus.setTextColor(Color.RED);
            mTxtStatusDetails.setText("You are Overweight , engage in regular physical activity and exercise");
        } else {
            mTxtstatus.setTextColor(Color.RED);
            mTxtStatusDetails.setText("You are Heavily overweight, do exercise and comsume healthy food");
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
