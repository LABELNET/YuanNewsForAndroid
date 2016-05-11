package cn.edu.hpu.yuan.yuannews.news.newsdetail.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.LikedVo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yuan on 16-5-12.
 */
public class NewsDetailGridViewAdapter extends BaseAdapter{

    final List<LikedVo>  likedVos=new ArrayList<>();

    public void initData(List<LikedVo>  Vos){
        likedVos.addAll(likedVos);
    }

    public void clearData(){
        likedVos.clear();
    }

    private Context context;

    public NewsDetailGridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return likedVos.size();
    }

    @Override
    public Object getItem(int position) {
        return likedVos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder viewHolder=null;
            if(convertView==null) {
                convertView = View.inflate(context, R.layout.news_detail_fragment_grid_item, parent);
                viewHolder=new ViewHolder();
            }
            viewHolder.circleImageView= (CircleImageView) convertView.findViewById(R.id.profile_image);
        LikedVo likedVo = likedVos.get(position);
        Glide.with(context).load(NewsAPI.BASE_IMAGE_URL+likedVo.getHead()).into(viewHolder.circleImageView);
        return convertView;
    }

    class ViewHolder{
        CircleImageView circleImageView;
    }
}
