package br.com.fiap.recipesfood.repository

import android.content.Context
import br.com.fiap.recipesfood.dao.RecipeDatabase
import br.com.fiap.recipesfood.model.User


class RoomUserRepository(context: Context) : UserRepository {

    private val userDao = RecipeDatabase.getDatabase(context).userDao()

    override fun saveUser(user: User) {
        userDao.save(user)
    }

    override fun getUser(): User {

        // retorna o usuário 1 se existir, senão um User vazio
        return userDao.getUserById(1) ?: User()
    }

    override fun getUser(id: Int): User {
        return userDao.getUserById(id) ?: User()
    }

    override fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    override fun login(email: String, password: String): Boolean {
        return userDao.login(email, password) != null
    }

    override fun update(user: User): Int {
        return userDao.update(user)
    }

    override fun delete(user: User): Int {
        return userDao.delete(user)
    }
}