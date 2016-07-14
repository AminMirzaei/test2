package ORMLite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by amin on 10/07/2016.
 */
@DatabaseTable(tableName = "hospital")
public class Hospital {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    public Hospital(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
