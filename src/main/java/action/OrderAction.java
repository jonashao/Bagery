package action;

import com.opensymphony.xwork2.ActionContext;
import constant.Config;
import org.apache.struts2.dispatcher.DefaultActionSupport;
import org.hibernate.HibernateException;
import po.*;
import service.AddressService;
import service.CartService;
import service.OrderService;
import service.UserService;
import constant.OrderStatus;

import java.util.*;

import static constant.Key.RESULT;

/**
 * Created by 41159 on 2016/6/29.
 */
public class OrderAction extends DefaultActionSupport {

    private UserService userService;
    private OrderService orderService;
    private AddressService addressService;
    private CartService cartService;
    private Order order;
    private User user;
    private Address defaultAddress;

    private double totalPrice;

    private List<Integer> itemIdList = new ArrayList<>();
    private List<CartItem> cartItemList = new ArrayList<>();
    private Collection<Address> addressList = new ArrayList<>();
    private Collection<Order> orderList = new ArrayList<>();
    private List<Integer> cartItemIdList = new ArrayList<>();
    private List<OrderItem> orderItemList = new ArrayList<>();

    private int userId;
    private int shipInfId;
    private double total;
    private int orderId;
    private String logistics;
    private String logisticsNum;
    private String logisticsCompany;
    private String status;
    private String instruction;

    private Map<String,Object> data=new HashMap<>();

    //����
    public String balance() throws Exception{
        try {
            user = userService.getCurrentUser();
            addressList = user.getAddresses();
            defaultAddress = addressService.get(user.getDefaultAddressId());

            if (cartItemIdList == null || cartItemIdList.size() == 0) {
                return INPUT;
            }

            for (Integer itemId : cartItemIdList) {
                CartItem cartItem = cartService.getCartItem(itemId);
                cartItemList.add(cartItem);
            }

            for (CartItem item : cartItemList) {
                totalPrice += item.getSubtotal();
            }

            return SUCCESS;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    //��������
    public String addOrder() throws Exception{
        if (cartItemIdList == null || cartItemIdList.size() == 0) {
            return INPUT;
        }
        try {
            user = userService.getCurrentUser();
            userId = user.getUserId();
            order = new Order();
            order.setUserId(userId);
            order.setAddressId(user.getDefaultAddressId());
            order.setInstruction(instruction);
//            order.setTotal(total);
//            order.setOrderStatus(OrderStatus.UNPAID);

            orderService.addOrder(order, cartItemIdList);

            return SUCCESS;
        }catch (HibernateException | NullPointerException e){
            e.printStackTrace();
        }
        return ERROR;

    }

    //�鿴����
    public String queryOrder() throws Exception{
        try {
            user = userService.getCurrentUser();
            orderList = user.getOrders();

            if(user.getUserGroup().equals("order_admin")){
                return SUCCESS;
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    //ɾ������
    public String deleteOrder() throws Exception{
        try {
            orderService.deleteOrder(orderId);
            data.put(RESULT, SUCCESS);
        }catch (HibernateException e){
            if (Config.DEBUG) {
                data.put(RESULT, SUCCESS);
            } else {
                data.put(RESULT, ERROR);
            }
        }
        return SUCCESS;
    }

    //ȡ������
    public String cancelOrder() throws Exception{
        try {
            order = orderService.getByOrderId(orderId);
            order.setOrderStatus(OrderStatus.CANCELED);
            orderService.updateOrder(order);
            return SUCCESS;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    //֧��
    public String payment() throws Exception{
        try {
            order = orderService.getByOrderId(orderId);
            order.setOrderStatus(OrderStatus.UNSHIPPED);
            orderService.updateOrder(order);
            return SUCCESS;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    //ȷ���ջ�
    public String confirmReceive() throws Exception{
        try {
            user = userService.getCurrentUser();
            order = orderService.getByOrderId(orderId);
            order.setOrderStatus(OrderStatus.COMPLETED);
            orderService.updateOrder(order);
            return SUCCESS;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    //�鿴������Ϣ
    public String getLogisticsStatus() throws Exception{
        try {
            user = userService.getCurrentUser();
            logistics = orderService.getLogisticsStatus(orderId);
            ActionContext.getContext().getSession().put("logistics", logistics);
            return SUCCESS;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return ERROR;
    }

    //�̼ҷ���
    public String sendPackage() throws Exception{
        try {
            orderService.sendPackage(orderId, logisticsNum, logisticsCompany);

            return SUCCESS;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return ERROR;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getItemIdList() {
        return itemIdList;
    }

    public void setItemIdList(List<Integer> itemIdList) {
        this.itemIdList = itemIdList;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Collection<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(Collection<Address> addressList) {
        this.addressList = addressList;
    }

    public Collection<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(Collection<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Integer> getCartItemIdList() {
        return cartItemIdList;
    }

    public void setCartItemIdList(List<Integer> cartItemIdList) {
        this.cartItemIdList = cartItemIdList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShipInfId() {
        return shipInfId;
    }

    public void setShipInfId(int shipInfId) {
        this.shipInfId = shipInfId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Address defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
