package com.monir.framework.mvp.baseapplication.data.local.user;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/10/2018 at 5:03 PM.
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

import java.util.List;

public class UserService {
    private final UserDao mUserDao;


    public UserService(UserDao userDao) {
        mUserDao = userDao;
    }

    public UserEntity getUser(String meshID) {
        return mUserDao.getUser(meshID);
    }

    public UserEntity getUserById(long id) {
        return mUserDao.getUserById(id);
    }

    public long insert(UserEntity userEntity) {
        long id = mUserDao.insert(userEntity);
        userEntity.setId(id);
        return id;
    }

    public int updateUser(UserEntity userEntity) {
        int value = mUserDao.update(userEntity);
        return value;
    }

    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }

    public void deleteItem(UserEntity userEntity) {
        mUserDao.delete(userEntity);
    }

    public List<UserEntity> getAllUser() {
        return mUserDao.getAllUser();
    }

    public LiveData<List<UserEntity>> getAllUserLiveData() {
        return mUserDao.getAllUserLiveData();
    }

    public LiveData<List<UserEntity>> getUsers() {
        return mUserDao.getAllUserLiveData();
    }
}