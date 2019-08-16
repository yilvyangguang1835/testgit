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
	// ����ȫ�ֵ� SqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;
			
	@Before
    public void setUp() throws Exception {
		// 1����ȡ�����ļ�
		String resource = "SqlMapConfig.xml"; 
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 2�����������ļ����� SqlSessionFactory
          sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testOne() {
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.close();
	}
	
	@Test
	public void testSelectByExample() {
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� carMapper �Ĵ������
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		
		//�������ֽ���ģ����ѯ
		CarExample example=new CarExample();
		//example��������һ��Criteria���������ò�ѯ�����������ѯȫ������Ҫ����criteria
		Criteria criteria = example.createCriteria();
		criteria.andCnameLike("%��%");
		
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
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� stuMapper �Ĵ������
		StudentMapper stuMapper=sqlSession.getMapper(StudentMapper.class);
		//ͨ��������
		Student selectByPrimaryKey = stuMapper.selectByPrimaryKey(1);
		
		System.out.println("-----------------------------------------");
		System.out.println(selectByPrimaryKey);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	@Test
	public void testDeleteByExample() {
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� carMapper �Ĵ������
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		
		//�������ֽ���ģ����ѯ
		CarExample example=new CarExample();
		//example��������һ��Criteria���������ò�ѯ����
		Criteria criteria = example.createCriteria();
		criteria.andCnameLike("%��%");
		//������Ӱ������
		int result=carMapper.deleteByExample(example);
		//��ɾ�Ĳ�����Ҫ��Ч����
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		
		System.out.println("��Ӱ������"+result);

		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	
	@Test
	public void testDeleteByPrimaryKey() {
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� carMapper �Ĵ������
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//������Ӱ������
		int result=carMapper.deleteByPrimaryKey(1);
		//��ɾ�Ĳ�����Ҫ��Ч����
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		
		System.out.println("��Ӱ������"+result);

		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	@Test
	public void testUpdateByExample(){
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� carMapper �Ĵ������
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//�޸ĵĶ�����Ϣ,���������ֶζ�Ҫ��ֵ
		Car record=new Car();
		record.setCid(1);
		record.setCname("����");
		record.setPid(4);
		CarExample example=new CarExample();
		//��������
		Criteria criteria = example.createCriteria();
		criteria.andCidBetween(1, 2);
		
		int result= carMapper.updateByExample(record, example);
		//��ɾ�Ĳ�����Ҫ��Ч����
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("��Ӱ������"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	@Test
	public void testUpdateByExampleSelective(){
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� carMapper �Ĵ������
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//�޸ĵĶ�����Ϣ,ֻ����ֵ�ĲŻᱻ�޸�
		//ע�⣺��������Ϊ��ʱ��һ��Ҫ��ֵ������ͻᱨ��
		Car record=new Car();
		record.setCname("���ĵĹ��ﳵ");
		CarExample example=new CarExample();
		//��������
		Criteria criteria = example.createCriteria();
		criteria.andCidBetween(1, 2);
		
		int result= carMapper.updateByExampleSelective(record, example);
		//��ɾ�Ĳ�����Ҫ��Ч����
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("��Ӱ������"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	@Test
	public void testAddByExample(){
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� carMapper �Ĵ������
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//���ӵĶ�����Ϣ,���ӵĶ��󶼱�����ֵ
		//ע�⣺��������Ϊ��ʱ��һ��Ҫ��ֵ������ͻᱨ��
		Car record=new Car();
		record.setCname("�����Ĺ��ﳵ");
		record.setCid(2);
		record.setPid(2);
		
		int result= carMapper.insert(record);
		//��ɾ�Ĳ�����Ҫ��Ч����
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("��Ӱ������"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
	
	@Test
	public void testAddByExampleSelective(){
		// ���� sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// ͨ�� SqlSession ���� carMapper �Ĵ������
		CarMapper carMapper=sqlSession.getMapper(CarMapper.class);
		//���ӵĶ�����Ϣ��û��ֵ�Ļ�Ĭ��Ϊ��
		//ע�⣺��������Ϊ��ʱ��һ��Ҫ��ֵ������ͻᱨ��
		Car record=new Car();
		record.setCid(5);
		record.setPid(1);
		
		int result= carMapper.insertSelective(record);
		//��ɾ�Ĳ�����Ҫ��Ч����
		sqlSession.commit();
		System.out.println("-----------------------------------------");
		System.out.println("��Ӱ������"+result);
		System.out.println("-----------------------------------------");
		sqlSession.close();
	}
}
