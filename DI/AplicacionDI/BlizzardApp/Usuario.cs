namespace BlizzardApp
{
    internal class Usuario
    {
        public string Nombre { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public Usuario() { }
        public Usuario(string nombre, string email, string password)
        {
            Nombre = nombre;
            Email = email;
            Password = password;
        }
    }
}