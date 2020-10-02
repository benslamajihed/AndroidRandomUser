package com.jbs.ttechnique.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.jbs.ttechnique.data.model.User
import com.jbs.ttechnique.databinding.UserDetailFragmentBinding
import com.jbs.ttechnique.utils.Resource
import com.jbs.ttechnique.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_user.*

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    companion object {
        const val ARG_NAME_ID = "namedId"
        const val ARG_VALUE_ID = "valueId"
    }

    private var binding: UserDetailFragmentBinding by autoCleared()
    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val namedId = it.getString(ARG_NAME_ID)
            val valueId = it.getString(ARG_VALUE_ID)

            if (namedId != null && valueId != null)
                viewModel.start(namedId, valueId)
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            binding.progressBarUserDetails.visibility = View.VISIBLE
            binding.userCl.visibility = View.GONE
            bindUser(user)
            binding.progressBarUserDetails.visibility = View.GONE
            binding.userCl.visibility = View.VISIBLE
        })
    }

    private fun bindUser(user: User) {
        Glide.with(binding.root)
            .load(user.picture.medium)
            .transform(CircleCrop())
            .into(binding.image)

        val name = "${user.nameUser.first} ${user.nameUser.last}"
        binding.name.text = name

        binding.emailContent.text = user.email
        binding.genderContent.text = user.gender
        binding.locationContent.text = user.location.city
        binding.phoneContent.text = user.phone
        binding.loginContent.text = user.login.username
        binding.dobContent.text = user.dob.dateDob
        binding.registeredContent.text = user.registered.dateRegistered
        binding.cellContent.text = user.cell
        binding.natContent.text = user.nat


    }

}