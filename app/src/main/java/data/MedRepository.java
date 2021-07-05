package data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import async.DeleteAsyncMed;
import async.InsertAsyncMed;
import async.UpdateAsyncMed;

public class MedRepository {

    private MedDatabase mMedDatabase;

    public MedRepository(Context context) {
        mMedDatabase = MedDatabase.getInstance(context);
    }

    public void insertMed(Med med){
        new InsertAsyncMed(mMedDatabase.getMedDao()).execute(med);
    }

    public void updateMed(Med med){
        new UpdateAsyncMed(mMedDatabase.getMedDao()).execute(med);
    }

    public LiveData<List<Med>> retrieveMed() {
        return mMedDatabase.getMedDao().getMeds();
    }

    public void deleteMed(Med med){
        new DeleteAsyncMed(mMedDatabase.getMedDao()).execute(med);
    }
}

