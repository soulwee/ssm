package com.seecen.service.Impl;

import java.util.List;

import com.seecen.dao.impl.BookShopDaoImpl;
import com.seecen.service.BookShopService;
import com.seecen.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Service("cashier")
public class CashierImpl implements Cashier {

	@Autowired
	private BookShopService bookShopService;
	@Autowired
	private BookShopDaoImpl bookShopDao;
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void checkout(int userId, List<String> isbns) {
		for (String isbn : isbns) {
			//调用BookShopService中买东西的方法
			bookShopService.purchase(userId, isbn);
			//purchase(userId, isbn);
		}
	}

	//在这里被调用注解是会失效的，并不会开启新事务，而是原来的事务
	@Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRES_NEW)
	public void purchase(int userId, String isbn) {
		//1.获取要买的图书的价格
		double bookPrice = bookShopDao.getBookPriceByIsbn(isbn);
//		System.out.println(bookPrice);
		//2.更新图书的库存
		bookShopDao.updateBookStock(isbn);
		//3.更新用户的余额
		bookShopDao.updateAccountBalance(userId, bookPrice);
//		double bookPriceByIsbn = bookShopDao.getBookPriceByIsbn(isbn);
//		System.out.println(bookPriceByIsbn);
	}

}
