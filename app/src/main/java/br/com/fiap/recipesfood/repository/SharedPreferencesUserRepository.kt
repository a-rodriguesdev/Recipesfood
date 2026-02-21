package br.com.fiap.recipesfood.repository

import android.content.Context
import br.com.fiap.recipesfood.model.User

class SharedPreferencesUserRepository(context: Context) : UserRepository {

    private val userPrefs = context
        .getSharedPreferences(
            "userPreferences",
            Context.MODE_PRIVATE
        )

    override fun saveUser(user: User) {
        userPrefs.edit()
            .putInt("id", user.id)
            .putString("name", user.name)
            .putString("email", user.email)
            .putString("password", user.password)
            .apply()
    }

    override fun getUser(): User {
        val id = userPrefs.getInt("id", 0)
        val namePrefs = userPrefs.getString("name", "")
        val emailPrefs = userPrefs.getString("email", "")
        val passwordPrefs = userPrefs.getString("password", "")
        return User(
            id = id,
            name = namePrefs!!,
            email = emailPrefs!!,
            password = passwordPrefs!!
        )
    }

    override fun getUser(id: Int): User {
        TODO("Not yet implemented")
    }

    override fun login(
        email: String,
        password: String
    ): Boolean {
        val namePrefs = userPrefs.getString("name", "")
        val emailPrefs = userPrefs.getString("email", "")
        val passwordPrefs = userPrefs.getString("password", "")
        return email == emailPrefs && password == passwordPrefs
    }

    override fun getUserByEmail(email: String): User {
        TODO("Not yet implemented")
    }
}