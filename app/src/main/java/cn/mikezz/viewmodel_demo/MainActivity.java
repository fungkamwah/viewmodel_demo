package cn.mikezz.viewmodel_demo;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    MessageViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

        //以下几行，在ContentFagment的onCreate方法之前给viewModel的liveData . setValue
        ContentFragment fragment = ContentFragment.getInstance("just show example");
        viewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        viewModel.liveData.setValue(new Message());
        //Android X 中getFragmentManager()已过时
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        //修改model的值，并给liveData重新setValue
        Message message = viewModel.liveData.getValue();
        switch (v.getId()) {
            case R.id.btn1:
                message.string = "clicked button1";
                break;
            case R.id.btn2:
                message.string = "clicked button2";
                break;

        }
        //setValue在Ui线程，postValue可以在非UI线程
        viewModel.liveData.postValue(message);

    }
}
