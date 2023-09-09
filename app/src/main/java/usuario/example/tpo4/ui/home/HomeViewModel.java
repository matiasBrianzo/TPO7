package usuario.example.tpo4.ui.home;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends AndroidViewModel {

    private Context context;
    private  MutableLiveData<String> mCadena;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();
    }

    public LiveData<String> getMError() {
        if(mCadena == null) {
            mCadena = new MutableLiveData<>();
        }
        return mCadena;
    }

    public void llamar(String numero){
        if(numero.isEmpty() || numero.equals(" ")){
            mCadena.setValue("El numero no puede estar vacio");
        }else{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numero.toString()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)context, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                return;
            }
            context.startActivity(intent);
        }
    }
    public void llamar2(String numero){
        if(numero.isEmpty() || numero.equals(" ")){
            mCadena.setValue("El numero no puede estar vacio");
        }else{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("content://call_log/calls" + numero));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           /* if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)context, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                return;
            }*/
            context.startActivity(intent);
        }
    }
}