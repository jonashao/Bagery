package serviceImpl;

import dao.Dao;
import po.*;
import service.ProductService;

import java.util.List;

@SuppressWarnings("unchecked")
public class ProductServiceImpl implements ProductService {
    public void setDao(Dao dao) {
        this.dao = dao;
    }

    private Dao dao;

    /**
     * 保存一个商品对象
     */
    public int addPriced(Priced priced) {
        return (int)dao.save(priced);
    }

    public void addProduct(Product product) {
        dao.save(product);
    }

    public void addPricedPro(PricedPro pricedPro) {
        dao.save(pricedPro);
    }

    /**
     * 更改商品对象信息
     */
    public void updatePriced(Priced priced) {
        dao.update(priced);
    }

    public void updateProduct(Product product) {
        dao.update(product);
    }

    public void updatePricedPro(PricedPro pricedPro) {
        dao.update(pricedPro);
    }

    /**
     * 通过productID查找商品对象
     */
    public Priced findPriced(int pricedID) {
        return dao.get(Priced.class, pricedID);
    }

    public Product findProduct(int productID) {
        return dao.get(Product.class, productID);
    }

    public PricedPro findPricedPro(int pricedProID) {
        return dao.get(PricedPro.class, pricedProID);
    }

    /**
     * 获得全部商品列表
     */
    public List<Priced> findAll() {
        return dao.query("from Priced").list();
    }

    /**
     * 通过productID删除商品对象
     */
    public void deletePriced(int pricedId) {
        Priced priced = dao.get(Priced.class, pricedId);
        dao.delete(priced);
    }

    public void deleteProduct(int productId) {
        Product product = dao.get(Product.class, productId);
        dao.delete(product);
    }

    /**
     * 根据关键词获取商品列表
     */
    public List<Priced> findPricedsByWord(String keyword) {
        return dao.query("from Priced where Priced.title like '%?%'").setParameter(0, keyword).list();

    }

    /**
     * 通过商品查找商品颜色
     */
    public List<Product> findProductsByPriced(int pricedID) {
        return dao.query("from Product where pricedId=?").setParameter(0, pricedID).list();
    }

    /**
     * 工具-属性数组转SQL字符串
     */
    public String convertToStr(List<Integer> list) {
        StringBuilder sb = new StringBuilder("(");
        for (Integer i : list)
            sb.append(i + ",");
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    /**
     * 通过类别信息获取商品列表
     */
    public List<Priced> findPricedsByProperty(List<Integer> pro1, List<Integer> pro2, List<Integer> pro3) {
        String l1 = convertToStr(pro1);
        String l2 = convertToStr(pro2);
        String l3 = convertToStr(pro3);
        return dao.query("select Priced from PricedPro join Priced where proId in ? and proId in ? and proId in ?").setParameter(0, l1).
                setParameter(1, l2).setParameter(2, l3).list();
    }

    /**
     * 通过用户ID找历史记录
     */
    public List<UserPricedRecord> findHistoryRecord(int userID) {
        return dao.query("from UserPricedRecord where userId=?").
                setParameter(0, userID).list();
    }

    /**
     * 通过分类找属性列表
     */
    public List<Property> findProsByCategory(String category) {
        return dao.query("from Property where category=?").setParameter(0, category).list();
    }

    public void deleteProductsByPriced(int pricedID)
    {

    }
}