package com.nithra.bmicalculator.utils;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;

import static com.nithra.bmicalculator.utils.Constants.MAIN_ABILITY;
import static com.nithra.bmicalculator.utils.Constants.PACKAGE_NAME;

public class NaviUtils {

    public static void gotoSlice_Present(AbilitySlice abilitySlice, AbilitySlice targetAbilitySlice, boolean terminateFlag) {
        Intent intent = new Intent();
        abilitySlice.present(targetAbilitySlice, intent);
        if (terminateFlag) {
            abilitySlice.terminate();
        }
    }
    public static void gotoSlice_Intent_Action(Ability ability, AbilitySlice abilitySlice, String action, boolean terminateFlag) {
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName(PACKAGE_NAME)
                .withAbilityName(MAIN_ABILITY)
                .withAction(action)
                .build();
        intent.setOperation(operation);
        ability.startAbility(intent, Intent.FLAG_ABILITY_NEW_MISSION  | Intent .FLAG_ABILITY_CLEAR_MISSION);
        if (terminateFlag) {
            abilitySlice.terminate();
        }
    }
}
