package cn.issac.address.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.issac.address.domain.Address;
import cn.itcast.jdbc.TxQueryRunner;

public class AddressDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/**
	 *查询用户收货地址
	 * @param uid
	 * @return
	 */
	public List<Address> findByUser(String uid){
		
		try {
			String sql = "select * from address where uid = ?";
			return qr.query(sql, new BeanListHandler<Address>(Address.class),uid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Address findByAid(String aid) {
		
		try {
			String sql  = "select * from address where aid = ?";
			return qr.query(sql, new BeanHandler<Address>(Address.class),aid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Address address) {
		try {
			String sql  = "insert into address values(?,?,?,?,?,?)";
			qr.update(sql, address.getAid(),address.getName(),address.getPhone(),address.isDefault(),
					address.getUser().getUid(),address.getAddressName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void update(Address address) {
		try {
			String sql  = "update address set name = ?,phone = ? ,isDefault= ?,uid = ?,addressName = ? where aid = ? ";
			qr.update(sql, address.getName(),address.getPhone(),address.isDefault(),
					address.getUser().getUid(),address.getAddressName(),address.getAid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public void delete(String aid) {
		try {
			String sql  = "delete from address where aid = ?";
			qr.update(sql,aid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
