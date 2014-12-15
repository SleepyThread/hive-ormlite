package com.sleepythread.metastorecache;

import com.sleepythread.exception.FactoryInstantiationException;
import junit.framework.TestCase;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.thrift.TException;
import org.hamcrest.Matcher;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class CacheStoreTest {


    @Test
    public void shouldCacheTheTableInfoAfterFirstCall() throws TException {

        HiveMetaStoreClient client = mock(HiveMetaStoreClient.class);

        Table table = new Table();
        ArrayList<FieldSchema> fieldSchemas = new ArrayList<FieldSchema>();

        when(client.getTable("default","student")).thenReturn(table);
        when(client.getFields("default","student")).thenReturn(fieldSchemas);

        CacheStore instance = CacheStore.Instance(client);

        HiveTableInfo tableInfo = instance.getTableInfo("default", "student");

        assertThat(tableInfo.getTable(), is(table));
        assertThat(tableInfo.getFieldSchemas(), Is.<List<FieldSchema>>is(fieldSchemas));

//        Calling method again, this time need to serve call from cache.

        tableInfo = instance.getTableInfo("default", "student");

        assertThat(tableInfo.getTable(), is(table));
        assertThat(tableInfo.getFieldSchemas(), Is.<List<FieldSchema>>is(fieldSchemas));


        verify(client,times(1)).getTable("default","student");
        verify(client,times(1)).getFields("default", "student");
        verifyNoMoreInteractions(client);

    }


}