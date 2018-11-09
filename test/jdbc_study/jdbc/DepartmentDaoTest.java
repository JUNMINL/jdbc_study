package jdbc_study.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentImpl;
import jdbc_study.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("setUpBeforeClass()");
		dao = new DepartmentImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MySQLJdbcUtilTest.LOG.debug("tearDownAfterClass()");
		dao = null;
	}

	@Test
	public void test01SelectDepartmentByAll() {
		List<Department> list = dao.selectDepartmentByAll();
		for(Department dept : list) {
			MySQLJdbcUtilTest.LOG.debug(dept);
		}
		Assert.assertNotEquals(0, list.size());
	}

	@Test
	public void test01InsertDepartment() {
		Department newDept = new Department(4, "자바개발부서", 15);
		try {
			int res = dao.insertDepartment(newDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
			if(e.getErrorCode()==1062) {
				JOptionPane.showMessageDialog(null, "해당 부서는 이미 존재합니다.");
			}
		}
	}
	
	@Test
	public void test05DeleteDepartment() {
		Department newDept = new Department(4);
		try {
			int res = dao.testDeleteDepartment(newDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	@Test
	public void test03UpdateDepartment() {
		Department newDept = new Department(4);
		try {
			int res = dao.testUpdateDepartment(newDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void test04selectDepartmentByNo() {
		Department newDept = new Department(4);
		try {
			int res = dao.selectDepartmentByNo(newDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}*/
	
}
