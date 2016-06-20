/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import souza.marlon.spring.data.mongodb.application.Application;
import souza.marlon.spring.data.mongodb.model.DocumentDB;
import souza.marlon.spring.data.mongodb.model.Item;
import souza.marlon.spring.data.mongodb.model.Pedido;
import souza.marlon.spring.data.mongodb.model.Pessoa;
import souza.marlon.spring.data.mongodb.repository.MongoRepository;

/**
 *
 * @author marlonsouza
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MongoRepositoryTest {
    
    @Autowired
    private MongoRepository mongoRepository;
    
    public MongoRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deveTestarCRUDMongo() {
       mongoRepository.deleteAll(Pedido.class);
       mongoRepository.deleteAll(Pessoa.class);

       int tamanhoAnterior = mongoRepository.listAll().size();
       
       Integer rows = 10 + tamanhoAnterior;
       
       ImmutableList.of("Um", "Dois", "Tres", "Quatro", "Cinco")
           .forEach(n -> {
               Pessoa pessoa = new Pessoa();
               pessoa.setNome("Pessoa "+n);
               mongoRepository.insert(pessoa);

               Pedido pedido = new Pedido();
               pedido.setCliente("Cliente "+n);
               mongoRepository.insert(pedido);
       });

       List<DocumentDB> result = mongoRepository.listAll();

       assertTrue(rows == result.size());

       mongoRepository.deleteAll(Pedido.class);
       mongoRepository.deleteAll(Pessoa.class);

    }
    
    @Test
    public void deveTestarCollections(){
        mongoRepository.deleteAll(Pedido.class);
        
        Pedido pedido = new Pedido();
        
        ImmutableList.of("Um", "Dois", "Tres", "Quatro", "Cinco")
           .forEach(n -> {
               Item item = new Item();
               item.setDescricao("Item "+n);
               pedido.addItem(item);
        });
        
        mongoRepository.insert(pedido);
        
        mongoRepository.listAll().stream()
                .filter(i -> i.getNameClass().equals(Pedido.class.getSimpleName()))
                .forEach(d -> {
            Pedido p = (Pedido) d;
            
            p.getItens()
                .forEach(i -> {
                    System.out.println(i.getDescricao());
                });
        }); 
        
        mongoRepository.deleteAll(Pedido.class);
    }
}
