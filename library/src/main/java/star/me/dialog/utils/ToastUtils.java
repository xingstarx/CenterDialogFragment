package star.me.dialog.utils;


import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastUtils {
    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, @StringRes int message) {
        show(context, context.getString(message));
    }
}
