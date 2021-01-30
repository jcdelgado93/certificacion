package cl.practica.certificacion.ui.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.practica.certificacion.R
import cl.practica.certificacion.databinding.FragmentBooksBinding
import cl.practica.certificacion.utils.Resource
import cl.practica.certificacion.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment(), BooksItemListener {

    private var binding: FragmentBooksBinding by autoCleared()
    private val viewModel: BooksViewModel by viewModels()
    private lateinit var adapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = BooksAdapter(this)
        binding.rvListado.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListado.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.books.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pbCargando.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING -> binding.pbCargando.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedBook(bookId: Int) {
        findNavController().navigate(
            R.id.action_booksFragment_to_bookDetailsFragment,
            bundleOf("id" to bookId)
        )
    }
}