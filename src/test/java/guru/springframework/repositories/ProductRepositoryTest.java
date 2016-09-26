package guru.springframework.repositories;

import guru.springframework.configuration.RepositoryConfiguration;
import guru.springframework.domain.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTest {

    private MessageRepository productRepository;

    @Autowired
    public void setProductRepository(MessageRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    public void testSaveProduct(){
        //setup product
        Message product = new Message();
        product.setUserName("张三");
        product.setContent("测试内容1");
        product.setDate(new Date());

        //save product, verify has ID value after save
        assertNull(product.getId()); //null before save
        productRepository.save(product);
        assertNotNull(product.getId()); //not null after save
        //fetch from DB
        Message fetchedProduct = productRepository.findOne(product.getId());

        //should not be null
        assertNotNull(fetchedProduct);

        //should equal
        assertEquals(product.getId(), fetchedProduct.getId());
        assertEquals(product.getContent(), fetchedProduct.getContent());

        //update description and save
        fetchedProduct.setContent("New Content");
        productRepository.save(fetchedProduct);

        //get from DB, should be updated
        Message fetchedUpdatedProduct = productRepository.findOne(fetchedProduct.getId());
        assertEquals(fetchedProduct.getContent(), fetchedUpdatedProduct.getContent());

        //verify count of products in DB
        long productCount = productRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<Message> products = productRepository.findAll();

        int count = 0;

        for(Message p : products){
            count++;
        }

        assertEquals(count, 1);
    }
}
