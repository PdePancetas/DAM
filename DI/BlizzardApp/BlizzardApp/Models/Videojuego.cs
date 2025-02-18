namespace BlizzardApp
{ 
    public class Videojuego
    {

        public int Id { get; set; }
        public string Titulo { get; set; }
        public string Descripcion { get; set; }
        public string Genero { get; set; }
        public string Desarrollador { get; set; }
        public string Editor { get; set; }
        public decimal? Precio { get; set; }
        public decimal? PrecioOriginal { get; set; }
        public decimal? Valoracion { get; set; }
        public bool SoporteVR { get; set; }
        public string Tamano { get; set; }
        public string Estado { get; set; }

        // Constructor que recibe los datos y los asigna
        public Videojuego(string titulo, string descripcion, string genero, string desarrollador, string editor,
                          decimal? precio, decimal? precioOriginal, decimal? valoracion, bool soporteVR,
                          string tamano, string estado)
        {
            Titulo = titulo;
            Descripcion = descripcion;
            Genero = genero;
            Desarrollador = desarrollador;
            Editor = editor;
            Precio = precio;
            PrecioOriginal = precioOriginal;
            Valoracion = valoracion;
            SoporteVR = soporteVR;
            Tamano = tamano;
            Estado = estado;
        }

        public Videojuego(int id, string titulo, string descripcion, string genero, string desarrollador, 
            string editor, decimal? precio, decimal? precioOriginal, decimal? valoracion, bool soporteVR, 
            string tamano, string estado)
        {
            Id = id;
            Titulo = titulo;
            Descripcion = descripcion;
            Genero = genero;
            Desarrollador = desarrollador;
            Editor = editor;
            Precio = precio;
            PrecioOriginal = precioOriginal;
            Valoracion = valoracion;
            SoporteVR = soporteVR;
            Tamano = tamano;
            Estado = estado;
        }
    }

}