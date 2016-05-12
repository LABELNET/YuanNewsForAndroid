package cn.edu.hpu.yuan.yuannews.user.center;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.CenterFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.NewsAPI;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.UserVo;
import cn.edu.hpu.yuan.yuannews.user.label.LabelActivity;
import cn.edu.hpu.yuan.yuannews.user.userIfo.UserifoActivity;

/**
 * Created by yuan on 16-5-12.
 */
public class CenterFragment extends NorbalBackFragment implements CenterContancts.CenterContanctsView {


    @Inject
    protected  CenterContancts.CenterContanctsPresenter centerContanctsPresenter;

    @Inject
    protected LinearLayout.LayoutParams layoutParams;

    private CenterFragmentBinding binding;

    private List<TasteVo> tasteVos=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.center_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        binding.btnEditIfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserifoActivity.class));
            }
        });
        binding.btnEditLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LabelActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("tasteVos", (Serializable) tasteVos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onloadData() {
        centerContanctsPresenter.getUserDetailData();
        centerContanctsPresenter.getUserAllLabels();
    }

    @Override
    protected void initComponent() {
        DaggerCenterComponent
                .builder()
                .centerModule(new CenterModule(this))
                .build()
                .injectCenterFragment(this);
    }

    @Override
    public void showDialog() {
        showMsg("加载中...");
    }

    @Override
    public void showError(String msg) {
        showMsg(msg);
    }

    @Override
    public void showUserDetail(UserVo userVo) {
        binding.setUserVo(userVo);
        Glide.with(getContext())
                .load(NewsAPI.BASE_IMAGE_URL+userVo.getHead())
                .placeholder(R.mipmap.user_head)
                .error(R.mipmap.ic_news)
                .into(binding.profileImage);
    }

    @Override
    public void showAllLabels(List<TasteVo> tasteVo) {
        tasteVos.clear();
        if(tasteVo!=null){
            if(tasteVo.size()==0){
                binding.userNoLabel.setVisibility(View.VISIBLE);
            }else{
                binding.userNoLabel.setVisibility(View.GONE);
                for (TasteVo taste : tasteVo){
                    addTextViewToLabels(taste);
                    tasteVos.add(taste);
                }
            }
        }else{
            binding.userNoLabel.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 动态的添加兴趣标签到布局中
     * @param taste
     */
    private void addTextViewToLabels(TasteVo taste) {
        TextView tv=new TextView(getContext());
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundResource(R.drawable.label_background);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(12);
        tv.setText(taste.getLabel());
        tv.setLayoutParams(layoutParams);
        binding.userlabels.addView(tv);
    }

    private void showMsg(String msg){
        Snackbar.make(binding.userCenterPage,msg,Snackbar.LENGTH_SHORT).show();
    }
}
