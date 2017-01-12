package com.zwl.dbutils2;

/**
 * Created by weilongzhang on 17/1/12.
 */

public abstract class BaseItem {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
