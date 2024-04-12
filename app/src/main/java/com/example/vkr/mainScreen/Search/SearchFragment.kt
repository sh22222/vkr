package com.example.vkr.mainScreen.Search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.R
import com.example.vkr.mainScreen.ForNews.AdapterForNews
import com.example.vkr.mainScreen.ForNews.News

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun SetSpinner(spinner:Spinner,array:Int, layout:Int){
        val adapter = ArrayAdapter.createFromResource(requireContext(), array, layout)
        spinner.adapter=adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etGenre = view.findViewById<EditText>(R.id.etSearchGenre)
        val etPlatform = view.findViewById<EditText>(R.id.etSearchPlatform)
        val spinnerGenres = view.findViewById<Spinner>(R.id.spinnerSearchGenre)
        val spinnerPlatform = view.findViewById<Spinner>(R.id.spinnerSearchPlatform)
        SetSpinner(spinnerGenres,R.array.arrayGenres,R.layout.spinner_dropdown_item)
        SetSpinner(spinnerPlatform,R.array.arrayPlatforms,R.layout.spinner_dropdown_item)
        spinnerGenres.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0){
                    var genre = parent?.getItemAtPosition(position).toString()
                    var etString : String = etGenre.getText().toString()
                    etString = etString+genre + ","
                    etGenre.setText(etString)
                    parent?.setSelection(0)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?){}
        }
        spinnerPlatform.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0){
                    var platform = parent?.getItemAtPosition(position).toString()
                    var etString : String = etPlatform.getText().toString()
                    etString = etString+ platform + ","
                    etPlatform.setText(etString)
                    parent?.setSelection(0)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        var games = ArrayList<Game>()

        var dataHeadline = arrayOf(
            "Но синтетическое тестирование",
            "Сложно сказать, почему представители современных социальных резервов",
            "Внезапно, сторонники тоталитаризма",
            "Задача организации, в особенности же курс на социально-ориентированный национальный проект",
            "Также как граница обучения кадров",
            "С другой стороны, современная методология",
            "Есть над чем задуматься",
            "Значимость этих проблем настолько очевидна",
            "Высокий уровень вовлечения представителей целевой аудитории",
            "С учётом сложившейся международной обстановки"
        )
        var dataImage = arrayOf(
            R.drawable.zenicu,
            R.drawable.baseline_person_24,
            R.drawable.search,
            R.drawable.zenicu,
            R.drawable.image,
            R.drawable.sakura,
            R.drawable.baseline_home_24,
            R.drawable.sakura,
            R.drawable.image,
            R.drawable.star
        )
        for(i in 0..9){
            games.add(Game(i,dataImage[i],dataHeadline[i],"platform","genre","developer","publisher","description", "12:00 20.01.2024"))
        }
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGames)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        var adapterForGames = AdapterForGames(games)
        recyclerView?.adapter=adapterForGames

    }
}