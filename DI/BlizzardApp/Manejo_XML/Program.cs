// See https://aka.ms/new-console-template for more information
using System.IO;
using System.Reflection.Metadata;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;


namespace CrearXML
{
    class Principal
    {
        static void Main(String[] args)
        {
            String path = "datos\\Empleados.xml";
            String pathMod = "datos\\EmpleadosModificado.xml";

            crearXML(path);
            Console.WriteLine("XML creado en \\bin\\Debug\\net8.0\\datos");

            Console.WriteLine("id empleado:");
            String id = Console.ReadLine();
            Console.WriteLine("nombre empleado:");
            String nombre = Console.ReadLine();
            Console.WriteLine("puesto empleado:");
            String puesto = Console.ReadLine();

            addEmpleado(id, nombre, puesto, path, pathMod);
            Console.WriteLine("\nEmpleado añadido a " + pathMod);

            Console.WriteLine("\nLectura del xml con XmlDoc: ");
            leerXMLDoc(path);

            Console.WriteLine("\nLectura del xml con BufferedReader");
            leerXMLSr(pathMod);
            
            String xsdPath = "datos\\Validador.xsd";
            if(validarXml(path, xsdPath))
                Console.WriteLine("El xml es valido");
            else
                Console.WriteLine("El xml no es valido");

            Console.WriteLine("\nId del empleado a buscar: ");
            String iD = Console.ReadLine();

            mostrarDatos(iD, pathMod);
        }

        private static void mostrarDatos(string iD, String pathMod)
        {
            XElement empleadoElement = XElement.Load(pathMod);
            IEnumerable<XElement> empleados = from empleado in empleadoElement.Elements() where (string) empleado.Element("id").Value == iD select empleado;
            Console.WriteLine("\nDatos del empleado "+iD+": ");
            foreach (var empleado in empleados)
            {
                Console.WriteLine(empleado);
                //Console.WriteLine("Puesto: "+empleado.Element("puesto").Value);
            
            }
        }

        private static void leerXMLSr(string pathMod)
        {
            using (StreamReader sr = new StreamReader(File.OpenRead(pathMod)))
            {
                string s = "";
                while ((s = sr.ReadLine()) != null)
                {
                    Console.WriteLine(s);
                }
            }
        }

        private static void leerXMLDoc(String path)
        {
            XmlDocument doc = new XmlDocument();
            doc.Load(path);

            foreach (XmlNode nodo in doc.ChildNodes)
                if (nodo.NodeType == XmlNodeType.Element)
                {
                    Console.WriteLine(nodo.Name);
                    foreach (XmlNode datos in nodo.ChildNodes)
                        if (nodo.NodeType == XmlNodeType.Element)
                        {
                            Console.WriteLine(datos.OuterXml);

                        }
                }

        }

        private static void crearXML(String path)
        {
            XmlDocument doc = new XmlDocument();
            XmlElement raiz = doc.CreateElement("empleados");

            XmlElement empleado1 = doc.CreateElement("empleado");

            XmlElement id = doc.CreateElement("id");
            id.AppendChild(doc.CreateTextNode("1"));
            empleado1.AppendChild(id);

            XmlElement nombre = doc.CreateElement("nombre");
            nombre.AppendChild(doc.CreateTextNode("Juan"));
            empleado1.AppendChild(nombre);

            XmlElement puesto = doc.CreateElement("puesto");
            puesto.AppendChild(doc.CreateTextNode("Desarrollador Web"));
            empleado1.AppendChild(puesto);

            raiz.AppendChild(empleado1);

            XmlElement empleado2 = doc.CreateElement("empleado");

            XmlElement id2 = doc.CreateElement("id");
            id2.AppendChild(doc.CreateTextNode("2"));
            empleado2.AppendChild(id2);


            XmlElement nombre2 = doc.CreateElement("nombre");
            nombre2.AppendChild(doc.CreateTextNode("Maria"));
            empleado2.AppendChild(nombre2);

            XmlElement puesto2 = doc.CreateElement("puesto");
            puesto2.AppendChild(doc.CreateTextNode("Desarrollador MultiPlataforma"));
            empleado2.AppendChild(puesto2);

            raiz.AppendChild(empleado2);
            doc.AppendChild(raiz);


            doc.Save(path);
        }

        private static void addEmpleado(String id, String nombre, String puesto, String path, String pathMod)
        {
            XmlDocument doc = new XmlDocument();
            doc.Load(path);

            XmlElement raiz = (XmlElement)doc.FirstChild;
            XmlElement empleado = doc.CreateElement("empleado");
            XmlElement idEmpleado = doc.CreateElement("id");
            idEmpleado.AppendChild(doc.CreateTextNode(id));

            XmlElement nombreEmpleado = doc.CreateElement("nombre");
            nombreEmpleado.AppendChild(doc.CreateTextNode(nombre));
            
            XmlElement puestoEmpleado = doc.CreateElement("puesto");
            puestoEmpleado.AppendChild(doc.CreateTextNode(puesto));

            empleado.AppendChild(idEmpleado);
            empleado.AppendChild(nombreEmpleado);
            empleado.AppendChild(puestoEmpleado);
            raiz.AppendChild(empleado);

            doc.Save(pathMod);
        }

        private static bool validarXml(string path, string xsdPath)
        {
            XmlDocument xml = new XmlDocument();
            xml.Load(path);

            xml.Schemas.Add(null, xsdPath);

            try
            {
                xml.Validate(null);
            }
            catch (XmlSchemaValidationException)
            {
                return false;
            }
            return true;
        }
    }
}