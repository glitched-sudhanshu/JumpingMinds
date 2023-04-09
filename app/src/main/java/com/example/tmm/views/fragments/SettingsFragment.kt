package com.example.tmm.views.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tmm.databinding.FragmentSettingsBinding
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_CHARACTERS
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_COMICS
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_CREATORS
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_EVENTS
import com.example.tmm.utils.Constants.LIMIT_VALUE_FOR_SERIES
import com.example.tmm.utils.Constants.SHARED_PREF
import com.example.tmm.utils.Constants.SP_CHARACTERS
import com.example.tmm.utils.Constants.SP_COMICS
import com.example.tmm.utils.Constants.SP_CREATORS
import com.example.tmm.utils.Constants.SP_EVENTS
import com.example.tmm.utils.Constants.SP_SERIES
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding : FragmentSettingsBinding? = null
    private lateinit var sharedPref : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        setValuesToLimits()

        setLimitTextWatcher(_binding!!.tilCharacterLimit, _binding!!.etCharacterLimit)
        setLimitTextWatcher(_binding!!.tilComicLimit, _binding!!.etComicLimit)
        setLimitTextWatcher(_binding!!.tilCreatorLimit, _binding!!.etCreatorLimit)
        setLimitTextWatcher(_binding!!.tilEventLimit, _binding!!.etEventLimit)
        setLimitTextWatcher(_binding!!.tilSeriesLimit, _binding!!.etSeriesLimit)

        setSubmitListener(_binding!!.tilCharacterLimit, _binding!!.etCharacterLimit)
        setSubmitListener(_binding!!.tilComicLimit, _binding!!.etComicLimit)
        setSubmitListener(_binding!!.tilCreatorLimit, _binding!!.etCreatorLimit)
        setSubmitListener(_binding!!.tilEventLimit, _binding!!.etEventLimit)
        setSubmitListener(_binding!!.tilSeriesLimit, _binding!!.etSeriesLimit)

        resetToDefaults()
    }

    private fun setValuesToLimits() {
        val limitCharacter = sharedPref.getString(SP_CHARACTERS, "15")
        val limitCreator = sharedPref.getString(SP_CREATORS, "15")
        val limitComic = sharedPref.getString(SP_COMICS, "15")
        val limitEvent = sharedPref.getString(SP_EVENTS, "15")
        val limitSeries = sharedPref.getString(SP_SERIES, "15")
        LIMIT_VALUE_FOR_CHARACTERS = limitCharacter?:"15"
        LIMIT_VALUE_FOR_CREATORS = limitCreator?:"15"
        LIMIT_VALUE_FOR_COMICS = limitComic?:"15"
        LIMIT_VALUE_FOR_EVENTS = limitEvent?:"15"
        LIMIT_VALUE_FOR_SERIES = limitSeries?:"15"
        _binding!!.tilCharacterLimit.helperText = "Current limit : $limitCharacter"
        _binding!!.tilCreatorLimit.helperText = "Current limit : $limitCreator"
        _binding!!.tilComicLimit.helperText = "Current limit : $limitComic"
        _binding!!.tilEventLimit.helperText = "Current limit : $limitEvent"
        _binding!!.tilSeriesLimit.helperText = "Current limit : $limitSeries"
    }

    private fun resetToDefaults() {
        _binding?.btnReset?.setOnClickListener {
            _binding!!.tilCharacterLimit.helperText = "Current limit : 15"
            _binding!!.etCharacterLimit.setText("")
            editor.putString(SP_CHARACTERS, "15")
            LIMIT_VALUE_FOR_CHARACTERS = "15"
            editor.apply()


            _binding!!.tilComicLimit.helperText = "Current limit : 15"
            _binding!!.etComicLimit.setText("")
            LIMIT_VALUE_FOR_COMICS = "15"
            editor.putString(SP_COMICS, "15")
            editor.apply()

            _binding!!.tilCreatorLimit.helperText = "Current limit : 15"
            _binding!!.etCreatorLimit.setText("")
            LIMIT_VALUE_FOR_CREATORS = "15"
            editor.putString(SP_CREATORS, "15")
            editor.apply()

            _binding!!.tilEventLimit.helperText = "Current limit : 15"
            _binding!!.etEventLimit.setText("")
            LIMIT_VALUE_FOR_EVENTS = "15"
            editor.putString(SP_EVENTS, "15")
            editor.apply()

            _binding!!.tilSeriesLimit.helperText = "Current limit : 15"
            _binding!!.etSeriesLimit.setText("")
            LIMIT_VALUE_FOR_SERIES = "15"
            editor.putString(SP_SERIES, "15")
            editor.apply()
        }
    }

    private fun setSubmitListener(tilLimit: TextInputLayout, etLimit: TextInputEditText) {
        etLimit.imeOptions = EditorInfo.IME_ACTION_DONE
        etLimit.setOnEditorActionListener { text, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                changeLimitValue(tilLimit, text.text.toString())
                true
            } else {
                false
            }

        }
    }

    private fun changeLimitValue(tilLimit: TextInputLayout, text : String) {
        when(tilLimit.id){
            _binding!!.tilCharacterLimit.id -> {
                tilLimit.helperText = "Current limit : $text"
                LIMIT_VALUE_FOR_CHARACTERS = text
                editor.putString(SP_CHARACTERS, text)
                editor.apply()
            }
            _binding!!.tilComicLimit.id -> {
                tilLimit.helperText = "Current limit : $text"
                LIMIT_VALUE_FOR_COMICS = text
                editor.putString(SP_COMICS, text)
                editor.apply()
            }
            _binding!!.tilCreatorLimit.id -> {
                tilLimit.helperText = "Current limit : $text"
                LIMIT_VALUE_FOR_CREATORS = text
                editor.putString(SP_CREATORS, text)
                editor.apply()
            }
            _binding!!.tilEventLimit.id -> {
                tilLimit.helperText = "Current limit : $text"
                LIMIT_VALUE_FOR_EVENTS = text
                editor.putString(SP_EVENTS, text)
                editor.apply()
            }
            _binding!!.tilSeriesLimit.id -> {
                tilLimit.helperText = "Current limit : $text"
                LIMIT_VALUE_FOR_SERIES = text
                editor.putString(SP_SERIES, text)
                editor.apply()
            }
        }
    }


    private fun setLimitTextWatcher(tilLimit: TextInputLayout, etLimit: TextInputEditText) {
        etLimit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    val limit = s.toString().toInt()
                    if (limit > 100) {
                        tilLimit.error = "Limit should be less than or equal to 100"
                    } else if (limit < 1) {
                        tilLimit.error = "Limit should be more than or equal to 1"
                    } else {
                        tilLimit.error = null
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}