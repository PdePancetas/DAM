//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package objetosXJC;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the objetosXJC package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: objetosXJC
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Pedidos }
     * 
     * @return
     *     the new instance of {@link Pedidos }
     */
    public Pedidos createPedidos() {
        return new Pedidos();
    }

    /**
     * Create an instance of {@link Pedidos.Pedido }
     * 
     * @return
     *     the new instance of {@link Pedidos.Pedido }
     */
    public Pedidos.Pedido createPedidosPedido() {
        return new Pedidos.Pedido();
    }

    /**
     * Create an instance of {@link Pedidos.Pedido.ListaProductos }
     * 
     * @return
     *     the new instance of {@link Pedidos.Pedido.ListaProductos }
     */
    public Pedidos.Pedido.ListaProductos createPedidosPedidoListaProductos() {
        return new Pedidos.Pedido.ListaProductos();
    }

    /**
     * Create an instance of {@link Pedidos.Pedido.Cliente }
     * 
     * @return
     *     the new instance of {@link Pedidos.Pedido.Cliente }
     */
    public Pedidos.Pedido.Cliente createPedidosPedidoCliente() {
        return new Pedidos.Pedido.Cliente();
    }

    /**
     * Create an instance of {@link Pedidos.Pedido.ListaProductos.Producto }
     * 
     * @return
     *     the new instance of {@link Pedidos.Pedido.ListaProductos.Producto }
     */
    public Pedidos.Pedido.ListaProductos.Producto createPedidosPedidoListaProductosProducto() {
        return new Pedidos.Pedido.ListaProductos.Producto();
    }

}
