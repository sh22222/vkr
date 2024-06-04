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
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.vkr.DataBase.Dao
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.R
import com.example.vkr.mainScreen.Profile.Profile
import com.google.firebase.Firebase
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

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

    fun SetAdapter(multi: MultiAutoCompleteTextView, list: List<String>, layout: Int) {
        var adapterMulti = ArrayAdapter(requireContext(), layout, list)
        multi.setTokenizer(CommaTokenizer())
        multi.setAdapter(adapterMulti)
    }

    fun CreateRecyclerView(game: ArrayList<Game>, profile: Profile) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewGames)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        var adapterForGames = AdapterForGames(game)
        recyclerView?.adapter = adapterForGames
        adapterForGames.setOnItemClickListener(object : AdapterForGames.onItemClickListener {
            override fun onItemClick(position: Int, gameItem: Game) {
                val intent = Intent(context, SpecificGame::class.java)
                //отправляем данные
                intent.putExtra("profile", profile)
                intent.putExtra("gamesItem", gameItem)
                startActivity(intent)
            }

        })
    }

    fun FindAllGames(profile: Profile) {
        var db = FirebaseFirestore.getInstance().collection("game")
        val game = ArrayList<Game>()
        db.orderBy("idGame", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { games ->
                for (g in games) {
                    var plat = g.data.get("platform")
                    var genre = g.data.get("genre")
                    var publ = g.data.get("publisher")
                    game.add(
                        Game(
                            g.data.get("idGame").toString().toInt(),
                            g.data.get("name").toString(),
                            plat as List<String>,
                            genre as List<String>,
                            g.data.get("developer").toString(),
                            publ as List<String>,
                            g.data.get("description").toString(),
                            g.data.get("releaseDate").toString(),
                            g.data.get("pathImage").toString()
                        )
                    )
                }
                CreateRecyclerView(game, profile)
            }
    }

    fun SetItemSelectedListenerForMulti(multi: MultiAutoCompleteTextView) {
        multi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var item = parent?.getItemAtPosition(position).toString()
                var text = multi.text.toString()
                text += "$item, "
                multi.setText(text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    //составление условий поиска после when
    fun addCondition(startCond: String, array: List<String>): String {
        var ident = startCond
        for (i in 0..array.size - 2) {
            val p = array[i]
            if (i <= array.size - 3)
                ident += "\"$p\","
            else ident += "\"$p\") "
        }
        return ident
    }

    //сравнение массива введенных данных и данных из базы данных
    fun compareArrays(arrayData: ArrayList<String>, arrayInter: List<String>): Boolean {
        var pr = 0
        for (i in arrayData) {
            for (j in 0..arrayInter.count()-1) {
                if (i.compareTo(arrayInter[j],true)==0) {
                    pr++
                    break
                }
            }
        }
        if (pr == arrayInter.size - 1 && pr != 0) {
            return true
        } else return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var db = FirebaseFirestore.getInstance()
        var profile = arguments?.getSerializable("profile") as Profile
        val etName = view.findViewById<EditText>(R.id.etSearchName)
        var multiGenre = view.findViewById<MultiAutoCompleteTextView>(R.id.mactvGenre)
        var multiPlatform = view.findViewById<MultiAutoCompleteTextView>(R.id.mactvPlatform)

        val autoDeveloper = view.findViewById<AutoCompleteTextView>(R.id.autoSearchDeveloper)
        val multiPublisher = view.findViewById<MultiAutoCompleteTextView>(R.id.mactvSearchPublisher)
        val etDataRelease = view.findViewById<EditText>(R.id.etSearchRealeaseData)
        //зададим адаптер и слушатель на список жанров

        var genre = ArrayList<String>()
        db.collection("genres").get().addOnSuccessListener { genres ->
            for (g in genres) {
                genre.add(g.data.get("genre").toString())
            }
        }
        SetAdapter(multiGenre, genre, R.layout.dropdown_item)
        multiGenre.setOnClickListener {
            multiGenre.showDropDown()
        }
        SetItemSelectedListenerForMulti(multiGenre)
        //зададим адаптер и слушатель на список платформ
        var platform = ArrayList<String>()
        db.collection("platforms").get().addOnSuccessListener { platforms ->
            for (p in platforms) {
                platform.add(p.data.get("platform").toString())
            }
        }
        SetAdapter(multiPlatform, platform, R.layout.dropdown_item)
        multiPlatform.setOnClickListener {
            multiPlatform.showDropDown()
        }
        SetItemSelectedListenerForMulti(multiPlatform)
        //зададим адаптер на выпаающий список при вводе разработчика
        var developer = ArrayList<String>()
        db.collection("developers").get().addOnSuccessListener { developers ->
            for (d in developers) {
                developer.add(d.data.get("developer").toString())
            }
        }
        var adapterDeveloper = ArrayAdapter(requireContext(), R.layout.dropdown_item, developer)
        autoDeveloper.threshold = 1
        autoDeveloper.setAdapter(adapterDeveloper)
        //зададим адаптер на выпаающий список при вводе издателя
        var publisher = ArrayList<String>()
        db.collection("publishers").get().addOnSuccessListener { publishers ->
            for (p in publishers) {
                publisher.add(p.data.get("publisher").toString())
            }
        }
        multiPublisher.threshold = 1
        SetAdapter(multiPublisher, publisher, R.layout.dropdown_item)
        SetItemSelectedListenerForMulti(multiPublisher)
        //кнопка очистки
        var btClear = view.findViewById<Button>(R.id.btSearchClear)
        btClear.setOnClickListener {
            multiGenre.setText("")
            multiPlatform.setText("")
        }
        //при первой загрузке выводим все игры
        FindAllGames(profile)
        //кнопка поиска
        val btSearch = view.findViewById<Button>(R.id.btSearch)
        btSearch.setOnClickListener {
            val name = etName.text.toString()
            val genre = multiGenre.text.toString()
            val platform = multiPlatform.text.toString()
            val developer = autoDeveloper.text.toString()
            val publisher = multiPublisher.text.toString()
            val dateRelease = etDataRelease.text.toString()
            var n = 0 //количество характеристик, по которым будем сравнивать
            var query = "select * from Games "
            var join = ""
            val whereArray = ArrayList<String>()
            //var where = "where "
            var where = ArrayList<String>()

            if (name != "") {
                n++
            }
            val arrGenre = genre.split(", ")
            if (genre != "") {
                n++
            }
            val arrPlatform = platform.split(", ")
            if (platform != "") {
                n++
            }
            if (developer != "") {
                n++
            }
            val arrPublisher = publisher.split(", ")
            if (publisher != "") {
                n++
            }
            if (dateRelease != "") {
                n++
            }
            if (n != 0) {
                var data = ArrayList<Game>()
                db.collection("game")
                    .orderBy("idGame",Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener { games ->
                        for (g in games) {
                            var plat = g.data.get("platform")
                            var genre = g.data.get("genre")
                            var publ = g.data.get("publisher")
                            data.add(
                                Game(
                                    g.data.get("idGame").toString().toInt(),
                                    g.data.get("name").toString(),
                                    plat as List<String>,
                                    genre as List<String>,
                                    g.data.get("developer").toString(),
                                    publ as List<String>,
                                    g.data.get("description").toString(),
                                    g.data.get("releaseDate").toString(),
                                    g.data.get("pathImage").toString()
                                )
                            )
                        }
                        var dataGame = ArrayList<Game>()
                        for (d in data) {
                            var m = 0
                            if (d.name.contains(name, true) && name != "") {
                                m++
                            }
                            if (compareArrays(d.genre as ArrayList<String>, arrGenre)) {
                                m++
                            }
                            if (compareArrays(d.platform as ArrayList<String>, arrPlatform)) {
                                m++
                            }
                            if (d.developer.contains(developer, true) && developer != "") {
                                m++
                            }
                            if (compareArrays(d.publisher as ArrayList<String>, arrPublisher)) {
                                m++
                            }
                            if (d.releaseDate.contains(dateRelease, true) && dateRelease != "") {
                                m++
                            }
                            if (m == n) {
                                dataGame.add(
                                    Game(
                                        d.idGame,
                                        d.name,
                                        d.platform,
                                        d.genre,
                                        d.developer,
                                        d.publisher,
                                        d.description,
                                        d.releaseDate,
                                        d.pathImage
                                    )
                                )
                            }
                        }
                        CreateRecyclerView(dataGame, profile)
                    }

            } else {
                FindAllGames(profile)
            }
        }

    }
}