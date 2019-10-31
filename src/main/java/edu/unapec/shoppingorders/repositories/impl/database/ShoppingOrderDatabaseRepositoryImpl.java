package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.ReportOrderDate;
import edu.unapec.shoppingorders.models.ShoppingOrder;
import edu.unapec.shoppingorders.repositories.ShoppingOrderRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;
import org.hibernate.Session;
import edu.unapec.shoppingorders.models.ReportOrderUser;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaQueryApiInspection")
public class ShoppingOrderDatabaseRepositoryImpl extends RepositoryDatabaseImpl<ShoppingOrder, Long>
        implements ShoppingOrderRepository {

    private final String GET_ORDER_DATE_QUERY = "CALL GetReportQuantityOrdersByDate";

    public ShoppingOrderDatabaseRepositoryImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, ShoppingOrder.class);
    }

    public List<ReportOrderUser> reportOrdersUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createNativeQuery(
                "CALL GetReportOrders()", ReportOrderUser.class);

        @SuppressWarnings("unchecked")
        List<ReportOrderUser> result = query.getResultList();

        return result;
    }

    @Override
    public List<ReportOrderDate> reportOrdersDate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createNativeQuery(GET_ORDER_DATE_QUERY);

        List<Object[]> rows = query.getResultList();
        List<ReportOrderDate> orderDates = new ArrayList<>();

        for (Object[] row : rows) {
            ReportOrderDate orderDate = new ReportOrderDate();

            orderDate.setOrderDate(row[0].toString());
            orderDate.setQuantity(Long.parseLong(row[1].toString()));

            orderDates.add(orderDate);
        }

        return orderDates;
    }

    @Override
    public List<ShoppingOrder> getShoppingOrderByUseId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createNamedQuery("getOrderByUserId", ShoppingOrder.class);
        query.setParameter("userId", id);


        List<ShoppingOrder> result = query.getResultList();

        return result;
    }


}
