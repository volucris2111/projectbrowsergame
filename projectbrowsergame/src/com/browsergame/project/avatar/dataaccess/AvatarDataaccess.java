
package com.browsergame.project.avatar.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Random;

import com.browsergame.project.avatar.datatransfer.Avatar;
import com.browsergame.project.avatar.datatransfer.AvatarClass;
import com.browsergame.project.avatar.datatransfer.Master;
import com.browsergame.project.core.helper.DatabaseAccessHelper;
import com.browsergame.project.user.datatransfer.User;

public class AvatarDataaccess
{
	private static AvatarDataaccess INSTANCE = new AvatarDataaccess();
	
	public static AvatarDataaccess getInstance()
	{
		return AvatarDataaccess.INSTANCE;
	}
	
	private AvatarDataaccess()
	{
	}
	
	public void activateMaster(final int masterId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("UPDATE master SET available = 1 WHERE masterId ='");
		sqlStatementAsString.append(masterId);
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = DatabaseAccessHelper.getInstance().openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while activating master: " + sqlEx);
		}
	}
	
	public void addAvatarToGroup(final int masterId, final int leaderAvatarId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO groupmember (leaderAvatarId, masterId) VALUES (");
		sqlStatementAsString.append(leaderAvatarId);
		sqlStatementAsString.append(", '");
		sqlStatementAsString.append(masterId);
		sqlStatementAsString.append("');");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.execute(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while adding Groupmember: " + sqlEx);
		}
	}
	
	public String createAvatar(final User user, final Avatar avatar)
	{
		String error = "";
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO avatar (userId, name, inventoryId, type, male, avatarClassId, positionX, positionY, lifePointsCurrent, spiritPointsCurrent, staminaPointsCurrent) VALUES (");
		sqlStatementAsString.append(user.getUserId());
		sqlStatementAsString.append(", '");
		sqlStatementAsString.append(avatar.getName());
		sqlStatementAsString.append("', ");
		sqlStatementAsString.append(avatar.getInventoryId());
		sqlStatementAsString.append(", '");
		sqlStatementAsString.append(avatar.getType());
		sqlStatementAsString.append("', ");
		sqlStatementAsString.append(avatar.isMale() ? 1 : 0);
		sqlStatementAsString.append(", ");
		sqlStatementAsString.append(avatar.getAvatarClassId());
		sqlStatementAsString.append(", 0, 0, 10, 10, 10);");
		Statement sqlStatment;
		ResultSet result;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.execute(sqlStatementAsString.toString(),
					Statement.RETURN_GENERATED_KEYS);
			result = sqlStatment.getGeneratedKeys();
			result.next();
			avatar.setAvatarId(result.getInt(1));
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println(sqlEx);
			error = sqlEx.getMessage();
		}
		return error;
	}
	
	public void createMaster(final Master master)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("INSERT INTO master (name, costs, avatarClassId, currentLocationId, male, available) VALUES ('");
		sqlStatementAsString.append(master.getName());
		sqlStatementAsString.append("', '");
		sqlStatementAsString.append(master.getCosts());
		sqlStatementAsString.append("', ");
		sqlStatementAsString.append(master.getAvatarClassId());
		sqlStatementAsString.append(", '");
		sqlStatementAsString.append(master.getCurrentLocationId());
		sqlStatementAsString.append("', ");
		sqlStatementAsString.append(master.isMale() ? 1 : 0);
		sqlStatementAsString.append(", 1);");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.execute(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.out.println("Error while creating Master: " + sqlEx);
		}
	}
	
	public LinkedList<AvatarClass> getAllAvatarClassesAsLinkedList()
	{
		LinkedList<AvatarClass> allAvatarClasses = new LinkedList<AvatarClass>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM avatarclass;");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				AvatarClass avatarClass = new AvatarClass();
				avatarClass.setAvatarClassId(result.getInt("avatarClassId"));
				avatarClass.setName(result.getString("name"));
				avatarClass.setNameForJspFemale(result
						.getString("nameForJspFemale"));
				avatarClass.setNameForJspMale(result
						.getString("nameForJspMale"));
				allAvatarClasses.add(avatarClass);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading all avatarclasses: "
					+ sqlEx);
		}
		return allAvatarClasses;
	}
	
	public LinkedList<Master> getAllMasters()
	{
		LinkedList<Master> travelingMastersOfLocation = new LinkedList<Master>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM master;");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				Master master = new Master();
				fillMaster(result, master);
				travelingMastersOfLocation.add(master);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return travelingMastersOfLocation;
	}
	
	public LinkedList<Integer> getAllUnavailableMasterIds()
	{
		LinkedList<Integer> allUnavailableMasterIds = new LinkedList<Integer>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM master WHERE available == 0;");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				allUnavailableMasterIds.add(result.getInt("masterId"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err
					.println("Error while loading masterIds of all unavailable masters: "
							+ sqlEx);
		}
		return allUnavailableMasterIds;
	}
	
	public Avatar getAvatarById(final int avatarId)
	{
		Avatar avatar = new Avatar();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM avatar WHERE avatarId ='");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			if (result.next())
			{
				fillAvatar(avatar, result);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return avatar;
	}
	
	public AvatarClass getAvatarClassWithId(final int avatarClassId)
	{
		AvatarClass avatarClass = new AvatarClass();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM avatarclass where avatarClassId ='");
		sqlStatementAsString.append(avatarClassId);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			if (result.next())
			{
				avatarClass.setAvatarClassId(result.getInt("avatarClassId"));
				avatarClass.setName(result.getString("name"));
				avatarClass.setNameForJspFemale(result
						.getString("nameForJspFemale"));
				avatarClass.setNameForJspMale(result
						.getString("nameForJspMale"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading all avatarclasses: "
					+ sqlEx);
		}
		return avatarClass;
	}
	
	public LinkedList<Integer> getAvatarIdsOfGroupOfHeroIdAsLinkedList(
			final int avatarId)
	{
		LinkedList<Integer> allAvatarIdsOfGroup = new LinkedList<Integer>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM groupmember WHERE leaderAvatarId = '");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				allAvatarIdsOfGroup.add(result.getInt("masterId"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading all avatarIds of group: "
					+ sqlEx);
		}
		return allAvatarIdsOfGroup;
		
	}
	
	public Avatar getAvatarOfUserID(final int userId, final String type)
	{
		Avatar avatar = new Avatar();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM avatar WHERE userId ='");
		sqlStatementAsString.append(userId);
		sqlStatementAsString.append("' AND type ='");
		sqlStatementAsString.append(type);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			if (result.next())
			{
				fillAvatar(avatar, result);
				
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return avatar;
	}
	
	public int getLevelForAvatarExp(final int avatarExp)
	{
		int lvl = 0;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM avatarlevel where exp <= ");
		sqlStatementAsString.append(avatarExp);
		sqlStatementAsString.append(" ORDER BY level DESC LIMIT 1;");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				lvl = result.getInt("level");
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while getting level of avatarExp: "
					+ sqlEx);
		}
		return lvl;
	}
	
	public LinkedList<Avatar> getListOfAvatarsOnPosition(final int positionX,
			final int positionY)
	{
		LinkedList<Avatar> avatarsOnField = new LinkedList<Avatar>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM avatar WHERE positionX ='");
		sqlStatementAsString.append(positionX);
		sqlStatementAsString.append("' AND positionY ='");
		sqlStatementAsString.append(positionY);
		sqlStatementAsString.append("' AND type = 'hero';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				Avatar avatar = new Avatar();
				fillAvatarForMap(avatar, result);
				avatarsOnField.add(avatar);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return avatarsOnField;
	}
	
	public LinkedList<Master> getMastersOfLocationId(final int locationId)
	{
		LinkedList<Master> travelingMastersOfLocation = new LinkedList<Master>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM master WHERE currentLocationId ='");
		sqlStatementAsString.append(locationId);
		sqlStatementAsString.append("' and available = true;");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			while (result.next())
			{
				Master master = new Master();
				fillMaster(result, master);
				travelingMastersOfLocation.add(master);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return travelingMastersOfLocation;
	}
	
	public String getRandomFirstName(final boolean male)
	{
		String firstname = "";
		int nameId = 0;
		if (male)
		{
			nameId = new Random().nextInt(2681);
			nameId++;
		}
		else
		{
			nameId = new Random().nextInt(2481);
			nameId++;
		}
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM ");
		sqlStatementAsString.append(male ? "male" : "female");
		sqlStatementAsString.append("name WHERE nameId ='");
		sqlStatementAsString.append(nameId);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			if (result.next())
			{
				firstname = result.getString("name");
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return firstname;
	}
	
	public String getRandomLastName()
	{
		String lastname = "";
		int nameId = 0;
		nameId = new Random().nextInt(88799);
		nameId++;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM lastname WHERE lastnameId ='");
		sqlStatementAsString.append(nameId);
		sqlStatementAsString.append("';");
		Connection sqlConnection;
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			ResultSet result = sqlStatment.executeQuery(sqlStatementAsString
					.toString());
			if (result.next())
			{
				lastname = result.getString("lastname");
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
		return lastname;
	}
	
	public void moveAvatar(final Avatar avatar)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE avatar SET positionX ='");
		sqlStatementAsString.append(avatar.getPositionX());
		sqlStatementAsString.append("', positionY ='");
		sqlStatementAsString.append(avatar.getPositionY());
		sqlStatementAsString.append("' WHERE avatarId ='");
		sqlStatementAsString.append(avatar.getAvatarId());
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
	}
	
	public Connection openConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException
	{
		Connection sqlConnection;
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
		sqlConnection = DriverManager.getConnection(
				"jdbc:mysql://localhost/projectbrowsergame", "root", "root");
		return sqlConnection;
	}
	
	public void setAvatarInside(final Avatar avatar)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE avatar SET inside ='");
		sqlStatementAsString.append(avatar.isInside() ? 1 : 0);
		sqlStatementAsString.append("', insideAction ='");
		sqlStatementAsString.append(avatar.getInsideAction());
		sqlStatementAsString.append("' WHERE avatarId ='");
		sqlStatementAsString.append(avatar.getAvatarId());
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println(sqlEx);
		}
	}
	
	public void updateAvatarCombatPoints(final Avatar avatar)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE avatar SET lifePointsCurrent ='");
		sqlStatementAsString.append(avatar.getLifePointsCurrent());
		sqlStatementAsString.append("', spiritPointsCurrent ='");
		sqlStatementAsString.append(avatar.getSpiritPointsCurrent());
		sqlStatementAsString.append("', staminaPointsCurrent ='");
		sqlStatementAsString.append(avatar.getStaminaPointsCurrent());
		sqlStatementAsString.append("' WHERE avatarId ='");
		sqlStatementAsString.append(avatar.getAvatarId());
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while updating combatpoints of Avatar: "
					+ sqlEx);
		}
	}
	
	public void updateExpOfAvatarWithId(final int avatarId, final int exp)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE avatar SET exp ='");
		sqlStatementAsString.append(exp);
		sqlStatementAsString.append("' WHERE avatarId ='");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while updating Exp of Avatar: " + sqlEx);
		}
	}
	
	public void updateMaster(final Master master)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE master SET name ='");
		sqlStatementAsString.append(master.getName());
		sqlStatementAsString.append("', costs ='");
		sqlStatementAsString.append(master.getCosts());
		sqlStatementAsString.append("', male ='");
		sqlStatementAsString.append(master.isMale() ? 1 : 0);
		sqlStatementAsString.append("', avatarClassId ='");
		sqlStatementAsString.append(master.getAvatarClassId());
		sqlStatementAsString.append("', costs ='");
		sqlStatementAsString.append(master.getCosts());
		sqlStatementAsString.append("', currentLocationId ='");
		sqlStatementAsString.append(master.getCurrentLocationId());
		sqlStatementAsString.append("', available = '");
		sqlStatementAsString.append(master.isAvailable() ? 1 : 0);
		sqlStatementAsString.append("' WHERE masterId ='");
		sqlStatementAsString.append(master.getMasterId());
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while updating master: " + sqlEx);
		}
	}
	
	public void updateMasterLocationId(final Master master)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE master SET currentLocationId ='");
		sqlStatementAsString.append(master.getCurrentLocationId());
		sqlStatementAsString.append("' WHERE masterId ='");
		sqlStatementAsString.append(master.getMasterId());
		sqlStatementAsString.append("';");
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			sqlStatment = sqlConnection.createStatement();
			sqlStatment.executeUpdate(sqlStatementAsString.toString());
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while updating master locationId: "
					+ sqlEx);
		}
	}
	
	private void fillAvatar(final Avatar avatar, final ResultSet result)
			throws SQLException
	{
		avatar.setName(result.getString("name"));
		avatar.setUserId(result.getInt("userId"));
		avatar.setType(result.getString("type"));
		avatar.setAvatarId(result.getInt("avatarId"));
		avatar.setPositionX(result.getInt("positionX"));
		avatar.setPositionY(result.getInt("positionY"));
		avatar.setInventoryId(result.getInt("inventoryId"));
		avatar.setMale(result.getBoolean("male"));
		avatar.setAvatarClassId(result.getInt("avatarClassId"));
		avatar.setLifePointsCurrent(result.getInt("lifePointsCurrent"));
		avatar.setSpiritPointsCurrent(result.getInt("spiritPointsCurrent"));
		avatar.setStaminaPointsCurrent(result.getInt("staminaPointsCurrent"));
		avatar.setInside(result.getBoolean("inside"));
		avatar.setInsideAction(result.getString("insideAction"));
		avatar.setExp(result.getInt("exp"));
	}
	
	private void fillAvatarForMap(final Avatar avatar, final ResultSet result)
			throws SQLException
	{
		avatar.setName(result.getString("name"));
		avatar.setType(result.getString("type"));
		avatar.setAvatarId(result.getInt("avatarId"));
		avatar.setMale(result.getBoolean("male"));
		avatar.setAvatarClassId(result.getInt("avatarClassId"));
		avatar.setInside(result.getBoolean("inside"));
		avatar.setInsideAction(result.getString("insideAction"));
	}
	
	private void fillMaster(final ResultSet result, final Master master)
			throws SQLException
	{
		master.setName(result.getString("name"));
		master.setMasterId(result.getInt("masterId"));
		master.setAvatarClassId(result.getInt("AvatarClassId"));
		master.setCosts(result.getInt("costs"));
		master.setCurrentLocationId(result.getInt("currentLocationId"));
		master.setMale(result.getBoolean("male"));
		master.setAvailable(result.getBoolean("available"));
	}
}
