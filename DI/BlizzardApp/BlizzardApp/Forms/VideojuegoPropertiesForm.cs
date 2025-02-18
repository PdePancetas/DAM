using BlizzardApp.Forms;
using MySqlConnector;
using System;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class VideojuegoPropertiesForm : Form
    {
        private string tituloJuego;
        private int idJuego;

        public VideojuegoPropertiesForm(string titulo)
        {
            InitializeComponent();

            if (LoggedUser.User.rol.Equals("user"))
            {
                this.btnCancelar.Visible = false;
                this.btnEditar.Visible = false;
                this.btnGuardar.Visible = false;
            } 


            this.tituloJuego = titulo;
            CargarDetallesJuego();
            HabilitarCampos(false);
        }

        // Método para cargar los datos del juego
        private void CargarDetallesJuego()
        {
            string query = "SELECT * FROM videojogos WHERE titulo = @titulo";

            using (MySqlConnection conexion = Func.Conectar_BD())
            {
                conexion.Open();
                using (MySqlCommand cmd = new MySqlCommand(query, conexion))
                {
                    cmd.Parameters.AddWithValue("@titulo", tituloJuego);

                    using (MySqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read())
                        {
                            // Manejo de NULLS para cada campo
                            txtTitulo.Text = reader["titulo"] != DBNull.Value ? reader["titulo"].ToString() : "Sin título";
                            txtDescripcion.Text = reader["descripcion"] != DBNull.Value ? reader["descripcion"].ToString() : "Sin descripción";
                            txtGenero.Text = reader["genero"] != DBNull.Value ? reader["genero"].ToString() : "";
                            txtDesarrollador.Text = reader["desarrollador"] != DBNull.Value ? reader["desarrollador"].ToString() : "";
                            txtEditor.Text = reader["editor"] != DBNull.Value ? reader["editor"].ToString() : "";
                            txtPrecio.Text = reader["precio"] != DBNull.Value ? Convert.ToDecimal(reader["precio"]).ToString("F2") : "0.00";
                            txtPrecioOriginal.Text = reader["precio_original"] != DBNull.Value ? Convert.ToDecimal(reader["precio_original"]).ToString("F2") : "0.00";
                            txtValoracion.Text = reader["valoracion"] != DBNull.Value ? Convert.ToDecimal(reader["valoracion"]).ToString("F1") : "0.0";
                            chkSoporteVR.Checked = reader["soporte_VR"] != DBNull.Value ? Convert.ToBoolean(reader["soporte_VR"]) : false;
                            txtTamaño.Text = reader["tamaño"] != DBNull.Value ? reader["tamaño"].ToString() : "";
                            cmbEstado.SelectedItem = reader["estado"] != DBNull.Value ? reader["estado"].ToString() : "Desconocido";



                            // Cargar imagen si existe
                            if (reader["img_src"] != DBNull.Value)
                            {
                                byte[] imagenBytes = (byte[])reader["img_src"];
                                using (MemoryStream ms = new MemoryStream(imagenBytes))
                                {
                                    pbImagen.Image = Image.FromStream(ms);
                                }
                            }
                            else
                            {
                                pbImagen.Image = null;
                            }
                        }
                        else
                        {
                            MessageBox.Show("Juego no encontrado.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                            this.Close();
                        }
                    }
                }
            }
        }


        // Método para habilitar o deshabilitar los campos
        private void HabilitarCampos(bool habilitar)
        {
            txtTitulo.ReadOnly = true; // El título no debería poder cambiarse
            txtDescripcion.ReadOnly = !habilitar;
            txtGenero.ReadOnly = !habilitar;
            txtDesarrollador.ReadOnly = !habilitar;
            txtEditor.ReadOnly = !habilitar;
            txtPrecio.ReadOnly = !habilitar;
            txtPrecioOriginal.ReadOnly = !habilitar;
            txtValoracion.ReadOnly = !habilitar;
            chkSoporteVR.Enabled = habilitar;
            txtTamaño.ReadOnly = !habilitar;
            cmbEstado.Enabled = habilitar;
            btnGuardar.Enabled = habilitar;
            btnCancelar.Enabled = habilitar;
        }

        // Botón para habilitar la edición
        private void btnEditar_Click(object sender, EventArgs e)
        {
            HabilitarCampos(true);
        }

        // Botón para guardar los cambios en la base de datos
        private void btnGuardar_Click(object sender, EventArgs e)
        {
            string query = @"UPDATE videojogos 
                     SET descripcion = @descripcion, 
                         genero = @genero, 
                         desarrollador = @desarrollador, 
                         editor = @editor, 
                         precio = @precio, 
                         precio_original = @precio_original, 
                         valoracion = @valoracion, 
                         soporte_VR = @soporteVR, 
                         tamaño = @tamano, 
                         estado = @estado 
                     WHERE titulo = @titulo";

            using (MySqlConnection conexion = Func.Conectar_BD())
            {
                conexion.Open();
                using (MySqlCommand cmd = new MySqlCommand(query, conexion))
                {
                    cmd.Parameters.AddWithValue("@titulo", txtTitulo.Text);
                    cmd.Parameters.AddWithValue("@descripcion", string.IsNullOrEmpty(txtDescripcion.Text) ? (object)DBNull.Value : txtDescripcion.Text);
                    cmd.Parameters.AddWithValue("@genero", string.IsNullOrEmpty(txtGenero.Text) ? (object)DBNull.Value : txtGenero.Text);
                    cmd.Parameters.AddWithValue("@desarrollador", string.IsNullOrEmpty(txtDesarrollador.Text) ? (object)DBNull.Value : txtDesarrollador.Text);
                    cmd.Parameters.AddWithValue("@editor", string.IsNullOrEmpty(txtEditor.Text) ? (object)DBNull.Value : txtEditor.Text);
                    cmd.Parameters.AddWithValue("@precio", decimal.TryParse(txtPrecio.Text, out decimal precio) ? precio : (object)DBNull.Value);
                    cmd.Parameters.AddWithValue("@precio_original", decimal.TryParse(txtPrecioOriginal.Text, out decimal precioOriginal) ? precioOriginal : (object)DBNull.Value);
                    cmd.Parameters.AddWithValue("@valoracion", decimal.TryParse(txtValoracion.Text, out decimal valoracion) ? valoracion : (object)DBNull.Value);
                    cmd.Parameters.AddWithValue("@soporteVR", chkSoporteVR.Checked);
                    cmd.Parameters.AddWithValue("@tamano", string.IsNullOrEmpty(txtTamaño.Text) ? (object)DBNull.Value : txtTamaño.Text);
                    cmd.Parameters.AddWithValue("@estado", cmbEstado.SelectedItem != null ? cmbEstado.SelectedItem.ToString() : (object)DBNull.Value);

                    int filasAfectadas = cmd.ExecuteNonQuery();

                    if (filasAfectadas > 0)
                    {
                        MessageBox.Show("Datos actualizados correctamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        HabilitarCampos(false);
                    }
                    else
                    {
                        MessageBox.Show("No se realizaron cambios.", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    }
                }
            }
        }


        // Botón para cancelar y cerrar el formulario
        private void btnCancelar_Click(object sender, EventArgs e)
        {
            CargarDetallesJuego();
            HabilitarCampos(false);
        }

        private void btnAddCesta_Click(object sender, EventArgs e)
        {
            CestaForm.AddJuego(tituloJuego);
        }
    }
}
