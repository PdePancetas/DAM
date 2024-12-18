using System.Net.Http.Headers;
using Newtonsoft.Json;
using ApiSpotify.Models;
using SpotifyAPI.Web;
using SpotifyAPI.Web.Auth;
using System.Linq.Expressions;

namespace ApiSpotify.Helpers
{
    public class Logic
    {

        public static async Task GetMyInfoAsync(SpotifyClient spotify)
        {    
            // Leer la respuesta
            PrivateUser userData = await spotify.UserProfile.Current();
            
            Console.WriteLine("\nDatos del usuario: ");
            Console.WriteLine(" País: "+userData.Country);
            Console.WriteLine(" Nombre de Perfil: " + userData.DisplayName);
            Console.WriteLine(" Email: "+userData.Email);
            Console.WriteLine(" Total de seguidores: " + userData.Followers.Total);
            Console.WriteLine(" URL de "+ userData.DisplayName+" : " + userData.Href);
            Console.WriteLine(" Id: " + userData.Id);
            Console.WriteLine(" Producto: "+userData.Product);
            Console.WriteLine(" Tipo de cuenta: " + userData.Type);
            Console.WriteLine(" Spotify Uri: " + userData.Uri);
            Console.WriteLine("\nPulsa una tecla para continuar..");

        }
        public static async Task GetPlaylistsAsync(SpotifyClient spotify)
        {
            Paging<FullPlaylist> userData = await spotify.Playlists.CurrentUsers();
            
            foreach(var item in userData.Items)
            {
                if (item != null)
                    Console.WriteLine($"\n{item.Owner.DisplayName} - {item.Name} ({item.Tracks.Total} canciones)" +
                                  $"\n{item.Description}");
            }

            Console.WriteLine("\nPulsa una tecla para continuar..");
        }
         public static async Task SearchInfoAsync(SpotifyClient spotify, String keyword)
         {
            SearchResponse resultado = await spotify.Search.Item(new SearchRequest(SearchRequest.Types.All, keyword));
            if (resultado.Albums != null)
            {
                Console.WriteLine("\nÁlbumes: ");
                foreach (var item in resultado.Albums.Items)
                {
                    if (item != null)
                        Console.WriteLine($"{string.Join(", ", item.Artists.Select(a => a.Name))} - {item.Name} ({item.TotalTracks} canciones) - {item.ExternalUrls["spotify"]}");
                }
            }

            if (resultado.Artists != null)
            {
                Console.WriteLine("\nArtistas: ");
                foreach (var item in resultado.Artists.Items)
                {
                    if(item!=null)
                        Console.WriteLine($"{item.Name} ({item.Followers.Total} seguidores) - {item.ExternalUrls["spotify"]}");
                }
            }

            if (resultado.Playlists != null)
            {
                Console.WriteLine("\nPlaylists: ");
                foreach (var item in resultado.Playlists.Items)
                {
                    if (item != null)
                        Console.WriteLine($"{item.Owner.DisplayName} - {item.Name} ({item.Tracks.Total} canciones) - {item.Description} \n{item.ExternalUrls["spotify"]}");
                }
            }

            if (resultado.Tracks != null)
            {
                Console.WriteLine("\nCanciones: ");
                foreach (var item in resultado.Tracks.Items)
                {
                    Console.WriteLine($"{string.Join(", ", item.Artists.Select(a => a.Name))} - {item.Name} - {item.Popularity} - {item.ExternalUrls["spotify"]}");
                }
            }

        }
        public static async Task detailSearchAsync(SpotifyClient spotify, String keyword)
        {
            var searchResponse = await spotify.Search.Item(new SearchRequest(SearchRequest.Types.All, keyword));

            
            if (searchResponse.Albums.Items.Count > 0)
            {
                var album = searchResponse.Albums.Items[0]; 

                
                Console.WriteLine($"Álbum encontrado: {album.Name}");
                Console.WriteLine($"Artista: {string.Join(", ", album.Artists.Select(a => a.Name))}");
                Console.WriteLine($"URL de Spotify: {album.ExternalUrls["spotify"]}");
            }
            else
            {
                Console.WriteLine("No se encontró ningún álbum con ese nombre.");
            }

            searchResponse = await spotify.Search.Item(new SearchRequest(SearchRequest.Types.All, keyword));

            
            if (searchResponse.Artists.Items.Count > 0)
            {
                var artist = searchResponse.Artists.Items[0];

                
                Console.WriteLine($"\n\nArtista encontrado: {artist.Name}");
                Console.WriteLine($"Seguidores: {artist.Followers.Total}");
                Console.WriteLine($"Géneros: {string.Join(", ", artist.Genres)}");
                Console.WriteLine($"URL de Spotify: {artist.ExternalUrls["spotify"]}");
            }
            else
            {
                Console.WriteLine("No se encontró ningún artista con ese nombre.");
            }

            searchResponse = await spotify.Search.Item(new SearchRequest(SearchRequest.Types.Playlist, keyword));

            
            if (searchResponse.Playlists.Items.Count > 0)
            {
                var playlist = searchResponse.Playlists.Items[0];

                if (playlist != null)
                {
                    Console.WriteLine($"\n\nPlaylist encontrada: {playlist.Name}");
                    Console.WriteLine($"Descripción: {playlist.Description}");
                    Console.WriteLine($"Artista: {playlist.Owner.DisplayName}");
                    Console.WriteLine($"Número de canciones: {playlist.Tracks.Total}");
                    Console.WriteLine($"URL de Spotify: {playlist.ExternalUrls["spotify"]}");
                }
            }
            else
            {
                Console.WriteLine("No se encontró ninguna playlist con ese nombre.");
            }

        }

       

    }
}
