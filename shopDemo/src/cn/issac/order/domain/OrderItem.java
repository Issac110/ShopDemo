package cn.issac.order.domain;

import cn.issac.good.domain.Good;

/**
 * 订单条目类
 * 
 * @author issac
 * 
 */
public class OrderItem {
	private String iid;
	private int count;// 数量
	private double subtotal;// 小计
	private Order order;// 所属订单
	private Good good;// 所要购买的图书

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal="
				+ subtotal + ", order=" + order + ", good=" + good + "]";
	}

	
}
