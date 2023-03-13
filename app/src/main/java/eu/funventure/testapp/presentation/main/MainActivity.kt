package eu.funventure.testapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import eu.funventure.testapp.R
import eu.funventure.testapp.databinding.ActivityMainBinding
import eu.funventure.testapp.presentation.number_info.NumberInfoActivity
import eu.funventure.testapp.util.collectLifecycleAware

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val editNumber by lazy { findViewById<EditText>(R.id.etNumber) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.rv) }
    private val numbersAdapter = NumberAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListeners()
        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        recyclerView.adapter = numbersAdapter
    }

    private fun initClickListeners() {
        binding.btnRandomNumber.setOnClickListener { viewModel.randomNumberClick() }

        binding.btnCurentNumber.setOnClickListener {
            val selectedNumber = editNumber.text.toString()
            if (selectedNumber == "") {
                Toast.makeText(this, "Enter a number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.numberFromUseClick(selectedNumber.toInt())
        }

        numbersAdapter.setOnItemClickListener { navigateToNumberInfo(it.value) }
    }

    private fun observeViewModel() {
        collectLifecycleAware(viewModel.number) { navigateToNumberInfo(it) }

        collectLifecycleAware(viewModel.numbersInfo) { numbersAdapter.submitList(it) }
    }

    private fun navigateToNumberInfo(number: Int) {
        startActivity(
            Intent(this@MainActivity, NumberInfoActivity::class.java)
                .apply { putExtra(NumberInfoActivity.NUMBER_KEY, number) }
        )
    }
}