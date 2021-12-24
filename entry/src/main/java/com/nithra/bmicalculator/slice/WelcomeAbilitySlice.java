package com.nithra.bmicalculator.slice;

import com.nithra.bmicalculator.ResourceTable;
import com.nithra.bmicalculator.utils.NaviUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

import static com.nithra.bmicalculator.utils.Constants.*;

public class WelcomeAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_welcome);
        navigateToBMICalculator();
    }
    /**
     * Navigate to app Bmi calculator screen.
     */
    private void navigateToBMICalculator() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               // NaviUtils.gotoSlice_Present(WelcomeAbilitySlice.this, new BMICalculatorAbilitySlice(),true);
                NaviUtils.gotoSlice_Intent_Action(getAbility(),WelcomeAbilitySlice.this,BMI_CALCULATOR_SLICE,true);

            }
        }, TIMER_MILLISECOND);
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
