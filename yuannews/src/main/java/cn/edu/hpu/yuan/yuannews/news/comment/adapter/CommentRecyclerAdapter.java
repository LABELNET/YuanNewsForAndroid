package cn.edu.hpu.yuan.yuannews.news.comment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentJo;


/**
 * Created by yuan on 16-5-13.
 * 在create的时候通过 tag 传递binding
 */
public class CommentRecyclerAdapter  extends RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewAdapter>{


    private final List<CommentJo> commentJos=new ArrayList<>();

    public void addComemntJo(ArrayList<CommentJo> commentJos){
        commentJos.addAll(commentJos);
    }

    public void clearComemntJos(){
        commentJos.clear();
    }

    @Override
    public CommentViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommentViewAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commentJos.size();
    }

    class CommentViewAdapter extends RecyclerView.ViewHolder{

        public CommentViewAdapter(View itemView) {
            super(itemView);
        }
    }

}
