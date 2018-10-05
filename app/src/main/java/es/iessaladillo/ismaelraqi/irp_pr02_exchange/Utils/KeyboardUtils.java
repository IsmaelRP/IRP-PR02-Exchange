package es.iessaladillo.ismaelraqi.irp_pr02_exchange.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import java.util.Objects;

public class KeyboardUtils {

    private KeyboardUtils() {
    }

    public static void HideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager)(activity).getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
    }
}
