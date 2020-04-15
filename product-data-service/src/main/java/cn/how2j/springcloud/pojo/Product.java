package cn.how2j.springcloud.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

	private int id;
	private String name;
	private int price;

}
