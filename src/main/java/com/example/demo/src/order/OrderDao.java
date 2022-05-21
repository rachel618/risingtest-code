package com.example.demo.src.order;

import com.example.demo.src.order.model.GetOrderRes;
import com.example.demo.src.order.model.PostOrderProductOptionReq;
import com.example.demo.src.order.model.PostOrderReq;
import com.example.demo.src.order.model.PostOrderRes;
import com.example.demo.src.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Repository
public class OrderDao {
    private JdbcTemplate jdbcTemplate;
    UserDao userDao = new UserDao();

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //    public List<String> getMenus(int userIdx){
//        String getOrderQuery="select orderIdx from Order where userIdx=?";
//        String getMenuQuery="select me"
//    }
    public List<GetOrderRes> getOrders(int userIdx) {
        String getOrdersQuery = "select orderIdx,money,orderDate from `Order` where userIdx=" + userIdx;
        List<GetOrderRes> Orders = this.jdbcTemplate.query(getOrdersQuery,
                (rs, rowNum) -> new GetOrderRes(
                        rs.getInt("orderIdx"),
                        rs.getInt("totalPrice"),
                        rs.getString("orderDate"))
        );
        System.out.println(Orders.get(0).getOrderIdx());
        String storeName, storeImageUrl;
        for (GetOrderRes getOrderRes : Orders) {
            String getStoreNameQuery = "select storeName from `Order`,Store" +
                    " where `Order`.storeIdx=Store.storeIdx and `Order`.orderIdx=" + getOrderRes.getOrderIdx();
            storeName = this.jdbcTemplate.queryForObject(getStoreNameQuery, String.class);
            System.out.println(storeName);
            String getStoreIMageQuery = "select profileImage from `Order`,Store" +
                    " where `Order`.storeIdx=Store.storeIdx and `Order`.orderIdx=" + getOrderRes.getOrderIdx();
            storeImageUrl = this.jdbcTemplate.queryForObject(getStoreNameQuery, String.class);
            System.out.println("2");
            String getMenuNamesQuery = "select menuName from Menu,OrderMenu where OrderMenu.menuIdx=Menu.menuIdx and orderIdx=" + getOrderRes.getOrderIdx();
            List<String> menuNames = this.jdbcTemplate.queryForList(getMenuNamesQuery, String.class);
            System.out.println("3");
            getOrderRes.setStoreName(storeName);
            getOrderRes.setProfileImage(storeImageUrl);
            getOrderRes.setMenus(menuNames);
        }
        return Orders;
    }

    @Transactional
    public PostOrderRes createMemberOrder(int userIdx,PostOrderReq postOrderReq){

        PostOrderRes postOrderRes= new PostOrderRes();

        String createOrderInfo="insert into `Order`(ordererName,ordererEmail,ordererPhoneNum,userIdx) values(?,?,?,?)";
        Object[] createOrderParams = new Object[]{postOrderReq.getOrdererName(), postOrderReq.getOrdererEmail(),postOrderReq.getOrdererPhoneNum(),userIdx};
        this.jdbcTemplate.update(createOrderInfo,createOrderParams);

        String query="select last_insert_id()";
        int orderIdx=this.jdbcTemplate.queryForObject(query,Integer.class);
        postOrderRes.setOrderIdx(orderIdx);

        String createAddressInfo="insert into AddressInfo(orderIdx,userIdx,addressName,receiverName,contact,address,request,isDefaultAddress) values(?,?,?,?,?,?,?,?)";
        Object[] createAddressInfoParams = new Object[]{orderIdx,userIdx,postOrderReq.getAddressName(), postOrderReq.getReceiverName(),postOrderReq.getContact(),
        postOrderReq.getAddress(), postOrderReq.getRequest(),postOrderReq.getIsDefaultAddress()};
        this.jdbcTemplate.update(createAddressInfo,createAddressInfoParams);
        String createPayment;
        Object[] createPaymentParams;
        if(Objects.equals(postOrderReq.getPayment(), "CARD")){
//            System.out.println("sdc");
            createPayment="insert into Payment(orderIdx,payment,cardType,installment,status) values(?,?,?,?,?)";
            createPaymentParams = new Object[]{orderIdx,postOrderReq.getPayment(), postOrderReq.getCardType(),postOrderReq.getInstallment(),"PAYMENT_DONE"};
            this.jdbcTemplate.update(createPayment,createPaymentParams);

        }
        else{
            createPayment="insert into Payment(payment) values (?) ";
            String createPaymentParam= postOrderReq.getPayment();
            this.jdbcTemplate.update(createPayment, createPaymentParam);
        }
//        System.out.println("4");
        List<PostOrderProductOptionReq> products= postOrderReq.getProducts();
        int totalProductPrice=0;
        int totalDeliveryTip=0;
        for(PostOrderProductOptionReq product: products){
            String getPrice= "select regularPrice from Product where productIdx= "+ product.getProductIdx();
            totalProductPrice += this.jdbcTemplate.queryForObject(getPrice,Integer.class);
//            System.out.println(totalProductPrice);
            String getDeliveryTip= "select deliveryTip from Brand,Product where Product.brandIdx=Brand.brandIdx and productIdx= "+ product.getProductIdx();
            totalDeliveryTip += this.jdbcTemplate.queryForObject(getDeliveryTip,Integer.class);

            String putProductOrder="insert ProductOrder(productIdx, orderIdx, productCnt,status) values(?,?,?,?)";
            Object[] putProductOrderParams=new Object[]{product.getProductIdx(), orderIdx, product.getProductCnt(),"PROCESSING"};
            this.jdbcTemplate.update(putProductOrder,putProductOrderParams);

            // 제품 테이블 주문수 추가
            String increaseOrders="update Product set numOfOrders = numOfOrders +1 where productIdx = ? ";
            this.jdbcTemplate.update(increaseOrders,product.getProductIdx());
        }
//        System.out.println("5");
        int point= postOrderReq.getPoint();
        String getPointQuery="update User set point = point-" + point + " where userIdx="+ userIdx;
        this.jdbcTemplate.update(getPointQuery);


        postOrderRes.setDeliveryTip(totalDeliveryTip);
        postOrderRes.setTotalProductPrice(totalProductPrice);
        postOrderRes.setPoint(point);
        postOrderRes.setTotalPrice(totalDeliveryTip + totalProductPrice - point);
        return postOrderRes;

    }

//    public PostOrderRes createNonMemberOrder(PostOrderReq postOrderReq){
//
//    }
}
