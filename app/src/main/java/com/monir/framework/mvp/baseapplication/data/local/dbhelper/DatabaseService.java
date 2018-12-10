package com.monir.framework.mvp.baseapplication.data.local.dbhelper;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 3:42 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/10/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.monir.framework.mvp.R;
import com.monir.framework.mvp.baseapplication.data.local.task.TaskDao;
import com.monir.framework.mvp.baseapplication.data.local.task.TaskEntity;
import com.monir.framework.mvp.baseapplication.data.local.user.UserDao;
import com.monir.framework.mvp.baseapplication.data.local.user.UserEntity;
import com.monir.framework.mvp.util.lib.roomdb.AppDatabase;

@Database(entities = {
        UserEntity.class,
        TaskEntity.class},
        version = 2, exportSchema = false)
public abstract class DatabaseService extends AppDatabase {

    private static final String MIGRATION_SQL_1_2 = "ALTER TABLE  Employee ADD COLUMN address TEXT";
    /*
    public static AppDatabaseService on(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabaseService.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabaseService.class, context.getString(R.string.app_name))
                            .build();
                }
            }
        }

        return INSTANCE;
    }
    */
    private static volatile DatabaseService sInstance;
    private static volatile DatabaseService INSTANCE;

    // Create the database
    private static DatabaseService createOld(Context context) {
        RoomDatabase.Builder<DatabaseService> builder =
                Room.databaseBuilder(context, DatabaseService.class, context.getString(R.string.app_name));

        return builder.build();
    }

    // Get a database instance
    public static synchronized DatabaseService on() {
        return sInstance;
    }

    public static synchronized DatabaseService init(Context context) {

        if (sInstance == null) {
            synchronized (DatabaseService.class) {
                sInstance = createDb(context, context.getString(R.string.app_name), DatabaseService.class
                        , MIGRATION_SQL_1_2);
            }
        }

        return sInstance;
    }

    public abstract UserDao userDao();

    public abstract TaskDao taskDao();
}
