package async;

import android.os.AsyncTask;

import data.Med;
import data.MedDao;

public class UpdateAsyncMed extends AsyncTask<Med, Void, Void> {

    private MedDao mMedDao;

    public UpdateAsyncMed(MedDao dao) {
        mMedDao = dao;
    }

    @Override
    protected Void doInBackground(Med... meds) {
        mMedDao.updateMed(meds);
        return null;
    }

}