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
import sanchez.juan.mydigimind.ui.Task

class HomeFragment : Fragment() {



    private var adaptador: AdaptadorTareas? = null

    private var _binding: FragmentHomeBinding? = null

    companion object{
        var task = ArrayList<Task>()
        var first = true
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        if(first){
            fillTasks()
            first = false
        }




        adaptador = AdaptadorTareas(root.context, task )
        val gridView: GridView = root.findViewById(R.id.gridview)

        gridView.adapter = adaptador

        return root
    }

    fun fillTasks(){
        task.add(Task("Practice 01", arrayListOf("Monday","Sunday"), "17:30"))
        task.add(Task("Practice 02", arrayListOf("Monday","Sunday"),"17:40"))
        task.add(Task("Practice 03", arrayListOf("Wednesday"),"14:00"))
        task.add(Task("Practice 04", arrayListOf("Saturday"),"11:00"))
        task.add(Task("Practice 05", arrayListOf("Friday"),"13:00"))
        task.add(Task("Practice 06", arrayListOf("Thursday"),"10:40"))
        task.add(Task("Practice 07", arrayListOf("Monday"),"12:00"))
    }

    private class AdaptadorTareas : BaseAdapter{

        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Task>){
            this.contexto = contexto
            this.tasks = tasks
        }

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
            var task = tasks[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view, null)

            var tv_title: TextView = vista.findViewById(R.id.tv_title)
            var tv_time: TextView = vista.findViewById(R.id.tv_time)
            var tv_days: TextView = vista.findViewById(R.id.tv_days)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}