
package com.browsergame.project.skill.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import com.browsergame.project.avatar.datatransfer.AvatarClass;
import com.browsergame.project.skill.datatransfer.Skill;

public class SkillDataaccess
{
	private static SkillDataaccess INSTANCE = new SkillDataaccess();
	
	public static SkillDataaccess getInstance()
	{
		return SkillDataaccess.INSTANCE;
	}
	
	private SkillDataaccess()
	{
	}
	
	public void createAllSkillsForAvatarId(final int avatarId)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		LinkedList<Integer> allSkillIds = getAllSkillIdAsList();
		Statement sqlStatment;
		try
		{
			sqlConnection = openConnection();
			for (int currentSkillId : allSkillIds)
			{
				sqlStatementAsString = new StringBuilder();
				sqlStatementAsString
						.append("INSERT INTO avatarskill (skillId, avatarId) VALUES ('");
				sqlStatementAsString.append(currentSkillId);
				sqlStatementAsString.append("', '");
				sqlStatementAsString.append(avatarId);
				sqlStatementAsString.append("'); ");
				sqlStatment = sqlConnection.createStatement();
				sqlStatment.execute(sqlStatementAsString.toString());
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while creating all skills for avatar: "
					+ sqlEx);
		}
	}
	
	public void fillSkillOfAvatarClass(final AvatarClass avatarClass)
	{
		if (avatarClass.getSkills() != null)
		{
			avatarClass.getSkills().clear();
		}
		else
		{
			avatarClass.setSkills(new HashMap<String, Skill>());
		}
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM avatarclassskill INNER JOIN skill ON avatarclassskill.skillId = skill.skillId where avatarClassId = ");
		sqlStatementAsString.append(avatarClass.getAvatarClassId());
		sqlStatementAsString.append(";");
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
				Skill skill = new Skill();
				fillSkill(result, skill);
				avatarClass.getSkills().put((result.getString("name")), skill);
				
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading skills of avatar class: "
					+ sqlEx);
		}
	}
	
	public LinkedList<Integer> getAllSkillIdAsList()
	{
		LinkedList<Integer> allSkillIds = new LinkedList<Integer>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM skill;");
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
				allSkillIds.add(result.getInt("skillId"));
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err.println("Error while loading all skill ids: " + sqlEx);
		}
		return allSkillIds;
	}
	
	public int getLevelForSkillExp(final int skillExp)
	{
		int lvl = 0;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("SELECT * FROM skilllevels where exp <= ");
		sqlStatementAsString.append(skillExp);
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
			System.err.println("Error while getting level of skillExp: "
					+ sqlEx);
		}
		return lvl;
	}
	
	public HashMap<String, Skill> getSkillsAsHashMapOfAvatarId(
			final int avatarId)
	{
		HashMap<String, Skill> skillsOfAvatar = new HashMap<String, Skill>();
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("SELECT * FROM avatarskill INNER JOIN skill ON avatarskill.skillId = skill.skillId WHERE avatarId ='");
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
				Skill skill = new Skill();
				fillSkill(result, skill);
				skillsOfAvatar.put((result.getString("name")), skill);
			}
			sqlConnection.close();
		}
		catch (Exception sqlEx)
		{
			System.err
					.println("Error while loading skills of Avatar as HashMap: "
							+ sqlEx);
		}
		return skillsOfAvatar;
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
	
	public void updateSkillSpecializationWithIdOfAvatarId(final int avatarId,
			final Skill skill)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString
				.append("UPDATE avatarskill SET specialization = '");
		sqlStatementAsString.append(skill.isSpecialization() ? 1 : 0);
		sqlStatementAsString.append("' WHERE avatarId = ");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append(" AND skillId = ");
		sqlStatementAsString.append(skill.getSkillId());
		sqlStatementAsString.append(";");
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
	
	public void updateSkillWithIdOfAvatarId(final int avatarId,
			final Skill skill)
	{
		Connection sqlConnection;
		StringBuilder sqlStatementAsString = new StringBuilder();
		sqlStatementAsString.append("UPDATE avatarskill SET exp = '");
		sqlStatementAsString.append(skill.getExp());
		sqlStatementAsString.append("' WHERE avatarId = ");
		sqlStatementAsString.append(avatarId);
		sqlStatementAsString.append(" AND skillId = ");
		sqlStatementAsString.append(skill.getSkillId());
		sqlStatementAsString.append(";");
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
	
	private void fillSkill(final ResultSet result, final Skill skill)
			throws SQLException
	{
		skill.setName(result.getString("name"));
		skill.setNameForJsp(result.getString("nameForJsp"));
		skill.setType(result.getString("type"));
		skill.setSkillId(result.getInt("skillId"));
		skill.setSpecialization(result.getBoolean("specialization"));
		skill.setExp(result.getInt("exp"));
	}
}
