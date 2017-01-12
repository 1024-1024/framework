package com.zwl.dbutils.exception;

/**
 * Created by weilongzhang on 17/1/12.
 */

public class DbTableMissingException extends Exception {
    public DbTableMissingException(String message) {
        super(message);
    }

}
