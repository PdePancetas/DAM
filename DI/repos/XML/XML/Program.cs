// See https://aka.ms/new-console-template for more information
using System.Collections.Generic;
using System.Xml;
namespace workingWithXML
{
    class Program
    {
        static void Main(string[] args)
        {
            //usingXMLReader("C:\\Users\\blanc\\source\\repos\\XML\\XML\\XMLFile1.xml");
            usingXMLDocument("C:\\Users\\blanc\\source\\repos\\XML\\XML\\XMLFile1.xml");
        }
        

        private static void usingXMLReader(String path)
        {
            XmlReader xmlReader = XmlReader.Create(path);
            while (xmlReader.Read())
            {
                if(xmlReader.NodeType == XmlNodeType.Element && xmlReader.Name=="empleados")
                {
                   
                        Console.WriteLine(xmlReader.Name);
                   
                }

            }

            Console.ReadKey();
        }
        private static void usingXMLDocument(String path)
        {
            XmlDocument doc = new XmlDocument();
            doc.LoadXml(path);
            
            foreach(XmlNode xmlNode in doc.ChildNodes[0].ChildNodes)
            {
                if(xmlNode.NodeType == XmlNodeType.Element)
                    Console.WriteLine(xmlNode.Name+": "+xmlNode.InnerXml);
            }



        }
    }
}