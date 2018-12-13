package com.monir.framework.mvp.baseapplication.ui.base;

/*
 *  ****************************************************************************
 *  * Created by : Md. Moniruzzaman Monir on 12/13/2018 at 6:18 PM.
 *  * Email : moniruzzaman@w3engineers.com
 *  *
 *  * Purpose:
 *  *
 *  * Last edited by : Md. Moniruzzaman Monir on 12/13/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private final List<T> mItemList;
    protected ItemClickListener mItemClickListener;
    protected ItemLongClickListener mItemLongClickListener;

    public BaseAdapter() {
        mItemList = new ArrayList<T>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return newViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        T itemData = getItem(position);

        ViewDataBinding viewDataBinding = holder.getViewDataBinding();

        holder.bind(itemData, viewDataBinding);
    }

    public abstract boolean isEqual(T left, T right);

    public abstract BaseViewHolder newViewHolder(ViewGroup parent, int viewType);

    /**
     * Commit child fragment of BaseFragment on a frameLayout
     *
     * @param itemLongClickListener ItemLongClickListener object
     * @return void
     */
    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
    }

    /**
     * Commit child fragment of BaseFragment on a frameLayout
     *
     * @param itemClickListener ItemClickListener object
     * @return void
     */
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }


    /**
     * @return current item list
     */
    public List<T> getItems() {
        return mItemList;
    }


    /**
     * @param position int value
     * @return get current Item based on Position
     */
    public T getItem(int position) {
        if (position < 0 || position >= mItemList.size()) return null;
        return mItemList.get(position);
    }

    /**
     * Remove a Item from list and update UI
     *
     * @param t T type object
     * @return void
     */
    public void removeItem(T t) {
        int toIndex = mItemList.indexOf(t);
        if (toIndex < 0 || toIndex >= mItemList.size()) return;
        mItemList.remove(toIndex);
        notifyDataSetChanged();
    }

    /**
     * Clear current item list and update UI
     *
     * @return void
     */
    public void clear() {
        mItemList.clear();
        notifyDataSetChanged();
    }

    /**
     * @param item T type object
     * @return int value: current item inserted position in list
     */
    public int addItem(T item) {
        T tItem = findItem(item);

        if (tItem == null) {
            mItemList.add(item);
            notifyItemInserted(mItemList.size() - 1);
            return mItemList.size() - 1;
        }
        return updateItem(item, tItem);
    }


    /**
     * @param items List<T> type object list
     * @return int value: current item inserted position in list
     */
    public void addItems(List<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }



    /**
     * @param item     T type object
     * @param position int value of position where value will be inserted
     * @return void
     */
    public void addItemToPosition(T item, int position) {
        mItemList.add(position, item);
        notifyItemInserted(position);
    }
    /**
     * @param item T type object
     * @return if found then item from list otherwise null
     */
    public T findItem(T item) {
        for (T tItem : mItemList) {
            if (isEqual(item, tItem)) {
                return tItem;
            }
        }
        return null;
    }

    /**
     * @param oldItem T type object
     * @param newItem T type object
     * @return int value: newItem position in list
     */
    public int updateItem(T oldItem, T newItem) {
        int toIndex = mItemList.indexOf(oldItem);
        mItemList.set(toIndex, newItem);
        notifyItemChanged(toIndex);
        return toIndex;
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public ViewDataBinding inflate(ViewGroup viewGroup, int item_layout) {
        return DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), item_layout, viewGroup, false);
    }
}
