package cn.issac.cart.domain;

import java.math.BigDecimal;

import cn.issac.good.domain.Good;



/**
 * 购物车条目类
 * 
 * @author issac
 * 
 */
public class CartItem {
	private Good good;// 商品
	private int count;// 数量
	
	/**
	 * 小计方法
	 * @return
	 * 处理了二进制运算误差问题
	 */
	public double getSubtotal() {//小计方法，但它没有对应的成员！
		BigDecimal d1 = new BigDecimal(good.getPrice() + "");
		BigDecimal d2 = new BigDecimal(count + "");
		return d1.multiply(d2).doubleValue();
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
