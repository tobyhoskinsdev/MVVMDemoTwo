package examples.aaronhoskins.com.mvvmdemotwo;

import android.text.TextUtils;
import android.util.Patterns;

public class Utilities {
    public static boolean isPasswordValid(String passedPassword) {
        return passedPassword.length() > 5;
    }

    public static boolean isUserEmailValid(String passedUserEmail) {
        return !TextUtils.isEmpty(passedUserEmail)
                && Patterns.EMAIL_ADDRESS.matcher(passedUserEmail).matches();
    }
}
