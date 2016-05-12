package cn.edu.hpu.yuan.yuannews.user.label.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.LabelFragmentItemBinding;
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

    private LayoutInflater inflater;

    private OnDeleteItemClick onDeleteItemClick;

    public void setOnDeleteItemClick(OnDeleteItemClick onDeleteItemClick) {
        this.onDeleteItemClick = onDeleteItemClick;
    }

    public LabelIfoAdapter(Context context){
        inflater=LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LabelFragmentItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.label_fragment_item,parent,false);
        convertView=binding.getRoot();
        final TasteVo tasteVo = tasteVos.get(position);
        binding.setLabel(tasteVo.getLabel());
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDeleteItemClick.onDelete(tasteVo)==0) {
                    removeTasteVo(position);
                }
            }
        });
        return convertView;
    }

    public interface OnDeleteItemClick{
        int onDelete(TasteVo tasteVo);
    }

}
