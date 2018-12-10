package com.monir.framework.mvp.baseapplication.data.local.user;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 5:02 PM.
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
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.monir.framework.mvp.baseapplication.data.local.base.BaseDao;
import com.monir.framework.mvp.baseapplication.data.local.dbhelper.ColumnNames;
import com.monir.framework.mvp.baseapplication.data.local.dbhelper.TableNames;

import java.util.List;

@Dao
public interface UserDao extends BaseDao<UserEntity> {
    @Query("SELECT * FROM " + TableNames.USERS)
    List<UserEntity> getAllUser();

    @Query("SELECT * FROM " + TableNames.USERS)
    LiveData<List<UserEntity>> getAllUserLiveData();

    @Query("SELECT * FROM " + TableNames.USERS + " WHERE " + ColumnNames.USER_ID + " = :meshID")
    UserEntity getUser(String meshID);

    @Query("SELECT * FROM " + TableNames.USERS + " WHERE " + ColumnNames.ID + " = :id")
    UserEntity getUserById(long id);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM " + TableNames.USERS)
    void deleteAllUsers();
}