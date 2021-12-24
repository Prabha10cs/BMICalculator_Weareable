/*
 *
 *  *
 *  *  * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *  *  *
 *  *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *    you may not use this file except in compliance with the License.
 *  *  *    You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  *    Unless required by applicable law or agreed to in writing, software
 *  *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *    See the License for the specific language governing permissions and
 *  *  *    limitations under the License.
 *  *
 *
 */

package com.nithra.bmicalculator.utils;

import com.nithra.bmicalculator.ResourceTable;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

/**
 * The type Constants.
 */
public class Constants {

    public static final int TOAST_DURATION_MILLISECOND = 20000;
    public static final int TIMER_MILLISECOND = 3000;
    public static final String PACKAGE_NAME = "com.nithra.bmicalculator";
    public static final String MAIN_ABILITY = "com.nithra.bmicalculator.MainAbility";
    public static final String BMI_CALCULATOR_SLICE = "action.bmicalculator.slice";
    public static final String WELCOME_SLICE = "action.welcome.slice";
    public static final String BMI_RESULT_SLICE = "action.bmiresult.slice";
    public static final String BMI_Value = "bmivalue";
    public static final String BMI_STATUS = "bmistatus";
    public static final String NAME = "name";
    public static final String Height = "height";
    public static final String Weight = "weight";


    /**
     * Showing toast message.
     *
     * @param context the context
     * @param msg     the msg
     */
    public static void showToast(Context context, String msg) {
        DirectionalLayout layout = (DirectionalLayout) LayoutScatter.getInstance(context)
                .parse(ResourceTable.Layout_layout_toast, null, false);
        Text text = (Text) layout.findComponentById(ResourceTable.Id_msg_toast);
        text.setText(msg);
        new ToastDialog(context)
                .setComponent(layout)
                .setAlignment(LayoutAlignment.CENTER)
                .setDuration(TOAST_DURATION_MILLISECOND)
                .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                .show();
    }
}
