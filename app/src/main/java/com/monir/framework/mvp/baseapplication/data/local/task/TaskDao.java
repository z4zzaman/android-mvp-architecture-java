package com.monir.framework.mvp.baseapplication.data.local.task;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 5:04 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/10/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;

import com.monir.framework.mvp.baseapplication.data.local.base.BaseDao;
import com.monir.framework.mvp.baseapplication.data.local.dbhelper.TableNames;

import java.util.List;

public interface TaskDao extends BaseDao<TaskEntity> {
    // Select all from TaskEntity table and order by "complete by" date
    //@Query("SELECT * FROM " + TableNames. TASK_TABLE + " ORDER By id")
    @Query("SELECT * FROM " + TableNames.TASK_TABLE)
    LiveData<List<TaskEntity>> getAllTasks();

    // Select all from TaskEntity table and order by "complete by" date
    @Query("SELECT * FROM " + TableNames.TASK_TABLE)
    List<TaskEntity> getAllTaskList();

    // Select one task from TaskEntity table by id
    @Query("SELECT * FROM " + TableNames.TASK_TABLE + " WHERE id=:id")
    LiveData<TaskEntity> getTaskById(String id);
}