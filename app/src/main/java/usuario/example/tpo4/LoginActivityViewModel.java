package usuario.example.tpo4;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

public class LoginActivityViewModel extends AndroidViewModel {

    private Context context;
    private Map<String,String> users = new HashMap<>();
    private MutableLiveData<String> error;
    private MutableLiveData<Boolean> access;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        users.put("1","1");
    }
    public LiveData<String> getMError() {
        if(error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }
    public LiveData<Boolean> getMAccess() {
        if(access == null){
            access = new MutableLiveData<>();
        }
        return access;
    }
    public void login(String username, String password){
        if(!users.containsKey(username)){
            error.setValue("El usuario no existe");
        }else if(!users.get(username).equals(password)){
            error.setValue("Contraseña incorrecta");
        }else{
            error.setValue(" ");
            access.setValue(true);
        }
    }

    public void OpenMenu(Boolean aBoolean) {
        if(aBoolean){
            // abrir activity el menu
            if(aBoolean){
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }

    }
}
