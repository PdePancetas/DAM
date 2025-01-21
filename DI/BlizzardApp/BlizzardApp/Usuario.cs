namespace BlizzardApp
{
    internal class Usuario
    {
        public string Nombre { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string rol {  get; set; }
        public Usuario() { }
        public Usuario(string nombre, string email, string password, string rol)
        {
            this.Nombre = nombre;
            this.Email = email;
            this.Password = password;
            this.rol = rol;
        }
    }
}