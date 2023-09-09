package usuario.example.tpo4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import usuario.example.tpo4.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         vm =new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

  vm.getMError().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(String s) {
          binding.tvMsjError.setText(s);
      }
  });
  binding.btllamar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          vm.llamar(binding.tvnumero.getText().toString());
      }
  });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}