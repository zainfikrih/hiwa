package men.ngopi.zain.hiwa.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import men.ngopi.zain.hiwa.model.Message;

import static men.ngopi.zain.hiwa.database.DatabaseHelper.DATABASE_NAME;

@Database(entities = {Message.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME)
                    .build();
        }

        return INSTANCE;
    }

    public static void destroy(){
        INSTANCE = null;
    }

    public abstract MessageDao messageDao();
}
