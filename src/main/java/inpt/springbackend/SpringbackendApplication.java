package inpt.springbackend;

import inpt.springbackend.dao.CategoryRepository;
import inpt.springbackend.dao.ProductRepository;
import inpt.springbackend.data.Category;
import inpt.springbackend.data.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Random;

@SpringBootApplication
public class SpringbackendApplication implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;



    public static void main(String[] args) {
        SpringApplication.run(SpringbackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.save(new Category(null , "Computers", null , null));
        categoryRepository.save(new Category( null,"Printers",null, null));
        categoryRepository.save(new Category( null, "Smartphones", null, null));

        Random rnd=new Random();

        categoryRepository.findAll().forEach(category -> {
            for (int i = 0; i <10 ; i++) {
                Product p = new Product(); p.setName(RandomString.make(10));
                p.setCurrentPrice(100 + rnd.nextInt(10000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setCategory(category);
                p.setPhotoName("unknown.png");
                productRepository.save(p);
            }
        });
    }

}
