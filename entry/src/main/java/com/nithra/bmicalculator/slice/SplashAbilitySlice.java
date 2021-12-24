package com.nithra.bmicalculator.slice;

import com.nithra.bmicalculator.ResourceTable;
import com.nithra.bmicalculator.utils.NaviUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import java.util.Timer;
import java.util.TimerTask;
import static com.nithra.bmicalculator.utils.Constants.TIMER_MILLISECOND;
import static com.nithra.bmicalculator.utils.Constants.WELCOME_SLICE;

public class SplashAbilitySlice extends AbilitySlice {

    /**
     * Abilityslice lifecycle method onStart.
     *
     * @param intent as Intent
     */
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_splash);
        navigateToWelcomeScreen();
    }

    /**
     * Navigate to app welcome screen.
     */
    private void navigateToWelcomeScreen() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               // NaviUtils.gotoSlice_Present(SplashAbilitySlice.this, new WelcomeAbilitySlice(),true);
                NaviUtils.gotoSlice_Intent_Action(getAbility(),SplashAbilitySlice.this,WELCOME_SLICE,true);

            }
        }, TIMER_MILLISECOND);
    }

    /**
     * Abilityslice lifecycle method onActive.
     */
    @Override
    public void onActive() {
        super.onActive();
    }

    /**
     * Abilityslice lifecycle method onForeground.
     *
     * @param intent as Intent
     */
    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
