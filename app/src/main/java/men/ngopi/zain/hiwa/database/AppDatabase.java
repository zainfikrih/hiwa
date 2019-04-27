package men.ngopi.zain.hiwa.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import men.ngopi.zain.hiwa.model.Message;

@Database(entities = {Message.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();
}
