package cn.edu.hpu.yuan.yuannews.news.newslist.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.NewsFragmentPageItemBinding;
import cn.edu.hpu.yuan.yuannews.main.data.model.news.NewsCustom;


/**
 * Created by yuan on 16-5-9.
 * RecyclerViewAdapter
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder>{


    private final List<NewsCustom> newsCustoms=new ArrayList<>();

    //添加数据
    public void initData(List<NewsCustom> news){
        newsCustoms.addAll(news);
    }

    //清空集合
    public void clearData(){
        newsCustoms.clear();
    }

    private LayoutInflater inflater;

    public CustomRecyclerViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsFragmentPageItemBinding bind = DataBindingUtil
                .inflate(inflater,R.layout.news_fragment_page_item,parent,false);
        CustomViewHolder viewHolder=new CustomViewHolder(bind.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        NewsCustom newsCustom = newsCustoms.get(position);
        NewsFragmentPageItemBinding bind=DataBindingUtil.getBinding(holder.itemView);
        bind.setTitle(newsCustom.getTitle());

    }

    @Override
    public int getItemCount() {
        return newsCustoms.size();
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



        public CustomViewHolder(View itemView) {
            super(itemView);
            NewsFragmentPageItemBinding binding = DataBindingUtil.getBinding(itemView);
            binding.ripple.setOnClickListener(this);//设置点击事件
        }

         @Override
         public void onClick(View v) {

         }
     }

}