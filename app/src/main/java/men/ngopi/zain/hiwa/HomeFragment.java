package men.ngopi.zain.hiwa;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import men.ngopi.zain.hiwa.database.AppDatabase;
import men.ngopi.zain.hiwa.database.DatabaseHelper;
import men.ngopi.zain.hiwa.model.Message;

public class HomeFragment extends Fragment {

    private View view;
    static private HomeFragment homeFragment;
    private String numberStr;
    private String messageStr;
    private DatabaseHelper databaseHelper;
    private AppDatabase db;

    @BindView(R.id.ccp)
    CountryCodePicker countryCodePicker;

    @BindView(R.id.editText_numberphone)
    EditText numberPhone;

    @BindView(R.id.editText_message)
    EditText message;

    public static HomeFragment newInstance(){
        homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        db = databaseHelper.getDatabaseInstance(getActivity()).getDatabase();

        ButterKnife.bind(this, view);

        countryCodePicker.registerCarrierNumberEditText(numberPhone);
        countryCodePicker.setNumberAutoFormattingEnabled(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.btn_send})
    public void btnOnClick(View view){
        switch (view.getId()){
            case R.id.btn_send:
                numberStr = countryCodePicker.getFullNumber();
                if(numberPhone.getText().toString().equals("")){
                    return;
                } else {
                    messageStr = message.getText().toString();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.setmPhone(countryCodePicker.getFullNumberWithPlus());
                            message.setmMessage(messageStr);
                            db.messageDao().insertAll(message);
                        }
                    }).start();

                    try {

                        PackageManager packageManager = getActivity().getPackageManager();
                        Intent i = new Intent(Intent.ACTION_VIEW);

                        String url = "https://api.whatsapp.com/send?phone="+ numberStr +"&text=" + URLEncoder.encode(messageStr, "UTF-8");
                        i.setPackage("com.whatsapp");
                        i.setData(Uri.parse(url));
                        if (i.resolveActivity(packageManager) != null) {
                            startActivity(i);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

                break;
        }
    }
}
