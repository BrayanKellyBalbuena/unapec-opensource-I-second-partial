package edu.unapec.shoppingorders.controllers;

import edu.unapec.shoppingorders.configurations.SpringDIConfiguration;
import edu.unapec.shoppingorders.models.Todo;
import edu.unapec.shoppingorders.services.ProductCategoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/todo")
public class TestController {
    private final ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringDIConfiguration.class);

    public TestController() {
//        HibernateUtil service =  ctx.getBean(HibernateUtil.class);
//
//        Session session =  service.getSessionFactory().openSession();
//
////      List<ProductCategory>  categories = session.createQuery("from pro", ProductCategory.class).list();
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<ProductCategory> cr = cb.createQuery(ProductCategory.class);
//        Root<ProductCategory> root = cr.from(ProductCategory.class);
//        cr.select(root);
//
//        Query<ProductCategory> query = session.createQuery(cr);
//        List<ProductCategory> results = query.getResultList();

        ProductCategoryService repo = ctx.getBean(ProductCategoryService.class);
        repo.getAll();
    }

    // This method is called if XML is requested
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Todo getXML() {
        Todo todo = new Todo();
        todo.setSummary("Application XML Todo Summary");
        todo.setDescription("Application XML Todo Description");
        return todo;
    }

    // This method is called if JSON is requested
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Todo getJSON() {
        Todo todo = new Todo();
        todo.setSummary("Application JSON Todo Summary");
        todo.setDescription("Application JSON Todo Description");
        return todo;
    }

    // This can be used to test the integration with the browser
    @GET
    @Produces({ MediaType.TEXT_XML })
    public Todo getHTML() {
        Todo todo = new Todo();
        todo.setSummary("XML Todo Summary");
        todo.setDescription("XML Todo Description");
        return todo;
    }

}
