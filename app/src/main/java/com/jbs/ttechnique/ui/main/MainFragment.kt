package com.jbs.ttechnique.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbs.ttechnique.R
import com.jbs.ttechnique.data.model.Id
import com.jbs.ttechnique.databinding.MainFragmentBinding
import com.jbs.ttechnique.ui.userdetails.UserDetailsFragment
import com.jbs.ttechnique.utils.Resource
import com.jbs.ttechnique.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MainFragment : Fragment(), UsersAdapter.UserItemListener {


    private var binding: MainFragmentBinding by autoCleared()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupSwipeToRefresh()
    }

    private fun setupSwipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            binding.swiperefresh.isRefreshing = true
            viewModel.reloadData()
            binding.swiperefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        adapter = UsersAdapter(this)
        binding.usersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR -> {
                    Timber.e(it.message)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedUser(userId: Id) {
        findNavController().navigate(
            R.id.action_mainFragmentFragment_to_userDetailFragment,
            bundleOf(
                UserDetailsFragment.ARG_NAME_ID to userId.nameId,
                UserDetailsFragment.ARG_VALUE_ID to userId.value
            )
        )
    }
}