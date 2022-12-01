package com.example.roomexample01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.roomexample01.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

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

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



        val instance = Room.databaseBuilder(
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
        Log.d("Database",instance.getTaskDao().getAllTask().toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}