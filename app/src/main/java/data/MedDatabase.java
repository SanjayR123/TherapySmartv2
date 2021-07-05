package data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Med.class}, version = 1)
public abstract class MedDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "med_db";

    private static MedDatabase instance;

    /**
     * Migrate from:
     * version 0 - using Room - to 1
     * to

     @VisibleForTesting
     static final Migration MIGRATION_2_3 = new Migration(2, 3) {
     @Override
     public void migrate(SupportSQLiteDatabase database) {
     database.execSQL("ALTER TABLE tasks "
     + " ADD COLUMN notification_number INTEGER");
     }
     };
     */

    static MedDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MedDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract MedDao getMedDao();
}
