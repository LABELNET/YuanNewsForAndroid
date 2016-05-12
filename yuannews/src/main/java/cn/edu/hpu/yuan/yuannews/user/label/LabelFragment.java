package cn.edu.hpu.yuan.yuannews.user.label;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import javax.inject.Inject;
import cn.edu.hpu.yuan.yuannews.R;
import cn.edu.hpu.yuan.yuannews.databinding.LabelFragmentBinding;
import cn.edu.hpu.yuan.yuannews.main.base.NorbalBackFragment;
import cn.edu.hpu.yuan.yuannews.main.data.model.basevo.TasteVo;
import cn.edu.hpu.yuan.yuannews.user.label.adapter.LabelIfoAdapter;

/**
 * Created by yuan on 16-5-12.
 */
public class LabelFragment extends NorbalBackFragment implements LabelContancts.LabelContanctsView,LabelIfoAdapter.OnDeleteItemClick{


    @Inject
    protected LabelContancts.LabelContanctsPresenter labelContanctsPresenter;

    @Inject
    protected LabelIfoAdapter labelIfoAdapter;

    private LabelFragmentBinding binding;

    @Override
    protected void initComponent() {
        DaggerLabelComponent
                .builder()
                .labelModule(new LabelModule(this,getContext()))
                .build()
                .injectLabelFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.label_fragment,container,false);
        return binding.getRoot();
    }

    @Override
    public void showDialog() {
        show("正在处理中");
    }

    @Override
    public void showMsg(String msg) {
        show(msg);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        labelIfoAdapter.setOnDeleteItemClick(this);
        List<TasteVo> tasteVos = (List<TasteVo>) getArguments().getSerializable("tasteVos");
        labelIfoAdapter.addTasteVo(tasteVos);
        labelIfoAdapter.notifyDataSetChanged();
    }

    private void show(String msg){
        Snackbar.make(binding.labelFragment,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public int onDelete(TasteVo tasteVo) {
        return labelContanctsPresenter.postDeleteLabel(tasteVo.getId());
    }
}
