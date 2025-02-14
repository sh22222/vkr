package com.example.vkr.mainScreen.Profile

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.vkr.R
import com.example.vkr.mainScreen.md5
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    interface DataTransfer{
        fun onDataTransfer(p: Profile)
    }
    private lateinit var dataTransferListener: DataTransfer
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataTransferListener = context as DataTransfer
    }
    fun showToast(text:String){
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.toast, activity?.findViewById(R.id.toastLayout))
        val tv = layout.findViewById<TextView>(R.id.tvToast)
        tv.setText(text)
        val toast = Toast.makeText(requireContext(),text, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0,40)
        toast.view = layout
        toast.show()
    }
    val db = FirebaseFirestore.getInstance()
    lateinit var profile: Profile
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profile= arguments?.getSerializable("profile") as Profile

        var etLogin = view.findViewById<EditText>(R.id.etLogin)
        var etEmail = view.findViewById<EditText>(R.id.etEmail)
        var etNewPass = view.findViewById<EditText>(R.id.etNewPasswordP)
        var etRepPass = view.findViewById<EditText>(R.id.etRepeatPasswordP)
        etLogin.setText(profile.getLogin())
        etEmail.setText(profile.getEmail())

        var button = view.findViewById<Button>(R.id.btApplyChanges)
        button.setOnClickListener {
            var login = etLogin.text.toString()
            var email = etEmail.text.toString()
            var newPass =etNewPass.text.toString()
            var repPass = etRepPass.text.toString()
            if (newPass != "" && repPass!=""){
                newPass =etNewPass.text.toString().md5()
                repPass = etRepPass.text.toString().md5()
            }
            if(login.compareTo(profile.getLogin())!=0 &&
                login != "" &&
                email!= ""){
                db.collection("profile").whereEqualTo("login", login)
                    .get().addOnSuccessListener { documents->
                        if (documents.isEmpty) {
                            if (email.compareTo(profile.getEmail()) != 0) {
                                db.collection("profile").whereEqualTo("email", email)
                                    .get().addOnSuccessListener { d ->
                                        if (d == null) {
                                            if (newPass != "" && newPass == repPass) {
                                                AddWithPass(login, email, newPass)
                                            } else if (newPass == "" && repPass == "") {
                                                AddWithoutPass(login, email)
                                            }
                                        } else showToast("Почта уже есть")
                                    }
                            } else if (email.compareTo(profile.getEmail()) == 0) {
                                if (newPass != "" && newPass == repPass) {
                                    AddWithPass(login, email, newPass)
                                } else if (newPass == "" && repPass == "") {
                                    AddWithoutPass(login, email)
                                }
                            }
                        }
                        else {
                            showToast("Логин уже есть")
                        }
                    }
            }
            else if (login.compareTo(profile.getLogin())==0 && email!= ""){
                if (email.compareTo(profile.getEmail()) != 0) {
                    db.collection("profile").whereEqualTo("email", email)
                        .get().addOnSuccessListener { d ->
                            if (d == null) {
                                if (newPass != "" && newPass == repPass) {
                                    AddWithPass(login, email, newPass)
                                } else if (newPass == "" && repPass == "") {
                                    AddWithoutPass(login, email)
                                }
                            } else showToast("Почта уже есть")
                        }
                } else if (email.compareTo(profile.getEmail()) == 0) {
                    if (newPass != "" && newPass == repPass) {
                        AddWithPass(login, email, newPass)
                    } else if (newPass == "" && repPass == "") {
                        AddWithoutPass(login, email)
                    }
                }
            }


//            db.collection("profile").whereEqualTo("login", login)
//                .get().addOnSuccessListener { documents->
//                    if (documents == null){
//                            if (login != "" &&
//                                email!= "" &&
//                                newPass != "" &&
//                                newPass == repPass){
//                                db.collection("profile").document(profile.getId()).update("login",login)
//                                db.collection("profile").document(profile.getId()).update("email",email)
//                                db.collection("profile").document(profile.getId()).update("password",newPass)
//
//                                profile.setLogin(login)
//                                profile.setEmail(email)
//
//                                showToast("Успешно")
//                            }
//                            else if (login != "" &&
//                                email!= "" &&
//                                newPass == ""&&
//                                repPass == ""){
//                                db.collection("profile").document(profile.getId()).update("login",login)
//                                db.collection("profile").document(profile.getId()).update("email",email)
//                                profile.setLogin(login)
//                                profile.setEmail(email)
//                                showToast("Успешно")
//                            }
//
//                            else{
//                                showToast("Ошибка записи")
//                            }
//
//
//                    }
//                    else{
//                        showToast("Ошибка записи")
//                    }
//
//            }

        }
    }
    fun AddWithPass(login:String, email:String, newPass:String){
        db.collection("profile").document(profile.getId()).update("login",login)
        db.collection("profile").document(profile.getId()).update("email",email)
        db.collection("profile").document(profile.getId()).update("password",newPass)
        profile.setLogin(login)
        profile.setEmail(email)
        showToast("Успешно")
        dataTransferListener.onDataTransfer(profile)
    }
    fun AddWithoutPass(login:String, email:String){
        db.collection("profile").document(profile.getId()).update("login",login)
        db.collection("profile").document(profile.getId()).update("email",email)
        profile.setLogin(login)
        profile.setEmail(email)
        showToast("Успешно")
        dataTransferListener.onDataTransfer(profile)
    }
}

