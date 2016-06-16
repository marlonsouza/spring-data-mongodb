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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import souza.marlon.spring.data.mongodb.model.DocumentDB;
import souza.marlon.spring.data.mongodb.model.Item;
import souza.marlon.spring.data.mongodb.model.Pedido;
import souza.marlon.spring.data.mongodb.model.Pessoa;
import souza.marlon.spring.data.mongodb.repository.MongoRepository;

/**
 *
 * @author marlonsouza
 */
public class MongoRepositoryTest {
    
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
       MongoRepository mongoRepository = new MongoRepository();
       Integer rows = 10;

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
        
        MongoRepository mongoRepository = new MongoRepository();
        
        Pedido pedido = new Pedido();
        
        ImmutableList.of("Um", "Dois", "Tres", "Quatro", "Cinco")
           .forEach(n -> {
               Item item = new Item();
               item.setDescricao("Item "+n);
               pedido.addItem(item);
        });
        
        mongoRepository.insert(pedido);
        
        mongoRepository.listAll().stream().forEach(d -> {
            Pedido p = (Pedido) d;
            
            p.getItens()
                .forEach(i -> {
                    System.out.println(i.getDescricao());
                });
        });
        
        mongoRepository.deleteAll(Pedido.class);
    }
}
