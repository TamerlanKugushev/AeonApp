package com.example.aeonapp.presentation.payments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aeonapp.R
import com.example.aeonapp.adapters.PaymentsAdapter
import com.example.aeonapp.data.models.PaymentInfo
import com.example.aeonapp.data.repository.Repository
import com.example.aeonapp.utils.BaseFragment
import com.example.aeonapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_payments.*


class PaymentsFragment : BaseFragment(), PaymentsView {

    companion object {
        fun newInstance(): PaymentsFragment {
            return PaymentsFragment()
        }
    }

    private lateinit var presenter: PaymentsPresenter
    private lateinit var paymentsAdapter: PaymentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payments, container, false)
    }

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is PaymentsPresenter) {
            this.presenter = PaymentsPresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): PaymentsPresenter {
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paymentsToolbar.title = "Payments"
        paymentsToolbar.inflateMenu(R.menu.exit_menu)
        paymentsToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> showLogOutDialog()
            }
            true
        }
        paymentsAdapter = PaymentsAdapter()
        paymentsRecyclerView.setHasFixedSize(true)
        paymentsRecyclerView.adapter = paymentsAdapter
        paymentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.exit_menu, menu)
    }

    private fun navigateToLoginFragment() {
        presenter.navigateToLoginScreen()
    }

    override fun updateState(paymentsScreenStates: PaymentsScreenStates) {
        paymentsRecyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        when (paymentsScreenStates) {
            PaymentsScreenStates.START -> {
            }
            PaymentsScreenStates.LOADING -> {
                paymentsRecyclerView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            PaymentsScreenStates.CONTENT -> {
                paymentsRecyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
            PaymentsScreenStates.ERROR -> {

            }
        }
    }

    override fun updatePayments(payments: List<PaymentInfo>) {
        val listCopy = payments.toMutableList().map { it.copy() }
        paymentsAdapter.submitList(listCopy)
    }

    private fun showLogOutDialog() {
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle(resources.getString(R.string.logout))
        alertDialog.setPositiveButton(resources.getString(R.string.exit)) { _, _ ->
            logout()
            navigateToLoginFragment()
        }
        alertDialog.setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
            dialog.cancel()
        }
        val alert = alertDialog.create()
        alert.show()
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY)
    }

    private fun logout() {
        Repository.logout()
    }
}
