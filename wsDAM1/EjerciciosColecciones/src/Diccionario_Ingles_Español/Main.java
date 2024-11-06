package Diccionario_Ingles_Español;

import java.util.HashMap;
import java.util.Map.Entry;

import utilidadesTeclado.Teclado;

import java.util.Set;

public class Main {

	public static void main(String[] args) {

		HashMap<String, String> diccionario = new HashMap<>();

		String palabras = "casa-house;gato-cat;perro-dog;libro-book;mesa-table;silla-chair;cama-bed;baño-bathroom;cocina-kitchen;mano-hand;pie-foot;boca-mouth;ojo-eye;nariz-nose;oído-ear;padre-father;madre-mother;hijo-son;hija-daughter;hermano-brother;hermana-sister;amigo-friend;amiga-girlfriend;novio-boyfriend;escuela-school;profesor-teacher;alumno-student;coche-car;autobús-bus;avión-airplane;barco-boat;tren-train;bicicleta-bicycle;ciudad-city;pueblo-town;país-country;montaña-mountain;río-river;mar-sea;cielo-sky;sol-sun;luna-moon;estrella-star;nube-cloud;viento-wind;lluvia-rain;nieve-snow;fuego-fire;tierra-earth;agua-water;aire-air;planta-plant;flor-flower;árbol-tree;animal-animal;perro-dog;gato-cat;caballo-horse;vaca-cow;cerdo-pig;gallina-hen;pollo-chicken;pato-duck;pez-fish;pájaro-bird;mariposa-butterfly;abeja-bee;araña-spider;mosca-fly;fruta-fruit;verdura-vegetable;carne-meat;pan-bread;leche-milk;queso-cheese;yogurt-yogurt;huevo-egg;café-coffee;té-tea;azúcar-sugar;sal-salt;pimienta-pepper;arroz-rice;pasta-pasta;pizza-pizza;hamburguesa-hamburger;sandwich-sandwich;ensalada-salad;sopa-soup;pastel-cake;galleta-cookie;helado-ice cream;chocolate-chocolate;caramelo-candy;jugo-juice;agua-water;vino-wine;cerveza-beer;licor-liquor;bebida-drink;rojo-red;verde-green;azul-blue;amarillo-yellow;negro-black;blanco-white;gris-gray;marrón-brown;rosa-pink;naranja-orange;púrpura-purple;dorado-gold;plateado-silver;brillante-bright;oscuro-dark;claro-light;pesado-heavy;ligero-light;grande-big;pequeño-small;largo-long;corto-short;alto-tall;bajo-short;ancho-wide;estrecho-narrow;gordo-fat;delgado-thin;caliente-hot;frío-cold;seco-dry;mojado-wet;bueno-good;malo-bad;nuevo-new;viejo-old;bonito-beautiful;feo-ugly;feliz-happy;triste-sad;enojado-angry;tranquilo-calm;emocionado-excited;aburrido-bored;cansado-tired;hambriento-hungry;sediento-thirsty;saludable-healthy;enfermo-sick;fuerte-strong;débil-weak;rápido-fast;lento-slow;fácil-easy;difícil-difficult;posible-possible;imposible-impossible;hoy-today;ayer-yesterday;mañana-tomorrow;ahora-now;temprano-early;tarde-late;noche-night;día-day;semana-week;mes-month;año-year;siglo-century;minuto-minute;hora-hour;segundo-second;tiempo-time;espacio-space;universo-universe;mundo-world;vida-life;muerte-death;amor-love;odio-hate;paz-peace;guerra-war;felicidad-happiness;tristeza-sadness;esperanza-hope;miedo-fear;sueño-dream;realidad-reality;imaginación-imagination;creatividad-creativity;inteligencia-intelligence;sabiduría-wisdom;conocimiento-knowledge;ignorancia-ignorance;verdad-truth;mentira-lie;justicia-justice;injusticia-injustice;libertad-freedom;opresión-oppression;igualdad-equality;desigualdad-inequality;democracia-democracy;dictadura-dictatorship;capitalismo-capitalism;comunismo-communism;socialismo-socialism;religión-religion;espiritualidad-spirituality;dios-god;diosa-goddess;fe-faith;ateísmo-atheism;ciencia-science;tecnología-technology;arte-art;música-music;literatura-literature;poesía-poetry;filosofía-philosophy";
		String[] palabra = palabras.split(";");

		for (int i = 0; i < palabra.length; i++)
			diccionario.put(palabra[i].split("-")[0], palabra[i].split("-")[1]);
		
		boolean acabado=false;
		while(acabado!=true) {
			
			System.out.println("1. Español-Ingles \n2. Ingles-Español\n3. Salir");
			System.out.print("-> ");
			int op = Teclado.leerEntero();
			switch (op) {
				
				case 1: 
					System.out.print("Palabra en español: ");
					String palabrita = Teclado.leerCadena();
		
					Set<Entry<String, String>> diccio1 = diccionario.entrySet();
					for (Entry<String, String> word : diccio1)
						if (word.getKey().equals(palabrita))
							System.out.println("Traducción: " + word.getValue() + "\n");
					
					break;
				case 2: 
					System.out.print("Palabra en ingles: ");
					palabrita = Teclado.leerCadena();
		
					diccio1 = diccionario.entrySet();
					for (Entry<String, String> word : diccio1)
						if (word.getValue().equals(palabrita))
							System.out.println("Traducción: " + word.getKey() + "\n");
					
					break;
				case 3: 
					acabado=true;
					System.out.println("Programa finalizado.");	
					break;
				default: System.out.println("Opcion no valida. \n");
			}
			
		}
	}

}
