package com.gabik.metro.model.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.gabik.metro.model.db.elementsFromDB.BranchParam;
import com.gabik.metro.model.db.elementsFromDB.CommunicationParam;
import com.gabik.metro.model.db.elementsFromDB.ReadableFromDB;
import com.gabik.metro.model.db.elementsFromDB.StationParam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GaBiK on 07.02.2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper implements DataBaseEmpl {

    private final static String DB_PATH = "/data/data/com.metro.gabmetro/databases/";
    private final static String DB_NAME = "base.db";
    private final Context myContext;

    private SQLiteDatabase database;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 14);
        this.myContext = context;
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDataBase() throws IOException {
        database = this.getReadableDatabase();
        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        File file = new File(DB_PATH + DB_NAME);
        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public List<ReadableFromDB> getParam(Select selectQuery) {
        Cursor c = database.rawQuery(selectQuery.toString(),null);
        List<ReadableFromDB> readableFromDBs = new ArrayList<ReadableFromDB>();

        if (selectQuery.equals(Select.Station)){
            while (c.moveToNext()){
                StationParam stationParam = new StationParam();
                stationParam.readElement(c);
                readableFromDBs.add(stationParam);
            }
        }
        if (selectQuery.equals(Select.Communication)){
            while (c.moveToNext()){
                CommunicationParam stationParam = new CommunicationParam();
                stationParam.readElement(c);
                readableFromDBs.add(stationParam);
            }
        }
        if (selectQuery.equals(Select.Branch)){
            while (c.moveToNext()){
                BranchParam branchParam = new BranchParam();
                branchParam.readElement(c);
                readableFromDBs.add(branchParam);
            }
        }
        return readableFromDBs;
    }

}
