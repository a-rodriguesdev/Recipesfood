package br.com.fiap.recipesfood.repository

import android.content.Context
import br.com.fiap.recipesfood.dao.RecipeDatabase
import br.com.fiap.recipesfood.model.User


class RoomUserRepository(context: Context): UserRepository {

    private val recipeFoodDatabase = RecipeDatabase.getDatabase(context).userDao()

    override fun saveUser(user: User) {
        recipeFoodDatabase.save(user)
    }

    override fun getUser(): User {
        TODO("Not yet implemented")
    }

    override fun getUser(id: Int): User {
        return recipeFoodDatabase.getUserById(id = 1) ?: User()
    }

    override fun getUserByEmail(email: String): User? {
        return recipeFoodDatabase.getUserByEmail(email)
    }

    override fun login(email: String, password: String): Boolean {
        val user = recipeFoodDatabase.login(email, password)
        return user != null
    }
}