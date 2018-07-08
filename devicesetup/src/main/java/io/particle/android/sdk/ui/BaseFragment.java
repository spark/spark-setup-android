package io.particle.android.sdk.ui;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;

import io.particle.android.sdk.cloud.SDKGlobals;
import io.particle.android.sdk.devicesetup.R;
import io.particle.android.sdk.utils.SEGAnalytics;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Julius.
 */
public class BaseFragment extends Fragment{
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public static boolean setupOnly = false;
    private static boolean customFontInitDone = false;

    @Override
    public void onAttach(Context newBase) {
        SEGAnalytics.initialize(newBase);

        if (!customFontInitDone) {
            // FIXME: make actually customizable via resources
            // (see file extension string formatting nonsense)
            CalligraphyConfig.initDefault(
                    new CalligraphyConfig.Builder()
                            .setDefaultFontPath(newBase.getString(R.string.normal_text_font_name))
                            .setFontAttrId(R.attr.fontPath)
                            .build());
            customFontInitDone = true;
        }
        super.onAttach(CalligraphyContextWrapper.wrap(newBase));
        SDKGlobals.init(newBase);
    }
}