package men.ngopi.zain.hiwa;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import men.ngopi.zain.hiwa.database.AppDatabase;
import men.ngopi.zain.hiwa.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Fragment selectedFragment;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_item1:
                        selectedFragment = HomeFragment.newInstance();
                        break;
                    case R.id.action_item2:
                        break;
                    case R.id.action_item3:
                        selectedFragment = MoreFragment.newInstance();
                        break;
                }

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_content, selectedFragment);
                fragmentTransaction.commit();
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
