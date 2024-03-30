package com.example.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
	private var _binding: FragmentTasksBinding? = null
	private val binding get() = _binding!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentTasksBinding.inflate(inflater, container, false)

		val application = requireNotNull(this.activity).application
		val dao = TaskDatabase.getInstance(application).taskDao
		val viewModelFactory = TasksViewModelFactory(dao)
		binding.viewModel = ViewModelProvider(this, viewModelFactory)[TasksViewModel::class.java]
		binding.lifecycleOwner = viewLifecycleOwner

		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}