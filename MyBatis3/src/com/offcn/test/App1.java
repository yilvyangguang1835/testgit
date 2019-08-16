package com.offcn.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.offcn.mapper.CarMapper;
import com.offcn.mapper.PersonMapper;
import com.offcn.mapper.StudentMapper;
import com.offcn.pojo.Car;
import com.offcn.pojo.CarExample;
import com.offcn.pojo.PersonExample;
import com.offcn.pojo.CarExample.Criteria;
import com.offcn.pojo.Student;

public class App1 {
	// 声明全局的 SqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;
			
	@Before
    public void setUp() throws Exception {
		// 1、读取配置文件
		String resource = "SqlMapConfig.xml"; 
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 2、根据配置文件创建 SqlSessionFactory
          sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testOne() {
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.close();
	}
	
	@Test
	public void testSelectByExample() {
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 carMapper 的代理对象
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		
		//根据名字进行模糊查询
		CarExample example=new CarExample();
		//example用于生成一个Criteria对象来设置查询条件，如需查询全部，则不要生成criteria
		Criteria criteria = example.createCriteria();
		criteria.andCnameLike("%购%");
		
		 List<Car> list = carMapper.selectByExample(example);
		System.out.println("-----------------------------------------");
		for (Car car : list) {
			System.out.println(car);
		}
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	@Test
	public void testselectByPrimaryKey() {
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 stuMapper 的代理对象
		StudentMapper stuMapper=sqlSession.getMapper(StudentMapper.class);
		//通过主键查
		Student selectByPrimaryKey = stuMapper.selectByPrimaryKey(1);
		
		System.out.println("-----------------------------------------");
		System.out.println(selectByPrimaryKey);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	@Test
	public void testDeleteByExample() {
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 carMapper 的代理对象
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		
		//根据名字进行模糊查询
		CarExample example=new CarExample();
		//example用于生成一个Criteria对象来设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andCnameLike("%夏%");
		//返回受影响行数
		int result=carMapper.deleteByExample(example);
		//增删改操作需要提效事务
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		
		System.out.println("受影响行数"+result);

		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	
	@Test
	public void testDeleteByPrimaryKey() {
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 carMapper 的代理对象
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//返回受影响行数
		int result=carMapper.deleteByPrimaryKey(1);
		//增删改操作需要提效事务
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		
		System.out.println("受影响行数"+result);

		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	@Test
	public void testUpdateByExample(){
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 carMapper 的代理对象
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//修改的对象信息,对象所有字段都要有值
		Car record=new Car();
		record.setCid(1);
		record.setCname("初夏");
		record.setPid(4);
		CarExample example=new CarExample();
		//设置条件
		Criteria criteria = example.createCriteria();
		criteria.andCidBetween(1, 2);
		
		int result= carMapper.updateByExample(record, example);
		//增删改操作需要提效事务
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("受影响行数"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	@Test
	public void testUpdateByExampleSelective(){
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 carMapper 的代理对象
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//修改的对象信息,只有有值的才会被修改
		//注意：当对象不能为空时，一定要赋值，否则就会报错
		Car record=new Car();
		record.setCname("初夏的购物车");
		CarExample example=new CarExample();
		//设置条件
		Criteria criteria = example.createCriteria();
		criteria.andCidBetween(1, 2);
		
		int result= carMapper.updateByExampleSelective(record, example);
		//增删改操作需要提效事务
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("受影响行数"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	@Test
	public void testAddByExample(){
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 carMapper 的代理对象
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//增加的对象信息,增加的对象都必须有值
		//注意：当对象不能为空时，一定要赋值，否则就会报错
		Car record=new Car();
		record.setCname("忆柳的购物车");
		record.setCid(2);
		record.setPid(2);
		
		int result= carMapper.insert(record);
		//增删改操作需要提效事务
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("受影响行数"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	@Test
	public void testAddByExampleSelective(){
		// 创建 sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 通过 SqlSession 构造 carMapper 的代理对象
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//增加的对象信息，没有值的会默认为空
		//注意：当对象不能为空时，一定要赋值，否则就会报错
		Car record=new Car();
		record.setCid(5);
		record.setPid(1);
		
		int result= carMapper.insertSelective(record);
		//增删改操作需要提效事务
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("受影响行数"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
}
