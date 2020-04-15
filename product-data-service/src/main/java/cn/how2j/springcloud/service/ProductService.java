package cn.how2j.springcloud.service;

import cn.how2j.springcloud.pojo.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

	@Value("${server.port}")
	private String port;

	@Resource
	private RedisTemplate redisTemplate;

	public List<Product> listProducts(){
    	List<Product> ps = new ArrayList<>();
    	ps.add(new Product(1,"product a from port: " + port, 50));
    	ps.add(new Product(2,"product b from port: " + port, 100));
    	ps.add(new Product(3,"product c from port: " + port, 150));
    	return ps;
	}

    public Object listRedisProducts() {
		List<Object> values = new ArrayList<>();

		// 字符串
		redisTemplate.opsForValue().set("key1", "value1");
		values.add(redisTemplate.opsForValue().get("key1"));
		values.add(redisTemplate.opsForValue().get("name"));

		// list
		ListOperations listOperations = redisTemplate.opsForList();
		if(redisTemplate.hasKey("list1")) {
			List list1 = listOperations.range("list1", 0, -1);
			System.out.println(list1);
			redisTemplate.delete("list1");
			System.out.println(listOperations.range("list1", 0, -1));
		}
		listOperations.rightPush("list1", "1");
		listOperations.rightPush("list1", "2");
		listOperations.rightPush("list1", "3");
		listOperations.leftPop("list1");
		values.add(listOperations.range("list1", 0, 1));

		// set
		SetOperations setOperations = redisTemplate.opsForSet();
		if(redisTemplate.hasKey("set1")) {
			Set set1 = setOperations.members("set1");
			System.out.println(set1);
			redisTemplate.delete("set1");
			System.out.println(setOperations.members("set1"));
		}
		setOperations.add("set1", "s1", "s2", "s3");
		values.add(setOperations.members("set1"));

		// hash
		HashOperations hashOperations = redisTemplate.opsForHash();
		if(redisTemplate.hasKey("hash1")) {
			Set set1 = hashOperations.keys("hash1");
			System.out.println(set1);
			redisTemplate.delete("hash1");
			System.out.println(hashOperations.keys("hash1"));
		}
		hashOperations.put("hash1", "k1", "v1");
		hashOperations.put("hash1", "k2", "v2");
		hashOperations.put("hash1", "k3", "v3");
		values.add(hashOperations.entries("hash1"));
		return values;
	}
}
