package men.ngopi.zain.hiwa;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;

import android.arch.persistence.room.Room;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Fragment selectedFragment;
    private static MainActivity mainActivity;

    public static MainActivity getInstance(){
        return mainActivity;
    }

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCenter.start(getApplication(), "4138ab41-ca55-4d8a-acc8-36798bddaaa0", Analytics.class, Crashes.class);

        mainActivity = this;

        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_item1:
                        selectedFragment = HomeFragment.newInstance();
                        break;
                    case R.id.action_item2:
                        selectedFragment = HistoryFragment.newInstance();
                        break;
                    case R.id.action_item3:
                        selectedFragment = MoreFragment.newInstance();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, selectedFragment).commit();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        bottomNavigationView.setSelectedItemId(R.id.action_item1);
    }
}
