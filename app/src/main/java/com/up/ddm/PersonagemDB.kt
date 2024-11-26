package com.up.ddm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonagemEntity::class], version = 1, exportSchema = false)
abstract class PersonagemDB : RoomDatabase() {

    abstract fun personagemDao(): PersonagemDao

    companion object {
        @Volatile
        private var INSTANCE: PersonagemDB? = null

        fun getInstance(context: Context): PersonagemDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDB::class.java,
                    "personagem_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        // Método para fechar e redefinir o banco de dados manualmente
        fun resetInstance() {
            INSTANCE?.close()  // Fecha o banco de dados se estiver aberto
            INSTANCE = null     // Redefine a instância
        }
    }
}