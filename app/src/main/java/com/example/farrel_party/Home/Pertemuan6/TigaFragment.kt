package com.example.farrel_party

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TigaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Memanggil tampilan dari fragment_tiga.xml
        return inflater.inflate(R.layout.fragment_tiga, container, false)
    }
}