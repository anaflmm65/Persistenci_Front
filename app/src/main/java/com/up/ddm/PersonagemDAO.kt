package com.up.ddm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete


@Dao
interface PersonagemDao {
    @Insert
    suspend fun insert(personagem: PersonagemEntity)

    @Update
    suspend fun update(personagem: PersonagemEntity)

    @Delete
    suspend fun delete(personagem: PersonagemEntity)

    @Query("SELECT * FROM personagem WHERE id = :id")
    suspend fun buscarPorId(id: Int): PersonagemEntity?
}
