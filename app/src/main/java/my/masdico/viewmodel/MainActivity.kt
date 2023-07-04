package my.masdico.viewmodel

import android.os.Bundle
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

        showFragment()
    }

    private fun showFragment() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(ListFragment::class.java.simpleName)
        if (fragment !is ListFragment){
            fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ListFragment(), ListFragment::class.java.simpleName)
                .commit()
        }
    }
}