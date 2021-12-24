package com.nithra.bmicalculator;

import com.nithra.bmicalculator.slice.BMICalculatorAbilitySlice;
import com.nithra.bmicalculator.slice.BMIResultAbilitySlice;
import com.nithra.bmicalculator.slice.SplashAbilitySlice;
import com.nithra.bmicalculator.slice.WelcomeAbilitySlice;
import com.nithra.bmicalculator.utils.Constants;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SplashAbilitySlice.class.getName());
        initActionRoute();
    }
    private void initActionRoute() {
        addActionRoute(Constants.WELCOME_SLICE, WelcomeAbilitySlice.class.getName());
        addActionRoute(Constants.BMI_CALCULATOR_SLICE, BMICalculatorAbilitySlice.class.getName());
        addActionRoute(Constants.BMI_RESULT_SLICE, BMIResultAbilitySlice.class.getName());
    }
}
