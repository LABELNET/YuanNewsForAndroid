package cn.edu.hpu.yuan.yuannews.user.center;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import cn.edu.hpu.yuan.yuancore.ui.dialog.ColorDialog;
import cn.edu.hpu.yuan.yuancore.ui.dialog.PromptDialog;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.CenterFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.app.BaseApplication;
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


    private final int REQUEST_CODE=2018;
    private final String SHRAED_MOREN_USER_IFNO="No Data";
    private final String BASE_BORDERCAST_ACTION="base_bordercast_action";
    private final String BASE_NOTIFIACTION_ACTION="baase_notification_action";
    private int tasteCode=0;

    @Inject
    protected  CenterContancts.CenterContanctsPresenter centerContanctsPresenter;

    @Inject
    protected ViewGroup.MarginLayoutParams marginLayoutParams;

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
        setHasOptionsMenu(true);

        binding.btnEditIfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), UserifoActivity.class),REQUEST_CODE);
            }
        });
        binding.btnEditLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LabelActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("tasteVos", (Serializable) tasteVos);
                intent.putExtras(bundle);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showOutDialog();
        return super.onOptionsItemSelected(item);
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
        if(tasteCode!=tasteVo.size()){
           activity.sendBroadcast(new Intent(BASE_NOTIFIACTION_ACTION));
        }else{
            tasteCode=tasteVo.size();
        }
        binding.userlabels.removeAllViews();
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
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setTextSize(12);
        tv.setText(taste.getLabel());
        tv.setLines(1);
        binding.userlabels.addView(tv,marginLayoutParams);
    }

    private void showMsg(String msg){
        Snackbar.make(binding.userCenterPage,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CODE==requestCode){
            onloadData();
        }
    }

    private void showOutDialog(){
        new PromptDialog(getContext()).setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                .setTitleText("温馨提示").setContentText("你确定注销账户吗")
                .setPositiveListener("确定", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        BaseApplication.newsAPIShared.putSharedUserIfo(SHRAED_MOREN_USER_IFNO,0,"新闻推荐");
                        Intent intent=new Intent(BASE_BORDERCAST_ACTION);
                        activity.sendBroadcast(intent);
                        dialog.dismiss();
                        activity.finish();
                    }
                }).show();

    }
}
