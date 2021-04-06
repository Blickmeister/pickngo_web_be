package cz.uhk.fim.bs.pickngo_web_be.BaguetteOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.uhk.fim.bs.pickngo_web_be.Item.Item;
import cz.uhk.fim.bs.pickngo_web_be.Customer.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class BaguetteOrder {

    @Id
    @SequenceGenerator(name="baguette_order_sequence", sequenceName = "baguette_order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "baguette_order_sequence")
    private Long id;
    @ManyToOne
    private Customer customer;
    private double price;
    private Date date;
    private int state;
    @JsonIgnore
    @OneToMany(mappedBy = "baguetteOrder")
    private List<Item> items;

    public BaguetteOrder(Customer customer, double price, Date date, int state, List<Item> items) {
        this.customer = customer;
        this.price = price;
        this.date = date;
        this.state = state;
        this.items = items;
    }

    public BaguetteOrder(Customer customer, double price, Date date, int state) {
        this.customer = customer;
        this.price = price;
        this.date = date;
        this.state = state;
    }

    public BaguetteOrder(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BaguetteOrder{" +
                "id=" + id +
                ", customer=" + customer +
                ", price=" + price +
                ", date=" + date +
                ", state=" + state +
                ", items=" + items +
                '}';
    }
}
