package com.monir.framework.mvp.baseapplication.data.local.user;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 4:50 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/10/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.monir.framework.mvp.baseapplication.data.local.base.RoomEntity;
import com.monir.framework.mvp.baseapplication.data.local.dbhelper.ColumnNames;
import com.monir.framework.mvp.baseapplication.data.local.dbhelper.TableNames;

@Entity(tableName = TableNames.USERS, indices = {@Index(value = {ColumnNames.USER_ID}, unique = true)})
public class UserEntity extends RoomEntity {
    @ColumnInfo(name = ColumnNames.USER_ID)
    @NonNull
    public String mMeshID;
    @Bindable
    @ColumnInfo(name = ColumnNames.USER_NAME)
    public String mUserName;

    public UserEntity(String meshID, String userName) {
        mMeshID = meshID;
        mUserName = userName;
    }

    @NonNull
    public String getMeshID() {
        return mMeshID;
    }

    public String getUserName() {
        return mUserName;
    }
}