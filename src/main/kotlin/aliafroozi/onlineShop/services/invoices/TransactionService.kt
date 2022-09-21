package aliafroozi.onlineShop.services.Transactions

import aliafroozi.onlineShop.models.invoice.Transaction
import aliafroozi.onlineShop.repositories.invoices.TransactionsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TransactionService {

    @Autowired
    lateinit var repository: TransactionsRepo

     fun insert(Transaction: Transaction): Transaction {
        return repository.save(Transaction)
    }

     fun update(Transaction: Transaction): Transaction? {
        val data = getById(Transaction.id)
        if (data == null)
            return null
        else{
            data.refId = Transaction. refId
            data.status = Transaction.status
            return repository.save(data)
        }
    }

    fun getById(TransactionId: Long): Transaction? {
        val data = repository.findById(TransactionId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }


    fun getAllByInvoiceId(invoiceId : Long): List<Transaction> {
        return repository.findByInvoiceId(invoiceId)
    }



}