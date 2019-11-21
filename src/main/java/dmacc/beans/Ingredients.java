package dmacc.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredients")
public class Ingredients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ingredient_id")//don't know why but w/o this it just creates some weird characters in the middle the column name
	private long id; //changed this to long instead of int
	private String name;
	private int calories;
	private double cost;
	
	public Ingredients() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ingredients(String name, int calories, double cost) {
		super();
		this.name = name;
		this.calories = calories;
		this.cost = cost;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Ingredients [name=" + name + ", calories=" + calories + ", cost=" + cost + "]";
	}
	
	
	
}
