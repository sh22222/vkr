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
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.R
import com.example.vkr.mainScreen.Profile.Profile

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
    fun CreateRecyclerView(game:ArrayList<Game>, profile: Profile){
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewGames)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        var adapterForGames = AdapterForGames(game)
        recyclerView?.adapter=adapterForGames
        adapterForGames.setOnItemClickListener(object : AdapterForGames.onItemClickListener{
            override fun onItemClick(position: Int, gameItem: Game) {
                val intent = Intent(context, SpecificGame::class.java)
                //отправляем данные
                intent.putExtra("profile", profile)
                intent.putExtra("gamesItem", gameItem)
                startActivity(intent)
            }

        })
    }
    //составление условий поиска после when
    fun addCondition (startCond:String, array:List<String>):String{
        var ident = startCond
        for (i in 0..array.size-2){
            val p = array[i]
            if (i <= array.size-3)
                ident += "\"$p\","
            else ident += "\"$p\")"
        }
        return ident
    }
    //сравнение массива введенных данных и данных из базы данных
    fun compareArrays(arrayData: ArrayList<String>, arrayInter: List<String> ):Boolean{
        var pr = 0
        for (i in arrayData) {
            for (j in arrayInter) {
                if (i.compareTo(j) == 0 && j != "") {
                    pr++
                    break
                }
            }
        }
        if (pr == arrayInter.size-1 && pr != 0) {
            return true
        } else return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = MainDataBase.getDataBase(requireContext())
        val dao = db.getDao()

        var profile = arguments?.getSerializable("profile") as Profile

        val etName = view.findViewById<EditText>(R.id.etSearchName)
        val etGenre = view.findViewById<EditText>(R.id.etSearchGenre)

        val etPlatform = view.findViewById<EditText>(R.id.etSearchPlatform)
        var actvPlatform = view.findViewById<AutoCompleteTextView>(R.id.actvPlatform)

        val etDeveloper = view.findViewById<EditText>(R.id.etSearchDeveloper)
        val etPublisher = view.findViewById<EditText>(R.id.etSearchPublisher)
        val etDataRelease = view.findViewById<EditText>(R.id.etSearchRealeaseData)
        val spinnerGenres = view.findViewById<Spinner>(R.id.spinnerSearchGenre)

        val spinnerPlatform = view.findViewById<Spinner>(R.id.spinnerSearchPlatform)



        var genre : List<String> = dao.getGenre()
        //genre = listOf("Жанр:") + genre
        genre = listOf("Жанр:") + genre
        var platform : List<String> = dao.getPlatform()
        //platform = listOf("Платформа:") + platform
        SetSpinner(spinnerGenres,genre,R.layout.spinner_dropdown_item)

        var adapterEt = ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item,platform)
        actvPlatform.threshold=0
        actvPlatform.setAdapter(adapterEt)
//        actvPlatform.setOnFocusChangeListener{
//            view, b -> if(b) actvPlatform.showDropDown()
//        }
        actvPlatform.setOnClickListener {
            actvPlatform.showDropDown()
        }
        actvPlatform.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val platform = parent?.getItemAtPosition(position).toString()
                var text = actvPlatform.text.toString()
                text += "$platform, "
                actvPlatform.setText(text)
                parent?.setSelection(-1)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        SetSpinner(spinnerPlatform,platform,R.layout.spinner_dropdown_item)
        spinnerGenres.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0){
                    val genre = parent?.getItemAtPosition(position).toString()
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
                    val platform = parent?.getItemAtPosition(position).toString()
                    var etString : String = etPlatform.getText().toString()
                    etString = etString+ platform + ", "
                    etPlatform.setText(etString)
                    etPlatform.setSelection(etString.length)
                    parent?.setSelection(0)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val game = ArrayList<Game>()
        val all =dao.getAllDataGames()
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
        CreateRecyclerView(game, profile)
        val btSearch = view.findViewById<Button>(R.id.btSearch)
        btSearch.setOnClickListener {
            val name = etName.text.toString()
            val genre = etGenre.text.toString()
            val platform = etPlatform.text.toString()
            val developer = etDeveloper.text.toString()
            val publisher = etPublisher.text.toString()
            val dataRelease = etDataRelease.text.toString()
            var n = 0 //количество характеристик, по которым будем сравнивать
            var query = "select * from Games "
            var join = ""
            val whereArray = ArrayList<String>()
            var where = "where "

            if(name != ""){
                whereArray.add("Games.nameGame like \"$name%\" ")
                n++
            }
            val arrGenre = genre.split(", ")
            if(genre != ""){
                join += "join genresForGames on genresForGames.idGame=Games.idGame " +
                        "join Genre on genresForGames.idGenre=Genre.idGenre "
                var ident = "Genre.name in ("
                whereArray.add(addCondition(ident,arrGenre))
                n++
            }
            val arrPlatform = platform.split(", ")
            if(platform != ""){
                join += "join platformsForGames on platformsForGames.idGame=Games.idGame " +
                        "join Platform on platformsForGames.idPlatform=Platform.idPlatform "
                var ident = "Platform.name in ("
                whereArray.add(addCondition(ident,arrPlatform))
                n++
            }
            if(developer != ""){
                join += "join Developer on Developer.idDeveloper=Games.idDeveloper "
                whereArray.add("Developer.nameDeveloper like \"$developer%\" ")
                n++
            }
            val arrPublisher = publisher.split(", ")
            if(publisher!=""){
                join += "join publishersForGames on publishersForGames.idGame=Games.idGame" +
                        "join Publisher on Publisher.idPublisher=publishersForGames.idPublisher "
                var ident = "Publisher.namePublisher in ("
                whereArray.add(addCondition(ident,arrPublisher))
                n++
            }
            if(dataRelease !=""){
                whereArray.add("Games.dataRelease like \"%$dataRelease%\" ")
                n++
            }
            for(i in 0..whereArray.size-1){
                if(i<=whereArray.size-2)
                    where += whereArray[i] + " or "
                else where += whereArray[i]
            }
            query += join + where + "group by Games.idGame"
            val simpleSqlQuery = SimpleSQLiteQuery(query)
            val data = dao.getDataGames(simpleSqlQuery)
            var dataGame = ArrayList<Game>()
            for(d in data){
                var m = 0
                if(d.games.nameGame.contains(name,true) && name != ""){
                    m++
                }
                if (compareArrays(d.getGenresName(),arrGenre)){
                    m++
                }
                if(compareArrays(d.getPlatformsName(),arrPlatform)){
                    m++
                }
                if(d.developer.nameDeveloper.contains(developer, true) && developer != ""){
                    m++
                }
                if(compareArrays(d.getPublishersName(), arrPublisher)){
                    m++
                }
                if(d.games.dataRelease.contains(dataRelease,true) && dataRelease != ""){
                    m++
                }
                if(m==n){
                    dataGame.add(Game(d.games.idGame,
                        d.games.nameGame,
                        d.getPlatformsName(),
                        d.getGenresName(),
                        d.developer.nameDeveloper,
                        d.getPublishersName(),
                        d.games.description,
                        d.games.dataRelease,
                        ""))
                }
            }
            CreateRecyclerView(dataGame,profile)
        }

    }
}