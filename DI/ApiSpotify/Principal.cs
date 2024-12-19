using SpotifyAPI;
using SpotifyAPI.Web;
using SpotifyAPI.Web.Http;
using System.Net;
using System.Web;
using ApiSpotify.Helpers;

namespace ApiSpotify
{
    using System;
    using System.Linq.Expressions;
    using System.Net.Http;
    using System.Net.Http.Headers;
    using System.Text;
    using System.Text.Json;
    using System.Threading.Tasks;
    using ApiSpotify.Helpers;
    using ApiSpotify.Models;
    using static Microsoft.Extensions.Logging.EventSource.LoggingEventSource;

    public class Principal
    { 

        public static string clientId = "ID_CLIENT"; // Reemplaza con tu Client ID
        public static string clientSecret = "ID_CLIENT_SECRET"; // Reemplaza con tu Client Secret
        public static string redirectUri = "http://localhost:5000/callback";
        public static string credentials = Convert.ToBase64String(Encoding.UTF8.GetBytes($"{clientId}:{clientSecret}"));

        static async Task Main(string[] args)
        {

            // Inicia el servidor en segundo plano
            var serverTask = StartServerAsync();

            // 1. Mostrar la URL de autorización en consola
            var authUrl = $"https://accounts.spotify.com/authorize?" +
                          $"response_type=code&client_id={clientId}&redirect_uri={Uri.EscapeDataString(redirectUri)}&" +
                          $"scope=user-read-private%20user-read-email%20user-follow-read%20playlist-read-private%20playlist-read-collaborative";

            Console.WriteLine("Por favor, abre la siguiente URL en tu navegador para autorizar la aplicación:");
            Console.WriteLine(authUrl);
            Console.WriteLine("Presiona una tecla cuando hayas autorizado la aplicación...");

            // 2. Esperar a que el usuario presione una tecla para continuar
            Console.ReadKey();

            // 3. Obtener el código de autorización de la URL de redirección
            Console.WriteLine("\nIntroduce el código de autorización recibido:");
            var code = Console.ReadLine();

            if (!string.IsNullOrEmpty(code))
            {

                var access_Token = await GetAccessToken(code);
                try { 
                    var config = SpotifyClientConfig.CreateDefault().WithToken(access_Token);
                    var spotify = new SpotifyClient(config);


                    bool exit = false;
                    while (!exit)
                    {
                        Console.Clear();
                        Console.WriteLine("============== Menú Principal ==============");
                        Console.WriteLine("Opción 1: Ver mi información");
                        Console.WriteLine("Opción 2: Ver playlists de un usuario");
                        Console.WriteLine("Opción 3: Buscar canciones, artistas o álbumes");
                        Console.WriteLine("Opción 4: Detalles de un artista o álbum");
                        Console.WriteLine("Opción 0: Salir");
                        Console.WriteLine("============================================");
                        Console.Write("Seleccione una opción: ");

                        string choice = Console.ReadLine();

                        switch (choice)
                        {
                            case "0":
                                exit = true;
                                Console.WriteLine("Saliendo del programa...");
                                break;
                            case "1":
                                Logic.GetMyInfoAsync(spotify);
                                Console.WriteLine("\nPulsa una tecla para continuar...");
                                Console.ReadKey();
                                break;
                            case "2":
                                Logic.GetPlaylistsAsync(spotify);
                                Console.WriteLine("\nPulsa una tecla para continuar...");
                                Console.ReadKey();
                                break;
                            case "3":
                                Console.Write("Ingresa un término a buscar: ");
                                String keyword = Console.ReadLine();
                                Logic.SearchInfoAsync(spotify, keyword);
                                Console.WriteLine("\nPulsa una tecla para continuar...");
                                Console.ReadKey();
                                break;
                            case "4":
                                Console.Write("Ingresa un término a buscar: ");
                                keyword = Console.ReadLine();
                                Logic.detailSearchAsync(spotify, keyword);
                                Console.WriteLine("\nPulsa una tecla para continuar...");
                                Console.ReadKey();
                                break;
                            default:
                                Console.WriteLine("Opción no válida. Presione una tecla para continuar..");
                                Console.ReadKey();
                                break;
                        }

                    }
                    serverTask.Dispose();

                }
                catch(Exception ex)
                {
                    Console.WriteLine("Hubo un error, saliendo..");
                }
                

            } else {
                Console.WriteLine("No se recibió un código de autorización válido.");
            }

            // Espera a que el servidor termine su ejecución
           
        }

        static async Task StartServerAsync()
        {
            // Configurar el servidor web
            var builder = WebApplication.CreateBuilder();
            var app = builder.Build();

            // Ruta de callback donde Spotify redirige con el código de autorización
            app.MapGet("/callback", async context =>
            {
                var code = context.Request.Query["code"];
                if (!string.IsNullOrEmpty(code))
                {
                    Console.WriteLine($"Código recibido: {code}");
                }
                else
                {
                    Console.WriteLine("No se recibió el código de autorización.");
                }

                // Responder al navegador (opcional)
                await context.Response.WriteAsync("Puedes cerrar esta ventana.");
            });

            // Ejecutar el servidor en localhost:5000
            await app.RunAsync("http://localhost:5000");
        }

        static async Task<string> GetAccessToken(string code)
        {
            var client = new HttpClient();
            var content = new FormUrlEncodedContent(new[]
            {
            new KeyValuePair<string, string>("grant_type", "authorization_code"),
            new KeyValuePair<string, string>("code", code),
            new KeyValuePair<string, string>("redirect_uri", redirectUri),
            new KeyValuePair<string, string>("client_id", clientId),
            new KeyValuePair<string, string>("client_secret", clientSecret),
        });

            var response = await client.PostAsync("https://accounts.spotify.com/api/token", content);
            var responseContent = await response.Content.ReadAsStringAsync();

            // Extraer el access token de la respuesta
            var tokenResponse = JsonSerializer.Deserialize<Token>(responseContent);
            return tokenResponse.access_token;
        }

    }



}
