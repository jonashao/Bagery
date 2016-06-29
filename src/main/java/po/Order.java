package po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by 41159 on 2016/6/29.
 */
@Entity
@Table(name = "orders", schema = "", catalog = "bagery")
public class Order {
    private int orderId;
    private int userId;
    private int addressId;
    private BigDecimal total;
    private String instruction;
    private String orderStatus;
    private String courierNumber;
    private String courierCompany;
    private Collection<Comment> comments;
    private Collection<OrderItem> orderItems;
    private User user;

    @Id
    @Column(name = "order_id", nullable = false, insertable = true, updatable = true)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ship_inf_id", nullable = false, insertable = true, updatable = true)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "total", nullable = true, insertable = true, updatable = true, precision = 0)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "instruction", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Basic
    @Column(name = "order_status", nullable = false, insertable = true, updatable = true, length = 9)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "courier_number", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    @Basic
    @Column(name = "courier_company", nullable = true, insertable = true, updatable = true, length = 20)
    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (addressId != order.addressId) return false;
        if (total != null ? !total.equals(order.total) : order.total != null) return false;
        if (instruction != null ? !instruction.equals(order.instruction) : order.instruction != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        if (courierNumber != null ? !courierNumber.equals(order.courierNumber) : order.courierNumber != null)
            return false;
        if (courierCompany != null ? !courierCompany.equals(order.courierCompany) : order.courierCompany != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + addressId;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (instruction != null ? instruction.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (courierNumber != null ? courierNumber.hashCode() : 0);
        result = 31 * result + (courierCompany != null ? courierCompany.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "order")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "order")
    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
