package men.ngopi.zain.hiwa.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import men.ngopi.zain.hiwa.database.AppDatabase;
import men.ngopi.zain.hiwa.model.Message;

public class MessageViewModel extends AndroidViewModel {

    private final LiveData<List<Message>> messages;

    public MessageViewModel(@NonNull Application application) {
        super(application);

        messages = AppDatabase.getInstance(getApplication()).messageDao().getAll();
    }

    public LiveData<List<Message>> getMessages(){
        return messages;
    }
}
