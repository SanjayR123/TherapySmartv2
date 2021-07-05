package async;

import android.os.AsyncTask;

import data.Med;
import data.MedDao;

public class DeleteAsyncMed extends AsyncTask<Med, Void, Void> {

    private MedDao mMedDao;

    public DeleteAsyncMed(MedDao dao) {
        mMedDao = dao;
    }

    @Override
    protected Void doInBackground(Med... meds) {
        mMedDao.deleteMed(meds);
        return null;
    }

}