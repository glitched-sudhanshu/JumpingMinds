package com.example.tmm.data.data_source.database

import androidx.room.*
import com.example.tmm.domain.model.Character
import com.example.tmm.utils.Constants.CHARACTER_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Query("SELECT * FROM $CHARACTER_TABLE_NAME ORDER BY name")
    fun getAllCharacters(): Flow<List<Character>>

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM $CHARACTER_TABLE_NAME WHERE id = :cId")
    suspend fun getCharacter(cId : Int) : Character?

}