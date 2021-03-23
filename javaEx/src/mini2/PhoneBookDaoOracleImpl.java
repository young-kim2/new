package mini2;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class PhoneBookDaoOracleImpl implements PhoneBookDao {
    private Connection getConnection() throws SQLException{
    	Connection conn=null;
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		String dbur1="jdbc:oracle:thin:@localhost:1521:xe";
    		conn=DriverManager.getConnection(dbur1,"C##KY","1234");
    	}catch(ClassNotFoundException e) {
    		System.err.println("드라이버 로드 실패!");
    	}
    	return conn;
    }
	@Override
	public List<PhoneBookVo> getList() {
		List<PhoneBookVo>list=new ArrayList<>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			
			String sql="SELECT id, name, hp, tel FROM phone_book";
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id=rs.getLong("id");
				String name=rs.getString("name");
				String hp=rs.getString("hp");
				String tel=rs.getString("tel");
				
				PhoneBookVo vo=new PhoneBookVo(id,name,hp,tel);
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {
		}
		
	}
		return list;
	}
   	@Override
	public List<PhoneBookVo> search(String keyword) {
		List<PhoneBookVo>list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			String sql="SELECT id, name, hp, tel FROM phone_book WHERE name LIKE?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneBookVo vo=new PhoneBookVo();
				vo.setId(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setHp(rs.getString(3));
				vo.setTel(rs.getString(3));
				
				list.add(vo);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public PhoneBookVo get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(PhoneBookVo vo) {
		Connection conn=null;
		String sql="INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL,?,?,?)";
		int insertedCount=0;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			insertedCount=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		
		return insertedCount==1;
	}


	@Override
	public boolean delete(Long id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int deletedCount=0;
		
		try {
			conn=getConnection();
			String sql="DELETE FROM phone_book WHERE id=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			deletedCount=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		return deletedCount==1;
	}

}
