package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.ShoppingOrder;
import edu.unapec.shoppingorders.repositories.ShoppingOrderRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;
import org.hibernate.Session;
import edu.unapec.shoppingorders.models.ReportOrderUser;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SuppressWarnings("JpaQueryApiInspection")
public class ShoppingOrderDatabaseRepositoryImpl extends RepositoryDatabaseImpl<ShoppingOrder, Long> implements ShoppingOrderRepository {
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
    public List<ShoppingOrder> getShoppingOrderByUseId(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createNamedQuery("getOrderByUserId", ShoppingOrder.class);
        query.setParameter("userId", id);


        List<ShoppingOrder> result = query.getResultList();

        return result;
    }


}
