package com.chinadream.www.userclient.base.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter2<E> extends BaseAdapter{
    Context context;
    List<E> list;
    public BaseAdapter2(Context context) {
        this.context = context;
        list=new ArrayList<E>();

    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
    public void addAllList(List<E> datas){
        list.addAll(datas);
    }
    public void addSingleData(E e){
        list.add(e);
    }
    public void addSingleData(E e,int position){
        list.add(position,e);
    }
    public void removeAllList(List<E> datas){
        list.removeAll(datas);
    }
    public void removeSingleData(E e){
        list.remove(e);
    }
    public void removeSingleData(int position){
        list.remove(position);
    }
    public void clearAllList(){
        list.clear();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public E getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
