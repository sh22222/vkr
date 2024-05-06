package com.example.vkr.mainScreen.Search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.vkr.DataBase.Dao
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.DataBase.Publisher
import com.example.vkr.R
import kotlinx.coroutines.MainScope

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
    fun SetSpinner(spinner:Spinner, list : List<String>, layout:Int){
        //val adapter = ArrayAdapter.createFromResource(requireContext(), array, layout)
        val adapter = ArrayAdapter(requireContext(), layout, list)
        spinner.adapter=adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = MainDataBase.getDataBase(requireContext())
        val dao = db.getDao()
        val etName = view.findViewById<EditText>(R.id.etSearchName)
        val etGenre = view.findViewById<EditText>(R.id.etSearchGenre)

        val etPlatform = view.findViewById<EditText>(R.id.etSearchPlatform)
        //var etPlatform = view.findViewById<AutoCompleteTextView>(R.id.actvPlatform)

        val etDeveloper = view.findViewById<EditText>(R.id.etSearchDeveloper)
        val etPublisher = view.findViewById<EditText>(R.id.etSearchPublisher)
        val etDataRelease = view.findViewById<EditText>(R.id.etSearchRealeaseData)
        val spinnerGenres = view.findViewById<Spinner>(R.id.spinnerSearchGenre)

        val spinnerPlatform = view.findViewById<Spinner>(R.id.spinnerSearchPlatform)



        var genre : List<String> = dao.getGenre()
        genre = listOf("Жанр:") + genre
        var platform : List<String> = dao.getPlatform()
        platform = listOf("Платформа:") + platform
        SetSpinner(spinnerGenres,genre,R.layout.spinner_dropdown_item)

//        var adapterEt = ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item,platform)
//        etPlatform.threshold=0
//        etPlatform.setAdapter(adapterEt)

        SetSpinner(spinnerPlatform,platform,R.layout.spinner_dropdown_item)
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
                    etString = etString+genre + ", "
                    etGenre.setText(etString)
                    etGenre.setSelection(etString.length)
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
                    etString = etString+ platform + ", "
                    etPlatform.setText(etString)
                    etPlatform.setSelection(etString.length)
                    parent?.setSelection(0)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        var game = ArrayList<Game>()
        var all =dao.getAllDataGames()
        for(i in 0..all.size-1){
            game.add(
                Game(all[i].games.idGame,
                    all[i].games.nameGame,
                    all[i].getPlatformsName(),
                    all[i].getGenresName(),
                    all[i].developer.nameDeveloper,
                    all[i].getPublishersName(),
                    all[i].games.description,
                    all[i].games.dataRelease,
                    ""
                ))
        }

        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewGames)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        var adapterForGames = AdapterForGames(game)
        recyclerView?.adapter=adapterForGames
        adapterForGames.setOnItemClickListener(object : AdapterForGames.onItemClickListener{
            override fun onItemClick(position: Int, gameItem: Game) {
                var intent = Intent(context, SpecificGame::class.java)
                //отправляем данные
                intent.putExtra("gamesItem", gameItem)
                startActivity(intent)
            }

        })
        var btSearch = view.findViewById<Button>(R.id.btSearch)
        btSearch.setOnClickListener {
//            val etName = view.findViewById<EditText>(R.id.etSearchName)
//            val etGenre = view.findViewById<EditText>(R.id.etSearchGenre)
//            val etPlatform = view.findViewById<EditText>(R.id.etSearchPlatform)
//            val etDeveloper = view.findViewById<EditText>(R.id.etSearchDeveloper)
//            val etPublisher = view.findViewById<EditText>(R.id.etSearchPublisher)
//            val etDataRelease = view.findViewById<EditText>(R.id.etSearchRealeaseData)
            //var data = dao.getPlatformDataGames()

//            var query0 = "select * from Games \n" +
//                    "join genresForGames on genresForGames.idGame=Games.idGame\n" +
//                    "join Genre on genresForGames.idGenre=Genre.idGenre\n" +
//                    "join platformsForGames on platformsForGames.idGame=Games.idGame\n" +
//                    "join Platform on platformsForGames.idPlatform=Platform.idPlatform\n" +
//                    "join publishersForGames on publishersForGames.idGame=Games.idGame\n" +
//                    "join Publisher on publishersForGames.idPublisher = Publisher.idPublisher\n" +
//                    "group by Games.idGame"
//            var simpleSqlQury: SimpleSQLiteQuery = SimpleSQLiteQuery(query0)
//            var data = dao.getDataGames(simpleSqlQury)

            var name = etName.text.toString()
            var genre = etGenre.text.toString()
            var platform = etPlatform.text.toString()
            var developer = etDeveloper.text.toString()
            var publisher = etPublisher.text.toString()
            var dataRelease = etDataRelease.text.toString()


            var query = "select * from Games "
            var join = ""
            var whereArray = ArrayList<String>()
            var where = "where "

            if(name != ""){
                whereArray.add("Games.nameGame like \"$name%\" ")
            }
            if(genre != ""){
                var arrGenre = genre.split(", ")
                join += "join genresForGames on genresForGames.idGame=Games.idGame " +
                        "join Genre on genresForGames.idGenre=Genre.idGenre "
                var ident = "Genre.name in ("
                for (i in 0..arrGenre.size-2){
                    var genre = arrGenre[i]
                    if (i <= arrGenre.size-3)
                        ident += "\"$genre\", "
                    else ident += "\"$genre\")"
                }
                whereArray.add(ident)
            }
            if(platform != ""){
                var arrPlatform = platform.split(", ")
                join += "join platformsForGames on platformsForGames.idGame=Games.idGame " +
                        "join Platform on platformsForGames.idPlatform=Platform.idPlatform "
                var ident = "Platform.name in ("
                for (i in 0..arrPlatform.size-2){
                    var p = arrPlatform[i]
                    if (i <= arrPlatform.size-3)
                        ident += "\"$p\","
                    else ident += "\"$p\")"
                }
                whereArray.add(ident)
            }
            if(developer != ""){
                join += "join Developer on Developer.idDeveloper=Games.idDeveloper "
                whereArray.add("Developer.nameDeveloper like \"$developer%\" ")
            }
            if(publisher!=""){
                var arrPublisher = publisher.split(", ")
                join += "join publishersForGames on publishersForGames.idGame=Games.idGame" +
                        "join Publisher on Publisher.idPublisher=publishersForGames.idPublisher "
                var ident = "Publisher.namePublisher in ("
                for (i in 0..arrPublisher.size-2){
                    var p = arrPublisher[i]
                    if (i <= arrPublisher.size-3)
                        ident += "\"$p\","
                    else ident += "\"$p\")"
                }
                whereArray.add(ident)
            }
            if(dataRelease !=""){
                whereArray.add("Games.dataRelease like \"$dataRelease%\" ")
            }
            for(i in 0..whereArray.size-1){
                if(i<=whereArray.size-2)
                    where += whereArray[i] + " or "
                else where += whereArray[i]
            }
            query += join + where + "group by Games.idGame order by Games.idGame desc"
            var simpleSqlQury: SimpleSQLiteQuery = SimpleSQLiteQuery(query)
            var data = dao.getDataGames(simpleSqlQury)
            var s = ""
        }

    }
}