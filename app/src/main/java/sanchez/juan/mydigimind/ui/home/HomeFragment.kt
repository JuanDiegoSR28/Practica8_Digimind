package sanchez.juan.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sanchez.juan.mydigimind.R
import sanchez.juan.mydigimind.databinding.FragmentHomeBinding
import sanchez.juan.mydigimind.ui.notifications.Task

class HomeFragment : Fragment() {

    private var adaptador: AdaptadorTareas? = null
    private var _binding: FragmentHomeBinding? = null

    companion object {
        var task = ArrayList<Task>()
        var first = true
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (first) {
            fillTasks()
            first = false
        }

        adaptador = AdaptadorTareas(requireContext(), task)
        val gridView: GridView = root.findViewById(R.id.gridview)
        gridView.adapter = adaptador

        return root
    }

    private fun fillTasks() {
        task.add(Task("Practice 01", arrayListOf("Monday", "Sunday"), "17:30"))
        task.add(Task("Practice 02", arrayListOf("Monday", "Sunday"), "17:40"))
        task.add(Task("Practice 03", arrayListOf("Wednesday"), "14:00"))
        task.add(Task("Practice 04", arrayListOf("Saturday"), "11:00"))
        task.add(Task("Practice 05", arrayListOf("Friday"), "13:00"))
        task.add(Task("Practice 06", arrayListOf("Thursday"), "10:40"))
        task.add(Task("Practice 07", arrayListOf("Monday"), "12:00"))
    }

    private class AdaptadorTareas(context: Context, private val tasks: ArrayList<Task>) : BaseAdapter() {

        private val inflador: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val vista = convertView ?: inflador.inflate(R.layout.task_view, parent, false)

            val tvtitle: TextView = vista.findViewById(R.id.tvTitle)
            val tvtime: TextView = vista.findViewById(R.id.tvTime)
            val tvdays: TextView = vista.findViewById(R.id.tvDays)

            val task = tasks[position]
            tvtitle.text = task.title
            tvtime.text = task.time
            tvdays.text = task.days.joinToString(", ")

            return vista
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
