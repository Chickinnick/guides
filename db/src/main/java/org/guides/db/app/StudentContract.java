package org.guides.db.app;

import android.provider.BaseColumns;

public final class StudentContract {

    public StudentContract() {}

    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
    }
}
