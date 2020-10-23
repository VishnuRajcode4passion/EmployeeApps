package com.whiterabit.employee.roomdataclass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.whiterabit.employee.model.Employee_datclass;
import com.whiterabit.employee.model.Util;
import com.whiterabit.employee.model.address_dataclass;
import com.whiterabit.employee.model.company_dataclass;
//created by vishnu
@Database(entities = {Employee_datclass.class , address_dataclass.class, company_dataclass.class}, version = 7, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract RoomInterface getRoomdao();

    private static AppDatabase noteDB;

    public static AppDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                Util.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }

    @Override
    public void clearAllTables() {
        //  noteDB = null;
    }


}
