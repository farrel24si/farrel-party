package com.example.farrel_party.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// --- DAFTAR IMPORT ACTIVITY ---
import com.example.farrel_party.AuthActivity
import com.example.farrel_party.Home.pertemuan_10.TenthActivity
import com.example.farrel_party.SixthActivity
import com.example.farrel_party.WebViewActivity
import com.example.farrel_party.Home.pertemuan_9.NinthActivity

// Tambahkan import ini jika BangunRuangActivity berada di luar folder Home
import com.example.farrel_party.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengaktifkan Toolbar (Title dikosongkan karena sudah ada teks sapaan di layout)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = ""
        }

        // 1. Tombol Market Place
        binding.cvMenuMarket.setOnClickListener {
            Toast.makeText(requireContext(), "Fitur Market Place belum tersedia", Toast.LENGTH_SHORT).show()
        }

        // 2. Tombol Kalkulator -> Arahkan ke BangunRuangActivity (Kalkulator Baru)
        binding.cvMenuCalculator.setOnClickListener {
            val intent = Intent(requireContext(), BangunRuangActivity::class.java)
            startActivity(intent)
        }

        // 3. Tombol Fragment P6
        binding.cvMenuFragment.setOnClickListener {
            startActivity(Intent(requireContext(), SixthActivity::class.java))
        }

        // 4. Tombol Peminjaman Fasilitas (Menggunakan NinthActivity dari Modul 9)
        binding.cvMenuPeminjaman.setOnClickListener {
            val intent = Intent(requireContext(), NinthActivity::class.java)
            startActivity(intent)
        }

        // 5. Tombol Lainnya (Sekarang akan langsung membuka halaman ListView/Settings)
        binding.cvMenuLainnya.setOnClickListener {
            val moreFragment = com.example.farrel_party.More.MoreFragment()
            val containerId = (requireView().parent as ViewGroup).id

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(containerId, moreFragment)
                .addToBackStack(null)
                .commit()
        }

        // 6. Tombol Logout
        binding.cvMenuLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah kamu yakin ingin keluar?")

            builder.setPositiveButton("Ya, Keluar") { _, _ ->
                val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(requireContext(), AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            builder.setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()
        }

        // Tombol Pertemuan 10
        binding.cvMenuP10.setOnClickListener {
            // Pastikan kamu sudah import kelas TenthActivity-nya ya!
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        // 7. BANNER BAWAH: Buka WebView FDPR
        binding.cvMenuFdpr.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}