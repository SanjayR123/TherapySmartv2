package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedDao {

    @Insert
    void insertMed(Med... meds);

    @Query("SELECT * FROM medications")
    LiveData<List<Med>> getMeds();

    @Delete
    void deleteMed(Med... meds);

    @Update
    void updateMed(Med... meds);
}

