package com.mercury.zippit.service.user

import java.util.function.Predicate

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
interface UserRepository {

    val all: Collection<User>

    fun get(condition: Predicate<User>): User?

    fun add(user: User)

    fun remove(condition: Predicate<User>): User

}
