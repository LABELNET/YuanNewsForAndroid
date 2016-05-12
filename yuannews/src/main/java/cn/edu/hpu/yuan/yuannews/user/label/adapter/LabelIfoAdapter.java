package cn.edu.hpu.yuan.yuannews.user.label.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelIfoAdapter extends BaseAdapter{

    private final List<TasteVo>  tasteVos=new ArrayList<>();

    public void addTasteVo(List<TasteVo> tastes){
        tasteVos.addAll(tastes);
    }

    public void removeTasteVo(int position){
        tasteVos.remove(position);
    }

    public void addTasteVo(TasteVo tasteVo){
        tasteVos.add(0,tasteVo);
    }

    @Override
    public int getCount() {
        return tasteVos.size();
    }

    @Override
    public Object getItem(int position) {
        return tasteVos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
