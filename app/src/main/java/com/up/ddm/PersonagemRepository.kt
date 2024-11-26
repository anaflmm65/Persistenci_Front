package com.up.ddm

import com.up.ddm.PersonagemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonagemRepository(private val dao: PersonagemDao) {
    suspend fun insert(personagem: PersonagemEntity) {
        withContext(Dispatchers.IO) {
            dao.insert(personagem)
        }
    }

    suspend fun update(personagem: PersonagemEntity) {
        withContext(Dispatchers.IO) {
            dao.update(personagem)
        }
    }

    suspend fun delete(personagem: PersonagemEntity) {
        withContext(Dispatchers.IO) {
            dao.delete(personagem)
        }
    }

//    suspend fun buscarporId(id: Int): PersonagemEntity? {
//        return withContext(Dispatchers.IO) {
//            dao.buscarporId(id)
//        }
//    }
}
