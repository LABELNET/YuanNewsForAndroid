package cn.edu.hpu.yuan.yuannews.news.comment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yuan on 16-5-13.
 * 在create的时候通过 tag 传递binding
 */
public class CommentRecyclerAdapter  extends RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewAdapter>{


    @Override
    public CommentViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CommentViewAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CommentViewAdapter extends RecyclerView.ViewHolder{

        public CommentViewAdapter(View itemView) {
            super(itemView);
        }
    }
}
