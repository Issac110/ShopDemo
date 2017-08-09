package cn.issac.good.domain;

import cn.issac.category.domain.Category;

/**
 * @author Issac-Su
 *
 */
public class Good {
	private String gid;
	private String gname;
	private double price;
	private String desc;
	private String image;
	private Category category;
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Good [gid=" + gid + ", gname=" + gname + ", price=" + price
				+ ", desc=" + desc + ", image=" + image + ", category="
				+ category.getCid()+","+category.getCname() + "]";
	}
	
	
	
	
	
}
