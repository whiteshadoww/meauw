package me.snowshadow.meauw.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import me.snowshadow.meauw.R
import me.snowshadow.meauw.utils.BaseFragment

class ProfileFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load("https://cdn2.thecatapi.com/images/47u.jpg").into(profile_image)
        profile_name.text = "Mr. Meauw"
        profile_bio.text = "I like to play and sleep a lot."
    }
}
