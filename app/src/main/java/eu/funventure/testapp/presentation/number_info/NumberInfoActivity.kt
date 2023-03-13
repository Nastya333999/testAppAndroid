package eu.funventure.testapp.presentation.number_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import eu.funventure.testapp.databinding.ActivitySecondBinding
import eu.funventure.testapp.util.collectLifecycleAware

@AndroidEntryPoint
class NumberInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel: NumberInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val number = intent.getIntExtra(NUMBER_KEY, -1)
        viewModel.setNumber(number)

        observeViewModel()
    }

    private fun observeViewModel() {
        collectLifecycleAware(viewModel.info) {
            binding.txtDescription.text = it.description
            binding.txtNumber.text = it.value.toString()
        }
    }

    companion object {
        const val NUMBER_KEY = "NUMBER_KEY"
    }
}