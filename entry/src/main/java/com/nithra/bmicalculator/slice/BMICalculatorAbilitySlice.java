package com.nithra.bmicalculator.slice;

import com.nithra.bmicalculator.MainAbility;
import com.nithra.bmicalculator.ResourceTable;
import com.nithra.bmicalculator.utils.Constants;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.multimodalinput.event.KeyEvent;
import ohos.multimodalinput.event.TouchEvent;

import static com.nithra.bmicalculator.utils.Constants.BMI_RESULT_SLICE;

public class BMICalculatorAbilitySlice extends AbilitySlice {
    private Button mBtncalculate;
    private TextField mEtAge;
    private TextField mEtweight;
    private TextField mEtHeight;
    private TextField mEtname;
    private Slider mSliderMyweight;
    private Slider mSliderMyheight;
    private Slider mSliderMyAge;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_bmicalculator);
        initView();
        fetchSliderValues();
        initClickListner();
    }
    /**
     * Init View.
     */
    private void initView() {
        mBtncalculate = (Button) findComponentById(ResourceTable.Id_btn_calculate);
        mEtHeight = (TextField) findComponentById(ResourceTable.Id_et_heightvalue);
        mEtweight = (TextField) findComponentById(ResourceTable.Id_et_weightvalue);
        mEtAge = (TextField) findComponentById(ResourceTable.Id_et_agevalue);
        mEtname = (TextField) findComponentById(ResourceTable.Id_et_name);
        mSliderMyheight = (Slider) findComponentById(ResourceTable.Id_slider_myheight);
        mSliderMyheight.setMaxValue(245);
        mSliderMyweight = (Slider) findComponentById(ResourceTable.Id_slider_myweightn);
        mSliderMyweight.setMaxValue(300);
        mSliderMyAge = (Slider) findComponentById(ResourceTable.Id_slider_myage);
        mSliderMyweight.setMaxValue(110);
        mEtname.setTouchEventListener(new Component.TouchEventListener() {
            @Override
            public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
                mEtname.setFocusable(1);
                mEtname.setTouchFocusable(true);
                return false;
            }
        });
        mEtname.setKeyEventListener(new Component.KeyEventListener() {
            @Override
            public boolean onKeyEvent(Component component, KeyEvent keyEvent) {
                if (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEY_ENTER) {
                    // Do your action
                    mEtname.setFocusable(0);
                    mEtname.clearFocus();
                    mEtname.setTextCursorVisible(false);
                    return true;
                }
                return false;
            }
        });
    }
    /**
     *Fetch SLider Values method.
     */
    private void fetchSliderValues() {
        myHeightSliderListener();
        myWeightSliderListener();
        myAgeSliderListener();
    }
    /**
     * Click listner method.
     */
    private void initClickListner() {
        mBtncalculate.setClickedListener(component -> {
            if (mEtname.getText().toString().isEmpty()) {
                Constants.showToast(this, "please enter name");
            } else if (mEtHeight.getText().equalsIgnoreCase("0")) {
                Constants.showToast(this, "please enter valid height");
            } else if (mEtweight.getText().equalsIgnoreCase("0")) {
                Constants.showToast(this, "please enter the valid weight");
            } else if (mEtAge.getText().equalsIgnoreCase("0")) {
                Constants.showToast(this, "please enter the valid Age");
            } else {
                calculateBMI();
            }

        });
    }
    /**
     * Calculate BMI method.
     */
    private void calculateBMI() {
        float height = Float.parseFloat(mEtHeight.getText().toString()) / 100;
        float weight = Float.parseFloat(mEtweight.getText().toString());
        //Calculate BMI value
        float bmiValue = calculateBMIFormula(weight, height);
        String bmiinterpret = interpretBMI(bmiValue);
        navigateToBMIResultAbility(String.valueOf(bmiValue), bmiinterpret, mEtname.getText().toString(), mEtHeight.getText().toString(), mEtweight.getText().toString());
        mEtHeight.setText("0");
        mEtweight.setText("0");
        mEtname.setText("");
        mEtAge.setText("0");
        mSliderMyheight.setProgressValue(0);
        mSliderMyweight.setProgressValue(0);
        mSliderMyAge.setProgressValue(0);
    }

    /**
     * CalculateBMI formula.
     */
    private float calculateBMIFormula(float weight, float height) {
        return (float) (weight / (height * height));
    }


    // Interpret what BMI means
    private String interpretBMI(float bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Healthy";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    /**
     * Navigate to BMI Result ability screen.
     */
    private void navigateToBMIResultAbility(String bmivalue, String bmistatus, String name, String height, String weight) {
        Intent intent = new Intent();
        Operation operation =
                new Intent.OperationBuilder()
                        .withBundleName(getBundleName())
                        .withAbilityName(MainAbility.class.getName())
                        .withAction(BMI_RESULT_SLICE)
                        .build();
        intent.setOperation(operation);
        intent.setParam(Constants.BMI_Value, bmivalue);
        intent.setParam(Constants.BMI_STATUS, bmistatus);
        intent.setParam(Constants.NAME, name);
        intent.setParam(Constants.Height, height);
        intent.setParam(Constants.Weight, weight);
        intent.addFlags(Intent.FLAG_ABILITY_CLEAR_MISSION);
        startAbility(intent);
    }
    /**
     * Height Slider Listner.
     */
    private void myHeightSliderListener() {
        mSliderMyheight.setValueChangedListener(new Slider.ValueChangedListener() {
            @Override
            public void onProgressUpdated(Slider slider, int i, boolean b) {
                mEtHeight.setText(String.valueOf(i));
            }

            @Override
            public void onTouchStart(Slider slider) {
            }

            @Override
            public void onTouchEnd(Slider slider) {
            }
        });
    }
    /**
     * Weight Slider Listner.
     */
    private void myWeightSliderListener() {
        mSliderMyweight.setValueChangedListener(new Slider.ValueChangedListener() {
            @Override
            public void onProgressUpdated(Slider slider, int i, boolean b) {
                mEtweight.setText(String.valueOf(i));
            }

            @Override
            public void onTouchStart(Slider slider) {
            }

            @Override
            public void onTouchEnd(Slider slider) {
            }
        });
    }
    /**
     * Age Slider Listner.
     */
    private void myAgeSliderListener() {
        mSliderMyAge.setValueChangedListener(new Slider.ValueChangedListener() {
            @Override
            public void onProgressUpdated(Slider slider, int i, boolean b) {
                mEtAge.setText(String.valueOf(i));
            }

            @Override
            public void onTouchStart(Slider slider) {
            }

            @Override
            public void onTouchEnd(Slider slider) {
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
        System.out.println("onActive");
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
        System.out.println("onForeground");
    }
}
