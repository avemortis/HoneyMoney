package com.vtorushin.feature.loan.history.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vtorushin.feature.loan.history.databinding.ItemLoanBinding
import com.vtorushin.shared.loan.domain.entity.Loan
import com.vtorushin.utils.datetime.trimTime

class LoanHistoryAdapter(
    private val loans: List<Loan>,
    private val onClick: (loanId: Int) -> Unit
) : RecyclerView.Adapter<LoanHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanHistoryViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoanHistoryViewHolder, position: Int) {
        holder.bind(loans[position])
        holder.itemView.setOnClickListener { onClick(loans[position].id) }
    }

    override fun getItemCount() = loans.size
}

class LoanHistoryViewHolder(binding: ItemLoanBinding): RecyclerView.ViewHolder(binding.root) {
    private val date = binding.loanDateValue
    private val amount = binding.loanAmountValue
    private val percent = binding.loanProcentValue
    private val status = binding.loanStatus

    fun bind(loan: Loan) {
        date.text = trimTime(loan.date)
        amount.text = loan.amount.toString()
        percent.text = loan.percent.toString()
        status.text = loan.state.name
    }
}