using MySqlConnector;
using System;
using System.Data;

namespace PruebasMySQL
{

    class Program
    {
        static void Main(string[] args)
        {
            string datos_conexion = @"server=localhost;userid=root;password=1234;database=prueba_users";

            MySqlConnection conexionBD = Func.conectar_BD(datos_conexion);
            //Func.createTable(conexionBD);
            Func.insertData(conexionBD);
        }
    }

}