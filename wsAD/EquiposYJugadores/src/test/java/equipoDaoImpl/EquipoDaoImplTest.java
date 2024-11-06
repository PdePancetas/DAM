package equipoDaoImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import beans.Equipo;
import beans.Jugador;
import dao.EquipoDao;
import exception.EquipoExisteException;
import exception.JugadorExisteException;

public class EquipoDaoImplTest {

	private EquipoDao equipoDao;
	private File testFile;

	@BeforeEach
	public void setUp() throws IOException {
		testFile = new File("test_equipos.dat");
		equipoDao = new EquipoDaoImpl(testFile);
	}

	@AfterEach
	public void tearDown() {
		if (testFile.exists()) {
			testFile.delete();
		}
	}

	@Test
	public void testCreateTeams() throws IOException, ClassNotFoundException, EquipoExisteException {
		createTestTeams();

		Equipo teamA = equipoDao.listEquipo("TeamA");
		Equipo teamB = equipoDao.listEquipo("TeamB");

		assertNotNull(teamA, "TeamA should be created successfully");
		assertNotNull(teamB, "TeamB should be created successfully");
	}

	@Test
	public void testCreateTeamsAndAddPlayers() throws IOException, ClassNotFoundException, EquipoExisteException {
		createTestTeams();

		// Add players to the teams
		
		try {
			addPlayersToTeam("TeamA", "PlayerA1", "PlayerA2");
			addPlayersToTeam("TeamB", "PlayerB1", "PlayerB2");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JugadorExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Verify players in TeamA
		verifyTeamHasPlayers("TeamA", 2, "PlayerA1", "PlayerA2");

		// Verify players in TeamB
		verifyTeamHasPlayers("TeamB", 2, "PlayerB1", "PlayerB2");
	}

	@Test
	public void testRemovePlayers() throws IOException, ClassNotFoundException, EquipoExisteException {
		// Create teams and add players
		testCreateTeamsAndAddPlayers();

		// Remove players from the teams
		equipoDao.deleteJugador("TeamA", "PlayerA1");
		equipoDao.deleteJugador("TeamB", "PlayerB1");

		// Verify remaining players in TeamA
		verifyTeamHasPlayers("TeamA", 1, "PlayerA2");

		// Verify remaining players in TeamB
		verifyTeamHasPlayers("TeamB", 1, "PlayerB2");
	}

	// Helper method to create test teams
	private void createTestTeams() throws IOException, ClassNotFoundException, EquipoExisteException {
		equipoDao.createEquipo("TeamA");
		equipoDao.createEquipo("TeamB");
	}

	// Helper method to add multiple players to a team
	private void addPlayersToTeam(String teamName, String... playerNames)
			throws IOException, ClassNotFoundException, JugadorExisteException {
		for (String playerName : playerNames) {
			equipoDao.insJugador(teamName, new Jugador(playerName));
		}
	}

	// Helper method to verify the number of players and their presence in the team
	private void verifyTeamHasPlayers(String teamName, int expectedPlayerCount, String... playerNames)
			throws IOException, ClassNotFoundException {
		Equipo team = equipoDao.listEquipo(teamName);
		assertNotNull(team, teamName + " should exist");
		assertEquals(expectedPlayerCount, team.getJugadores().size(),
				teamName + " should have " + expectedPlayerCount + " players");

		for (String playerName : playerNames) {
			assertTrue(team.getJugadores().contains(new Jugador(playerName)),
					teamName + " should contain player " + playerName);
		}
	}
}
