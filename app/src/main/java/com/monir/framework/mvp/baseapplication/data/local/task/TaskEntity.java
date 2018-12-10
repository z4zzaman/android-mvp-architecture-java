package com.monir.framework.mvp.baseapplication.data.local.task;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 4:57 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/10/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.arch.persistence.room.Entity;

import com.monir.framework.mvp.baseapplication.data.local.base.RoomEntity;
import com.monir.framework.mvp.baseapplication.data.local.dbhelper.TableNames;

@Entity(tableName = TableNames.TASK_TABLE)
public class TaskEntity extends RoomEntity {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String title;
    private String description;
}
