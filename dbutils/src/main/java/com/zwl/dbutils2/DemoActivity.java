package com.zwl.dbutils2;

import android.app.Activity;
import android.os.Bundle;

import com.zwl.dbutils2.item.DataCacheItem;
import com.zwl.dbutils2.table.DataCacheTable;

/**
 * Created by weilongzhang on 17/1/14.
 */

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataCacheTable dataCacheTable = DataBaseManager.getDataBaseManager().getDefaultDB()
                .getTable(DataCacheTable.class);
        dataCacheTable.insert(new DataCacheItem());
    }
}
