//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.5 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//

package objetosXJC;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para anonymous complex type.
 * </p>
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que
 * haya en esta clase.
 * </p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="pedido" maxOccurs="unbounded" minOccurs="0">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="cliente">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base=
"{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="nombre" type=
"{http://www.w3.org/2001/XMLSchema}string"/>
 *                             <element name="nif" type=
"{http://www.w3.org/2001/XMLSchema}string"/>
 *                           </sequence>
 *                           <attribute name="id" type=
"{http://www.w3.org/2001/XMLSchema}int" />
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="listaProductos">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base=
"{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="producto" maxOccurs=
"unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base=
"{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <sequence>
 *                                       <element name="descripcion" type=
"{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       <element name="precio" type=
"{http://www.w3.org/2001/XMLSchema}double"/>
 *                                       <element name="cantidad" type=
"{http://www.w3.org/2001/XMLSchema}int"/>
 *                                     </sequence>
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "pedido" })
@XmlRootElement(name = "pedidos")
public class Pedidos {

	protected List<Pedidos.Pedido> pedido;

	public Pedidos() {
		super();
	}

	public Pedidos(List<Pedido> pedido) {
		super();
		this.pedido = pedido;
	}

	/**
	 * Gets the value of the pedido property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the pedido property.
	 * </p>
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * </p>
	 * 
	 * <pre>
	 * getPedido().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Pedidos.Pedido }
	 * </p>
	 * 
	 * 
	 * @return The value of the pedido property.
	 */
	public List<Pedidos.Pedido> getPedido() {
		if (pedido == null) {
			pedido = new ArrayList<>();
		}
		return this.pedido;
	}

	/**
	 * <p>
	 * Clase Java para anonymous complex type.
	 * </p>
	 * 
	 * <p>
	 * El siguiente fragmento de esquema especifica el contenido que se espera que
	 * haya en esta clase.
	 * </p>
	 * 
	 * <pre>{@code
	 * <complexType>
	 *   <complexContent>
	 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       <sequence>
	 *         <element name="cliente">
	 *           <complexType>
	 *             <complexContent>
	 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 <sequence>
	 *                   <element name="nombre" type=
	"{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   <element name="nif" type=
	"{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 </sequence>
	 *                 <attribute name="id" type=
	"{http://www.w3.org/2001/XMLSchema}int" />
	 *               </restriction>
	 *             </complexContent>
	 *           </complexType>
	 *         </element>
	 *         <element name="listaProductos">
	 *           <complexType>
	 *             <complexContent>
	 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 <sequence>
	 *                   <element name="producto" maxOccurs="unbounded" minOccurs=
	"0">
	 *                     <complexType>
	 *                       <complexContent>
	 *                         <restriction base=
	"{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           <sequence>
	 *                             <element name="descripcion" type=
	"{http://www.w3.org/2001/XMLSchema}string"/>
	 *                             <element name="precio" type=
	"{http://www.w3.org/2001/XMLSchema}double"/>
	 *                             <element name="cantidad" type=
	"{http://www.w3.org/2001/XMLSchema}int"/>
	 *                           </sequence>
	 *                         </restriction>
	 *                       </complexContent>
	 *                     </complexType>
	 *                   </element>
	 *                 </sequence>
	 *               </restriction>
	 *             </complexContent>
	 *           </complexType>
	 *         </element>
	 *       </sequence>
	 *     </restriction>
	 *   </complexContent>
	 * </complexType>
	 * }</pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "cliente", "listaProductos" })
	public static class Pedido {

		@XmlElement(required = true)
		protected Pedidos.Pedido.Cliente cliente;
		@XmlElement(required = true)
		protected Pedidos.Pedido.ListaProductos listaProductos;

		public Pedido() {
			super();
		}

		public Pedido(Cliente cliente, ListaProductos listaProductos) {
			super();
			this.cliente = cliente;
			this.listaProductos = listaProductos;
		}

		/**
		 * Obtiene el valor de la propiedad cliente.
		 * 
		 * @return possible object is {@link Pedidos.Pedido.Cliente }
		 * 
		 */
		public Pedidos.Pedido.Cliente getCliente() {
			return cliente;
		}

		/**
		 * Define el valor de la propiedad cliente.
		 * 
		 * @param value allowed object is {@link Pedidos.Pedido.Cliente }
		 * 
		 */
		public void setCliente(Pedidos.Pedido.Cliente value) {
			this.cliente = value;
		}

		/**
		 * Obtiene el valor de la propiedad listaProductos.
		 * 
		 * @return possible object is {@link Pedidos.Pedido.ListaProductos }
		 * 
		 */
		public Pedidos.Pedido.ListaProductos getListaProductos() {
			return listaProductos;
		}

		/**
		 * Define el valor de la propiedad listaProductos.
		 * 
		 * @param value allowed object is {@link Pedidos.Pedido.ListaProductos }
		 * 
		 */
		public void setListaProductos(Pedidos.Pedido.ListaProductos value) {
			this.listaProductos = value;
		}

		/**
		 * <p>
		 * Clase Java para anonymous complex type.
		 * </p>
		 * 
		 * <p>
		 * El siguiente fragmento de esquema especifica el contenido que se espera que
		 * haya en esta clase.
		 * </p>
		 * 
		 * <pre>{@code
		 * <complexType>
		 *   <complexContent>
		 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       <sequence>
		 *         <element name="nombre" type=
		"{http://www.w3.org/2001/XMLSchema}string"/>
		 *         <element name="nif" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       </sequence>
		 *       <attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
		 *     </restriction>
		 *   </complexContent>
		 * </complexType>
		 * }</pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "nombre", "nif" })
		public static class Cliente {

			@XmlElement(required = true)
			protected String nombre;
			@XmlElement(required = true)
			protected String nif;
			@XmlAttribute(name = "id")
			protected Integer id;

			public Cliente() {
				super();
			}

			public Cliente(String nombre, String nif, Integer id) {
				super();
				this.nombre = nombre;
				this.nif = nif;
				this.id = id;
			}

			/**
			 * Obtiene el valor de la propiedad nombre.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNombre() {
				return nombre;
			}

			@Override
			public String toString() {
				return nombre + " con nif " + nif;
			}

			/**
			 * Define el valor de la propiedad nombre.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setNombre(String value) {
				this.nombre = value;
			}

			/**
			 * Obtiene el valor de la propiedad nif.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getNif() {
				return nif;
			}

			/**
			 * Define el valor de la propiedad nif.
			 * 
			 * @param value allowed object is {@link String }
			 * 
			 */
			public void setNif(String value) {
				this.nif = value;
			}

			/**
			 * Obtiene el valor de la propiedad id.
			 * 
			 * @return possible object is {@link Integer }
			 * 
			 */
			public Integer getId() {
				return id;
			}

			/**
			 * Define el valor de la propiedad id.
			 * 
			 * @param value allowed object is {@link Integer }
			 * 
			 */
			public void setId(Integer value) {
				this.id = value;
			}

		}

		/**
		 * <p>
		 * Clase Java para anonymous complex type.
		 * </p>
		 * 
		 * <p>
		 * El siguiente fragmento de esquema especifica el contenido que se espera que
		 * haya en esta clase.
		 * </p>
		 * 
		 * <pre>{@code
		 * <complexType>
		 *   <complexContent>
		 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       <sequence>
		 *         <element name="producto" maxOccurs="unbounded" minOccurs="0">
		 *           <complexType>
		 *             <complexContent>
		 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 <sequence>
		 *                   <element name="descripcion" type=
		"{http://www.w3.org/2001/XMLSchema}string"/>
		 *                   <element name="precio" type=
		"{http://www.w3.org/2001/XMLSchema}double"/>
		 *                   <element name="cantidad" type=
		"{http://www.w3.org/2001/XMLSchema}int"/>
		 *                 </sequence>
		 *               </restriction>
		 *             </complexContent>
		 *           </complexType>
		 *         </element>
		 *       </sequence>
		 *     </restriction>
		 *   </complexContent>
		 * </complexType>
		 * }</pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "producto" })
		public static class ListaProductos {

			protected List<Pedidos.Pedido.ListaProductos.Producto> producto;

			public ListaProductos() {
				super();
			}

			public ListaProductos(List<Producto> producto) {
				super();
				this.producto = producto;
			}

			/**
			 * Gets the value of the producto property.
			 * 
			 * <p>
			 * This accessor method returns a reference to the live list, not a snapshot.
			 * Therefore any modification you make to the returned list will be present
			 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
			 * for the producto property.
			 * </p>
			 * 
			 * <p>
			 * For example, to add a new item, do as follows:
			 * </p>
			 * 
			 * <pre>
			 * getProducto().add(newItem);
			 * </pre>
			 * 
			 * 
			 * <p>
			 * Objects of the following type(s) are allowed in the list
			 * {@link Pedidos.Pedido.ListaProductos.Producto }
			 * </p>
			 * 
			 * 
			 * @return The value of the producto property.
			 */
			public List<Pedidos.Pedido.ListaProductos.Producto> getProducto() {
				if (producto == null) {
					producto = new ArrayList<>();
				}
				return this.producto;
			}

			/**
			 * <p>
			 * Clase Java para anonymous complex type.
			 * </p>
			 * 
			 * <p>
			 * El siguiente fragmento de esquema especifica el contenido que se espera que
			 * haya en esta clase.
			 * </p>
			 * 
			 * <pre>{@code
			 * <complexType>
			 *   <complexContent>
			 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       <sequence>
			 *         <element name="descripcion" type=
			"{http://www.w3.org/2001/XMLSchema}string"/>
			 *         <element name="precio" type=
			"{http://www.w3.org/2001/XMLSchema}double"/>
			 *         <element name="cantidad" type=
			"{http://www.w3.org/2001/XMLSchema}int"/>
			 *       </sequence>
			 *     </restriction>
			 *   </complexContent>
			 * </complexType>
			 * }</pre>
			 * 
			 * 
			 */
			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "descripcion", "precio", "cantidad" })
			public static class Producto {

				@XmlElement(required = true)
				protected String descripcion;
				protected double precio;
				protected int cantidad;
				
				

				@Override
				public int hashCode() {
					return Objects.hash(descripcion);
				}

				@Override
				public boolean equals(Object obj) {
					if (this == obj)
						return true;
					if (obj == null)
						return false;
					if (getClass() != obj.getClass())
						return false;
					Producto other = (Producto) obj;
					return Objects.equals(descripcion, other.descripcion);
				}

				public Producto() {
					super();
				}

				public Producto(String descripcion, double precio, int cantidad) {
					super();
					this.descripcion = descripcion;
					this.precio = precio;
					this.cantidad = cantidad;
				}

				/**
				 * Obtiene el valor de la propiedad descripcion.
				 * 
				 * @return possible object is {@link String }
				 * 
				 */
				public String getDescripcion() {
					return descripcion;
				}

				/**
				 * Define el valor de la propiedad descripcion.
				 * 
				 * @param value allowed object is {@link String }
				 * 
				 */
				public void setDescripcion(String value) {
					this.descripcion = value;
				}

				/**
				 * Obtiene el valor de la propiedad precio.
				 * 
				 */
				public double getPrecio() {
					return precio;
				}

				/**
				 * Define el valor de la propiedad precio.
				 * 
				 */
				public void setPrecio(double value) {
					this.precio = value;
				}

				/**
				 * Obtiene el valor de la propiedad cantidad.
				 * 
				 */
				public int getCantidad() {
					return cantidad;
				}

				/**
				 * Define el valor de la propiedad cantidad.
				 * 
				 */
				public void setCantidad(int value) {
					this.cantidad = value;
				}

			}

		}

	}

}
