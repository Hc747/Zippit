package com.mercury.zippit.service.user;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 30/10/17
 */
public interface UserRepository {

	User get(Predicate<User> condition);

	void add(User user);

	User remove(Predicate<User> condition);

	Collection<User> getAll();

}
