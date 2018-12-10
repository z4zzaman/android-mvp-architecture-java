package com.monir.framework.mvp.baseapplication.data.local.base;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 4:53 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/10/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

public interface BaseDao<T> {
    /**
     * Insert a entity in the database. If the entity already exists, replace it.
     *
     * @param entity the entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T entity);

    /**
     * Update a entity in the database.
     *
     * @param entity the entity to be updated.
     */
    @Update
    int update(T... entity);

    /**
     * Delete a entity in the database.
     *
     * @param entity the entity to be delete.
     */
    @Delete
    void delete(T... entity);
}
