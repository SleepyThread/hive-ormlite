package com.sleepythread;


import com.sleepythread.exception.DbConfigAnnotaionMissingException;
import com.sleepythread.exception.FactoryInstantiationException;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.io.Text;
import org.apache.thrift.TException;

import java.util.List;

public class HiveBuilderFactory {

  public static final String FIELD_DELIM = "field.delim";
  private HiveMetaStoreClient hiveMetaStoreClient;
  private static HiveBuilderFactory instance;

  private HiveBuilderFactory(String metaStoreUri) throws MetaException {

    HiveConf conf = new HiveConf(HiveBuilderFactory.class);
    conf.setVar(HiveConf.ConfVars.METASTOREURIS,metaStoreUri);
    hiveMetaStoreClient = new HiveMetaStoreClient(conf);
  }

  public static HiveBuilderFactory Instance(String metaStoreUri) throws FactoryInstantiationException {
    if(instance == null){
      try {
        instance = new HiveBuilderFactory(metaStoreUri);
      } catch (MetaException e) {
        throw new FactoryInstantiationException();
      }
    }
    return instance;
  }

  <T> T deSerialize(Class<T> className,Text record) throws DbConfigAnnotaionMissingException, TException, IllegalAccessException, InstantiationException {
    if(!className.isAnnotationPresent(HiveDbConfig.class)){
      throw new DbConfigAnnotaionMissingException();
    }
    ObjectTableInfo objectTableInfo = new ObjectTableInfo(className);
    String tableName = objectTableInfo.getTableName();
    String databaseName = objectTableInfo.getDatabaseName();
    String delimeter = hiveMetaStoreClient.getTable(databaseName, tableName).getSd().getSerdeInfo().getParameters().get(FIELD_DELIM);
    List<FieldSchema> fields = hiveMetaStoreClient.getFields(databaseName, tableName);
    HiveTable hiveTable = new HiveTable(databaseName, tableName, fields,new HiveTypeToJavaTypeMapper(),delimeter);

    HiveRecordToObjectFactory<T> tHiveRecordToObjectFactory = new HiveRecordToObjectFactory<T>(objectTableInfo, hiveTable, record);


    return tHiveRecordToObjectFactory.getObject(className.newInstance());
  }


}
