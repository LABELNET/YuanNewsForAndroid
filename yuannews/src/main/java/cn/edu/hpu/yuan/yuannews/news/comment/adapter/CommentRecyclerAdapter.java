package cn.edu.hpu.yuan.yuannews.news.comment.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.CommentsFragmentItemBinding;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.CommentJo;


/**
 * Created by yuan on 16-5-13.
 * 在create的时候通过 tag 传递binding
 */
public class CommentRecyclerAdapter  extends RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewAdapter>{


    private  List<CommentJo> commentJos=new ArrayList<>();

    public void addComemntJo(ArrayList<CommentJo> commentss){
        commentJos.addAll(commentss);
    }

    public void clearComemntJos(){
        commentJos.clear();
    }

    private LayoutInflater inflater;
    private Context context;

    public CommentRecyclerAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public CommentViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        CommentsFragmentItemBinding binding= DataBindingUtil.inflate(inflater, R.layout.comments_fragment_item,parent,false);
        return new CommentViewAdapter(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(CommentViewAdapter holder, int position) {
        CommentsFragmentItemBinding binding=DataBindingUtil.bind(holder.itemView);
        CommentJo commentJo = commentJos.get(position);
        binding.setCommentJo(commentJo);
        Glide.with(context)
                .load(NewsAPI.BASE_IMAGE_URL+commentJo.getHead())
                .error(R.mipmap.ic_news)
                .placeholder(R.mipmap.user_head)
                .into(binding.profileImage);
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
