package examples.aaronhoskins.com.mvvmdemotwo.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

import examples.aaronhoskins.com.mvvmdemotwo.BR;
import examples.aaronhoskins.com.mvvmdemotwo.Utilities;
import examples.aaronhoskins.com.mvvmdemotwo.model.Constants;
import examples.aaronhoskins.com.mvvmdemotwo.model.User;

public class LoginViewModel extends AndroidViewModel implements Observable {
    PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    @Bindable
    private String emailValidStatus;
    @Bindable
    private String passwordValidStatus;

    User user;

    public LoginViewModel(@NonNull Application application, User user) {
        super(application);
        this.user = user;
    }

    public String getEmailValidStatus() {
        return emailValidStatus;
    }

    public void setEmailValidStatus(String emailValidStatus) {
        this.emailValidStatus = emailValidStatus;
        notifySingleBindedItems(BR.emailValidStatus);
    }

    public String getPasswordValidStatus() {
        return passwordValidStatus;
    }

    public void setPasswordValidStatus(String passwordValidStatus) {
        this.passwordValidStatus = passwordValidStatus;
        notifySingleBindedItems(BR.passwordValidStatus);
    }

    public void afterPasswordTextChanged(CharSequence charSequence) {
        String string = charSequence.toString();
        user.setPassword(string);
        if(Utilities.isPasswordValid(string)) {
            setPasswordValidStatus(Constants.VALID_PASSWORD);
        } else {
            setPasswordValidStatus(Constants.INVALID_PASSWORD);
        }
    }

    public void afterEmailTextChanged(CharSequence charSequence) {
        String string = charSequence.toString();
        user.setUserEmail(string);
        if(Utilities.isUserEmailValid(string)) {
            setEmailValidStatus(Constants.VALID_EMAIL);
        } else {
            setEmailValidStatus(Constants.INVALID_EMAIL);
        }
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.remove(callback);
    }

    public void notifyAllBindedItems() {
        propertyChangeRegistry.notifyCallbacks(this, 0, null);
    }

    public void notifySingleBindedItems(int fieldID) {
        propertyChangeRegistry.notifyCallbacks(this, fieldID, null);
    }
}
