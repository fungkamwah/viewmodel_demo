package cn.mikezz.viewmodel_demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Mike(175511@qq.com) on 2019-05-08
 */
public class ContentFragment extends Fragment {

    MessageViewModel viewModel;

    /**
     * 获取ContentFragment实例，setArguments
     * @param example
     * @return
     */
    public static ContentFragment getInstance(String example){
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",example);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment,null);
        Bundle b =getArguments();
        String srt = b.getString("key");//没用到的
        final TextView tv = view.findViewById(R.id.tv);

        viewModel.liveData.observe(this, new Observer<Message>() {
            @Override
            public void onChanged(Message message) {
                //model发生了变化，更新UI内容
                tv.setText(message.string);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取得activity的对应的viewModel
        viewModel = ViewModelProviders.of(getActivity()).get(MessageViewModel.class);
    }
}
