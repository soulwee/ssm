package com.seecen.service.Impl;

import com.seecen.dao.BookShopDao;
import com.seecen.dao.impl.BookShopDaoImpl;
import com.seecen.service.BookShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Transactional注解
 * 	该注解可以添加到类上，也可以添加到方法上
 * 	如果添加到类上，那么类中所有的方法都添加上了事务
 * 	如果添加到方法上，只有添加了该注解的方法才添加了事务
 */
//@Transactional
@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
	/*//	private BookShopDao bookShopDao;*/
	@Autowired
	private BookShopDaoImpl bookShopDao;
	//这里原来配了mybatis，又配了jdbcTemplate，所以bookShopDao无法注入，先直接写实现类测试了。


	//1.请简单介绍Spring支持的常用数据库事务传播属性和事务隔离级别？

	/**
	 * 事务的属性：
	 * 	1.★propagation：用来设置事务的传播行为
	 * 		事务的传播行为：一个方法运行在了一个开启了事务的方法中时，当前方法是使用原来的事务还是开启一个新的事务
	 * 		-Propagation.REQUIRED：默认值，使用原来的事务
	 * 		-Propagation.REQUIRES_NEW：将原来的事务挂起，开启一个新的事务
	 * 	2.★isolation：用来设置事务的隔离级别
	 * 		-Isolation.REPEATABLE_READ：可重复读，MySQL默认的隔离级别
	 * 		-Isolation.READ_COMMITTED：读已提交，Oracle默认的隔离级别，开发时通常使用的隔离级别
	 * 	https://blog.csdn.net/Trigl/article/details/50968079
	 */
	@Transactional(rollbackFor = Exception.class,propagation=Propagation.REQUIRES_NEW)
	@Override
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

	/**
	 * 阿里巴巴规范：需要在Transactional注解指定rollbackFor或者在方法中显示的rollback
	 * error是一定会回滚的
	 * 这里Exception是异常，他又分为运行时异常RuntimeException和非运行时异常
	 * 可查的异常（checked exceptions）:Exception下除了RuntimeException外的异常
	 * 不可查的异常（unchecked exceptions）:RuntimeException及其子类和错误（Error）
	 * 1 让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class)
	 * 2 让unchecked例外不回滚： @Transactional(notRollbackFor=RunTimeException.class)
	 * 3 不需要事务管理的(只查询的)方法：@Transactional(propagation=Propagation.NOT_SUPPORTED)
	 * 注意： 如果异常被try｛｝catch｛｝了，事务就不回滚了，如果想让事务回滚必须再往外抛try｛｝catch｛throw Exception｝。
	 */

	/**
	 * 注意：
	 * Spring团队的建议是你在具体的类（或类的方法）上使用 @Transactional 注解，而不要使用在类所要实现的任何接口上。你当然可以在接口上使用 @Transactional 注解，但是这将只能当你设置了基于接口的代理时它才生效。因为注解是不能继承的，这就意味着如果你正在使用基于类的代理时，那么事务的设置将不能被基于类的代理所识别，而且对象也将不会被事务代理所包装（将被确认为严重的）。因此，请接受Spring团队的建议并且在具体的类上使用 @Transactional 注解。
	 * @Transactional 注解标识的方法，处理过程尽量的简单。尤其是带锁的事务方法，能不放在事务里面的最好不要放在事务里面。可以将常规的数据库查询操作放在事务前面进行，而事务内进行增、删、改、加锁查询等操作。
	 */
}
