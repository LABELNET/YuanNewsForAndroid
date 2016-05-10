package cn.edu.hpu.yuan.yuannews.news.newslist.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nshmura.recyclertablayout.RecyclerTabLayout;

import java.util.List;

import cn.edu.hpu.yuan.yuannews.R;

public class CustomRecyclerViewAdapter extends RecyclerTabLayout.Adapter<CustomRecyclerViewAdapter.ViewHolder> {


    private LayoutInflater inflater;

    private List<String> list;

    public CustomRecyclerViewAdapter(ViewPager viewPager, Context context, List<String> list) {
        super(viewPager);
        inflater=LayoutInflater.from(context);
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.news_fragment_page_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String content= list.get(position);
        holder.tv_test.setText(content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_test;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_test= (TextView) itemView.findViewById(R.id.tv_test);
        }
    }
}