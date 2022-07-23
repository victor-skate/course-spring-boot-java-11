package system.developer.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity; //É PREFERÍVEL IMPORTAR ESSA ESPECIFICAÇÃO INVÉS DA IMPLEMENTAÇÃO
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;// PARA TRABALHAR COM DATAS, É MUITO SUPERIOR AO TIPO DATE

	private Integer orderStatus;

	@ManyToOne // MUITOS PEDIDOS PARA UM CLIENTE
	@JoinColumn(name = "client_id") // NOME DA CHAVE ESTRANGEIRA NA TABELA ORDER
	// @JsonIgnore ESSA ANOTAÇÃO DESSE LADO DA ASSOCIAÇÃO FAZ COM QUE QUANDO EU
	// BUSCAR UM client
	// SEJAM RETORNADOS OS PEDIDOS RELACIONADOS A ELE SEM GERAR EXCEÇÃO DE LOOP
	// INFINITO
	private User client;
	
	@OneToMany(mappedBy = "id.order")//O OrderItem TEM O id EO id É QUE TEM O PEDIDO. OBSERVE O ATRIBUTO id NA CLASSE OrderItem
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//cascadeType.all, FAZ COM QUE O PAGAMENTO RECEBA O MESMO ID DO PEDIDO
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus status, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(status);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItem(){
		return items;
	}
	
	public Double getTotal() {
		Double sum = 0.0;
		for(OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
