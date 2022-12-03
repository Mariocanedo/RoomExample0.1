package com.example.roomexample01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.roomexample01.Model.TaskEntity
import com.example.roomexample01.ViewModel.TaskViewModel
import com.example.roomexample01.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var _binding : FragmentFirstBinding
    // agregar View Model
     private val viewModel: TaskViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // recorrer la base de datos
        viewModel.allTask.observe(viewLifecycleOwner, Observer {

            it?.let {
                 binding.tvt.text=it.toString()            }

        })

        val taskExample = TaskEntity(title = "Ejemplo Otro",
            descripcion = "Descripcion",
            author = "MarioC")
          viewModel.insertTask(taskExample)
        Log.d("Database",viewModel.allTask.toString())




        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



     /*   val instance = Room.databaseBuilder(
            // la bade datos sea una para toda la app
            requireContext().applicationContext,
            TaskDataBase::class.java,
            "Task_db")
            // agregro esto para que el error se valla lo agrega al hilo principal
           .allowMainThreadQueries()
            .build()

        var taskExample = TaskEntity(title = "Ejemplo Titulo",
            descripcion = "Descripcion",
            author = "Cristian")
         instance.getTaskDao().insertTask(taskExample)
        Log.d("Database",instance.getTaskDao().getAllTask().toString())*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}