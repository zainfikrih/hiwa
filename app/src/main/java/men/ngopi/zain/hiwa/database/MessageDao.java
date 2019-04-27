package men.ngopi.zain.hiwa.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import men.ngopi.zain.hiwa.model.Message;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM message")
    List<Message> getAll();

    @Insert
    void insertAll(Message... users);

    @Delete
    void delete(Message user);
}
