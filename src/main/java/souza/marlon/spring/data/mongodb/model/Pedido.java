/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souza.marlon.spring.data.mongodb.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import souza.marlon.spring.data.mongodb.immutable.ImmutableBindMongo;
import souza.marlon.spring.data.mongodb.json.FromJson;

/**
 *
 * @author marlonsouza
 */
@Document
public class Pedido extends DocumentDB{
    
    @Id
    private BigInteger id;
    
    private String cliente;
    
    private final List<Item> itens = Lists.newArrayList();

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens(){
        return ImmutableList.copyOf(this.itens);
    }
    
    public void addItem(Item item){
        Preconditions.checkNotNull(item);
        this.itens.add(item);
    }
    
    @Override
    protected FromJson fromJson(String json) {
        return ImmutableBindMongo.builder().clazz(Pedido.class).json(json)::build;
    }
}
