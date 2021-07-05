package async;

import android.os.AsyncTask;

import data.Med;
import data.MedDao;

public class InsertAsyncMed extends AsyncTask<Med, Void, Void> {

    private MedDao mMedDao;

    public InsertAsyncMed(MedDao dao) {
        mMedDao = dao;
    }

    @Override
    protected Void doInBackground(Med... meds) {
        mMedDao.insertMed(meds);
        return null;
    }

}