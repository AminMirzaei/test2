package ORMLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.amin.volunteerjobs.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by amin on 10/07/2016.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME="MyDB.db";
    private static final int DATABASE_VIRSION=1;
    private Dao<Hospital,Integer> hospitalDao;
    private Dao<Patient,Integer> patientDao;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VIRSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.clearTable(connectionSource,Hospital.class);
            TableUtils.clearTable(connectionSource,Patient.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Dao<Hospital, Integer> getHospitalDao() throws SQLException {
        if (hospitalDao == null) {
            hospitalDao = getDao(Hospital.class);
        }
        return hospitalDao;
    }
    public Dao<Patient,Integer> getPatientDao() throws SQLException
    {
        if(patientDao==null)
            patientDao=getDao(Patient.class);
        return patientDao;
    }
    public void InsertHospital (String name) throws SQLException {
        Hospital h=new Hospital();
        h.setName(name);
        hospitalDao.create(h);
    }
    public void InsertPatient (String name,Hospital hospital) throws SQLException {
        Patient p=new Patient();
        p.setName(name);
        p.setHospital(hospital);
        patientDao.create(p);
    }
    public void delete(String name) throws SQLException {
        DeleteBuilder<Patient,Integer> deleteBuilder=patientDao.deleteBuilder();
        deleteBuilder.where().eq("name",name);
        deleteBuilder.delete();
    }
    public List<Hospital> getAllHospital() throws SQLException {
        List<Hospital> hospitals =hospitalDao.queryForAll();
        return hospitals;
    }
    public Hospital getHospital(int id) throws SQLException {
        Hospital hospital =hospitalDao.queryForId(id);
        return hospital;
    }
    public List<Hospital> getHospitalLessTwo() throws SQLException {
    return hospitalDao.queryBuilder().where().lt("id",2).query();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
