package com.example.vkr.mainScreen.Wishlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.R
import com.example.vkr.mainScreen.Profile.Profile
import com.example.vkr.mainScreen.Search.AdapterForGames
import com.example.vkr.mainScreen.Search.Game
import com.example.vkr.mainScreen.Search.SpecificGame
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WishlistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WishlistFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WishlistFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WishlistFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    fun CreateRecyclerView(game:ArrayList<Game>){
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewWishlist)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        var adapterForGames = AdapterForGames(game)
        recyclerView?.adapter=adapterForGames
        adapterForGames.setOnItemClickListener(object : AdapterForGames.onItemClickListener{
            override fun onItemClick(position: Int, gameItem: Game) {
                val intent = Intent(context, SpecificGame::class.java)
                //отправляем данные
                intent.putExtra("profile", profile)
                intent.putExtra("gamesItem", gameItem)
                startActivityForResult(intent,10)
            }
        })
    }
    fun findList(){
        var db = FirebaseFirestore.getInstance()
        var game = ArrayList<Game>()
        var list = ArrayList<Long>()
        db.collection("profile").document(profile.getId()).get().addOnSuccessListener {p->
            if(p.exists()){
                list = p.data?.get("listGames") as ArrayList<Long>
            }
            for(i in list){
                db.collection("game").document(i.toString()).get().addOnSuccessListener {g->
                    if(g.exists()){
                        var plat = g.data?.get("platform")
                        var genre = g.data?.get("genre")
                        var publ = g.data?.get("publisher")
                        game.add(
                            Game(
                                g.data?.get("idGame").toString().toInt(),
                                g.data?.get("name").toString(),
                                plat as List<String>,
                                genre as List<String>,
                                g.data?.get("developer").toString(),
                                publ as List<String>,
                                g.data?.get("description").toString(),
                                g.data?.get("releaseDate").toString(),
                                g.data?.get("pathImage").toString()
                            )
                        )
                    }
                    CreateRecyclerView(game)
                }
            }
        }
    }
    lateinit var profile : Profile
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode == Activity.RESULT_OK)
//            findList()
//    }
    override fun onResume() {
        super.onResume()
        findList()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profile = arguments?.getSerializable("profile") as Profile
    }
}