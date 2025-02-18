namespace BlizzardApp
{
    internal class Usuario
    {

        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string rol {  get; set; }
        public Usuario() { }
        public Usuario(int Id, string nombre, string email, string password, string rol)
        {
            this.Id = Id;
            this.Nombre = nombre;
            this.Email = email;
            this.Password = password;
            this.rol = rol;
        }
    }
}