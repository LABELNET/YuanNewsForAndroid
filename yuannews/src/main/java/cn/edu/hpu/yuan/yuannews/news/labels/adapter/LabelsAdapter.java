package cn.edu.hpu.yuan.yuannews.news.labels.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.LabelFragmentItemBinding;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelsAdapter extends RecyclerView.Adapter<LabelsAdapter.LabelsViewHolder>{


    private final List<String> tasteVos=new ArrayList<>();

    public void initTasteVo(){
        tasteVos.clear();
    }

    public void addTasteVo(List<String> tastes){
        tasteVos.removeAll(tastes);//移除以存在的兴趣标签
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
    public LabelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LabelFragmentItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.label_fragment_item,parent,false);
        LabelsViewHolder labelsViewHolder=new LabelsViewHolder(binding.getRoot());
        return labelsViewHolder;
    }

    @Override
    public void onBindViewHolder(LabelsViewHolder holder, final int position) {
        final String tasteVo = tasteVos.get(position);
        LabelFragmentItemBinding binding=DataBindingUtil.getBinding(holder.itemView);
        binding.setLabel(tasteVo);
        binding.num.setText((position+1)+"");
        binding.btnDelete.setText("关注");
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteItemClick.onDelete(tasteVo,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasteVos.size();
    }

    class LabelsViewHolder extends RecyclerView.ViewHolder{

        public LabelsViewHolder(View itemView) {
            super(itemView);
        }
    }


    public interface OnDeleteItemClick{
        void onDelete(String tasteVo, int position);
    }

}
