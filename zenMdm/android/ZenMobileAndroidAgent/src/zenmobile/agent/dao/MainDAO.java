package zenmobile.agent.dao;



import zenmobile.agent.util.Constants;

import zenmobile.agent.vo.GeneralInfoVO;

import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;



//Main DAO for the App

public class MainDAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "zenmobileDB";

    

    //Table AGENT_INFO

    private static final String TABLE_AGENT_INFO = "AGENT_INFO";

    private static final String KEY_ID = "agent_id";

    private static final String CREATE_AGENT_INFO_TABLE = "create table " + TABLE_AGENT_INFO + 

                                                          "( " + 

                                                                 KEY_ID + " text primary key, " + 

                                                                 "agent_version" + " text not null, " + 

                                                                 "install_date" + " text not null, " + 

                                                                 "last_recorded" + " text not null, " + 

                                                                 "applied_policy" + " text not null " + 

                                                          ");";

    

    //Table ENROLLMENT_INFO

    private static final String TABLE_ENROLLMENT_INFO = "ENROLLMENT_INFO";

    private static final String ENROLLMENT_ID = "enrollment_id";

    private static final String CREATE_ENROLLMENT_INFO_TABLE = "create table " + TABLE_ENROLLMENT_INFO + 

                                                          "( " + 

                                                                 ENROLLMENT_ID + " text primary key, " + 

                                                                 "IS_ENROLLED" + " text not null" + 

                                                          ");";    

    

    public MainDAO(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

                    db.execSQL(CREATE_AGENT_INFO_TABLE);

                    db.execSQL(CREATE_ENROLLMENT_INFO_TABLE);

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(MainDAO.class.getName(),

                "Upgrading database from version " + oldVersion + " to "

                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENT_INFO);

        onCreate(db);

    }

    

    //add category

    public void addGeneralInfo(GeneralInfoVO gvo) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

                      values.put("agent_id", Constants.DEFAULT_AGENT_ID);

                      values.put("agent_version", gvo.getAgentVersion());

                      values.put("install_date", gvo.getAgentInstallDate());

                      values.put("last_recorded", gvo.getLastRecordedDate());

                      values.put("applied_policy", gvo.getAppliedPolicy());

             db.insert(TABLE_AGENT_INFO, null, values);

             db.close(); // Closing database connection

    }




    public GeneralInfoVO getGeneralInfo(String id) {

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_AGENT_INFO, new String[] { KEY_ID, "agent_version", "install_date","last_recorded","applied_policy" }, KEY_ID + "=?",

                new String[] { id }, null, null, null, null);

        if (cursor != null)

            cursor.moveToFirst();

        else return null;

        GeneralInfoVO gvo = new GeneralInfoVO();

               gvo.setAgentId(Constants.DEFAULT_AGENT_ID);

               gvo.setAgentVersion(cursor.getString(1));

               gvo.setAgentInstallDate(cursor.getString(2));

               gvo.setLastRecordedDate(cursor.getString(3));

               gvo.setAppliedPolicy(cursor.getString(4));



        return gvo;

    } 



    public boolean Exists(String _id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select 1 from AGENT_INFO where agent_id=?", 

             new String[] { _id });

        boolean exists = (cursor.getCount() > 0);

        cursor.close();

        return exists;

     }

    

    public boolean enrollmentExists(String _id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select 1 from ENROLLMENT_INFO where ENROLLMENT_ID=?", 

             new String[] { _id });

        boolean exists = (cursor.getCount() > 0);

        cursor.close();

        return exists;

     }

    

    // Updating single contact

    public int updateGeneralInfo(GeneralInfoVO gvo) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

            values.put("agent_version", gvo.getAgentVersion());

            values.put("install_date", gvo.getAgentInstallDate());

            values.put("last_recorded", gvo.getLastRecordedDate());

            values.put("applied_policy", gvo.getAppliedPolicy());


        // updating row

        return db.update(TABLE_AGENT_INFO, values, KEY_ID + " = ?",

                new String[] { gvo.getAgentId()} );

    }

    

    

    public boolean isEnrolled() {

        if(enrollmentExists(Constants.DEFAULT_ENROLLMENT_ID)) {

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery("select 1 from ENROLLMENT_INFO where ENROLLMENT_ID=? and IS_ENROLLED='" + Constants.IS_YES + "'", 

                 new String[] { Constants.DEFAULT_ENROLLMENT_ID });

            boolean exists = (cursor.getCount() > 0);

            cursor.close();

            return exists; 

        } else return false;

    }

    

    //enrol device

    public long insertEnrolmentInDB() {

        SQLiteDatabase db = this.getWritableDatabase();        

        ContentValues values = new ContentValues();

            values.put("enrollment_id", Constants.DEFAULT_ENROLLMENT_ID);

            values.put("IS_ENROLLED", Constants.IS_YES);        

        return db.insertOrThrow(TABLE_ENROLLMENT_INFO, null, values);

    }



}
