package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

interface JavaFeature {
	public void say(String str);

}

public class Java8Practice {
	public static void main(String[] args) {

		JavaFeature s = (String str) -> {
			System.out.println("Nothing to print " + str);
			return;
		};
//		s.say("Raghav");
//		s.say("Raghav");
//		s.say("Raghav");
//		forEachmethod();

		List<Product> productsList = new ArrayList<Product>();
		// Adding Products
		productsList.add(new Product(1, "HP Laptop", 25000f));
		productsList.add(new Product(2, "Dell Laptop", 30000f));
		productsList.add(new Product(3, "Lenevo Laptop", 28000f));
		productsList.add(new Product(4, "Sony Laptop", 28000f));
		productsList.add(new Product(5, "Apple Laptop", 90000f));
		System.out.println("Test GIT");
		List m = productsList.stream().map(k->k.price).collect(Collectors.toList());
		System.out.println(m);
		Set<Float> m1 = productsList.stream().map(k->k.price).collect(Collectors.toSet());
		System.out.println(m1);
	}

	public static void forEachmethod() {
		List l = new ArrayList<>();
		l.add("Raghav");
		l.add("Raghav");
		l.add("Raghav");
		l.add("Raghav");
		l.forEach(s -> System.out.println(s));
		l.forEach(System.out::println);
	}

}

class Product {
	int id;
	String name;
	float price;

	public Product(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
}
