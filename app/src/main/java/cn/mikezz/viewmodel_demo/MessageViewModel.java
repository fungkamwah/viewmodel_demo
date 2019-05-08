package cn.mikezz.viewmodel_demo;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel
 *
* Created by Mike(175511@qq.com) on 2019-05-08
*/
public  class MessageViewModel extends ViewModel   {

    //被观察的liveData
    public final MutableLiveData<Message> liveData = new MutableLiveData<>();


}
