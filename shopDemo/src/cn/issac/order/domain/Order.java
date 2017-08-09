package cn.issac.order.domain;

import java.util.Date;
import java.util.List;

import cn.issac.address.domain.Address;
import cn.issac.user.domain.User;



/**
 * 订单类
 * 
 * @author issac
 * 
 */
public class Order {
	private String oid;
	private Date ordertime;// 下单时间
	private Date paytime;//付款时间
	private Date reviewtime;//评论时间
	private double total;// 合计
	private int orderState;// 订单状态有四种：1未付款 2已付款但未发货 3已发货但未确认收货 4已确认交易成功
	private User owner;// 订单所有者
	private Address address;// 收货地址
	private String comment;//备注
	
	private List<OrderItem> orderItemList;//当前订单下所有条目

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public Date getReviewtime() {
		return reviewtime;
	}

	public void setReviewtime(Date reviewtime) {
		this.reviewtime = reviewtime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", paytime="
				+ paytime + ", reviewtime=" + reviewtime + ", total=" + total
				+ ", orderState=" + orderState + ", owner=" + owner
				+ ", address=" + address + ", comment=" + comment
				+ ", orderItemList=" + orderItemList + "]";
	}

	
	
}
