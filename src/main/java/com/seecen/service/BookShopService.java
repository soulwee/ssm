package com.seecen.service;

public interface BookShopService {

	/**
	 * 买东西
	 * 
	 * @param userId
	 * @param isbn
	 */
	void purchase(int userId, String isbn);
}
