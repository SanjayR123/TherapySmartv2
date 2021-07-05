package data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications")
public class Med implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "units")
    private int units;

    @ColumnInfo(name = "dose")
    private int dose;

    @ColumnInfo(name = "reminder")
    private String reminderTime;

    @ColumnInfo(name = "days")
    private int days;

    @ColumnInfo(name = "repetition")
    private String repetition;

    public static final int pills = 0;
    public static final int pessary = 1;
    public static final int piece = 2;
    public static final int patches = 3;
    public static final int puffs = 4;
    public static final int sachets = 5;
    public static final int sprays = 6;
    public static final int tablespoons = 7;
    public static final int teaspoons = 8;

    public Med(String name, int units, int dose, String reminderTime, int days, String repetition) {
        this.name = name;
        this.units = units;
        this.dose = dose;
        this.reminderTime = reminderTime;
        this.repetition = repetition;
        this.days = days;
    }

    @Ignore
    public Med() {

    }


    protected Med(Parcel in) {
        id = in.readInt();
        name = in.readString();
        units = in.readInt();
        dose = in.readInt();
        days = in.readInt();
        reminderTime = in.readString();
        repetition = in.readString();
    }

    public static final Creator<Med> CREATOR = new Creator<Med>() {
        @Override
        public Med createFromParcel(Parcel in) {
            return new Med(in);
        }

        @Override
        public Med[] newArray(int size) {
            return new Med[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }


    @Override
    public String toString() {
        return "Med{" +
                "name='" + name + '\'' +
                ", units='" + units + '\'' +
                ", dose='" + dose + '\'' +
                ", reminderTime='" + reminderTime + '\'' +
                ", length='" + days + '\'' +
                ", repetition='" + repetition + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(units);
        parcel.writeInt(dose);
        parcel.writeString(reminderTime);
        parcel.writeString(repetition);
        parcel.writeInt(days);
    }
}