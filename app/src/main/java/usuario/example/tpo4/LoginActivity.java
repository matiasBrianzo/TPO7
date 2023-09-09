package usuario.example.tpo4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import usuario.example.tpo4.databinding.ActivityLoginBinding;
import usuario.example.tpo4.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);

        vm.getMError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
              binding.tvError.setText(s);
            }
        });
        vm.getMAccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                vm.OpenMenu(aBoolean);
            }
        });
        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.login(binding.etUsuario.getText().toString(), binding.etContrasenia.getText().toString());
            }
        });
    }
}