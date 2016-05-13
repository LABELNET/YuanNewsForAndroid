package cn.edu.hpu.yuan.yuannews.news.labels.adapter;

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
public class LabelsAdapter extends BaseAdapter{

    private final List<String>  tasteVos=new ArrayList<>();


    public void initTasteVo(){
        tasteVos.clear();
    }

    public void addTasteVo(List<String> tastes){
        tasteVos.addAll(tastes);
    }

    public void removeTasteVo(String label){
        tasteVos.remove(label);
    }


    private LayoutInflater inflater;

    private OnDeleteItemClick onDeleteItemClick;

    public void setOnDeleteItemClick(OnDeleteItemClick onDeleteItemClick) {
        this.onDeleteItemClick = onDeleteItemClick;
    }

    public LabelsAdapter(Context context){
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
        final String tasteVo = tasteVos.get(position);
        binding.setLabel(tasteVo);
        binding.num.setText((position+1)+"");
        binding.btnDelete.setText("关注");
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteItemClick.onDelete(tasteVo,position);
            }
        });
        return convertView;
    }

    public interface OnDeleteItemClick{
        void onDelete(String tasteVo, int position);
    }

}
