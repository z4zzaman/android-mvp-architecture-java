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
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

public class TaskService {
    private final TaskDao mTaskDao;
    private MediatorLiveData<List<TaskEntity>> mObservableProducts;

    public TaskService(TaskDao taskDao) {
        mTaskDao = taskDao;
        mObservableProducts = new MediatorLiveData<>();
    }

    public LiveData<List<TaskEntity>> getAllTasks() {
        mObservableProducts.addSource(mTaskDao.getAllTasks(), new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntity> userEntities) {
                mObservableProducts.postValue(userEntities);
            }
        });

        return mObservableProducts;
    }

    public void insert(TaskEntity taskEntity) {
        long id = mTaskDao.insert(taskEntity);
    }
}
