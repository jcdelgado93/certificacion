package cl.practica.certificacion.ui.bookdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.practica.certificacion.data.entities.Book
import cl.practica.certificacion.databinding.FragmentBookDetailsBinding
import cl.practica.certificacion.utils.Resource
import cl.practica.certificacion.utils.autoCleared
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailsFragment : Fragment() {

    private var binding: FragmentBookDetailsBinding by autoCleared()
    private val viewModel: BookDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.book.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindBook(it.data!!)
                    binding.pbCargando.visibility = View.GONE
                    binding.clDetalles.visibility = View.VISIBLE
                    binding.clSinopsis.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.pbCargando.visibility = View.VISIBLE
                    binding.clDetalles.visibility = View.GONE
                    binding.clSinopsis.visibility = View.GONE
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bindBook(book: Book) {
        Glide.with(binding.ivCaratula)
            .load(book.imageLink)
            .into(binding.ivCaratula)
        binding.tvTitulo.text = book.title
        binding.tvAutor.text = "Autor: ${book.author}"
        binding.tvIdioma.text = "Idioma: ${book.language}"
        binding.tvPais.text = "País: ${book.country}"
        binding.tvAnio.text = "Año: ${book.year}"
        binding.tvPaginas.text = "Nro de paginas: ${book.pages}"
        binding.tvPrecio.text = "Precio: CLP ${book.price}"
    }
}