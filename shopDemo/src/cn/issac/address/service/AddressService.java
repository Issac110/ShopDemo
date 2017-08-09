package cn.issac.address.service;

import java.util.List;

import cn.issac.address.dao.AddressDao;
import cn.issac.address.domain.Address;

public class AddressService {
	AddressDao addressDao = new AddressDao();
	
	public List<Address> findByUser(String uid){
		return addressDao.findByUser(uid);
	}

	public Address load(String aid) {
		
		return addressDao.findByAid(aid);
	}
}
