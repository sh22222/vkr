package com.example.vkr.mainScreen.News

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.DataBase.DataClass
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun addData(){
        var db = MainDataBase.getDataBase(requireContext())
        var dao = db.getDao()
        var dataClass = DataClass()
        var listGames = dataClass.getGames()
        var listGenres = dataClass.getGenres()
        var listPlatforms = dataClass.getPlatforms()
        var listDevelopers = dataClass.getDevelopers()
        var listPublishers = dataClass.getPublishers()
        var listGenresGames = dataClass.getGenresGames()
        var listPlatformsGames = dataClass.getPlatformsGames()
        var listPublishersGames = dataClass.getPublisherGames()
        var listNews = dataClass.getNews()

        Thread{
            listGames.forEach{dao.insertGame(it)}
            listGenres.forEach { dao.insertGenre(it) }
            listPlatforms.forEach { dao.insertPlatform(it) }
            listDevelopers.forEach { dao.insertDeveloper(it) }
            listPublishers.forEach { dao.insertPublisher(it) }
            listGenresGames.forEach { dao.insertGenresForGames(it) }
            listPlatformsGames.forEach { dao.insertPlatformsForGames(it) }
            listPublishersGames.forEach { dao.insertPublishersForGames(it) }
            listNews.forEach{dao.insertNews(it)}
        }.start()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //addData()
        var dao = MainDataBase.getDataBase(requireContext()).getDao()
        var news = dao.getNews()
        var data = ArrayList<News>()
        for (i in 0..news.size-1){
            data.add(News(news[i].idNews, news[i].pathPict,news[i].headline,news[i].description,news[i].datePublish))
        }
        var recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        var adapter = AdapterForNews(data)
        recyclerView?.adapter = adapter
        //событие нажатия на item
        adapter.setOnClickListener(object : AdapterForNews.onItemClickListener{
            override fun onItemClick(position: Int, newsItem: News) {
                var intent = Intent(context, SpecificNews::class.java)
                //отправляем данные
                intent.putExtra("newsItem", newsItem)
                startActivity(intent)
            }
        })

    }
}

