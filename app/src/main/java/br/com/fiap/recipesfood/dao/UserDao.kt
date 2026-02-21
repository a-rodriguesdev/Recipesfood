package br.com.fiap.recipesfood.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.recipesfood.model.User

@Dao
interface UserDao {

    @Insert
    fun save(user: User): Long

    @Delete
    fun delete(user: User): Int

    @Update
    fun update(user: User): Int

    @Query("SELECT * FROM tb_user WHERE Id = :id")
    fun getUser(id: Int): User

    @Query("SELECT * FROM tb_user WHERE Id = :id")
    fun getUserById(id: Int): User?

    @Query("SELECT * FROM tb_user WHERE email = :email")
    fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM tb_user WHERE email = :email AND password = :password")
    fun login(email: String, password: String): User?

}