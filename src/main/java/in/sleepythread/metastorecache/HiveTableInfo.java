package in.sleepythread.metastorecache;

import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Table;

import java.util.List;

public class HiveTableInfo {

    public static final String FIELD_DELIM = "field.delim";
    private Table table;
    List<FieldSchema> fieldSchemas;

    public HiveTableInfo(Table table,List<FieldSchema> fieldSchemas){
        this.table = table;
        this.fieldSchemas = fieldSchemas;
    }

    public String getDelimeter(){
       return table.getSd().getSerdeInfo().getParameters().get(FIELD_DELIM);
    }

    public Table getTable(){
        return table;
    }

    public List<FieldSchema> getFieldSchemas(){
        return fieldSchemas;
    }
}
