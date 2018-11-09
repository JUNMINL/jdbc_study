package jdbc_study.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.jdbc.MySQLJdbcUtil;

public class DepartmentImpl implements DepartmentDao {
	Logger LOG = LogManager.getLogger();

	@Override
	public List<Department> selectDepartmentByAll() {
		List<Department> list = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department";
		try (Connection conn = MySQLJdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			LOG.debug(pstmt);
			while (rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

}
