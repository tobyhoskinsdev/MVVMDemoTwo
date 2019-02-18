package examples.aaronhoskins.com.mvvmdemotwo.views.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import java.util.ArrayList;

import examples.aaronhoskins.com.mvvmdemotwo.R;
import examples.aaronhoskins.com.mvvmdemotwo.databinding.ActivityMainBinding;
import examples.aaronhoskins.com.mvvmdemotwo.model.User;
import examples.aaronhoskins.com.mvvmdemotwo.viewmodels.LoginViewModel;
import examples.aaronhoskins.com.mvvmdemotwo.views.adapters.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLoginViewModel(new LoginViewModel(getApplication(), new User()));
        activityMainBinding.executePendingBindings();

        activityMainBinding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(testingUsers());
        activityMainBinding.rvRecyclerView.setAdapter(recyclerViewAdapter);
    }

    @BindingAdapter({"android:text"})
    public static void setTextViewsText(TextView tv, String message) {
        if(message != null) {
            tv.setText(message);
        }
    }

    public ArrayList<User> testingUsers() {
        ArrayList<User> returnList = new ArrayList<>();
        returnList.add(new User("Bob", "password"));
        returnList.add(new User("Jill", "upTheHill"));
        returnList.add(new User("Sam", "whoReallyCares"));
        returnList.add(new User("Jake", "StopTypingAlready"));
        returnList.add(new User("Aaron", "TheDevilISwear"));
        return returnList;
    }
}
