package men.ngopi.zain.hiwa.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseHelper {
    public static final String DATABASE_NAME = "message_db";
    private DatabaseHelper databaseHelper;
    private AppDatabase db;

    public DatabaseHelper getDatabaseInstance(Context context){
        if(databaseHelper != null){
            databaseHelper = new DatabaseHelper();
            db = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return databaseHelper;
    }

    public AppDatabase getDatabase(){
        return db;
    }
}
