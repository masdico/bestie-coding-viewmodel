package my.masdico.viewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import my.masdico.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainBinding.tvName.text = mainViewModel.displayName

        mainBinding.btnToast.setOnClickListener {
            Toast.makeText(this, "rotate device to see the change", Toast.LENGTH_LONG).show()

            val name: String = mainBinding.etName.text.toString()
            mainBinding.etName.setText("")

            mainViewModel.changeName(name)
            mainBinding.tvName.text = mainViewModel.displayName
        }
    }
}