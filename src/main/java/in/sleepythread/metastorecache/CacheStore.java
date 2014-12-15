package in.sleepythread.metastorecache;

import in.sleepythread.exception.FactoryInstantiationException;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.thrift.TException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheStore {

    private static CacheStore instance;
    Map<String,Map<String,HiveTableInfo>> localTableCache;
    private HiveMetaStoreClient hiveMetaStoreClient;

    private CacheStore(HiveMetaStoreClient hiveMetaStoreClient){
        this.hiveMetaStoreClient = hiveMetaStoreClient;
        localTableCache = new HashMap<String, Map<String, HiveTableInfo>>();
    }

    public HiveTableInfo getTableInfo(String databaseName, String tableName) throws TException {
        Map<String, HiveTableInfo> localDatabaseCache = localTableCache.get(databaseName);

        if(localDatabaseCache == null || localDatabaseCache.get(tableName) == null){
            Table table = hiveMetaStoreClient.getTable(databaseName, tableName);
            List<FieldSchema> fields = hiveMetaStoreClient.getFields(databaseName, tableName);
            if(localDatabaseCache == null ){
                localTableCache.put(databaseName,new HashMap<String,HiveTableInfo>());
            }
            localTableCache.get(databaseName).put(tableName,new HiveTableInfo(table,fields));
        }
        return localTableCache.get(databaseName).get(tableName);
    }

    public static CacheStore Instance(HiveMetaStoreClient client) {
        if(instance == null){
            instance = new CacheStore(client);
        }
        return instance;
    }
}
