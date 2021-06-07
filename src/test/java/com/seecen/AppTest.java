package com.seecen;

import com.seecen.dao.BookShopDao;
import com.seecen.service.BookShopService;
import com.seecen.service.Cashier;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    //01.Spring Bean的作用域之间有什么区别
    //创建IOC容器对象
    ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testBookShopDao() {
        //获取BookDao
        BookShopDao bookShopDao = (BookShopDao) ioc.getBean("bookShopDaoImpl");
        double bookPrice = bookShopDao.getBookPriceByIsbn("1001");
        System.out.println(bookPrice);
        //更新图书的库存
        bookShopDao.updateBookStock("1001");
        //更新账户的余额
        bookShopDao.updateAccountBalance(1, bookPrice);
    }

    @Test
    public void testBookShopService() {
        BookShopService bookShopService = (BookShopService) ioc.getBean("bookShopService");
        bookShopService.purchase(1, "1001");
    }

    @Test
    public void testCashier() {
        Cashier cashier = (Cashier) ioc.getBean("cashier");
        //创建List
        List<String> isbns = new ArrayList<>();
        isbns.add("1001");
        isbns.add("1002");
        //去结账
        cashier.checkout(1, isbns);
    }


}
