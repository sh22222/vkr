package com.example.vkr.mainScreen.ForNews

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.DataBase.DataClass
import com.example.vkr.DataBase.Games
import com.example.vkr.DataBase.Genre
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.R
import kotlinx.coroutines.flow.observeOn

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

        Thread{
            listGames.forEach{dao.insertGame(it)}
            listGenres.forEach { dao.insertGenre(it) }
            listPlatforms.forEach { dao.insertPlatform(it) }
            listDevelopers.forEach { dao.insertDeveloper(it) }
            listPublishers.forEach { dao.insertPublisher(it) }
            listGenresGames.forEach { dao.insertGenresForGames(it) }
            listPlatformsGames.forEach { dao.insertPlatformsForGames(it) }
            listPublishersGames.forEach { dao.insertPublishersForGames(it) }
        }.start()
        db.close()

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data = ArrayList<News>()
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
        var dataDescription = arrayOf(
            "Но синтетическое тестирование создаёт необходимость включения в производственный план целого ряда внеочередных мероприятий с учётом комплекса глубокомысленных рассуждений. Являясь всего лишь частью общей картины, сторонники тоталитаризма в науке, инициированные исключительно синтетически, обнародованы. В целом, конечно, современная методология разработки играет определяющее значение для экспериментов, поражающих по своей масштабности и грандиозности.",
            "Сложно сказать, почему представители современных социальных резервов формируют глобальную экономическую сеть и при этом — представлены в исключительно положительном свете. С другой стороны, сплочённость команды профессионалов играет важную роль в формировании позиций, занимаемых участниками в отношении поставленных задач. В целом, конечно, постоянное информационно-пропагандистское обеспечение нашей деятельности обеспечивает широкому кругу (специалистов) участие в формировании новых предложений. Прежде всего, синтетическое тестирование играет важную роль в формировании дальнейших направлений развития. Ясность нашей позиции очевидна: социально-экономическое развитие предоставляет широкие возможности для новых предложений. Современные технологии достигли такого уровня, что синтетическое тестирование обеспечивает актуальность глубокомысленных рассуждений.",
            "Внезапно, сторонники тоталитаризма в науке ограничены исключительно образом мышления. Таким образом, существующая теория представляет собой интересный эксперимент проверки благоприятных перспектив.",
            "Задача организации, в особенности же курс на социально-ориентированный национальный проект прекрасно подходит для реализации своевременного выполнения сверхзадачи. Учитывая ключевые сценарии поведения, семантический разбор внешних противодействий прекрасно подходит для реализации системы обучения кадров, соответствующей насущным потребностям. Прежде всего, социально-экономическое развитие говорит о возможностях системы массового участия. Учитывая ключевые сценарии поведения, укрепление и развитие внутренней структуры однозначно фиксирует необходимость приоретизации разума над эмоциями. А также реплицированные с зарубежных источников, современные исследования призывают нас к новым свершениям, которые, в свою очередь, должны быть заблокированы в рамках своих собственных рациональных ограничений. Равным образом, реализация намеченных плановых заданий обеспечивает актуальность как самодостаточных, так и внешне зависимых концептуальных решений. Имеется спорная точка зрения, гласящая примерно следующее: сделанные на базе интернет-аналитики выводы указаны как претенденты на роль ключевых факторов. Лишь активно развивающиеся страны третьего мира призывают нас к новым свершениям, которые, в свою очередь, должны быть призваны к ответу. Лишь независимые государства объективно рассмотрены соответствующими инстанциями. Наше дело не так однозначно, как может показаться: постоянный количественный рост и сфера нашей активности предполагает независимые способы реализации позиций, занимаемых участниками в отношении поставленных задач.",
            "Также как граница обучения кадров влечет за собой процесс внедрения и модернизации анализа существующих паттернов поведения. Банальные, но неопровержимые выводы, а также стремящиеся вытеснить традиционное производство, нанотехнологии рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок. В рамках спецификации современных стандартов, действия представителей оппозиции рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок. Однозначно, активно развивающиеся страны третьего мира обнародованы.",
            "С другой стороны, современная методология разработки говорит о возможностях первоочередных требований. Как уже неоднократно упомянуто, некоторые особенности внутренней политики представлены в исключительно положительном свете. В рамках спецификации современных стандартов, некоторые особенности внутренней политики набирают популярность среди определенных слоев населения, а значит, должны быть указаны как претенденты на роль ключевых факторов.",
            "Есть над чем задуматься: реплицированные с зарубежных источников, современные исследования, превозмогая сложившуюся непростую экономическую ситуацию, указаны как претенденты на роль ключевых факторов. Мы вынуждены отталкиваться от того, что существующая теория позволяет выполнить важные задания по разработке глубокомысленных рассуждений. В своём стремлении повысить качество жизни, они забывают, что постоянный количественный рост и сфера нашей активности предоставляет широкие возможности для новых принципов формирования материально-технической и кадровой базы. Картельные сговоры не допускают ситуации, при которой некоторые особенности внутренней политики освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок. Предварительные выводы неутешительны: перспективное планирование выявляет срочную потребность существующих финансовых и административных условий.",
            "Значимость этих проблем настолько очевидна, что современная методология разработки позволяет выполнить важные задания по разработке первоочередных требований. Предварительные выводы неутешительны: существующая теория представляет собой интересный эксперимент проверки инновационных методов управления процессами. Приятно, граждане, наблюдать, как сделанные на базе интернет-аналитики выводы неоднозначны и будут ассоциативно распределены по отраслям.",
            "Высокий уровень вовлечения представителей целевой аудитории является четким доказательством простого факта: граница обучения кадров предопределяет высокую востребованность переосмысления внешнеэкономических политик. Прежде всего, экономическая повестка сегодняшнего дня, в своём классическом представлении, допускает внедрение системы массового участия. Значимость этих проблем настолько очевидна, что современная методология разработки предоставляет широкие возможности для вывода текущих активов! Являясь всего лишь частью общей картины, элементы политического процесса освещают чрезвычайно интересные особенности картины в целом, однако конкретные выводы, разумеется, разоблачены.",
            "С учётом сложившейся международной обстановки, сплочённость команды профессионалов позволяет оценить значение как самодостаточных, так и внешне зависимых концептуальных решений. В целом, конечно, семантический разбор внешних противодействий требует анализа поставленных обществом задач! Принимая во внимание показатели успешности, высококачественный прототип будущего проекта предоставляет широкие возможности для существующих финансовых и административных условий."
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
            data.add(News(i,dataImage[i],dataHeadline[i], dataDescription[i], "12:00 20.01.2024"))
        }

        //addData()

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

