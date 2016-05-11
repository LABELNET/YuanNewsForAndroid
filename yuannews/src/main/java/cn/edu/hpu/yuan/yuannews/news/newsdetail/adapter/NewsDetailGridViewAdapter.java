package cn.edu.hpu.yuan.yuannews.news.newsdetail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.NewsDetailFragmentGridItemBinding;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.LikedVo;

/**
 * Created by yuan on 16-5-12.
 */
public class NewsDetailGridViewAdapter extends BaseAdapter{

    private final List<LikedVo>  likedVos=new ArrayList<>();

    public void initData(List<LikedVo>  Vos){
        likedVos.addAll(Vos);
    }

    public void clearData(){
        likedVos.clear();
    }

    private Context context;

    private LayoutInflater inflater;

    public NewsDetailGridViewAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);

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
        NewsDetailFragmentGridItemBinding  binding = DataBindingUtil.inflate(inflater, R.layout.news_detail_fragment_grid_item, parent, false);
        String url=NewsAPI.BASE_IMAGE_URL+likedVos.get(position).getHead();
        Glide.with(context)
                .load(url)
                .error(R.mipmap.loaderror)
                .placeholder(R.mipmap.loading)
                .into(binding.profileImage);
        return binding.getRoot();
    }


}
