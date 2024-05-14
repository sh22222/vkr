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
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.R
import com.example.vkr.mainScreen.showCustomToast

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var profile= arguments?.getSerializable("profile") as Profile

        var etLogin = view.findViewById<EditText>(R.id.etLogin)
        var etEmail = view.findViewById<EditText>(R.id.etEmail)
        var etNewPass = view.findViewById<EditText>(R.id.etNewPasswordP)
        var etRepPass = view.findViewById<EditText>(R.id.etRepeatPasswordP)
        etLogin.setText(profile.getLogin())
        etEmail.setText(profile.getEmail())

        var button = view.findViewById<Button>(R.id.btApplyChanges)
        button.setOnClickListener {
            val db = MainDataBase.getDataBase(requireContext())
            val dao = db.getDao()
            var login = etLogin.text.toString()
            var email = etEmail.text.toString()
            var newPass =etNewPass.text.toString()
            var repPass = etRepPass.text.toString()
            if (newPass != "" && newPass == repPass){
                dao.updateProfileWithPass(profile.getLogin(), login, email, newPass)
                profile.setLogin(login)
                profile.setEmail(email)

                showToast("Успешно")
            }
            else if (login!= profile.getLogin() || email != profile.getEmail() && newPass == "" && repPass == ""){
                dao.updateProfile(profile.getLogin(), login, email)
                profile.setLogin(login)
                profile.setEmail(email)
                showToast("Успешно")
            }
            else{
                showToast("Ошибка записи")
            }
        }
    }
}