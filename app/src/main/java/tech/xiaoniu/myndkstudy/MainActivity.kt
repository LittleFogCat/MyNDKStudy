package tech.xiaoniu.myndkstudy

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.xiaoniu.myndkstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding
    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val ndkBridge = NdkBridge()

    private var start = 0L

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCal.setOnClickListener {
            val i = binding.etI.text.toString().toIntOrNull() ?: run {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "btnCal: Invalid input")
                return@setOnClickListener
            }
            start = System.currentTimeMillis()
            coroutine.launch {
                val result = ndkBridge.computeFibonacci(i).result
                withContext(Dispatchers.Main) {
                    binding.tvResult.text = java.lang.String.valueOf(result)
                    binding.tvTime.text = (System.currentTimeMillis() - start).toString()
                }
            }
        }
        binding.btnMinus.setOnClickListener {
            binding.etI.setText((binding.etI.text.toString().toIntOrNull()?.minus(1) ?: 0).toString())
        }
        binding.btnPlus.setOnClickListener {
            binding.etI.setText((binding.etI.text.toString().toIntOrNull()?.plus(1) ?: 0).toString())
        }
    }
}